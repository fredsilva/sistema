package br.gov.to.sefaz.util.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe de conversão de xml em um Objeto.
 *
 * @param <T> Tipo do objeto, em que será convertido o xml.
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 05/07/2016 16:35:00
 */
@Component
public class XmlToObjectConverter<T> {

    /**
     * Classe de conversão de {@link InputStream} xml em um Objeto.
     *
     * @param xml  xml a ser convertido.
     * @param claz Tipo do objeto, em que será convertido o xml.
     */
    public T convert(InputStream xml, Class<T> claz) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        return xmlMapper.readValue(xml, claz);
    }

    /**
     * Classe de conversão de {@link String} xml em um Objeto.
     *
     * @param xml  xml a ser convertido.
     * @param claz Tipo do objeto, em que será convertido o xml.
     */
    public T convert(String xml, Class<T> claz) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        return xmlMapper.readValue(xml, claz);
    }

    /**
     * Classe de conversão de {@link File} xml em um Objeto.
     *
     * @param xml  xml a ser convertido.
     * @param claz Tipo do objeto, em que será convertido o xml.
     */
    public T convert(File xml, Class<T> claz) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        return xmlMapper.readValue(xml, claz);
    }

    /**
     * Classe de conversão de xml em um Objeto.
     *
     * @param xml  xml a ser convertido.
     * @param claz Tipo do objeto, em que será convertido o xml.
     */
    public T convert(byte[] xml, Class<T> claz) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();

        return xmlMapper.readValue(xml, claz);
    }

}
