package br.gov.to.sefaz.arr.processamento.converter.str;

import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoReceitaStrEnum;

/**
 * Enum de mapeamento de Tipos de Receita de Arquivos STR para Tipos de Convênio.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 16:22:00
 */
public enum TipoReceitaToTipoConvenioEnum {

    ICMS(TipoReceitaStrEnum.ICMS, TipoConvenioEnum.ICMS),
    IPVA(TipoReceitaStrEnum.IPVA, TipoConvenioEnum.IPVA),
    SIMPLES_NACIONAL(TipoReceitaStrEnum.SIMPLES_NACIONAL, TipoConvenioEnum.SIMPLES_NACIONAL),
    RECEITAS_NAO_TRIB(TipoReceitaStrEnum.RECEITAS_NAO_TRIBUTARIAS, TipoConvenioEnum.OUTRAS_RECEITAS_NAO_TRIBUTARIAS),
    GNRE(TipoReceitaStrEnum.GNRE, TipoConvenioEnum.GNRE);

    TipoReceitaStrEnum code;
    TipoConvenioEnum value;

    TipoReceitaToTipoConvenioEnum(TipoReceitaStrEnum code, TipoConvenioEnum value) {
        this.code = code;
        this.value = value;
    }

    public TipoReceitaStrEnum getCode() {
        return code;
    }

    public TipoConvenioEnum getValue() {
        return value;
    }

    /**
     * Encontra a {@link TipoConvenioEnum} correspondente ao código {@link TipoReceitaStrEnum} passado como parâmetro.
     *
     * @param code código {@link TipoReceitaStrEnum} do tipo de receita.
     * @return retorna {@link TipoConvenioEnum} correspondente ao código {@link TipoReceitaStrEnum} passado como
     *     parâmetro.
     */
    public static TipoConvenioEnum forTipoReceita(TipoReceitaStrEnum code) {
        for (TipoReceitaToTipoConvenioEnum dePara : TipoReceitaToTipoConvenioEnum.values()) {
            if (dePara.getCode().equals(code)) {
                return dePara.getValue();
            }
        }
        throw new IllegalArgumentException("Código não existe no TipoReceitaToTipoConvenioEnum: codigo = " + code);
    }

}
