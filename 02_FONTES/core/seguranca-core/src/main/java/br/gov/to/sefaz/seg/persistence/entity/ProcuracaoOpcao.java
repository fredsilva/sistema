package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PROCURACAO_OPCAO")
    @SequenceGenerator(name = "SQ_PROCURACAO_OPCAO", schema = "SEFAZ_SEG",
            sequenceName = "SQ_PROCURACAO_OPCAO", allocationSize = 1)
    @NotNull
    @Column(name = "IDENTIFICACAO_PROCURADO_OPCAO")
    private Long identificacaoProcuradoOpcao;

    @NotNull
    @Column(name = "IDENTIFICACAO_PROCUR_USUARIO")
    private Long identificacaoProcurUsuario;

    @NotNull
    @Column(name = "IDENTIFICACAO_OPCAO_APLICACAO")
    private Long identificacaoOpcaoAplicacao;

    public ProcuracaoOpcao() {
        // Construtor para inicialização por reflexão.
    }

    public ProcuracaoOpcao(Long identificacaoProcuradoOpcao, Long identificacaoOpcaoAplicacao) {
        this.identificacaoProcuradoOpcao = identificacaoProcuradoOpcao;
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
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

    public Long getIdentificacaoOpcaoAplicacao() {
        return identificacaoOpcaoAplicacao;
    }

    public void setIdentificacaoOpcaoAplicacao(Long identificacaoOpcaoAplicacao) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
    }

    public Long getIdentificacaoProcurUsuario() {
        return identificacaoProcurUsuario;
    }

    public void setIdentificacaoProcurUsuario(Long identificacaoProcurUsuario) {
        this.identificacaoProcurUsuario = identificacaoProcurUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProcuracaoOpcao that = (ProcuracaoOpcao) o;
        return Objects.equals(identificacaoProcuradoOpcao, that.identificacaoProcuradoOpcao)
                && Objects.equals(identificacaoProcurUsuario, that.identificacaoProcurUsuario)
                && Objects.equals(identificacaoOpcaoAplicacao, that.identificacaoOpcaoAplicacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoProcuradoOpcao, identificacaoProcurUsuario, identificacaoOpcaoAplicacao);
    }

    @Override
    public String toString() {
        return "ProcuracaoOpcao{"
                + "identificacaoProcuradoOpcao=" + identificacaoProcuradoOpcao
                + ", identificacaoProcurUsuario=" + identificacaoProcurUsuario
                + ", identificacaoOpcaoAplicacao=" + identificacaoOpcaoAplicacao
                + '}';
    }
}
