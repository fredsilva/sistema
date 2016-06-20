package br.gov.to.sefaz.seg.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para o tipo de operação da navegação do usuário.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 18/06/2016 11:52:00
 */
public enum TipoOperacaoEnum implements EnumCodeLabel<Character> {

    NAVEGACAO('N', "Navegação"), TENTATIVA_NEGADA('T', "Tentativa Negada");

    private final Character code;
    private final String label;

    TipoOperacaoEnum(
            Character code, String label) {
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
     * Converte caractere em {@link TipoOperacaoEnum} baseado no seu code. Se for passado caractere que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoOperacaoEnum}
     */
    public static TipoOperacaoEnum getValue(Character code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoOperacaoEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
