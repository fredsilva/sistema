package br.gov.to.sefaz.seg.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Situação Usuário.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 06/06/2016 18:01:01
 */
public enum SituacaoUsuarioEnum implements EnumCodeLabel<Character> {

    ATIVO('A', "Ativo"), INATIVO('I', "Inativo");

    private final Character code;
    private final String label;

    SituacaoUsuarioEnum(Character code, String label) {
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
     * Converte caractere em {@link SituacaoUsuarioEnum} baseado no seu code. Se for passado caractere que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link SituacaoUsuarioEnum}
     */
    public static SituacaoUsuarioEnum getValue(Character code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoUsuarioEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
