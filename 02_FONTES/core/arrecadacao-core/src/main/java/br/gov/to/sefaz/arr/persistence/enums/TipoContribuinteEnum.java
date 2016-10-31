package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Classificação de Tipo de Contribuinte por um número de identificação.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 18/08/2016 13:49:00
 */
public enum TipoContribuinteEnum implements EnumCodeLabel<Integer> {

    ICMS(1, "ICMS"),
    IPVA(2, "IPVA"),
    ITCD(3, "ITCD"),
    NAO_CONTRIBUINTE(4, "Não Contribuinte");

    private final Integer code;
    private final String label;

    TipoContribuinteEnum(
            Integer code, String label) {
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
     * Converte inteiro em {@link TipoContribuinteEnum} baseado no seu code.
     * Se for passado inteiro que não existe no enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoContribuinteEnum}
     */
    public static TipoContribuinteEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoContribuinteEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
