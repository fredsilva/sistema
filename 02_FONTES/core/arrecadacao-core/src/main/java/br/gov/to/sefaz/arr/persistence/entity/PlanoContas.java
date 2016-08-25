package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.TipoContaEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.TipoContaEnum;
import br.gov.to.sefaz.persistence.converter.OneOrTwoBooleanConverter;
import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_PLANO_CONTAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_PLANO_CONTAS", schema = "SEFAZ_ARR")
public class PlanoContas extends AbstractEntity<Long> {

    private static final long serialVersionUID = -2793378430460417239L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_plano_contas")
    @SequenceGenerator(name = "sq_plano_contas", schema = "SEFAZ_ARR", sequenceName = "sq_plano_contas",
            allocationSize = 1)
    @Max(value = 9999999999L, message = "#{arr_msg['parametros.planoContas.idPlanocontas.maximo']}")
    @Min(value = 1, message = "#{arr_msg['parametros.planoContas.idPlanocontas.minimo']}")
    @Column(name = "ID_PLANOCONTAS", nullable = false)
    private Long idPlanocontas;

    @NotEmpty(message = "#{arr_msg['parametros.planoContas.codigoPlanoContas.obrigatorio']}")
    @Size(max = 20, message = "#{arr_msg['parametros.planoContas.codigoPlanoContas.maximo']}")
    @Pattern(regexp = "^[a-zA-Z0-9](\\.[a-zA-Z0-9])*$",
            message = "#{arr_msg['parametros.planoContas.codigoPlanoContas.formato']}")
    @Column(name = "CODIGO_PLANO_CONTAS", nullable = false, length = 20)
    private String codigoPlanoContas;

    @NotEmpty(message = "#{arr_msg['parametros.planoContas.nomeConta.obrigatorio']}")
    @Size(max = 150, message = "#{arr_msg['parametros.planoContas.nomeConta.maximo']}")
    @Column(name = "NOME_CONTA", nullable = false, length = 150)
    private String nomeConta;

    @Size(max = 20, message = "#{arr_msg['parametros.planoContas.contaHierarquica.maximo']}")
    @Pattern(regexp = "^([a-zA-Z0-9](\\.[a-zA-Z0-9])*|)$",
            message = "#{arr_msg['parametros.planoContas.contaHierarquica.formato']}")
    @Column(name = "CONTA_HIERARQUICA", length = 20)
    private String contaHierarquica;

    @NotEmpty(message = "#{arr_msg['parametros.planoContas.codigoContabil.obrigatorio']}")
    @Size(max = 20, message = "#{arr_msg['parametros.planoContas.codigoContabil.maximo']}")
    @Column(name = "CODIGO_CONTABIL", nullable = false, length = 20)
    private String codigoContabil;

    @NotNull(message = "#{arr_msg['parametros.planoContas.tipoConta.obrigatorio']}")
    @Convert(converter = TipoContaEnumConverter.class)
    @Column(name = "TIPO_CONTA", nullable = false)
    private TipoContaEnum tipoConta;

    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "RATEIO", nullable = false)
    private Boolean rateio = Boolean.FALSE;

    @NotNull(message = "#{arr_msg['parametros.planoContas.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    @NotNull(message = "#{arr_msg['parametros.planoContas.gruposCnae.obrigatorio']}")
    @JoinColumn(name = "ID_GRUPO_CNAE", referencedColumnName = "ID_GRUPO_CNAE")
    @ManyToOne
    private TipoGruposCnaes gruposCnaes;

    public PlanoContas() {
        gruposCnaes = new TipoGruposCnaes();
    }

    public PlanoContas(Long idPlanocontas, String codigoPlanoContas, String nomeConta, String contaHierarquica,
            String codigoContabil, TipoContaEnum tipoConta, SituacaoEnum situacao, TipoGruposCnaes gruposCnaes) {
        this.idPlanocontas = idPlanocontas;
        this.codigoPlanoContas = codigoPlanoContas;
        this.nomeConta = nomeConta;
        this.contaHierarquica = contaHierarquica;
        this.codigoContabil = codigoContabil;
        this.tipoConta = tipoConta;
        this.situacao = situacao;
        this.gruposCnaes = gruposCnaes;
    }

    public Long getIdPlanocontas() {
        return idPlanocontas;
    }

    @Override
    public Long getId() {
        return getIdPlanocontas();
    }

    public void setIdPlanocontas(Long idPlanocontas) {
        this.idPlanocontas = idPlanocontas;
    }

    public String getCodigoPlanoContas() {
        return codigoPlanoContas;
    }

    public void setCodigoPlanoContas(String codigoPlanoContas) {
        this.codigoPlanoContas = codigoPlanoContas;
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

    public Boolean getRateio() {
        return rateio;
    }

    public void setRateio(Boolean rateio) {
        this.rateio = rateio;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public TipoGruposCnaes getGruposCnaes() {
        return gruposCnaes;
    }

    public void setGruposCnaes(TipoGruposCnaes gruposCnaes) {
        this.gruposCnaes = gruposCnaes;
    }

    public Integer getIdGruposCnaes() {
        return gruposCnaes.getId();
    }

    /**
     * Seta o ID do GrupoCNAE.
     * @param idGrupoCnae identificação nova do GrupoCnae.
     */
    public void setIdGruposCnaes(Integer idGrupoCnae) {
        gruposCnaes.setIdGrupoCnae(idGrupoCnae);
    }

    public String getCompositeName() {
        return getCodigoPlanoContas() + " - " + getNomeConta();
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
        return Objects.equals(idPlanocontas, that.idPlanocontas)
                && Objects.equals(codigoPlanoContas, that.codigoPlanoContas)
                && Objects.equals(nomeConta, that.nomeConta) && Objects.equals(contaHierarquica, that.contaHierarquica)
                && Objects.equals(codigoContabil, that.codigoContabil) && tipoConta == that.tipoConta
                && rateio == that.rateio && situacao == that.situacao && Objects.equals(gruposCnaes, that.gruposCnaes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlanocontas, codigoPlanoContas, nomeConta, contaHierarquica, codigoContabil, tipoConta,
                rateio, situacao, gruposCnaes);
    }

    @Override
    public String toString() {
        return "PlanoContas{" + "idPlanocontas=" + idPlanocontas + ", codigoPlanoContas='" + codigoPlanoContas + '\''
                + ", nomeConta='" + nomeConta + '\'' + ", contaHierarquica='" + contaHierarquica + '\''
                + ", codigoContabil='" + codigoContabil + '\'' + ", tipoConta=" + tipoConta + ", rateio=" + rateio
                + ", situacao=" + situacao + ", gruposCnaes=" + gruposCnaes + '}';
    }
}
