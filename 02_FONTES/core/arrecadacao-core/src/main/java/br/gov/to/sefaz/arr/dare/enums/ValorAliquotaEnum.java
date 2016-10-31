package br.gov.to.sefaz.arr.dare.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

/**
 * Enum com os valores em porcentagem de Aliquotas.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/08/2016 14:48:00
 */
public enum ValorAliquotaEnum implements EnumCodeLabel<Double> {
    DOIS_PORCENTO(0.02, "2%"),
    DOZE_PORCENTO(0.12, "12%"),
    DEZOITO_PORCENTO(0.18, "18%"),
    DEZENOVE_PORCENTO(0.19, "19%"),
    VINTE_CINCO_PORCENTO(0.25, "25%"),
    VINTE_SETE_PORCENTO(0.27, "27%");

    private final Double value;
    private final String label;

    ValorAliquotaEnum(Double value, String label) {

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
