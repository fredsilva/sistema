package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_PROCURACAO_OPCAO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_PROCURACAO_OPCAO", schema = "SEFAZ_SEG")
public class ProcuracaoOpcao extends AbstractEntity<Long> {

    private static final long serialVersionUID = 5199782231950473498L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_PROCURADO_OPCAO")
    private Long identificacaoProcuradoOpcao;

    @JoinColumn(name = "IDENTIFICACAO_OPCAO_APLICACAO", referencedColumnName = "IDENTIFICACAO_OPCAO_APLICACAO")
    @ManyToOne(optional = false)
    private OpcaoAplicacao opcaoAplicacao;

    @JoinColumn(name = "IDENTIFICACAO_PROCUR_USUARIO", referencedColumnName = "IDENTIFICACAO_PROCUR_USUARIO")
    @ManyToOne(optional = false)
    private ProcuracaoUsuario procuracaoUsuario;

    public ProcuracaoOpcao() {
        // Construtor para inicialização por reflexão.
    }

    public ProcuracaoOpcao(Long identificacaoProcuradoOpcao, OpcaoAplicacao opcaoAplicacao,
            ProcuracaoUsuario procuracaoUsuario) {
        this.identificacaoProcuradoOpcao = identificacaoProcuradoOpcao;
        this.opcaoAplicacao = opcaoAplicacao;
        this.procuracaoUsuario = procuracaoUsuario;
    }

    @Override
    public Long getId() {
        return identificacaoProcuradoOpcao;
    }

    public Long getIdentificacaoProcuradoOpcao() {
        return identificacaoProcuradoOpcao;
    }

    public void setIdentificacaoProcuradoOpcao(Long identificacaoProcuradoOpcao) {
        this.identificacaoProcuradoOpcao = identificacaoProcuradoOpcao;
    }

    public OpcaoAplicacao getOpcaoAplicacao() {
        return opcaoAplicacao;
    }

    public void setOpcaoAplicacao(OpcaoAplicacao opcaoAplicacao) {
        this.opcaoAplicacao = opcaoAplicacao;
    }

    public ProcuracaoUsuario getProcuracaoUsuario() {
        return procuracaoUsuario;
    }

    public void setProcuracaoUsuario(ProcuracaoUsuario procuracaoUsuario) {
        this.procuracaoUsuario = procuracaoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoProcuradoOpcao, opcaoAplicacao, procuracaoUsuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProcuracaoOpcao that = (ProcuracaoOpcao) obj;
        return Objects.equals(this.identificacaoProcuradoOpcao, that.identificacaoProcuradoOpcao)
                && Objects.equals(this.opcaoAplicacao, that.opcaoAplicacao)
                && Objects.equals(this.procuracaoUsuario, that.procuracaoUsuario);
    }

    @Override
    public String toString() {
        return "ProcuracaoOpcao [identificacaoProcuradoOpcao=" + identificacaoProcuradoOpcao + ", opcaoAplicacao="
                + opcaoAplicacao + ", procuracaoUsuario=" + procuracaoUsuario + "]";
    }

}
