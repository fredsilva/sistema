package br.gov.to.sefaz.arr.processamento.domain.str;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum de Tipos de Valor Informativo de Arquivos STR por código e descrição.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 08/07/2016 11:47:00
 */
public enum TipoValorInformativoEnum implements EnumCodeLabel<Integer> {

    VALOR_REAL(1, "VALOR REAL"),
    ICMS_PARCELA_ESTADO(2, "ICMS PARCELA DO ESTADO"),
    ICMS_PARCELA_MUNICIPIO(3, "ICMS PARCELA DO MUNICIPIO"),
    ICMS_FUNDEB(4, "ICMS FUNDEB"),
    ICMS_PRINCIPAL_SEM_MULTA(5, "ICMS PRINCIPAL SEM MULTA"),
    ICMS_PRINCIPAL_MULTA(6, "ICMS PRINCIPAL COM MULTA"),
    ICMS_HONORARIOS(7, "ICMS HONORÁRIOS"),
    ICMS_ACRESCIMOS_LEGAIS(8, "ICMS ACRSCIMOS LEGAIS"),
    IPVA_TOTAL(9, "IPVA TOTAL"),
    IPVA_PARCELA_ESTADO(10, "IPVA PARCELA ESTADO"),
    IPVA_PARCELA_MUNICIPIO(11, "IPVA PARCELA MUNICIPIO"),
    ITCD(12, "ITCD"),
    TAXAS(13, "TAXAS"),
    MULTAS_TRANSITO_TOTAL(14, "MULTAS DE TRÂNSITO TOTAL"),
    MULTAS_TRANSITO_PARCELA_ESTADO(15, "MULTAS DE TRÂNSITO PARCELA DO ESTADO"),
    MULTAS_TRANSITO_PARCELA_FUNSET(16, "MULTAS DE TRÂNSITO PARCELA FUNSET"),
    OUTRAS_RECEITAS_TRIBUTARIAS(17, "OUTRAS RECEITAS TRIBUTÁRIAS"),
    OUTRAS_RECEITAS_NAO_TRIBUTARIAS(18, "OUTRAS RECEITAS NÃO TRIBUTÁRIAS"),
    GNRE_TOTAL(19, "GNRE TOTAL"),
    GNRE_ICMS(20, "GNRE ICMS"),
    GNRE_OUTRAS_RECEITAS(21, "GNRE OUTRAS RECEITAS"),
    GNRE_CLASSIFICAR(22, "GNRE A CLASSIFICAR"),
    RECEITAS_CLASSIFICAR(23, "RECEITAS A CLASSIFICAR"),
    MULTAS_TRANSITO_PARCELA_RODOVIARIA_FEDERAL(24, "MULTAS DE TRÂNSITO - PARCELA RODOVIÁRIA FEDERAL"),
    TODOS(25, "TODOS");

    private final Integer code;
    private final String label;

    TipoValorInformativoEnum(Integer code, String label) {
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
     * Encontra a {@link TipoValorInformativoEnum} correspondente ao código passado como parâmetro.
     *
     * @param code código da situação do arquivo.
     * @return retorna {@link TipoValorInformativoEnum} correspondente ao código passado como parâmetro.
     */
    public static TipoValorInformativoEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoValorInformativoEnum: codigo="
                        + code));
    }

}
