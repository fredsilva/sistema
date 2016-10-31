package br.gov.to.sefaz.util.pdf;

import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextUserAgent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Implementação custom de um {@link ITextUserAgent}, para resolução de URI. Levando em consideração
 * um diretório common de resources independente de modulo para colocar arquivos que podem ser utilizados em
 * qualquer relatorio (Ex: logo de tocantins). Suporta também a passagem de resources em memória, des de que a URI do
 * resource sejá equvalente a uma chave contida em {@link #params}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 12/08/2016 11:35:00
 */
public class SatPdfRenderUserAgent extends ITextUserAgent {

    private static final String FILE_PROTOCOL = "file:";
    private static final String LAST_SLASH_PATTERN = "[^/]+$";
    private final Map<String, Object> params;

    public SatPdfRenderUserAgent(ITextOutputDevice outputDevice, Map<String, Object> params) {
        super(outputDevice);
        this.params = params;
    }

    @Override
    protected InputStream resolveAndOpenStream(String uri) {
        String originalUri = uri.replace(getBaseURL().replaceAll(LAST_SLASH_PATTERN, EMPTY), EMPTY);

        // se tiver o parametro, tenta gerar o inputStream
        if (params.containsKey(originalUri)) {
            Object file = params.get(originalUri);

            if (file instanceof byte[]) {
                return new ByteArrayInputStream((byte[]) file);
            } else if (file instanceof InputStream) {
                return (InputStream) file;
            }
        }

        // se não tiver o parametro ou não conseguir gerar o inputStream, tenta acessar a uri
        InputStream inputStream = super.resolveAndOpenStream(uri);
        if (inputStream == null) {
            return SatPdfRenderUserAgent.class.getResourceAsStream("/" + uri.substring(FILE_PROTOCOL.length()));
        }

        return inputStream;
    }
}
