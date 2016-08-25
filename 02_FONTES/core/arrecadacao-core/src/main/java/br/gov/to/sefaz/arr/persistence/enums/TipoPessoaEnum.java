package br.gov.to.sefaz.arr.persistence.enums;

import java.util.stream.Stream;

/**
 * Enum que representa os valores possíveis para os Tipos de Pessoa para Arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 17:54:00
 */
public enum TipoPessoaEnum {

    INSCRICAO(1), CNPJ(2), CPF(3), RENAVAN(4);

    private final Integer code;

    TipoPessoaEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * Converte inteiro em {@link TipoPessoaEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoPessoaEnum}
     */
    public static TipoPessoaEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoPessoaEnum: codigo="
                        + code));
    }
}
