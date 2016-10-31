package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Classificação de Origem de Débito por um número de identificação.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 18/08/2016 13:49:00
 */
public enum OrigemDebitoEnum implements EnumCodeLabel<Integer> {

    DECLARADO_ICMS(1, "DECLARADO ICMS"),
    DECLARADO_ICMS_ST(2, "DECLARADO ICMS ST"),
    DECLARADO_ICMS_COMPL(3, "DECLARADO ICMS COMPL."),
    COBRANCA_TRANSITO(4, "COBRANÇA TRÂNSITO"),
    AUTO_INFRACAO_NLD(5, "AUTO DE INFRAÇÃO / NLD"),
    PARCELAMENTO(6, "PARCELAMENTO"),
    PARCELAMENTO_DIVIDA_ATIVA(7, "PARCELAMENTO DÍVIDA ATIVA"),
    DIVIDA_ATIVA(8, "DÍVIDA ATIVA"),
    NOTA_AVULSA(9, "NOTA AVULSA"),
    ICMS_FRETE(10, "ICMS FRETE"),
    NF_OPERACOES_EXPONTANEAS_ST(11, "NF OPERAÇÕES EXPONTÂNEAS ST"),
    DPCA(12, "DPCA"),
    IPVA(13, "IPVA"),
    ITCD(14, "ITCD"),
    MULTAS(15, "MULTAS"),
    TAXAS(16, "TAXAS"),
    OUTRAS_RECEITAS(17, "OUTRAS RECEITAS");

    private final Integer code;
    private final String label;

    OrigemDebitoEnum(
            Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Converte inteiro em {@link OrigemDebitoEnum} baseado no seu code.
     * Se for passado inteiro que não existe no enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link OrigemDebitoEnum}
     */
    public static OrigemDebitoEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no OrigemDebitoEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
