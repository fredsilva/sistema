package br.gov.to.sefaz.seg.business.mail.builder;

import br.gov.to.sefaz.seg.business.mail.domain.Anexo;
import br.gov.to.sefaz.seg.business.mail.domain.CorreioEletronico;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Builder para facilitar a criação de um {@link CorreioEletronico}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 03/06/2016 14:28:00
 */
public class CorreioEletronicoBuilder {

    private final List<CorreioContribuinte> correiosContribuinte;
    private final CompressAnexo compressAnexo;

    private boolean isHtml;

    public CorreioEletronicoBuilder() {
        this.correiosContribuinte = new ArrayList<>();
        this.compressAnexo = new CompressAnexo();
    }

    /**
     * Adiciona usuários a lista de destinatarios (to) do e-mail.
     *
     * @param usuarios destinatarios do e-mail
     * @return instancia do builder que esta sendo manipulada
     */
    public CorreioEletronicoBuilder mailTo(UsuarioSistema... usuarios) {
        for (UsuarioSistema usuario : usuarios) {
            CorreioContribuinte correioContribuinte = new CorreioContribuinte();
            correioContribuinte.setCpfDestinatario(usuario.getCpfUsuario());
            correioContribuinte.setCorreioEletronico(usuario.getCorreioEletronico());
            correiosContribuinte.add(correioContribuinte);
        }

        return this;
    }

    /**
     * Atribui um assunto (subject) ao e-mail.
     *
     * @param subject assunto do e-mail
     * @return instancia do builder que esta sendo manipulada
     */
    public CorreioEletronicoBuilder subject(String subject) {
        for (CorreioContribuinte correioContribuinte : correiosContribuinte) {
            correioContribuinte.setAssunto(subject);
        }

        return this;
    }

    /**
     * Atribui o corpo da mensagem do email (body or message), e informa se a mensagem é HTML, caso contrario será
     * considerada como texto plano sem estilo ou formatação pré definida (text/plain).
     *
     * @param body corpo da mensagem
     * @param isHtml se é html ou texto plano
     * @return instancia do builder que esta sendo manipulada
     */
    public CorreioEletronicoBuilder body(String body, boolean isHtml) {
        for (CorreioContribuinte correioContribuinte : correiosContribuinte) {
            correioContribuinte.setConteudo(body);
        }
        this.isHtml = isHtml;

        return this;
    }

    /**
     * Atribui anéxos que serão comprimidos (.zip) e anexados ao e-mail.
     *
     * @param anexos anéxos do e-mail
     * @return instancia do builder que esta sendo manipulada
     * @throws IOException caos ocorra algum erro de I/O ao gerar .zip com os anéxos
     */
    public CorreioEletronicoBuilder attachments(Anexo... anexos) throws IOException {
        List<Anexo> anexoList = Arrays.asList(anexos);

        if (!anexoList.isEmpty()) {
            byte[] zipBytes = compressAnexo.zipBytes(anexoList);
            for (CorreioContribuinte contribuinte : correiosContribuinte) {
                contribuinte.setAnexo(zipBytes);
            }
        }

        return this;
    }

    /**
     * Cria o e-mail cofigurado e pronto para ser enviado, de acordo com as configurações feitas durante neste
     * builder até a chamada deste método.
     *
     * @return e-mail pronto para ser enviado
     */
    public CorreioEletronico build() {
        LocalDateTime dataEnvio = LocalDateTime.now();
        for (CorreioContribuinte correioContribuinte : correiosContribuinte) {
            correioContribuinte.setDataEnvio(dataEnvio);
        }

        return new CorreioEletronico(correiosContribuinte, isHtml);
    }
}
