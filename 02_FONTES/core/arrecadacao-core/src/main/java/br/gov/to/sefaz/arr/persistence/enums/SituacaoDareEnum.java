package br.gov.to.sefaz.arr.persistence.enums;

/**
 * Enum que representa os valores possíveis para a Situação do Dare.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 15:52:00
 */
public enum SituacaoDareEnum {
    OK(0), ERRO(1);

    private final Integer code;

    SituacaoDareEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
