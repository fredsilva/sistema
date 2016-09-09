package br.gov.to.sefaz.seg.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para o tipo de período de consulta de comunicação realizada com o contribuinte.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 29/08/2016 14:36:00
 */
public enum TipoPeriodoConsultaComunicacaoEnum implements EnumCodeLabel<String> {

    HOJE("HOJE", "Hoje", 0),
    ULTIMO_7("ULTIMO_7", "Últimos 7 dias", 7),
    ULTIMO_15("ULTIMO_15", "Últimos 15 dias", 15),
    ULTIMO_30("ULTIMO_30", "Últimos 30 dias", 30),
    ULTIMO_45("ULTIMO_45", "Últimos 45 dias", 45),
    ULTIMO_60("ULTIMO_60", "Últimos 60 dias", 60),
    ULTIMO_90("ULTIMO_90", "Últimos 90 dias", 90),
    PERIODO("PERIODO", "Período específico", -1);

    private final String code;
    private final String label;
    private final int days;

    TipoPeriodoConsultaComunicacaoEnum(String code, String label, int days) {
        this.code = code;
        this.label = label;
        this.days = days;
    }

    /**
     * Converte uma string em {@link TipoPeriodoConsultaComunicacaoEnum} baseado no seu code.
     * Se for passado uma string que não existe no enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoPeriodoConsultaComunicacaoEnum}
     */
    public static TipoPeriodoConsultaComunicacaoEnum getValue(final String code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no "
                        + "TipoPeriodoConsultaComunicacaoEnum: codigo=" + code));
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Obtém o número de dias que esse o tipo de periodo representa.
     *
     * @return o número de dias
     */
    public int getDays() {
        return days;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
