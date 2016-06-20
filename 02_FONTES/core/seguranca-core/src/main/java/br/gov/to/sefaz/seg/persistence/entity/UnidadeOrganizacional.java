package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_UNIDADE_ORGANIZACIONAL do Banco de Dados.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:32:00
 */
@Entity
@Table(name = "TA_UNIDADE_ORGANIZACIONAL", schema = "SEFAZ_SEG")
public class UnidadeOrganizacional extends AbstractEntity<Long> {

    private static final long serialVersionUID = -4868598745088678257L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_unidade_organizacional")
    @SequenceGenerator(name = "sq_unidade_organizacional", schema = "SEFAZ_SEG",
            sequenceName = "sq_unidade_organizacional",
            allocationSize = 1)
    @Max(value = 9999999999L,
            message = "#{seg_msg['seg.gestao.unidadeOrgazinacional.identificacaoUnidOrganizac.maximo']}")
    @Column(name = "IDENTIFICACAO_UNID_ORGANIZAC")
    private Long identificacaoUnidOrganizac;

    @NotEmpty(message = "#{seg_msg['seg.gestao.unidadeOrgazinacional.nomeUnidOrganizac.obrigatorio']}")
    @NotNull(message = "#{seg_msg['seg.gestao.unidadeOrgazinacional.nomeUnidOrganizac.obrigatorio']}")
    @Size(max = 100, message = "#{seg_msg['seg.gestao.unidadeOrgazinacional.nomeUnidOrganizac.tamanho']}")
    @Column(name = "NOME_UNID_ORGANIZAC")
    private String nomeUnidOrganizac;

    @Max(value = 9999999999L,
            message = "#{seg_msg['seg.gestao.unidadeOrgazinacional.identificacaoUnidOrganizac.maximo']}")
    @Column(name = "UNID_ORGANIZAC_PAI")
    private Long unidOrganizacPai;

    @ManyToOne
    @JoinColumn(name = "UNID_ORGANIZAC_PAI", referencedColumnName = "IDENTIFICACAO_UNID_ORGANIZAC",
            insertable = false, updatable = false)
    private UnidadeOrganizacional unidadeOrganizacionalPai;

    @Size(max = 30, message = "#{seg_msg['seg.gestao.unidadeOrgazinacional.telefone.tamanho']}")
    @Column(name = "TELEFONE")
    private String telefone;

    @Size(max = 100, message = "#{seg_msg['seg.gestao.unidadeOrgazinacional.endereco.tamanho']}")
    @Column(name = "ENDERECO")
    private String endereco;

    @Size(max = 60, message = "#{seg_msg['seg.gestao.unidadeOrgazinacional.chefeGeral.tamanho']}")
    @Column(name = "CHEFE_GERAL")
    private String chefeGeral;

    public UnidadeOrganizacional() {
        // Construtor para inicialização por reflexão.
    }

    public UnidadeOrganizacional(
            Long identificacaoUnidOrganizac, String nomeUnidOrganizac, Long unidOrganizacPai,
            String telefone, String endereco, String chefeGeral) {
        this.identificacaoUnidOrganizac = identificacaoUnidOrganizac;
        this.nomeUnidOrganizac = nomeUnidOrganizac;
        this.unidOrganizacPai = unidOrganizacPai;
        this.telefone = telefone;
        this.endereco = endereco;
        this.chefeGeral = chefeGeral;
    }

    @Override
    public Long getId() {
        return identificacaoUnidOrganizac;
    }

    public Long getIdentificacaoUnidOrganizac() {
        return identificacaoUnidOrganizac;
    }

    public void setIdentificacaoUnidOrganizac(Long identificacaoUnidOrganizac) {
        this.identificacaoUnidOrganizac = identificacaoUnidOrganizac;
    }

    public String getNomeUnidOrganizac() {
        return nomeUnidOrganizac;
    }

    public void setNomeUnidOrganizac(String nomeUnidOrganizac) {
        this.nomeUnidOrganizac = nomeUnidOrganizac;
    }

    public Long getUnidOrganizacPai() {
        return unidOrganizacPai;
    }

    public void setUnidOrganizacPai(Long unidOrganizacPai) {
        this.unidOrganizacPai = unidOrganizacPai;
    }

    public String getNomeUnidOrganizacPai() {
        return Objects.isNull(unidadeOrganizacionalPai) ? "" : unidadeOrganizacionalPai.getNomeUnidOrganizac();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getChefeGeral() {
        return chefeGeral;
    }

    public void setChefeGeral(String chefeGeral) {
        this.chefeGeral = chefeGeral;
    }

    public UnidadeOrganizacional getUnidadeOrganizacionalPai() {
        return unidadeOrganizacionalPai;
    }

    public void setUnidadeOrganizacionalPai(UnidadeOrganizacional unidadeOrganizacionalPai) {
        this.unidadeOrganizacionalPai = unidadeOrganizacionalPai;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoUnidOrganizac, nomeUnidOrganizac, unidOrganizacPai, telefone, endereco,
                chefeGeral);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnidadeOrganizacional that = (UnidadeOrganizacional) obj;
        return Objects.equals(this.identificacaoUnidOrganizac, that.identificacaoUnidOrganizac)
                && Objects.equals(this.nomeUnidOrganizac, that.nomeUnidOrganizac)
                && Objects.equals(this.unidOrganizacPai, that.unidOrganizacPai)
                && Objects.equals(this.telefone, that.telefone) && Objects.equals(this.endereco, that.endereco)
                && Objects.equals(this.chefeGeral, that.chefeGeral);
    }

    @Override
    public String toString() {
        return "UnidadeOrganizacional [identificacaoUnidOrganizac=" + identificacaoUnidOrganizac
                + ", nomeUnidOrganizac=" + nomeUnidOrganizac + ", unidOrganizacPai=" + unidOrganizacPai + ", telefone="
                + telefone + ", endereco=" + endereco + ", chefeGeral=" + chefeGeral + "]";
    }

}
