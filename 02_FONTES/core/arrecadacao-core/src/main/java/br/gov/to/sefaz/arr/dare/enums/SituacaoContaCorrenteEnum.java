package br.gov.to.sefaz.arr.dare.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Situação para um {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 11/10/2016 14:25:00
 */
public enum SituacaoContaCorrenteEnum implements EnumCodeLabel<Integer> {
    DEBITO(1, "DEBITO"),
    CREDITO(2, "CREDITO");

    private Integer code;
    private String label;

    SituacaoContaCorrenteEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Converte inteiro em {@link SituacaoContaCorrenteEnum} baseado no seu code.
     * Se for passado inteiro que não existe no enum,
     * é disparada {@link IllegalArgumentException}.
     *
     * @param code valor do enum
     * @return {@link SituacaoContaCorrenteEnum}
     */
    public static SituacaoContaCorrenteEnum getValue(Integer code) {
        return Stream.of(values())
                .filter(e -> e.code.equals(code))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Código não existe no SituacaoContaCorrenteEnum: codigo="
                                + code));
    }
}
