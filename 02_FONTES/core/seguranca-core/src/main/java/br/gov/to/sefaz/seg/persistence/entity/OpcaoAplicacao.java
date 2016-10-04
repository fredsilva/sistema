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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_opcao_aplicacao")
    @SequenceGenerator(name = "sq_opcao_aplicacao", schema = "SEFAZ_SEG",
            sequenceName = "sq_opcao_aplicacao",
            allocationSize = 1)
    @Max(value = 9999999999L,
            message = "#{seg_msg['seg.gestao.opcaoAplicacao.identificacaoOpcaoAplicacao.maximo']}")
    @Column(name = "IDENTIFICACAO_OPCAO_APLICACAO")
    private Long identificacaoOpcaoAplicacao;

    @NotEmpty(message = "#{seg_msg['seg.gestao.opcaoAplicacao.casoUso.obrigatorio']}")
    @Size(max = 10, message = "#{seg_msg['seg.gestao.opcaoAplicacao.casoUso.tamanho']}")
    @Column(name = "CASO_USO")
    private String casoUso;

    @NotEmpty(message = "#{seg_msg['seg.gestao.opcaoAplicacao.opcao.obrigatorio']}")
    @Size(max = 60, message = "#{seg_msg['seg.gestao.opcaoAplicacao.opcao.tamanho']}")
    @Column(name = "DESCRIPCAO_OPCAO")
    private String descripcaoOpcao;

    @NotEmpty(message = "#{seg_msg['seg.gestao.opcaoAplicacao.opcaoUrl.obrigatorio']}")
    @Size(max = 100, message = "#{seg_msg['seg.gestao.opcaoAplicacao.opcaoUrl.tamanho']}")
    @Column(name = "OPCAO_URL")
    private String opcaoUrl;

    @NotNull(message = "#{seg_msg['seg.gestao.opcaoAplicacao.ajudaOpcao.obrigatorio']}")
    @Size(max = 2000, min = 1, message = "#{seg_msg['seg.gestao.opcaoAplicacao.ajudaOpcao.tamanho']}")
    @Lob
    @Column(name = "AJUDA_OPCAO")
    private String ajudaOpcao;

    @NotNull(message = "#{seg_msg['seg.gestao.opcaoAplicacao.identificacaoAplicacaoModulo.obrigatorio']}")
    @Max(value = 9999999999L,
            message = "#{seg_msg['seg.gestao.opcaoAplicacao.identificacaoAplicacaoModulo.maximo']}")
    @Column(name = "IDENTIFICACAO_APLICACAO_MODULO")
    private Long identificacaoAplicacaoModulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENTIFICACAO_APLICACAO_MODULO", referencedColumnName = "IDENTIFICACAO_APLICACAO_MODULO",
            insertable = false, updatable = false)
    private AplicacaoModulo aplicacaoModulo;


    public OpcaoAplicacao() {
        // Construtor para inicialização por reflexão.
        aplicacaoModulo = new AplicacaoModulo();
    }

    public OpcaoAplicacao(Long identificacaoOpcaoAplicacao, String casoUso, String descripcaoOpcao, String opcaoUrl,
            String ajudaOpcao, Long identificacaoAplicacaoModulo) {
        this();
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

    public AplicacaoModulo getAplicacaoModulo() {
        return aplicacaoModulo;
    }

    public void setAplicacaoModulo(AplicacaoModulo aplicacaoModulo) {
        this.aplicacaoModulo = aplicacaoModulo;
    }

    public String getDescricaoAplicacao() {
        return Objects.isNull(aplicacaoModulo) ? "" : aplicacaoModulo.getDescricaoAplicacaoModulo() ;
    }

    public String getAbreviacaoModulo() {
        return aplicacaoModulo.getAbreviacaoSistema();
    }

    /**
     * Busca o id do módulo de uma funcionalidade.
     *
     * @return o id do módulo de uma funcionalidade
     */
    public Long getIdModulo() {
        if (Objects.isNull(aplicacaoModulo) || Objects.isNull(aplicacaoModulo.getModuloSistema())) {
            return null;
        } else {
            return aplicacaoModulo.getModuloSistema().getIdentificacaoModuloSistema();
        }
    }

    public String getDescricaoModulo() {
        return Objects.isNull(aplicacaoModulo) || Objects.isNull(aplicacaoModulo.getModuloSistema()) ? ""
                : String.format("%s (%s)",
                aplicacaoModulo.getModuloSistema().getAbreviacaoModulo(),
                aplicacaoModulo.getModuloSistema().getDescricaoModuloSistema());
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
