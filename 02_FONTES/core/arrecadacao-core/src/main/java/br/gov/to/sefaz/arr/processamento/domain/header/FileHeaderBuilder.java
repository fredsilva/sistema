package br.gov.to.sefaz.arr.processamento.domain.header;

/**
 * Builder para {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader},
 * referentes a um tipo de arquivo a ser processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 14:11:00
 */
public interface FileHeaderBuilder {
    /**
     * Define o HEADER para a construção do {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader}.
     *
     * @param header do arquivo a ser processado
     * @return o próprio builder para dar sequência a sua construção
     */
    FileHeaderBuilder withLineHeader(String header);

    /**
     * Neste método deve conter todos os parâmetros
     * para construção do {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader}.
     *
     * @return o próprio builder para dar sequência a sua construção
     */
    FileHeaderBuilder withAllParameters();

    /**
     * Constroi o {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader} com os parâmetros de construção
     * obtidos através do {@link #withAllParameters()}.
     *
     * @return um pojo com os valores do HEADER de um arquivo a ser processado
     */
    FileHeader build();
}
