package br.gov.to.sefaz.persistence.enums;

import java.util.stream.Stream;

/**
 * Enum de Situacao.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
public enum SituacaoEnum implements EnumCodeLabel<Integer> {

    ATIVO(1, "Ativo"), CANCELADO(2, "Cancelado");

    private Integer code;
    private String label;

    SituacaoEnum(Integer code, String label) {
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
     * Converte inteiro em {@link SituacaoEnum} baseado no seu code. Se for passado inteiro que não existe no enum, é
     * disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link SituacaoEnum}
     */
    public static SituacaoEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoEnum: codigo=" + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
