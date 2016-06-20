package br.gov.to.sefaz.arr.parametros.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Tipo de Código de Barra por um número de identificação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 16:58:00
 */
public enum TipoCodigoBarraEnum implements EnumCodeLabel<Integer> {

    GNRE(1, "GNRE"), DARE(2, "DARE"), IPVA(3, "IPVA"), SIMPLES_NACIONAL(4, "SIMPLES NACIONAL"), SEM_BARRA(5,
            "SEM BARRA");

    private final Integer code;
    private final String label;

    private TipoCodigoBarraEnum(Integer code, String label) {
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
     * Converte inteiro em {@link TipoCodigoBarraEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoCodigoBarraEnum}
     */
    public static TipoCodigoBarraEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoCodigoBarraEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
