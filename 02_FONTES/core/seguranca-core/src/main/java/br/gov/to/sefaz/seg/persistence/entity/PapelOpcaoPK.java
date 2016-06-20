package br.gov.to.sefaz.seg.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a primary key da tabela SEFAZ_SEG.TA_PAPEL_OPCAO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
public class PapelOpcaoPK implements Serializable {

    private static final long serialVersionUID = 8455915673162431260L;

    @NotNull
    @Column(name = "IDENTIFICACAO_OPCAO_APLICACAO")
    private Long identificacaoOpcaoAplicacao;

    @NotNull
    @Column(name = "IDENTIFICACAO_PAPEL")
    private Long identificacaoPapel;

    public PapelOpcaoPK() {
        // Construtor para inicialização por reflexão.
    }

    public PapelOpcaoPK(Long identificacaoOpcaoAplicacao, Long identificacaoPapel) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
        this.identificacaoPapel = identificacaoPapel;
    }

    public Long getIdentificacaoOpcaoAplicacao() {
        return identificacaoOpcaoAplicacao;
    }

    public void setIdentificacaoOpcaoAplicacao(Long identificacaoOpcaoAplicacao) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
    }

    public Long getIdentificacaoPapel() {
        return identificacaoPapel;
    }

    public void setIdentificacaoPapel(Long identificacaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
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
        PapelOpcaoPK that = (PapelOpcaoPK) obj;
        return Objects.equals(this.identificacaoOpcaoAplicacao, that.identificacaoOpcaoAplicacao)
                && Objects.equals(this.identificacaoPapel, that.identificacaoPapel);
    }

    @Override
    public String toString() {
        return "PapelOpcaoPK [identificacaoOpcaoAplicacao=" + identificacaoOpcaoAplicacao + ", identificacaoPapel="
                + identificacaoPapel + "]";
    }

}
