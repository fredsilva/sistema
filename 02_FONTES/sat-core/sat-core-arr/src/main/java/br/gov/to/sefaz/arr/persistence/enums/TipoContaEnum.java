package br.gov.to.sefaz.arr.persistence.enums;

import java.util.stream.Stream;

/**
 * Enum de Situacao.
 *
 * @author roger.gouveia@ntconsult.com.br
 */
public enum TipoContaEnum {

    SINTETICA(1, "Sintética"), ANALITICA(2, "Analítica");

    private Integer code;
    private String label;

    private TipoContaEnum(Integer code, String label) {
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
     * Converte inteiro em {@link TipoContaEnum} baseado no seu code. Se for
     * passado inteiro que não existe no enum, é disparada
     * {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoContaEnum}
     */
    public static TipoContaEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoEnum: codigo=" + code));
    }

}
