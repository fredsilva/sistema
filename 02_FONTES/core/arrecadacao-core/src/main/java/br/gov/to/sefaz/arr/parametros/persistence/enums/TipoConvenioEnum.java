package br.gov.to.sefaz.arr.parametros.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Tipo de Convênio por um número de identificação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 17:12:43
 */
public enum TipoConvenioEnum implements EnumCodeLabel<Integer> {

    ICMS(27, "ICMS"), IPVA(173, "IPVA"), OUTRAS_RECEITAS_TRIBUTARIAS(174,
            "OUTRAS RECEITAS TRIBUTÁRIAS"), OUTRAS_RECEITAS_NAO_TRIBUTARIAS(175,
                    "OUTRAS RECEITAS NÃO TRIBUTÁRIAS"), GNRE(316, "GNRE"), SIMPLES_NACIONAL(607, "SIMPLES NACIONAL");

    private final Integer code;
    private final String label;

    TipoConvenioEnum(Integer code, String label) {
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
     * Converte inteiro em {@link TipoConvenioEnum} baseado no seu code. Se for passado inteiro que não existe no enum,
     * é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoConvenioEnum}
     */
    public static TipoConvenioEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoConvenioEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
