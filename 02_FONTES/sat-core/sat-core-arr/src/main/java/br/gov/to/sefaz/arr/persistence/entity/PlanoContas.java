package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.enums.RateioEnum;
import br.gov.to.sefaz.arr.persistence.enums.RateioEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.arr.persistence.enums.SituacaoEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.TipoContaEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoContaEnumConverter;
import br.gov.to.sefaz.common.model.SerializableEntity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade dos Planos de Contas.
 *
 * @author roger.gouveia
 */
@Entity
@Table(name = "ta_plano_contas")
public class PlanoContas implements SerializableEntity<String> {
    @Id
    @Column(name = "id_planocontas")
    @NotEmpty(message = "O campo Plano Contas é obrigatório e não foi informado!")
    @Size(max = 20, message = "O campo Plano Contas deve possuir no máximo 20 caracteres!")
    private String idPlanocontas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_grupo_cnae")
    @NotNull(message = "O campo Grupo Cnae é obrigatório e não foi informado!")
    @Max(value = 99, message = "O campo Grupo Cnae deve possuir no máximo 8 dígitos!")
    @Min(value = 1, message = "O campo Grupo Cnae deve ser maior do que 0!")
    private TipoGruposCnae idGrupoCnae;

    @Column(name = "nome_conta")
    @NotEmpty(message = "O campo Descrição do Plano é obrigatório e não foi informado!")
    @Size(max = 150, message = "O campo Descrição do Plano deve possuir no máximo 150 caracteres!")
    private String nomeConta;

    @Column(name = "conta_hierarquica")
    @Size(max = 20, message = "O campo Conta Hierárquica deve possuir no máximo 20 caracteres!")
    private String contaHierarquica;

    @Column(name = "codigo_contabil")
    @Size(max = 20, message = "O campo Código Contábil deve possuir no máximo 20 caracteres!")
    private String codigoContabil;

    @Column(name = "tipo_conta")
    @NotNull(message = "O campo Tipo de Conta é obrigatório e não foi informado!")
    @Range(min = 1, max = 2, message = "O campo Tipo de Conta deve ser Analítico(1) ou Sintético(2)!")
    @Convert(converter = TipoContaEnumConverter.class)
    private TipoContaEnum tipoConta;

    @Column(name = "rateio")
    @Range(min = 1, max = 2, message = "O campo Rateio deve ser Sim(1) ou Não(2)!")
    @Convert(converter = RateioEnumConverter.class)
    private RateioEnum rateio;

    @Column(name = "situacao")
    @NotNull(message = "O campo Situação é obrigatório e deve ser informado!")
    @Range(min = 1, max = 2, message = "O campo Situação deve ser Ativo(1) ou Cancelado(2)!")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    public PlanoContas() {
        // Construtor para inicialização por reflexão.
    }

    public PlanoContas(String idPlanocontas, TipoGruposCnae idGrupoCnae, String nomeConta, String contaHierarquica,
            String codigoContabil, TipoContaEnum tipoConta, RateioEnum rateio, SituacaoEnum situacao) {
        this.idPlanocontas = idPlanocontas;
        this.idGrupoCnae = idGrupoCnae;
        this.nomeConta = nomeConta;
        this.contaHierarquica = contaHierarquica;
        this.codigoContabil = codigoContabil;
        this.tipoConta = tipoConta;
        this.rateio = rateio;
        this.situacao = situacao;
    }

    @Override
    public String getId() {
        return idPlanocontas;
    }

    public String getIdPlanocontas() {
        return idPlanocontas;
    }

    public void setIdPlanocontas(String idPlanocontas) {
        this.idPlanocontas = idPlanocontas;
    }

    public TipoGruposCnae getIdGrupoCnae() {
        return idGrupoCnae;
    }

    public void setIdGrupoCnae(TipoGruposCnae idGrupoCnae) {
        this.idGrupoCnae = idGrupoCnae;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public String getContaHierarquica() {
        return contaHierarquica;
    }

    public void setContaHierarquica(String contaHierarquica) {
        this.contaHierarquica = contaHierarquica;
    }

    public String getCodigoContabil() {
        return codigoContabil;
    }

    public void setCodigoContabil(String codigoContabil) {
        this.codigoContabil = codigoContabil;
    }

    public TipoContaEnum getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoContaEnum tipoConta) {
        this.tipoConta = tipoConta;
    }

    public RateioEnum getRateio() {
        return rateio;
    }

    public void setRateio(RateioEnum rateio) {
        this.rateio = rateio;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlanoContas that = (PlanoContas) obj;
        return idPlanocontas == that.idPlanocontas && situacao == that.situacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlanocontas, idGrupoCnae, situacao);
    }

    @Override
    public String toString() {
        return "Bancos{" + "idPlanocontas=" + idPlanocontas + ", nomeConta='" + nomeConta + "', situacao=" + situacao
                + '}';
    }
}
