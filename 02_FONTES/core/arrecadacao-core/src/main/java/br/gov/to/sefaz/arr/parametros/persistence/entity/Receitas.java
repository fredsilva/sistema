package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.arr.parametros.persistence.converter.ClassificacaoReceitaEnumConverter;
import br.gov.to.sefaz.arr.parametros.persistence.converter.TipoReceitaEnumConverter;
import br.gov.to.sefaz.arr.parametros.persistence.enums.ClassificacaoReceitaEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoReceitaEnum;
import br.gov.to.sefaz.persistence.converter.OneOrTwoBooleanConverter;
import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_RECEITAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 09:57:47
 */
@Entity
@Table(name = "TA_RECEITAS", schema = "SEFAZ_ARR")
public class Receitas extends AbstractEntity<Integer> {

    private static final long serialVersionUID = -552161416903571751L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.receitas.idReceita.obrigatorio']}")
    @Max(value = 9999, message = "#{arr_msg['parametros.receitas.idReceita.maximo']}")
    @Column(name = "ID_RECEITA")
    private Integer idReceita;

    @NotEmpty(message = "#{arr_msg['parametros.receitas.descricaoReceita.obrigatorio']}")
    @Size(max = 250, message = "#{arr_msg['parametros.receitas.descricaoReceita.maximo']}")
    @Column(name = "DESCRICAO_RECEITA")
    private String descricaoReceita;

    @NotNull(message = "#{arr_msg['parametros.receitas.tipoReceita.obrigatori']}")
    @Convert(converter = TipoReceitaEnumConverter.class)
    @Column(name = "TIPO_RECEITA")
    private TipoReceitaEnum tipoReceita;

