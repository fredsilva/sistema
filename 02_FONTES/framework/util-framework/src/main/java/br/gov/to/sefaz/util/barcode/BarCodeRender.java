package br.gov.to.sefaz.util.barcode;

import br.gov.to.sefaz.exception.UnhandledException;
import org.krysalis.barcode4j.impl.int2of5.Interleaved2Of5Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Classe utilitaria para a geração de uma imagem de um codigo de barras.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 11/08/2016 18:14:00
 */
public class BarCodeRender {

    private static final int DPI = 96;

    /**
     * Gera uma imagem png (image/x-png) de um código de barras, onde cada barra tem 0.5mm de largura e 25mm de altura
     * em uma resolução de {@value DPI} DPI, se precisar do tamanho  em px é recomendavel expandir ou reduzir a imagem
     * depois de gerada visto que dificilmente a imagem vai ser deformada.
     *
     * @param code código de origem do código de barras
     * @return os bytes de uma imagem png com o codigo de barras
     */
    public static byte[] createInterleaved2Of5(String code) {
        return createInterleaved2Of5(code, 0.5, 25);
    }

    /**
     * Gera uma imagem png (image/x-png) de um código de barras com a altura e largura de cada barra é passada por
     * parametro em mm (milimetros) em uma resolução de {@value DPI} DPI.
     *
     * @param code       código de origem do código de barras
     * @param widthInMm  largura de cada barra do código em mm ({@value DPI} DPI)
     * @param heightInMm altura das barras em mm ({@value DPI} DPI)
     * @return os bytes de uma imagem png com o codigo de barras
     */
    public static byte[] createInterleaved2Of5(String code, double widthInMm, double heightInMm) {
        //Open output file
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", DPI, BufferedImage.TYPE_BYTE_BINARY, false, 0);

            //Create the barcode bean
            Interleaved2Of5Bean bean = new Interleaved2Of5Bean();

            //Configure the barcode generator
            bean.setModuleWidth(widthInMm); //makes the narrow bar
            bean.setBarHeight(heightInMm);
            bean.doQuietZone(false);
            bean.setFontSize(0);

            //Generate the barcode
            bean.generateBarcode(canvas, code);

            //Signal end of generation
            canvas.finish();

            return out.toByteArray();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }
}
