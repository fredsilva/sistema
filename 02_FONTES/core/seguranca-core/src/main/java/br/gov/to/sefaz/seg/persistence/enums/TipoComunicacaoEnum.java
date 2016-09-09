package br.gov.to.sefaz.seg.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para o tipo de comunicação realizada com o contribuinte.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 14:01:00
 */
public enum TipoComunicacaoEnum implements EnumCodeLabel<String> {

    EMAIL("EMAIL", "E-mail"),
    SMS("SMS", "SMS");

    private final String code;
    private final String label;

    TipoComunicacaoEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * Converte uma string em {@link br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum} baseado no seu code.
     * Se for passado uma string que não existe no enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum}
     */
    public static TipoComunicacaoEnum getValue(final String code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoComunicacaoEnum: codigo="
                        + code));
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