    @NotNull(message = "#{arr_msg['parametros.receitas.classificacaoReceita.obrigatorio']}")
    @Convert(converter = ClassificacaoReceitaEnumConverter.class)
    @Column(name = "CLASSIFICACAO_RECEITA")
    private ClassificacaoReceitaEnum classificacaoReceita;

    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "IMPRIME_DARE")
    private Boolean imprimeDare;

    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "PERMITE_TAXAS")
    private Boolean permiteTaxas;

    @NotNull(message = "#{arr_msg['parametros.receitas.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoEnum situacao;

    @Max(value = 99, message = "#{arr_msg['parametros.receitas.idBarra.maximo']}")
    @Column(name = "ID_BARRA")
    private Integer idBarra;

    @Max(value = 9999, message = "#{arr_msg['parametros.receitas.idReceitaMulta.maximo']}")
    @Column(name = "ID_RECEITA_MULTA")
    private Integer idReceitaMulta;

    @Max(value = 9999, message = "#{arr_msg['parametros.receitas.idReceitaJuros.maximo']}")
    @Column(name = "ID_RECEITA_JUROS")
    private Integer idReceitaJuros;

    @Max(value = 9999, message = "#{arr_msg['parametros.receitas.idReceitaCorrecaoMonetaria.maximo']}")
    @Column(name = "ID_RECEITA_CORRECAO_MONETARIA")
    private Integer idReceitaCorrecaoMonetaria;

    @Max(value = 9999, message = "#{arr_msg['parametros.receitas.idReceitaTaxas.maximo']}")
    @Column(name = "ID_RECEITA_TAXAS")
    private Integer idReceitaTaxas;

    @JoinColumn(name = "ID_PLANO_CONTAS", referencedColumnName = "ID_PLANOCONTAS")
    @ManyToOne
    private PlanoContas planoContas;

    @Transient
    private List<ReceitasRepasse> receitasRepasseCollection;

    @Transient
    private List<ReceitasTaxas> receitasTaxasCollection;

    public Receitas() {
        receitasRepasseCollection = new ArrayList<>();
        receitasTaxasCollection = new ArrayList<>();
    }

    @Override
    public Integer getId() {
        return idReceita;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public String getDescricaoReceita() {
        return descricaoReceita;
    }

    public void setDescricaoReceita(String descricaoReceita) {
        this.descricaoReceita = descricaoReceita;
    }

    public TipoReceitaEnum getTipoReceita() {
        return tipoReceita;
    }

    public void setTipoReceita(TipoReceitaEnum tipoReceita) {
        this.tipoReceita = tipoReceita;
    }

    public ClassificacaoReceitaEnum getClassificacaoReceita() {
        return classificacaoReceita;
    }

    public void setClassificacaoReceita(ClassificacaoReceitaEnum classificacaoReceita) {
        this.classificacaoReceita = classificacaoReceita;
    }

    public Boolean getImprimeDare() {
        return imprimeDare;
    }

    public void setImprimeDare(Boolean imprimeDare) {
        this.imprimeDare = imprimeDare;
    }

    public Boolean getPermiteTaxas() {
        return permiteTaxas;
    }

    public void setPermiteTaxas(Boolean permiteTaxas) {
        this.permiteTaxas = permiteTaxas;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Integer getIdBarra() {
        return idBarra;
    }

    public void setIdBarra(Integer idBarra) {
        this.idBarra = idBarra;
    }

    public Integer getIdReceitaMulta() {
        return idReceitaMulta;
    }

    public void setIdReceitaMulta(Integer idReceitaMulta) {
        this.idReceitaMulta = idReceitaMulta;
    }

    public Integer getIdReceitaJuros() {
        return idReceitaJuros;
    }

    public void setIdReceitaJuros(Integer idReceitaJuros) {
        this.idReceitaJuros = idReceitaJuros;
    }

    public Integer getIdReceitaCorrecaoMonetaria() {
        return idReceitaCorrecaoMonetaria;
    }

    public void setIdReceitaCorrecaoMonetaria(Integer idReceitaCorrecaoMonetaria) {
        this.idReceitaCorrecaoMonetaria = idReceitaCorrecaoMonetaria;
    }

    public Integer getIdReceitaTaxas() {
        return idReceitaTaxas;
    }

    public void setIdReceitaTaxas(Integer idReceitaTaxas) {
        this.idReceitaTaxas = idReceitaTaxas;
    }

    public PlanoContas getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(PlanoContas planoContas) {
        this.planoContas = planoContas;
    }

    public List<ReceitasRepasse> getReceitasRepasse() {
        return receitasRepasseCollection;
    }

    public void setReceitasRepasse(List<ReceitasRepasse> receitasRepasseCollection) {
        this.receitasRepasseCollection = receitasRepasseCollection;
    }

    public List<ReceitasTaxas> getReceitasTaxas() {
        return receitasTaxasCollection;
    }

    public void setReceitasTaxas(List<ReceitasTaxas> receitasTaxas) {
        this.receitasTaxasCollection = receitasTaxas;
    }

    /**
     * Adiciona uma taxa à Receita.
     * @param receitasTaxas objeto de ReceitasTaxas.
     */
    public void addTaxa(ReceitasTaxas receitasTaxas) {
        getReceitasTaxas().add(receitasTaxas);
    }

    public String getCompositeName() {
        return getIdReceita() + " - " + getDescricaoReceita();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Receitas that = (Receitas) o;
        return Objects.equals(idReceita, that.idReceita)
                && Objects.equals(descricaoReceita, that.descricaoReceita)
                && Objects.equals(tipoReceita, that.tipoReceita)
                && Objects.equals(classificacaoReceita, that.classificacaoReceita)
                && Objects.equals(imprimeDare, that.imprimeDare)
                && Objects.equals(permiteTaxas, that.permiteTaxas)
                && Objects.equals(situacao, that.situacao)
                && Objects.equals(idBarra, that.idBarra)
                && Objects.equals(idReceitaMulta, that.idReceitaMulta)
                && Objects.equals(idReceitaJuros, that.idReceitaJuros)
                && Objects.equals(idReceitaCorrecaoMonetaria, that.idReceitaCorrecaoMonetaria)
                && Objects.equals(idReceitaTaxas, that.idReceitaTaxas)
                && Objects.equals(planoContas, that.planoContas)
                && Objects.equals(receitasRepasseCollection, that.receitasRepasseCollection)
                && Objects.equals(receitasTaxasCollection, that.receitasTaxasCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReceita, descricaoReceita, tipoReceita, classificacaoReceita, imprimeDare, permiteTaxas,
                situacao, idBarra, idReceitaMulta, idReceitaJuros, idReceitaCorrecaoMonetaria, idReceitaTaxas,
                planoContas, receitasRepasseCollection, receitasTaxasCollection);
    }

    @Override
    public String toString() {
        return "Receitas{"
                + "idReceita=" + idReceita
                + ", descricaoReceita='" + descricaoReceita + '\''
                + ", tipoReceita=" + tipoReceita
                + ", classificacaoReceita=" + classificacaoReceita
                + ", imprimeDare=" + imprimeDare
                + ", permiteTaxas=" + permiteTaxas
                + ", situacao=" + situacao
                + ", idBarra=" + idBarra
                + ", idReceitaMulta=" + idReceitaMulta
                + ", idReceitaJuros=" + idReceitaJuros
                + ", idReceitaCorrecaoMonetaria=" + idReceitaCorrecaoMonetaria
                + ", idReceitaTaxas=" + idReceitaTaxas
                + ", planoContas=" + planoContas
                + ", receitasRepasseCollection=" + receitasRepasseCollection
                + ", receitasTaxasCollection=" + receitasTaxasCollection
                + '}';
    }
}
