package br.gov.to.sefaz.arr.persistence.enums;

import java.util.stream.Stream;

/**
 * Enum que representa os valores possíveis para o Estado de Lotes de Arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 16:10:00
 */
public enum EstadoLoteEnum {

    ABERTO(1), FECHADO(2);

    private final Integer code;

    EstadoLoteEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * Converte inteiro em {@link EstadoLoteEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link EstadoLoteEnum}
     */
    public static EstadoLoteEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no EstadoLoteEnum: codigo="
                        + code));
    }
}
