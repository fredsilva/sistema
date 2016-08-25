package br.gov.to.sefaz.util.report;

import br.gov.to.sefaz.exception.UnexpectedErrorException;
import br.gov.to.sefaz.util.formatter.FormatterUtil;
import com.lowagie.text.DocumentException;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static org.apache.commons.lang3.text.StrSubstitutor.replace;

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
     * @param url url para localização do arquivo
     * @param params parametros que serão substituidos no html nas variaveis (${nomeDaVariavel})
     * @return os bytes do relatorio do pdf
     */
    public static byte[] createBytes(URL url, Map<String, Object> params) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            createOutputStream(url, os, params);
            return os.toByteArray();
        } catch (IOException | DocumentException e) {
            throw new UnexpectedErrorException(e);
        }
    }

    /**
     * Cria um PDF e salva no caminho especificado.
     *
     * @param url url para localização do arquivo
     * @param outputFile caminho para arquivo PDF gerado
     * @param params parametros que serão substituidos no html nas variaveis (${nomeDaVariavel})
     */
    public static void createFile(URL url, Path outputFile, Map<String, Object> params) {
        try (OutputStream os = Files.newOutputStream(outputFile)) {
            createOutputStream(url, os, params);
        } catch (IOException | DocumentException e) {
            throw new UnexpectedErrorException(e);
        }
    }

    /**
     * Cria um PDF e armazena no {@link OutputStream} passado.
     *
     * @param url url para localização do arquivo
     * @param outputStream que os bytes do array serão armazenados
     * @param params parametros que serão substituidos no html nas variaveis (${nomeDaVariavel})
     */
    public static void createOutputStream(URL url, OutputStream outputStream,  Map<String, Object> params)
            throws IOException, DocumentException {
        String report = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
        handlerParams(params);
        report = replace(report, params);
        Document doc = XMLResource.load(new ByteArrayInputStream(report.getBytes("UTF-8"))).getDocument();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(doc, url.toString());
        renderer.layout();
        renderer.createPDF(outputStream);
    }

    private static void handlerParams(Map<String, Object> params) {
        for (Map.Entry<String, Object> obj : params.entrySet()) {
            if (Objects.isNull(obj.getValue())) {
                obj.setValue("");
            } else if (obj.getValue() instanceof LocalDate) {
                obj.setValue(FormatterUtil.formatDate((LocalDate) obj.getValue()));
            } else if (obj.getValue() instanceof LocalDateTime) {
                obj.setValue(FormatterUtil.formatDateTime((LocalDateTime) obj.getValue()));
            } else if (obj.getValue() instanceof Number) {
                obj.setValue(FormatterUtil.formatNumber((Number) obj.getValue()));
            }
        }
    }
}