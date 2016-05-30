package br.gov.to.sefaz.arr.parametros.persistence.enums;

import br.gov.to.sefaz.business.managedbean.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Classificação de Receita por um número de identificação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 11:45:00
 */
public enum ClassificacaoReceitaEnum implements EnumCodeLabel<Integer> {

    ICMS(1, "ICMS"), IPVA(2, "IPVA"), ITCD(3, "ITCD"),
    OUTRAS(4, "OUTRAS RECEITAS"), DEDUCOES(5, "DEDUÇÕES"),
    ICMS_ATIVA(6, "ICMS DIVIDA ATIVA"), IPVA_ATIVA(7, "IPVA DIVIDA ATIVA"),
    ITCD_ATIVA(8, "ITCD DIVIDA ATIVA"), OUTRAS_NAO_TRIBUTARIAS(9, "OUTRAS RECEITAS NÃO TRIBUTÁRIAS");

    private final Integer code;
    private final String label;

    ClassificacaoReceitaEnum(Integer code, String label) {
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
     * Converte inteiro em {@link ClassificacaoReceitaEnum} baseado no seu code. Se for passado inteiro que não
     * existe no enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link ClassificacaoReceitaEnum}
     */
    public static ClassificacaoReceitaEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no ClassificacaoReceitaEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
