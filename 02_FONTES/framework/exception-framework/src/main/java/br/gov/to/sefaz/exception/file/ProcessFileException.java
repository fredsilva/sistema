package br.gov.to.sefaz.exception.file;

/**
 * Exceção lançada para erros de processamento de arquivos.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 15:09:00
 */
public class ProcessFileException extends RuntimeException {
    private static final long serialVersionUID = 2432031191929400654L;

    private final Integer codigoRejeicao;

    public ProcessFileException(Integer codigoRejeicao) {
        this.codigoRejeicao = codigoRejeicao;
    }

    public Integer getCodigoRejeicao() {
        return codigoRejeicao;
    }

}
