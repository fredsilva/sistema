package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.hibernate.validator.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_RECEITAS_TAXAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 09:57:46
 */
@Entity
@Table(name = "TA_RECEITAS_TAXAS", schema = "SEFAZ_ARR")
@IdClass(ReceitasTaxasPK.class)
public class ReceitasTaxas extends AbstractEntity<ReceitasTaxasPK> {

    private static final long serialVersionUID = -8811512315852988096L;

    @Id
    @SequenceGenerator(name = "SQ_RECEITAS_TAXAS", schema = "SEFAZ_ARR", sequenceName = "SQ_RECEITAS",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RECEITAS_TAXAS")
    @Column(name = "ID_SUBCODIGO")
    private Integer idSubcodigo;

    @Id
    @NotNull(message = "#{arr_msg['parametros.receitasTaxas.idReceita.obrigatorio']}")
    @Max(value = 9999, message = "#{arr_msg['parametros.receitasTaxas.idReceita.maximo']}")
    @Column(name = "ID_RECEITA")
    private Integer idReceita;

    @NotEmpty(message = "#{arr_msg['parametros.receitasTaxas.subcodigo.obrigatorio']}")
    @Size(max = 4, message = "#{arr_msg['parametros.receitasTaxas.subcodigo.maximo']}")
    @Column(name = "SUBCODIGO")
    private String subcodigo;

    @NotEmpty(message = "#{arr_msg['parametros.receitasTaxas.descricao.obrigatorio']}")
    @Size(max = 200, message = "#{arr_msg['parametros.receitasTaxas.descricao.maximo']}")
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotEmpty(message = "#{arr_msg['parametros.receitasTaxas.unidade.obrigatorio']}")
    @Size(max = 10, message = "#{arr_msg['parametros.receitasTaxas.unidade.maximo']}")
    @Column(name = "UNIDADE")
    private String unidade;

    @NotNull(message = "#{arr_msg['parametros.receitasTaxas.valorUnitario.obrigatorio']}")
    @Digits(integer = 6, fraction = 2, message = "#{arr_msg['parametros.receitasTaxas.valorUnitario.dígitos']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['parametros.receitasTaxas.valorUnitario.minimo']}")
    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;

    @Digits(integer = 6, fraction = 2, message = "#{arr_msg['parametros.conveniosTarifa.valor.digitos']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['parametros.receitasTaxas.valorLimite.minimo']}")
    @Column(name = "VALOR_LIMITE")
    private BigDecimal valorLimite;

    @Digits(integer = 6, fraction = 2, message = "#{arr_msg['parametros.conveniosTarifa.valor.digitos']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['parametros.receitasTaxas.valorAcrescimo.minimo']}")
    @Column(name = "VALOR_ACRESCIMO")
    private BigDecimal valorAcrescimo;

    @NotNull(message = "#{arr_msg['parametros.receitasTaxas.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoEnum situacao;

    @JoinColumn(name = "ID_RECEITA", referencedColumnName = "ID_RECEITA", insertable = false, updatable = false)
    @ManyToOne
    private Receitas receitas;

    @Override
    public ReceitasTaxasPK getId() {
        return new ReceitasTaxasPK();
    }

    public Integer getIdSubcodigo() {
        return idSubcodigo;
    }

    public void setIdSubcodigo(Integer idSubcodigo) {
        this.idSubcodigo = idSubcodigo;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Integer idReceita) {
        this.idReceita = idReceita;
    }

    public String getSubcodigo() {
        return subcodigo;
    }

    public void setSubcodigo(String subcodigo) {
        this.subcodigo = subcodigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorLimite() {
        return valorLimite;
    }

    public void setValorLimite(BigDecimal valorLimite) {
        this.valorLimite = valorLimite;
    }

    public BigDecimal getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(BigDecimal valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Receitas getReceitas() {
        return receitas;
    }

    public void setReceitas(Receitas receitas) {
        this.receitas = receitas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReceitasTaxas that = (ReceitasTaxas) o;
        return Objects.equals(idSubcodigo, that.idSubcodigo)
                && Objects.equals(idReceita, that.idReceita)
                && Objects.equals(subcodigo, that.subcodigo)
                && Objects.equals(descricao, that.descricao)
                && Objects.equals(unidade, that.unidade)
                && Objects.equals(valorUnitario, that.valorUnitario)
                && Objects.equals(valorLimite, that.valorLimite)
                && Objects.equals(valorAcrescimo, that.valorAcrescimo)
                && Objects.equals(situacao, that.situacao)
                && Objects.equals(receitas, that.receitas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubcodigo, idReceita, subcodigo, descricao, unidade, valorUnitario, valorLimite,
                valorAcrescimo, situacao, receitas);
    }

    @Override
    public String toString() {
        return "ReceitasTaxasService{"
                + "idSubcodigo=" + idSubcodigo
                + ", idReceita=" + idReceita
                + ", subcodigo='" + subcodigo + '\''
                + ", descricao='" + descricao + '\''
                + ", unidade='" + unidade + '\''
                + ", valorUnitario=" + valorUnitario
                + ", valorLimite=" + valorLimite
                + ", valorAcrescimo=" + valorAcrescimo
                + ", situacao=" + situacao
                + ", receitas=" + receitas
                + '}';
    }
}
