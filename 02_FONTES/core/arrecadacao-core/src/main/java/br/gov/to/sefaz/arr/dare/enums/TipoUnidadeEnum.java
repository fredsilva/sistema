package br.gov.to.sefaz.arr.dare.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum que contém os valores da SEFAZ_PAR.TA_PARAMETRO_GERAL de
 * LISTAGEM DE TIPOS DE UNIDADES ORGANIZACIONAIS DO SISTEMA.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 09/09/2016 18:10:00
 */
public enum TipoUnidadeEnum implements EnumCodeLabel<Character> {

    COLETORIA('C', "Coletoria"),
    Delegacia('D', "Delegacia"),
    ORGAOS_EXTERNOS('E', "Órgãos Externos"),
    POSTO('P', "Posto"),
    POSTO_VISTORIA('V', "Posto Vistoria"),
    REGIONAL('R', "Regional"),
    UNIDADE('U', "Unidade"),
    SEFAZ('S', "SEFAZ");

    private final Character code;
    private final String label;

    TipoUnidadeEnum(Character code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public Character getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Converte inteiro em {@link TipoUnidadeEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoUnidadeEnum}
     */
    public static TipoUnidadeEnum getValue(Character code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoUnidadeEnum: codigo="
                        + code));
    }
}
