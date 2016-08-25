package br.gov.to.sefaz.arr.processamento.domain.detalhe;

/**
 * Builder para {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalhe},
 * referentes a um tipo de arquivo a ser processado.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 14:11:00
 */
public interface FileDetalheBuilder {
    /**
     * Define o HEADER para a construção do {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader}.
     *
     * @param header do arquivo a ser processado
     * @return o próprio builder para dar sequência a sua construção
     */
    FileDetalheBuilder withLine(String header);

    /**
     * Neste método deve conter todos os parâmetros
     * para construção do {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader}.
     *
     * @return o próprio builder para dar sequência a sua construção
     */
    FileDetalheBuilder withAllParameters();

    /**
     * Constroi o {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.FileDetalhe} com os parâmetros de
     * construção obtidos através do {@link #withAllParameters()}.
     *
     * @return um POJO com os valores de Detalhe de um arquivo a ser processado
     */
    FileDetalhe build();
}
