package br.gov.to.sefaz.arr.persistence.enums;

import java.util.stream.Stream;

/**
 * Enum que representa os valores possíveis para os Tipos de Lotes de Arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 16:14:00
 */
public enum TipoLotePagosEnum {

    BDAR(1), TPAR(2);

    private final Integer code;

    TipoLotePagosEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * Converte inteiro em {@link TipoLotePagosEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoLotePagosEnum}
     */
    public static TipoLotePagosEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoLotePagosEnum: codigo="
                        + code));
    }
}
