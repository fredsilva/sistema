package br.gov.to.sefaz.arr.parametros.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum de Tipos de Repasse.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/05/2016 10:10:00
 */
public enum TipoRepasseEnum implements EnumCodeLabel<Integer> {

    UF(1, "Repasse UF"), FUNDEB_UF(2, "Repasse Fundeb UF"), MUNICIPIO(3, "Repasse Município"), FUNDEB_MUNICIPIO(4,
            "Repasse Fundeb Município");

    private final Integer code;
    private final String label;

    TipoRepasseEnum(
            Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * Converte inteiro em {@link TipoRepasseEnum} baseado no seu code. Se for passado inteiro que não existe no enum, é
     * disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoRepasseEnum}
     */
    public static TipoRepasseEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoRepasseEnum: codigo="
                        + code));
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
