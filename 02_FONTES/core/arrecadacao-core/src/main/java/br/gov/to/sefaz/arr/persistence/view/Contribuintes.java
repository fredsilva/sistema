package br.gov.to.sefaz.arr.persistence.view;

import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * View referente a SEFAZ_ARR.VW_CONTRIBUINTES da base de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 14:49:42
 */
@Entity
@Table(name = "VW_CONTRIBUINTES", schema = "SEFAZ_ARR")
@IdClass(ContribuintesPK.class)
public class Contribuintes implements Serializable {

    private static final long serialVersionUID = -5230022378562728655L;

    @Id
    @Column(name = "TIPO_CONTRIBUINTE")
    private Integer tipoContribuinte;

    @Id
    @Column(name = "TIPO_PESSOA")
    private Integer tipoPessoa;

    @Id
    @Column(name = "ID_PESSOA")
    private Long idPessoa;

    @Column(name = "RAZAO_SOCIAL_NOME")
    private String razaoSocialNome;

    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoEnum situacao;

    @Column(name = "RENAVAM")
    private Long renavam;

    public ContribuintesPK getId() {
        return new ContribuintesPK(tipoContribuinte, tipoPessoa, idPessoa);
    }

    public Integer getTipoContribuinte() {
        return tipoContribuinte;
    }

    public void setTipoContribuinte(Integer tipoContribuinte) {
        this.tipoContribuinte = tipoContribuinte;
    }

    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getRazaoSocialNome() {
        return razaoSocialNome;
    }

    public void setRazaoSocialNome(String razaoSocialNome) {
        this.razaoSocialNome = razaoSocialNome;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Long getRenavam() {
        return renavam;
    }

    public void setRenavam(Long renavam) {
        this.renavam = renavam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contribuintes that = (Contribuintes) o;
        return Objects.equals(tipoContribuinte, that.tipoContribuinte)
                && Objects.equals(tipoPessoa, that.tipoPessoa)
                && Objects.equals(idPessoa, that.idPessoa)
                && Objects.equals(razaoSocialNome, that.razaoSocialNome)
                && Objects.equals(situacao, that.situacao)
                && Objects.equals(renavam, that.renavam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoContribuinte, tipoPessoa, idPessoa, razaoSocialNome, situacao, renavam);
    }

    @Override
    public String toString() {
        return "Contribuintes{"
                + "tipoContribuinte=" + tipoContribuinte
                + ", tipoPessoa=" + tipoPessoa
                + ", idPessoa=" + idPessoa
                + ", razaoSocialNome='" + razaoSocialNome + '\''
                + ", situacao=" + situacao
                + ", renavam=" + renavam
                + '}';
    }
}
