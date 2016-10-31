package br.gov.to.sefaz.par.gestao.persistence.entity;

import java.io.Serializable;

/**
 * Classe para mapeamento de pk composta de {@link br.gov.to.sefaz.par.gestao.persistence.entity.Feriado}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 22/09/2016 13:58:00
 */
public class FeriadoPK implements Serializable {

    private static final long serialVersionUID = -3994394097233111226L;

    private Integer diaFeriado;
    private Integer mesFeriado;
    private Integer anoFeriado;

    public FeriadoPK() {
        // Construtor para inicialização por reflexão.
    }

    public FeriadoPK(Integer diaFeriado, Integer mesFeriado, Integer anoFeriado) {
        this.diaFeriado = diaFeriado;
        this.mesFeriado = mesFeriado;
        this.anoFeriado = anoFeriado;
    }

    public Integer getDiaFeriado() {
        return diaFeriado;
    }

    public void setDiaFeriado(Integer diaFeriado) {
        this.diaFeriado = diaFeriado;
    }

    public Integer getMesFeriado() {
        return mesFeriado;
    }

    public void setMesFeriado(Integer mesFeriado) {
        this.mesFeriado = mesFeriado;
    }

    public Integer getAnoFeriado() {
        return anoFeriado;
    }

    public void setAnoFeriado(Integer anoFeriado) {
        this.anoFeriado = anoFeriado;
    }

}
