package br.gov.to.sefaz.arr.dare.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

/**
 * Enum com os valores em porcentagem de Multa.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/08/2016 14:48:00
 */
public enum ValorMultaEnum implements EnumCodeLabel<Double> {
    ZERO_PORCENTO(0.0, "0%"),
    QUARENTA_PORCENTO(0.4, "40%"),
    SESSENTA_PORCENTO(0.6, "60%");

    private final Double value;
    private final String label;

    ValorMultaEnum(Double value, String label) {
        this.value = value;
        this.label = label;
    }

    @Override
    public Double getCode() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
