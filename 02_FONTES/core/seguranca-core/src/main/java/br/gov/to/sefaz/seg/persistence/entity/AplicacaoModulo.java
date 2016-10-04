package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_APLICACAO_MODULO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_APLICACAO_MODULO", schema = "SEFAZ_SEG")
public class AplicacaoModulo extends AbstractEntity<Long> {

    private static final long serialVersionUID = 2262759749890737090L;

    @Id
    @NotNull(message = "#{seg_msg['seg.gestao.aplicacaoModulo.identificacaoAplicacaoModulo.obrigatorio']}")
    @Column(name = "IDENTIFICACAO_APLICACAO_MODULO")
    private Long identificacaoAplicacaoModulo;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESCRICAO_APLICACAO_MODULO")
    private String descricaoAplicacaoModulo;

    @NotNull
    @Column(name = "IDENTIFICACAO_MODULO_SISTEMA")
    private Long identificacaoModuloSistema;

    @OneToMany(mappedBy = "aplicacaoModulo")
    private Set<OpcaoAplicacao> opcoesAplicacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENTIFICACAO_MODULO_SISTEMA", referencedColumnName = "IDENTIFICACAO_MODULO_SISTEMA",
            insertable = false, updatable = false)
    private ModuloSistema moduloSistema;

    public AplicacaoModulo() {
        // Construtor para inicialização por reflexão.
        moduloSistema = new ModuloSistema();
    }

    public AplicacaoModulo(Long identificacaoAplicacaoModulo, String descricaoAplicacaoModulo,
            Long identificacaoModuloSistema) {
        this();
        this.identificacaoAplicacaoModulo = identificacaoAplicacaoModulo;
        this.descricaoAplicacaoModulo = descricaoAplicacaoModulo;
        this.identificacaoModuloSistema = identificacaoModuloSistema;
    }

    @Override
    public Long getId() {
        return identificacaoAplicacaoModulo;
    }

    public Long getIdentificacaoAplicacaoModulo() {
        return identificacaoAplicacaoModulo;
    }

    public void setIdentificacaoAplicacaoModulo(Long identificacaoAplicacaoModulo) {
        this.identificacaoAplicacaoModulo = identificacaoAplicacaoModulo;
    }

    public String getDescricaoAplicacaoModulo() {
        return descricaoAplicacaoModulo;
    }

    public void setDescricaoAplicacaoModulo(String descricaoAplicacaoModulo) {
        this.descricaoAplicacaoModulo = descricaoAplicacaoModulo;
    }

    public Long getIdentificacaoModuloSistema() {
        return identificacaoModuloSistema;
    }

    public void setIdentificacaoModuloSistema(Long identificacaoModuloSistema) {
        this.identificacaoModuloSistema = identificacaoModuloSistema;
    }

    public Set<OpcaoAplicacao> getOpcoesAplicacao() {
        return opcoesAplicacao;
    }

    public void setOpcoesAplicacao(Set<OpcaoAplicacao> opcoesAplicacao) {
        this.opcoesAplicacao = opcoesAplicacao;
    }

    public ModuloSistema getModuloSistema() {
        return moduloSistema;
    }

    public void setModuloSistema(ModuloSistema moduloSistema) {
        this.moduloSistema = moduloSistema;
    }

    public String getAbreviacaoSistema() {
        return Objects.isNull(moduloSistema) ? "" : moduloSistema.getAbreviacaoModulo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AplicacaoModulo that = (AplicacaoModulo) o;
        return Objects.equals(identificacaoAplicacaoModulo, that.identificacaoAplicacaoModulo)
                && Objects.equals(descricaoAplicacaoModulo, that.descricaoAplicacaoModulo)
                && Objects.equals(identificacaoModuloSistema, that.identificacaoModuloSistema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoAplicacaoModulo, descricaoAplicacaoModulo, identificacaoModuloSistema);
    }

    @Override
    public String toString() {
        return "AplicacaoModulo{"
                + "identificacaoAplicacaoModulo=" + identificacaoAplicacaoModulo
                + ", descricaoAplicacaoModulo='" + descricaoAplicacaoModulo + '\''
                + ", identificacaoModuloSistema=" + identificacaoModuloSistema
                + ", opcoesAplicacao=" + opcoesAplicacao
                + '}';
    }
}
