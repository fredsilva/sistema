package br.gov.to.sefaz.par.gestao.persistence.entity;

import br.gov.to.sefaz.par.gestao.persistence.converter.TipoFeriadoEnumConverter;
import br.gov.to.sefaz.par.gestao.persistence.enums.TipoFeriadoEnum;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Entidade referente a tabela SEFAZ_PAR.TA_FERIADOS do Banco de Dados.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 21/09/2016 14:24:00
 */
@Entity
@Table(name = "TA_FERIADOS", schema = "SEFAZ_PAR")
@IdClass(value = FeriadoPK.class)
public class Feriado extends AbstractEntity<FeriadoPK> implements Serializable {

    private static final long serialVersionUID = 4933628842956367416L;

    @Id
    @Column(name = "DIA_FERIADO")
    private Integer diaFeriado;

    @Id
    @Column(name = "MES_FERIADO")
    private Integer mesFeriado;

    @Id
    @Column(name = "ANO_FERIADO")
    private Integer anoFeriado;

    @Column(name = "TIPO_FERIADO")
    @Convert(converter = TipoFeriadoEnumConverter.class)
    private TipoFeriadoEnum tipoFeriado;

    @Column(name = "TXT_OBSERVA")
    private String txtObserva;

    @Column(name = "UNIDADE_FEDERACAO")
    private String unidadeFederacao;

    @Column(name = "CODIGO_IBGE")
    private Integer codigoIbge;

    @Override
    public FeriadoPK getId() {
        return new FeriadoPK(diaFeriado, mesFeriado, anoFeriado);
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

    public TipoFeriadoEnum getTipoFeriado() {
        return tipoFeriado;
    }

    public void setTipoFeriado(TipoFeriadoEnum tipoFeriado) {
        this.tipoFeriado = tipoFeriado;
    }

    public String getTxtObserva() {
        return txtObserva;
    }

    public void setTxtObserva(String txtObserva) {
        this.txtObserva = txtObserva;
    }

    public String getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(String unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Feriado feriado = (Feriado) o;
        return Objects.equals(diaFeriado, feriado.diaFeriado)
                && Objects.equals(mesFeriado, feriado.mesFeriado)
                && Objects.equals(anoFeriado, feriado.anoFeriado)
                && tipoFeriado == feriado.tipoFeriado
                && Objects.equals(txtObserva, feriado.txtObserva)
                && Objects.equals(unidadeFederacao, feriado.unidadeFederacao)
                && Objects.equals(codigoIbge, feriado.codigoIbge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diaFeriado, mesFeriado, anoFeriado, tipoFeriado, txtObserva, unidadeFederacao, codigoIbge);
    }

    @Override
    public String toString() {
        return "Feriado{"
                + "diaFeriado=" + diaFeriado
                + ", mesFeriado=" + mesFeriado
                + ", anoFeriado=" + anoFeriado
                + ", tipoFeriado=" + tipoFeriado
                + ", txtObserva='" + txtObserva + '\''
                + ", unidadeFederacao='" + unidadeFederacao + '\''
                + ", codigoIbge=" + codigoIbge
                + '}';
    }
}
