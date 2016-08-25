package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_PAPEL_SISTEMA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_PAPEL_SISTEMA", schema = "SEFAZ_SEG")
public class PapelSistema extends AbstractEntity<Long> {

    private static final long serialVersionUID = 3619773195182690829L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_papel_sistema")
    @SequenceGenerator(name = "sq_papel_sistema", schema = "SEFAZ_SEG",
            sequenceName = "sq_papel_sistema",
            allocationSize = 1)
    @Column(name = "IDENTIFICACAO_PAPEL")
    private Long identificacaoPapel;

    @NotEmpty(message = "#{seg_msg['seg.gestao.papelSistema.nomePapelSistema.vazio']}")
    @Size(max = 30, message = "#{seg_msg['seg.gestao.papelSistema.nomePapelSistema.maximo']}")
    @Column(name = "NOME_PAPEL")
    private String nomePapel;

    @NotEmpty(message = "#{seg_msg['seg.gestao.papelSistema.descricaoPapelSistema.vazio']}")
    @Size(max = 120, message = "#{seg_msg['seg.gestao.papelSistema.descricaoPapelSistema.maximo']}")
    @Column(name = "DESCRICAO_PAPEL")
    private String descricaoPapel;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENTIFICACAO_PAPEL", referencedColumnName = "IDENTIFICACAO_PAPEL",
            insertable = false, updatable = false)
    private Set<PapelOpcao> papelOpcao;

    @OneToMany(mappedBy = "papelSistema", fetch = FetchType.LAZY)
    private Set<PerfilPapel> perfilPapel;

    @Transient
    private Long totalOpcoes;

    @Transient
    private Long vezesAtribuido;

    public PapelSistema() {
        // Construtor para inicialização por reflexão.
        papelOpcao = new HashSet<>();
    }

    public PapelSistema(Long identificacaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
    }

    public PapelSistema(Long identificacaoPapel, String nomePapel, String descricaoPapel, Long totalOpcoes,
            Long vezesAtribuido) {
        this.identificacaoPapel = identificacaoPapel;
        this.nomePapel = nomePapel;
        this.descricaoPapel = descricaoPapel;
        this.totalOpcoes = totalOpcoes;
        this.vezesAtribuido = vezesAtribuido;
    }

    @Override
    public Long getId() {
        return identificacaoPapel;
    }

    public Long getIdentificacaoPapel() {
        return identificacaoPapel;
    }

    public void setIdentificacaoPapel(Long identificacaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
    }

    public String getNomePapel() {
        return nomePapel;
    }

    public void setNomePapel(String nomePapel) {
        this.nomePapel = nomePapel;
    }

    public String getDescricaoPapel() {
        return descricaoPapel;
    }

    public void setDescricaoPapel(String descricaoPapel) {
        this.descricaoPapel = descricaoPapel;
    }

    public Set<PapelOpcao> getPapelOpcao() {
        return papelOpcao;
    }

    public void setPapelOpcao(Set<PapelOpcao> papelOpcao) {
        this.papelOpcao = papelOpcao;
    }

    public Long getTotalOpcoes() {
        return totalOpcoes;
    }

    public void setTotalOpcoes(Long totalOpcoes) {
        this.totalOpcoes = totalOpcoes;
    }

    public Long getVezesAtribuido() {
        return vezesAtribuido;
    }

    public void setVezesAtribuido(Long vezesAtribuido) {
        this.vezesAtribuido = vezesAtribuido;
    }

    public Set<PerfilPapel> getPerfilPapel() {
        return perfilPapel;
    }

    public void setPerfilPapel(Set<PerfilPapel> perfilPapel) {
        this.perfilPapel = perfilPapel;
    }

    public String getListPerfis() {
        return Objects.isNull(perfilPapel) ? "" : perfilPapel.stream()
                .map(pp -> pp.getIdentificacaoPerfil().toString())
                .collect(Collectors.joining(","));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PapelSistema that = (PapelSistema) o;
        return Objects.equals(identificacaoPapel, that.identificacaoPapel)
                && Objects.equals(nomePapel, that.nomePapel)
                && Objects.equals(descricaoPapel, that.descricaoPapel)
                && Objects.equals(totalOpcoes, that.totalOpcoes)
                && Objects.equals(vezesAtribuido, that.vezesAtribuido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoPapel, nomePapel, descricaoPapel, totalOpcoes, vezesAtribuido);
    }

    @Override
    public String toString() {
        return "PapelSistema{"
                + "identificacaoPapel=" + identificacaoPapel
                + ", nomePapel='" + nomePapel + '\''
                + ", descricaoPapel='" + descricaoPapel + '\''
                + '}';
    }
}
