package br.gov.to.sefaz.persistence.enums;

import java.util.stream.Stream;

/**
 * Enum de opções Sim e Não .
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 05/05/2016 16:00:31
 */
public enum YesNoEnum {

    SIM('S', "Sim", true), NAO('N', "Não", false);

    private final Character code;
    private final Boolean bool;
    private final String label;

    private YesNoEnum(Character code, String label, Boolean bool) {
        this.code = code;
        this.label = label;
        this.bool = bool;
    }

    public Character getCode() {
        return code;
    }

    public Boolean getBool() {
        return bool;
    }

    public String getLabel() {
        return label;
    }

    /**
     * Converte inteiro em {@link YesNoEnum} baseado no seu code. Se for passado inteiro que não existe no enum, é
     * disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link YesNoEnum}
     */
    public static YesNoEnum getValue(Character code) {
        if (code == null) {
            return YesNoEnum.NAO;
        }
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TrueFalseEnum: codigo=" + code));
    }

    /**
     * Converte boolean em {@link YesNoEnum}. Se for passado booblean que não existe no enum, é disparada
     * {@link IllegalArgumentException}
     *
     * @param bool valor do enum
     * @return {@link YesNoEnum}
     */
    public static YesNoEnum getValue(Boolean bool) {
        if (bool == null) {
            return YesNoEnum.NAO;
        }
        return Stream.of(values()).filter(e -> e.bool.equals(bool)).findFirst().orElseThrow(
            () -> new IllegalArgumentException("Booleano não existe no TrueFalseEnum: booleano=" + bool));
    }

}
