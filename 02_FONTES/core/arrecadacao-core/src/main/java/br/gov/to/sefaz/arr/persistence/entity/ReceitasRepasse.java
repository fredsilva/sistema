package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.TipoRepasseEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.TipoRepasseEnum;
import br.gov.to.sefaz.persistence.converter.OneOrTwoBooleanConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_RECEITAS_REPASSE do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 09:57:47
 */
@Entity
@Table(name = "TA_RECEITAS_REPASSE", schema = "SEFAZ_ARR")
@IdClass(ReceitasRepassePK.class)
public class ReceitasRepasse extends AbstractEntity<ReceitasRepassePK> {

    private static final long serialVersionUID = 3986002397242116268L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.receitas.idReceita.obrigatorio']}")
    @Max(value = 9999, message = "#{arr_msg['parametros.receitasRepasse.idReceita.maximo']}")
    @Column(name = "ID_RECEITA")
    private Integer idReceita;

    @Id
    @NotNull(message = "#{arr_msg['parametros.receitasRepasse.tipoRepasse.obrigatorio']}")
    @Convert(converter = TipoRepasseEnumConverter.class)
    @Column(name = "TIPO_REPASSE")
    private TipoRepasseEnum tipoRepasse;

    @NotNull(message = "#{arr_msg['parametros.receitasRepasse.dataInicio.obrigatorio']}")
    @Column(name = "DATA_INICIO")
    private LocalDate dataInicio;

    @Column(name = "DATA_FINAL")
    private LocalDate dataFinal;

    @NotNull(message = "#{arr_msg['parametros.receitasRepasse.percentualRepasse.obrigatorio']}")
    @Digits(integer = 5, fraction = 2, message = "#{arr_msg['parametros.receitasRepasse.percentualRepasse.digitos']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['parametros.receitasRepasse.percentualRepasse.minimo']}")
    @DecimalMax(value = "100.00", message = "#{arr_msg['parametros.receitasRepasse.percentualRepasse.maximo']}")
    @Column(name = "PERCENTUAL_REPASSE")
    private BigDecimal percentualRepasse;

    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "REPARTE_PRINCIPAL")
    private Boolean repartePrincipal;

    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "REPARTE_MULTA")
    private Boolean reparteMulta;

    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "REPARTE_JUROS")
    private Boolean reparteJuros;

    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "REPARTE_CORRECAO")
    private Boolean reparteCorrecao;

    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "REPARTE_TAXA")
    private Boolean reparteTaxa;

    @JoinColumn(name = "ID_RECEITA", referencedColumnName = "ID_RECEITA", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Receitas receitas;

    @Override
    public ReceitasRepassePK getId() {
        return new ReceitasRepassePK(getIdReceita(), getTipoRepasse(), getDataInicio());
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

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public BigDecimal getPercentualRepasse() {
        return percentualRepasse;
    }

    public void setPercentualRepasse(BigDecimal percentualRepasse) {
        this.percentualRepasse = percentualRepasse;
    }

    public Boolean getRepartePrincipal() {
        return repartePrincipal;
    }

    public void setRepartePrincipal(Boolean repartePrincipal) {
        this.repartePrincipal = repartePrincipal;
    }

    public Boolean getReparteMulta() {
        return reparteMulta;
    }

    public void setReparteMulta(Boolean reparteMulta) {
        this.reparteMulta = reparteMulta;
    }

    public Boolean getReparteJuros() {
        return reparteJuros;
    }

    public void setReparteJuros(Boolean reparteJuros) {
        this.reparteJuros = reparteJuros;
    }

    public Boolean getReparteCorrecao() {
        return reparteCorrecao;
    }

    public void setReparteCorrecao(Boolean reparteCorrecao) {
        this.reparteCorrecao = reparteCorrecao;
    }

    public Boolean getReparteTaxa() {
        return reparteTaxa;
    }

    public void setReparteTaxa(Boolean reparteTaxa) {
        this.reparteTaxa = reparteTaxa;
    }

    public Receitas getReceitas() {
        return receitas;
    }

    public void setReceitas(Receitas receitas) {
        this.receitas = receitas;
    }

    /**
     * Busca a label de Incidência.
     * @return label completa de incidência.
     */
    public String getIncidenciaLabel() {
        StringBuilder stringBuilder = new StringBuilder();

        appendPrincipalLabel(stringBuilder);
        appendMultaLabel(stringBuilder);
        appendJurosLabel(stringBuilder);
        appendCorrecaoLabel(stringBuilder);
        appendTaxaLabel(stringBuilder);

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReceitasRepasse that = (ReceitasRepasse) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(tipoRepasse, that.tipoRepasse)
                && Objects.equals(dataInicio, that.dataInicio)
                && Objects.equals(dataFinal, that.dataFinal)
                && Objects.equals(percentualRepasse, that.percentualRepasse)
                && Objects.equals(repartePrincipal, that.repartePrincipal)
                && Objects.equals(reparteMulta, that.reparteMulta)
                && Objects.equals(reparteJuros, that.reparteJuros)
                && Objects.equals(reparteCorrecao, that.reparteCorrecao)
                && Objects.equals(reparteTaxa, that.reparteTaxa)
                && Objects.equals(receitas, that.receitas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, tipoRepasse, dataInicio, dataFinal, percentualRepasse, repartePrincipal,
                reparteMulta, reparteJuros, reparteCorrecao, reparteTaxa, receitas);
    }

    @Override
    public String toString() {
        return "ReceitasRepasseRepository{"
                + "idReceita=" + idReceita
                + ", tipoRepasse=" + tipoRepasse
                + ", dataInicio=" + dataInicio
                + ", dataFinal=" + dataFinal
                + ", percentualRepasse=" + percentualRepasse
                + ", repartePrincipal=" + repartePrincipal
                + ", reparteMulta=" + reparteMulta
                + ", reparteJuros=" + reparteJuros
                + ", reparteCorrecao=" + reparteCorrecao
                + ", reparteTaxa=" + reparteTaxa
                + ", receitas=" + receitas
                + '}';
    }

    private void appendTaxaLabel(StringBuilder stringBuilder) {
        if (getReparteTaxa()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('-');
            }
            stringBuilder.append('T');
        }
    }

    private void appendCorrecaoLabel(StringBuilder stringBuilder) {
        if (getReparteCorrecao()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('-');
            }
            stringBuilder.append('C');
        }
    }

    private void appendJurosLabel(StringBuilder stringBuilder) {
        if (getReparteJuros()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('-');
            }
            stringBuilder.append('J');
        }
    }

    private void appendMultaLabel(StringBuilder stringBuilder) {
        if (getReparteMulta()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('-');
            }
            stringBuilder.append('M');
        }
    }

    private void appendPrincipalLabel(StringBuilder stringBuilder) {
        if (getRepartePrincipal()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('-');
            }
            stringBuilder.append('P');
        }
    }
}
