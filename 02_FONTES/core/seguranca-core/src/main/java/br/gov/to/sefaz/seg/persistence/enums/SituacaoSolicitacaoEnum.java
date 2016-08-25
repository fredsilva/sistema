package br.gov.to.sefaz.seg.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Situação da Solicitação do Usuário.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 17:32:00
 *
 */
public enum SituacaoSolicitacaoEnum implements EnumCodeLabel<Character> {

    PENDENTE('P', "Pendente"), CRIADO('C', "Criado");

    private final Character code;
    private final String label;

    SituacaoSolicitacaoEnum(Character code, String label) {
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
     * Converte caractere em {@link SituacaoSolicitacaoEnum} baseado no seu code. Se for passado caractere que não
     * existe no enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link SituacaoSolicitacaoEnum}
     */
    public static SituacaoSolicitacaoEnum getValue(Character code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoSolicitacaoEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
