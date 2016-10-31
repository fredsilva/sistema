package br.gov.to.sefaz.par.gestao.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum de Situacao.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
public enum TipoParametroGeralEnum implements EnumCodeLabel<Character> {

    ESTATICO('E', "Estático"), DINAMICO('D', "Dinâmico");

    private Character code;
    private String label;

    TipoParametroGeralEnum(Character code, String label) {
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
     * Converte inteiro em {@link TipoParametroGeralEnum}
     * baseado no seu code. Se for passado inteiro que não existe no enum, é
     * disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoParametroGeralEnum}
     */
    public static TipoParametroGeralEnum getValue(Character code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoEnum: codigo=" + code));
    }

    @Override
    public String toString() {
        return String.valueOf(getLabel());
    }
}
