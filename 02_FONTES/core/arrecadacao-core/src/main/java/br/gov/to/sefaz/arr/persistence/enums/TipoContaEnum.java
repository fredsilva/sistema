package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum de Tipo de Conta.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
public enum TipoContaEnum implements EnumCodeLabel<Integer> {

    SINTETICA(1, "Sintética"), ANALITICA(2, "Analítica");

    private final Integer code;
    private final String label;

    private TipoContaEnum(Integer code, String label) {
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
     * Converte inteiro em {@link TipoContaEnum} baseado no seu code. Se for passado inteiro que não existe no enum, é
     * disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoContaEnum}
     */
    public static TipoContaEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoEnum: codigo=" + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
