package br.gov.to.sefaz.arr.processamento.type;

import br.gov.to.sefaz.arr.processamento.domain.FileContent;
import br.gov.to.sefaz.arr.processamento.domain.header.FileHeaderBuilder;
import br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidatorBuilder;

/**
 * Define o que um processador de arquivo deve possuir para cada tipo,
 * definido nas regras de negócio do sistema.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 15:56:00
 */
public interface ProcessFileType {

    /**
     * Retorna o {@link br.gov.to.sefaz.arr.processamento.validation.validator.header.HeaderValidatorBuilder}
     * contendo as validações para o HEADER do tipo de arquivo.
     *
     * @return o builder que contém todas as regras de validação referentes a um tipo de arquivo a ser processado.
     */
    HeaderValidatorBuilder getHeaderValidatorBuilder();

    /**
     * Retorna o {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeaderBuilder} que contém os atributos
     * necessários para criar um {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader} que contém as
     * informações do HEADER do arquivo a ser processado.
     *
     * @return builder para criação do {@link br.gov.to.sefaz.arr.processamento.domain.header.FileHeader}.
     */
    FileHeaderBuilder getFileHeaderBuilder();

    /**
     * Realiza o processamento das linhas de conteúdo do arquivo conforme as regras de negócio para cada tipo de
     * arquivo.
     *
     * @param fileContent {@link br.gov.to.sefaz.arr.processamento.domain.FileContent} contém todas as linhas do
     *                    arquivo e com isto realiza as operações de processamento conforme as regras de negócio
     *                    de cada tipo de arquivo.
     */
    void processFileContent(FileContent fileContent);
}
