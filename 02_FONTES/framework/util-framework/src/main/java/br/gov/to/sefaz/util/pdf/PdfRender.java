package br.gov.to.sefaz.util.pdf;

import br.gov.to.sefaz.exception.UnhandledException;
import br.gov.to.sefaz.util.pdf.handler.TextExpressionHandler;
import br.gov.to.sefaz.util.reflection.ReflectionUtils;
import com.lowagie.text.DocumentException;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextOutputDevice;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Cria um PDF a partir de um arquivo HTML e CSS.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/07/2016 18:48:00
 */
public class PdfRender {

    /**
     * Cria um PDF e retorna um array de bytes.
     *
     * @param url    url para localização do arquivo
     * @param params parametros que serão substituidos no html nas variaveis (${nomeDaVariavel})
     * @return os bytes do relatorio do pdf
     */
    public static byte[] createBytes(URL url, Object params) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            createOutputStream(url, os, params);
            return os.toByteArray();
        } catch (IOException | DocumentException e) {
            throw new UnhandledException(e);
        }
    }

    /**
     * Cria um PDF e salva no caminho especificado.
     *
     * @param url        url para localização do arquivo
     * @param outputFile caminho para arquivo PDF gerado
     * @param params     parametros que serão substituidos no html nas variaveis (${nomeDaVariavel})
     */
    public static void createFile(URL url, Path outputFile, Object params) {
        try (OutputStream os = Files.newOutputStream(outputFile)) {
            createOutputStream(url, os, params);
        } catch (IOException | DocumentException e) {
            throw new UnhandledException(e);
        }
    }

    /**
     * Cria um PDF e armazena no {@link OutputStream} passado.
     *
     * @param url          url para localização do arquivo
     * @param outputStream que os bytes do array serão armazenados
     * @param params       parametros que serão substituidos no html nas variaveis (${nomeDaVariavel})
     */
    public static void createOutputStream(URL url, OutputStream outputStream, Object params)
            throws IOException, DocumentException {
        Map<String, Object> paramsMap = ReflectionUtils.objToMap(params);

        String report = buildReportDocument(url, paramsMap);
        Document doc = XMLResource.load(new ByteArrayInputStream(report.getBytes(UTF_8.name()))).getDocument();

        ITextRenderer renderer = createRenderer(paramsMap);

        renderer.setDocument(doc, url.toString());
        renderer.layout();
        renderer.createPDF(outputStream);
    }

    private static ITextRenderer createRenderer(Map<String, Object> params) {
        // uma exata copia das constantes (privadas) DEFAULT_DOTS_PER_POINT e DEFAULT_DOTS_PER_PIXEL
        // da classe ITextRenderer, não uso elas pois são privadas.
        float dotsPerPoint = 20f * 4f / 3f;
        int dotsPerPixel = 20;
        ITextOutputDevice outputDevice = new ITextOutputDevice(dotsPerPoint);
        SatPdfRenderUserAgent userAgent = new SatPdfRenderUserAgent(outputDevice, params);

        return new ITextRenderer(dotsPerPoint, dotsPerPixel, outputDevice, userAgent);
    }

    private static String buildReportDocument(URL url, Map<String, Object> params) throws IOException {
        String report = new Scanner(url.openStream(), UTF_8.name()).useDelimiter("\\A").next();

        TextExpressionHandler textExpressionHandler = new TextExpressionHandler();
        report = textExpressionHandler.handleText(report, params);

        return report;
    }
}