package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.seg.persistence.converter.TipoOperacaoEnumConverter;
import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_LOG_NAVEGACAO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_LOG_NAVEGACAO", schema = "SEFAZ_SEG")
public class LogNavegacao extends AbstractEntity<Long> {

    private static final long serialVersionUID = -3367960460104112850L;

    @Id
    @Column(name = "IDENTIFICACAO_NAVEGACAO")
    @SequenceGenerator(name = "SQ_LOG_NAVEGACAO", schema = "SEFAZ_SEG",
            sequenceName = "SQ_LOG_NAVEGACAO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_LOG_NAVEGACAO")
    private Long identificacaoNavegacao;

    @NotNull
    @Column(name = "DATA_OPERACAO")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataOperacao;

    @NotNull
    @Convert(converter = TipoOperacaoEnumConverter.class)
    @Column(name = "TIPO_OPERACAO")
    private TipoOperacaoEnum tipoOperacao;

    @NotNull
    @Lob
    @Column(name = "DETALHE_NAVEGACAO")
    private String detalheNavegacao;

    @JoinColumn(name = "CPF_USUARIO", referencedColumnName = "CPF_USUARIO",
            insertable = false, updatable = false)
    @ManyToOne
    private UsuarioSistema usuarioSistema;

    @NotNull
    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @Column(name = "CPF_CNPJ_PROCURADO")
    private String cpfCnpjProcurado;

    @Column(name = "URL_ACESSO")
    private String urlAcesso;

    public LogNavegacao() {
        // Construtor para inicialização por reflexão.
    }

    public LogNavegacao(LocalDateTime dataOperacao, TipoOperacaoEnum tipoOperacao,
            String detalheNavegacao, String cpfUsuario) {
        this.dataOperacao = dataOperacao;
        this.tipoOperacao = tipoOperacao;
        this.detalheNavegacao = detalheNavegacao;
        this.cpfUsuario = cpfUsuario;
    }

    @Override
    public Long getId() {
        return identificacaoNavegacao;
    }

    public Long getIdentificacaoNavegacao() {
        return identificacaoNavegacao;
    }

    public void setIdentificacaoNavegacao(Long identificacaoNavegacao) {
        this.identificacaoNavegacao = identificacaoNavegacao;
    }

    public LocalDateTime getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(LocalDateTime dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public TipoOperacaoEnum getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(TipoOperacaoEnum tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getDetalheNavegacao() {
        return detalheNavegacao;
    }

    public void setDetalheNavegacao(String detalheNavegacao) {
        this.detalheNavegacao = detalheNavegacao;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomeUsuarioSistema() {
        return getUsuarioSistema().getNomeCompletoUsuario();
    }

    public String getDescricaoTipoUsuario() {
        return getUsuarioSistema().getDescricaoTipoUsuario();
    }

    public String getCpfCnpjProcurado() {
        return cpfCnpjProcurado;
    }

    public void setCpfCnpjProcurado(String cpfCnpjProcurado) {
        this.cpfCnpjProcurado = cpfCnpjProcurado;
    }

    public String getUrlAcesso() {
        return urlAcesso;
    }

    public void setUrlAcesso(String urlAcesso) {
        this.urlAcesso = urlAcesso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LogNavegacao that = (LogNavegacao) o;
        return Objects.equals(getIdentificacaoNavegacao(), that.getIdentificacaoNavegacao())
                && Objects.equals(getDataOperacao(), that.getDataOperacao())
                && getTipoOperacao() == that.getTipoOperacao()
                && Objects.equals(getDetalheNavegacao(), that.getDetalheNavegacao())
                && Objects.equals(getUsuarioSistema(), that.getUsuarioSistema())
                && Objects.equals(getCpfUsuario(), that.getCpfUsuario())
                && Objects.equals(getCpfCnpjProcurado(), that.getCpfCnpjProcurado())
                && Objects.equals(getUrlAcesso(), that.getUrlAcesso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificacaoNavegacao(), getDataOperacao(), getTipoOperacao(), getDetalheNavegacao(),
                getUsuarioSistema(), getCpfUsuario(), getCpfCnpjProcurado(), getUrlAcesso());
    }

    @Override
    public String toString() {
        return "LogNavegacao{"
                + "identificacaoNavegacao=" + identificacaoNavegacao
                + ", dataOperacao=" + dataOperacao
                + ", tipoOperacao=" + tipoOperacao
                + ", detalheNavegacao='" + detalheNavegacao + '\''
                + ", usuarioSistema=" + usuarioSistema
                + ", cpfUsuario='" + cpfUsuario + '\''
                + ", cpfCnpjUsuario='" + cpfCnpjProcurado + '\''
                + ", urlAcesso='" + urlAcesso + '\''
                + '}';
    }
}
