package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_POSTO_TRABALHO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_POSTO_TRABALHO", schema = "SEFAZ_SEG")

public class PostoTrabalho extends AbstractEntity<Integer> {

    private static final long serialVersionUID = -2743122709932667159L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_posto_trabalho")
    @SequenceGenerator(name = "sq_posto_trabalho", schema = "SEFAZ_SEG",
            sequenceName = "sq_posto_trabalho",
            allocationSize = 1)
    @Column(name = "IDENTIFICACAO_POSTO_TRABALHO")
    private Integer identificacaoPostoTrabalho;

    @Size(max = 100, message = "#{seg_msg['seg.gestao.PostoTrabalho.nomePostoTrabalho.tamanho']}")
    @NotEmpty(message = "#{seg_msg['seg.gestao.PostoTrabalho.nomePostoTrabalho.obrigatorio']}")
    @Column(name = "NOME_POSTO_TRABALHO")
    private String nomePostoTrabalho;

    @NotNull(message = "#{seg_msg['seg.gestao.PostoTrabalho.identificacaoUnidOrganizac.obrigatorio']}")
    @Column(name = "IDENTIFICACAO_UNID_ORGANIZAC")
    private Long identificacaoUnidOrganizac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENTIFICACAO_UNID_ORGANIZAC", referencedColumnName = "IDENTIFICACAO_UNID_ORGANIZAC",
            insertable = false, updatable = false)
    private UnidadeOrganizacional unidadeOrganizacional;

    public PostoTrabalho() {
        // Construtor para inicialização por reflexão.
    }

    public PostoTrabalho(Integer identificacaoPostoTrabalho, String nomePostoTrabalho, Long
            identificacaoUnidOrganizac) {
        this.identificacaoPostoTrabalho = identificacaoPostoTrabalho;
        this.nomePostoTrabalho = nomePostoTrabalho;
        this.identificacaoUnidOrganizac = identificacaoUnidOrganizac;
    }

    @Override
    public Integer getId() {
        return identificacaoPostoTrabalho;
    }

    public Integer getIdentificacaoPostoTrabalho() {
        return identificacaoPostoTrabalho;
    }

    public void setIdentificacaoPostoTrabalho(Integer identificacaoPostoTrabalho) {
        this.identificacaoPostoTrabalho = identificacaoPostoTrabalho;
    }

    public String getNomePostoTrabalho() {
        return nomePostoTrabalho;
    }

    public void setNomePostoTrabalho(String nomePostoTrabalho) {
        this.nomePostoTrabalho = nomePostoTrabalho;
    }

    public UnidadeOrganizacional getUnidOrganizac() {
        return unidadeOrganizacional;
    }

    public Long getIdentificacaoUnidOrganizac() {
        return identificacaoUnidOrganizac;
    }

    public void setIdentificacaoUnidOrganizac(Long identificacaoUnidOrganizac) {
        this.identificacaoUnidOrganizac = identificacaoUnidOrganizac;
    }

    public UnidadeOrganizacional getUnidadeOrganizacional() {
        return unidadeOrganizacional;
    }

    public String getNomeUnidOrganizac() {
        return Objects.isNull(unidadeOrganizacional) ? "" : unidadeOrganizacional.getNomeUnidOrganizac() ;
    }

    public void setUnidadeOrganizacional(UnidadeOrganizacional unidadeOrganizacional) {
        this.unidadeOrganizacional = unidadeOrganizacional;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoPostoTrabalho, nomePostoTrabalho, unidadeOrganizacional);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PostoTrabalho that = (PostoTrabalho) obj;
        return Objects.equals(this.identificacaoPostoTrabalho, that.identificacaoPostoTrabalho)
                && Objects.equals(this.nomePostoTrabalho, that.nomePostoTrabalho)
                && Objects.equals(this.unidadeOrganizacional, that.unidadeOrganizacional);
    }

    @Override
    public String toString() {
        return "PostoTrabalho [identificacaoPostoTrabalho=" + identificacaoPostoTrabalho + ", nomePostoTrabalho="
                + nomePostoTrabalho + ", unidadeOrganizacional=" + unidadeOrganizacional + "]";
    }

}
