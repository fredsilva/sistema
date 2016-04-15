package br.gov.to.sefaz.arr.persistence.enums;

import java.util.stream.Stream;

/**
 * Enum de Situacao.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
public enum RateioEnum {

    SIM(1, "SIM"), NAO(2, "NÃO");

    private Integer code;
    private String label;

    private RateioEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public Integer getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Converte inteiro em {@link RateioEnum} baseado no seu code. Se for passado
     * inteiro que não existe no enum, é disparada
     * {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link RateioEnum}
     */
    public static RateioEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoEnum: codigo=" + code));
    }

}
