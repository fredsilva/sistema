package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Tipo de Receita por um número de identificação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 11:34:00
 */
public enum TipoReceitaEnum implements EnumCodeLabel<Integer> {

    IMPOSTO(1, "Imposto"), MULTA(2, "Multa"), JUROS(3, "Juros"), CORRECAO(4, "Correção Monetária"), TAXAS(5, "Taxas");

    private final Integer code;
    private final String label;

    TipoReceitaEnum(Integer code, String label) {
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
     * Converte inteiro em {@link TipoReceitaEnum} baseado no seu code. Se for passado inteiro que não existe no enum, é
     * disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoReceitaEnum}
     */
    public static TipoReceitaEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoReceitaStrEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
