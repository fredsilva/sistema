package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoRepasseEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidade referente a primary key da tabela SEFAZ_ARR.TA_RECEITAS_REPASSE do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 09:57:47
 */
public class ReceitasRepassePK implements Serializable {

    private static final long serialVersionUID = -5765099330754036246L;

    private Integer idReceita;
    private TipoRepasseEnum tipoRepasse;
    private LocalDate dataInicio;

    public ReceitasRepassePK() {
        // Construtor para inicialização por reflexão.
    }

    public ReceitasRepassePK(Integer idReceita, TipoRepasseEnum tipoRepasse, LocalDate dataInicio) {
        this.idReceita = idReceita;
        this.tipoRepasse = tipoRepasse;
        this.dataInicio = dataInicio;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public TipoRepasseEnum getTipoRepasse() {
        return tipoRepasse;
    }

    public void setTipoRepasse(TipoRepasseEnum tipoRepasse) {
        this.tipoRepasse = tipoRepasse;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReceitasRepassePK that = (ReceitasRepassePK) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(tipoRepasse, that.tipoRepasse)
                && Objects.equals(dataInicio, that.dataInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, tipoRepasse, dataInicio);
    }

    @Override
    public String toString() {
        return "ReceitasRepassePK{"
                + "idReceita=" + idReceita
                + ", tipoRepasse=" + tipoRepasse
                + ", dataInicio=" + dataInicio
                + '}';
    }
}
