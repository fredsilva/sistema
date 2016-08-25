package br.gov.to.sefaz.arr.processamento.domain.detalhe;

/**
 * Interface que representa o detalhe de arquivos de arquivos.
 * Os demais atributos deverão estar presentes em sua respectiva implementação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/06/2016 17:55:00
 */
public interface FileDetalhe {

    /**
     * Retorna o Número Sequencial que identifica o arquivo.
     *
     * @return número sequencial do detalhe do arquivo
     */
    String getNumeroSequencial();

    /**
     * Retorna o Número de Autenticação do arquivo.
     *
     * @return número de autenticação do detalhe do arquivo.
     */
    String getNumeroAutenticacao();

    /**
     * Retorna o conteúdo da linha do detalhe do arquivo.
     *
     * @return conteúdo da linha
     */
    String getConteudoLinha();
}
