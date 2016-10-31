package br.gov.to.sefaz.par.gestao.persistence.enums;

import java.util.stream.Stream;

/**
 * Enumeração que representa os tipos de feriados.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 21/09/2016 14:43:00
 */
public enum TipoFeriadoEnum {

    NACIONAL('N', "Nacional"),
    ESTADUAL('E', "Estadual"),
    MUNICIPAL('M', "Municipal"),
    OUTROS('O', "Outros");

    private Character code;
    private String descricao;

    TipoFeriadoEnum(Character code, String descricao) {
        this.code = code;
        this.descricao = descricao;
    }

    /**
     * Converte caracter em {@link TipoFeriadoEnum}
     * baseado no seu code. Se for passado caracter que não existe no enum, é
     * disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoFeriadoEnum}
     */
    public static TipoFeriadoEnum getValue(Character code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoFeriadoEnum: codigo="
                        + code));
    }

    public Character getCode() {
        return code;
    }

    public String getDescricao() {
        return descricao;
    }

}
