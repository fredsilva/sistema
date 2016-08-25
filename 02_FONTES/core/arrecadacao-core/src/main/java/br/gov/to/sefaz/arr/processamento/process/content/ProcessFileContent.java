package br.gov.to.sefaz.arr.processamento.process.content;

import br.gov.to.sefaz.arr.processamento.domain.FileContent;

/**
 * Interface que define como será o processamento do conteúdo do arquivo conforme a regra de negócio do
 * sistema.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 11:53:00
 */
public interface ProcessFileContent {

    /**
     * Realiza o processamento do {@link br.gov.to.sefaz.arr.processamento.domain.FileContent} conforme as regras de
     * negócio definidas para cada tipo de arquivo.
     *
     * @param fileContent conteúdo do arquivo a ser processado
     */
    void process(FileContent fileContent);
}
