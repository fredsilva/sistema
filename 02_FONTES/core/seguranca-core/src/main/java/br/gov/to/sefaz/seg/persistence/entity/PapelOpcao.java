package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_PAPEL_OPCAO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_PAPEL_OPCAO", schema = "SEFAZ_SEG")
@IdClass(value = PapelOpcaoPK.class)
public class PapelOpcao extends AbstractEntity<PapelOpcaoPK> {

    private static final long serialVersionUID = 766325366418265099L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_OPCAO_APLICACAO")
    private Long identificacaoOpcaoAplicacao;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_PAPEL")
    private Long identificacaoPapel;

    @ManyToOne
    @JoinColumn(name = "IDENTIFICACAO_OPCAO_APLICACAO", referencedColumnName = "IDENTIFICACAO_OPCAO_APLICACAO",
            insertable = false, updatable = false)
    private OpcaoAplicacao opcaoAplicacao;

    public PapelOpcao() {
        // Construtor para inicialização por reflexão.
    }

    public PapelOpcao(Long identificacaoOpcaoAplicacao, Long identificacaoPapel) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
        this.identificacaoPapel = identificacaoPapel;
    }

    @Override
    public PapelOpcaoPK getId() {
        return new PapelOpcaoPK(identificacaoOpcaoAplicacao, identificacaoPapel);
    }

    public OpcaoAplicacao getOpcaoAplicacao() {
        return opcaoAplicacao;
    }

    public void setOpcaoAplicacao(OpcaoAplicacao taOpcaoAplicacao) {
        this.opcaoAplicacao = taOpcaoAplicacao;
    }

    public Long getIdentificacaoOpcaoAplicacao() {
        return identificacaoOpcaoAplicacao;
    }

    public void setIdentificacaoOpcaoAplicacao(Long identificacaoOpcaoAplicacao) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
    }

    public void setIdentificacaoPapel(Long identificacaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
    }

    public Long getIdentificacaoPapel() {
        return identificacaoPapel;
    }

    public String getDescricaoAplicacao() {
        return opcaoAplicacao.getDescricaoAplicacao();
    }

    public String getAbreviacaoModulo() {
        return opcaoAplicacao.getAbreviacaoModulo();
    }

    public String getDescricaoOpcao() {
        return opcaoAplicacao.getDescripcaoOpcao();
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoOpcaoAplicacao, identificacaoPapel);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PapelOpcao that = (PapelOpcao) obj;
        return Objects.equals(this.identificacaoOpcaoAplicacao, that.identificacaoOpcaoAplicacao)
                && Objects.equals(this.identificacaoPapel, that.identificacaoPapel);
    }

    @Override
    public String toString() {
        return "PapelOpcao [identificacaoOpcaoAplicacao=" + identificacaoOpcaoAplicacao + ", identificacaoPapel="
                + identificacaoPapel + "]";
    }

}
