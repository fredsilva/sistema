package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_OPCAO_APLICACAO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_OPCAO_APLICACAO", schema = "SEFAZ_SEG")
public class OpcaoAplicacao extends AbstractEntity<Long> {

    private static final long serialVersionUID = 6633954130829802798L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_OPCAO_APLICACAO")
    private Long identificacaoOpcaoAplicacao;

    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CASO_USO")
    private String casoUso;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "DESCRIPCAO_OPCAO")
    private String descripcaoOpcao;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OPCAO_URL")
    private String opcaoUrl;

    @NotNull
    @Lob
    @Column(name = "AJUDA_OPCAO")
    private String ajudaOpcao;

    @NotNull
    @Column(name = "IDENTIFICACAO_APLICACAO_MODULO")
    private Long identificacaoAplicacaoModulo;

    public OpcaoAplicacao() {
        // Construtor para inicialização por reflexão.
    }

    public OpcaoAplicacao(Long identificacaoOpcaoAplicacao, String casoUso, String descripcaoOpcao, String opcaoUrl,
            String ajudaOpcao, Long identificacaoAplicacaoModulo) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
        this.casoUso = casoUso;
        this.descripcaoOpcao = descripcaoOpcao;
        this.opcaoUrl = opcaoUrl;
        this.ajudaOpcao = ajudaOpcao;
        this.identificacaoAplicacaoModulo = identificacaoAplicacaoModulo;
    }

    @Override
    public Long getId() {
        return identificacaoOpcaoAplicacao;
    }

    public Long getIdentificacaoOpcaoAplicacao() {
        return identificacaoOpcaoAplicacao;
    }

    public void setIdentificacaoOpcaoAplicacao(Long identificacaoOpcaoAplicacao) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
    }

    public String getCasoUso() {
        return casoUso;
    }

    public void setCasoUso(String casoUso) {
        this.casoUso = casoUso;
    }

    public String getDescripcaoOpcao() {
        return descripcaoOpcao;
    }

    public void setDescripcaoOpcao(String descripcaoOpcao) {
        this.descripcaoOpcao = descripcaoOpcao;
    }

    public String getOpcaoUrl() {
        return opcaoUrl;
    }

    public void setOpcaoUrl(String opcaoUrl) {
        this.opcaoUrl = opcaoUrl;
    }

    public String getAjudaOpcao() {
        return ajudaOpcao;
    }

    public void setAjudaOpcao(String ajudaOpcao) {
        this.ajudaOpcao = ajudaOpcao;
    }

    public Long getIdentificacaoAplicacaoModulo() {
        return identificacaoAplicacaoModulo;
    }

    public void setIdentificacaoAplicacaoModulo(Long identificacaoAplicacaoModulo) {
        this.identificacaoAplicacaoModulo = identificacaoAplicacaoModulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OpcaoAplicacao that = (OpcaoAplicacao) o;
        return Objects.equals(identificacaoOpcaoAplicacao, that.identificacaoOpcaoAplicacao)
                && Objects.equals(casoUso, that.casoUso)
                && Objects.equals(descripcaoOpcao, that.descripcaoOpcao)
                && Objects.equals(opcaoUrl, that.opcaoUrl)
                && Objects.equals(ajudaOpcao, that.ajudaOpcao)
                && Objects.equals(identificacaoAplicacaoModulo, that.identificacaoAplicacaoModulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoOpcaoAplicacao, casoUso, descripcaoOpcao, opcaoUrl, ajudaOpcao,
                identificacaoAplicacaoModulo);
    }

    @Override
    public String toString() {
        return "OpcaoAplicacao{"
                + "identificacaoOpcaoAplicacao=" + identificacaoOpcaoAplicacao
                + ", casoUso='" + casoUso + '\''
                + ", descripcaoOpcao='" + descripcaoOpcao + '\''
                + ", opcaoUrl='" + opcaoUrl + '\''
                + ", ajudaOpcao='" + ajudaOpcao + '\''
                + ", identificacaoAplicacaoModulo=" + identificacaoAplicacaoModulo
                + '}';
    }
}
