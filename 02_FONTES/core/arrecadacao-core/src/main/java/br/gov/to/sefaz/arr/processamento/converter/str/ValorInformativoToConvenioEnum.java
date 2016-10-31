package br.gov.to.sefaz.arr.processamento.converter.str;

import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoValorInformativoEnum;
import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

/**
 * Enum de mapeamento de Tipos de Valor Informativo {@link TipoValorInformativoEnum} de Arquivos STR
 * para Tipo de Convênio {@link TipoConvenioEnum}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 08/07/2016 13:33:00
 */
public enum ValorInformativoToConvenioEnum implements EnumCodeLabel<TipoValorInformativoEnum> {

    VALOR_REAL(TipoValorInformativoEnum.VALOR_REAL, TipoConvenioEnum.ICMS),
    ICMS_PARCELA_ESTADO(TipoValorInformativoEnum.ICMS_PARCELA_ESTADO, TipoConvenioEnum.ICMS),
    ICMS_PARCELA_MUNICIPIO(TipoValorInformativoEnum.ICMS_PARCELA_MUNICIPIO, TipoConvenioEnum.ICMS),
    ICMS_FUNDEB(TipoValorInformativoEnum.ICMS_FUNDEB, TipoConvenioEnum.ICMS),
    ICMS_PRINCIPAL_SEM_MULTA(TipoValorInformativoEnum.ICMS_PRINCIPAL_SEM_MULTA, TipoConvenioEnum.ICMS),
    ICMS_PRINCIPAL_MULTA(TipoValorInformativoEnum.ICMS_PRINCIPAL_MULTA, TipoConvenioEnum.ICMS),
    ICMS_HONORARIOS(TipoValorInformativoEnum.ICMS_HONORARIOS, TipoConvenioEnum.ICMS),
    ICMS_ACRESCIMOS_LEGAIS(TipoValorInformativoEnum.ICMS_ACRESCIMOS_LEGAIS, TipoConvenioEnum.ICMS),
    IPVA_TOTAL(TipoValorInformativoEnum.IPVA_TOTAL, TipoConvenioEnum.IPVA),
    IPVA_PARCELA_ESTADO(TipoValorInformativoEnum.IPVA_PARCELA_ESTADO, TipoConvenioEnum.IPVA),
    IPVA_PARCELA_MUNICIPIO(TipoValorInformativoEnum.IPVA_PARCELA_MUNICIPIO, TipoConvenioEnum.IPVA),
    ITCD(TipoValorInformativoEnum.ITCD, TipoConvenioEnum.OUTRAS_RECEITAS_TRIBUTARIAS),
    TAXAS(TipoValorInformativoEnum.TAXAS, TipoConvenioEnum.OUTRAS_RECEITAS_TRIBUTARIAS),
    MULTAS_TRANSITO_TOTAL(TipoValorInformativoEnum.MULTAS_TRANSITO_TOTAL, TipoConvenioEnum.IPVA),
    MULTAS_TRANSITO_PARCELA_ESTADO(TipoValorInformativoEnum.MULTAS_TRANSITO_PARCELA_ESTADO, TipoConvenioEnum.IPVA),
    MULTAS_TRANSITO_PARCELA_FUNSET(TipoValorInformativoEnum.MULTAS_TRANSITO_PARCELA_FUNSET, TipoConvenioEnum.IPVA),
    OUTRAS_RECEITAS_TRIBUTARIAS(TipoValorInformativoEnum.OUTRAS_RECEITAS_TRIBUTARIAS,
            TipoConvenioEnum.OUTRAS_RECEITAS_TRIBUTARIAS),
    OUTRAS_RECEITAS_NAO_TRIBUTARIAS(TipoValorInformativoEnum.OUTRAS_RECEITAS_NAO_TRIBUTARIAS,
            TipoConvenioEnum.OUTRAS_RECEITAS_NAO_TRIBUTARIAS),
    GNRE_TOTAL(TipoValorInformativoEnum.GNRE_TOTAL, TipoConvenioEnum.GNRE),
    GNRE_ICMS(TipoValorInformativoEnum.GNRE_ICMS, TipoConvenioEnum.GNRE),
    GNRE_OUTRAS_RECEITAS(TipoValorInformativoEnum.GNRE_OUTRAS_RECEITAS, TipoConvenioEnum.GNRE),
    GNRE_CLASSIFICAR(TipoValorInformativoEnum.GNRE_CLASSIFICAR, TipoConvenioEnum.GNRE),
    RECEITAS_CLASSIFICAR(TipoValorInformativoEnum.RECEITAS_CLASSIFICAR, TipoConvenioEnum.OUTRAS_RECEITAS_TRIBUTARIAS),
    MULTAS_TRANSITO_PARCELA_RODOVIARIA_FEDERAL(TipoValorInformativoEnum.MULTAS_TRANSITO_PARCELA_RODOVIARIA_FEDERAL,
            TipoConvenioEnum.IPVA),
    TODOS(TipoValorInformativoEnum.TODOS, TipoConvenioEnum.OUTRAS_RECEITAS_NAO_TRIBUTARIAS);

    TipoValorInformativoEnum code;
    TipoConvenioEnum value;

    ValorInformativoToConvenioEnum(TipoValorInformativoEnum code, TipoConvenioEnum value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public TipoValorInformativoEnum getCode() {
        return code;
    }

    public TipoConvenioEnum getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return value.getLabel();
    }

    /**
     * Encontra a {@link TipoConvenioEnum} correspondente ao código passado como parâmetro.
     *
     * @param code código {@link TipoValorInformativoEnum} do tipo de receita.
     * @return retorna {@link TipoConvenioEnum} correspondente ao código passado como parâmetro.
     */
    public static TipoConvenioEnum forValorInformativo(TipoValorInformativoEnum code) {
        for (TipoReceitaToTipoConvenioEnum dePara : TipoReceitaToTipoConvenioEnum.values()) {
            if (dePara.getCode().equals(code)) {
                return dePara.getValue();
            }
        }
        throw new IllegalArgumentException("Código não existe no TipoConvenioEnum: codigo = " + code);
    }

}
