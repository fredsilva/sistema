package br.gov.to.sefaz.arr.processamento;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;

/**
 * Serviço de processamento (leitura, converção, validação, persistência) de um arquivo.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 09:28:00
 */
public interface ProcessArquivo {
    /**
     * Processa (leitura, converção, validação, persistência) um arquivo.
     *
     * @param file     {@link FileInputStream} arquivo lido a ser processado
     * @param fileName nome do arquivo
     * @throws IOException    Lança exceção de entrada/saída na leitura do arquivo
     * @throws ParseException Lança exceção de formatação ao converter os dados
     */
    void processFile(FileInputStream file, String fileName) throws IOException, ParseException;

}
