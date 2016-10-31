package br.gov.to.sefaz.util.file;

import org.springframework.stereotype.Component;

/**
 * Utilitário para o tratamento de Strings para utilizar em leitura de arquivos.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 09:32:00
 */
@Component
public class FileLineExtractor {

    /**
     * Realiza a extração da {@link java.lang.String} conformeuma substring definida por um início e fim, realizando
     * uma operação de {@link String#trim()} para não conter espaçoes no valor.
     *
     * @param value String a ser extraída um valor
     * @param start início do valor a ser extraído
     * @param end   fim do valor a ser extraído
     * @return valor desejado extraído da {@link java.lang.String} passsada como valor.
     */
    public String getValueFromString(String value, int start, int end) {
        return value.substring(start, end).trim();
    }
}
