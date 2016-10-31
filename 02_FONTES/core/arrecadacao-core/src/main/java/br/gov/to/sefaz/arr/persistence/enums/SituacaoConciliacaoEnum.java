package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enumeração para as situações possíveis do processo da conciliação.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 13/07/2016 14:41:00
 */
public enum SituacaoConciliacaoEnum implements EnumCodeLabel<Integer> {
    CONCILIADO(1, "CONCILIADO"),
    NAO_CONCILIADO(2, "NÃO CONCILIADO");

    private Integer code;
    private String label;

    SituacaoConciliacaoEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    /**
     * Retorna o {@link SituacaoConciliacaoEnum} correspondente ao
     * código da situação.
     *
     * @param code código da situação.
     * @return {@link SituacaoConciliacaoEnum} correspondente.
     */
    public static SituacaoConciliacaoEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoConciliacaoEnum: codigo="
                        + code));
    }
}
