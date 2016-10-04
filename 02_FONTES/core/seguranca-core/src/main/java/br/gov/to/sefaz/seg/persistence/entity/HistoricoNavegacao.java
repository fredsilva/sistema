package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.seg.persistence.converter.TipoOperacaoEnumConverter;
import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade referente a view SEFAZ_SEG.VW_HISTORICO_NAVEGACAO do Banco de Dados.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 14:16:37
 */
@Entity
@Table(name = "VW_HISTORICO_NAVEGACAO", schema = "SEFAZ_SEG")
public class HistoricoNavegacao implements Serializable {

    private static final long serialVersionUID = -3256482443943912237L;

    @Id
    @Column(name = "IDENTIFICACAO_NAVEGACAO")
    private Long identificacaoNavegacao;

    @Column(name = "DATA_OPERACAO", insertable = false, updatable = false)
    private LocalDateTime dataOperacao;

    @Column(name = "TIPO_OPERACAO", insertable = false, updatable = false)
    @Convert(converter = TipoOperacaoEnumConverter.class)
    private TipoOperacaoEnum tipoOperacao;

    @Lob
    @Column(name = "DETALHE_NAVEGACAO", insertable = false, updatable = false)
    private String detalheNavegacao;

    @JoinColumn(name = "CPF_USUARIO", referencedColumnName = "CPF_USUARIO",
            insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioSistema usuarioSistema;

    @Column(name = "CPF_USUARIO", insertable = false, updatable = false)
    private String cpfUsuario;

    @Column(name = "CPF_CNPJ_PROCURADO", insertable = false, updatable = false)
    private String cpfCnpjProcurado;

    @Column(name = "NOME_PROCURADO", insertable = false, updatable = false)
    private String nomeProcurado;

    @Column(name = "URL_ACESSO", insertable = false, updatable = false)
    private String urlAcesso;

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

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getCpfCnpjProcurado() {
        return cpfCnpjProcurado;
    }

    public void setCpfCnpjProcurado(String cpfCnpjProcurado) {
        this.cpfCnpjProcurado = cpfCnpjProcurado;
    }

    public String getNomeProcurado() {
        return nomeProcurado;
    }

    public void setNomeProcurado(String nomeProcurado) {
        this.nomeProcurado = nomeProcurado;
    }

    public String getUrlAcesso() {
        return urlAcesso;
    }

    public void setUrlAcesso(String urlAcesso) {
        this.urlAcesso = urlAcesso;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getNomeUsuario() {
        return getUsuarioSistema().getNomeCompletoUsuario();
    }

    /**
     * Obtem o path da url acessada.
     *
     * @return O path da url acessada.
     */
    public String getUrlPath() {
        try {
            return new URL(getUrlAcesso()).getPath();
        } catch (IOException e) {
            return getUrlAcesso();
        }
    }

    public String getDescricaoTipoUsuario() {
        return this.usuarioSistema.getDescricaoTipoUsuario();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HistoricoNavegacao that = (HistoricoNavegacao) o;
        return Objects.equals(getIdentificacaoNavegacao(), that.getIdentificacaoNavegacao())
                && Objects.equals(getDataOperacao(), that.getDataOperacao())
                && getTipoOperacao() == that.getTipoOperacao()
                && Objects.equals(getDetalheNavegacao(), that.getDetalheNavegacao())
                && Objects.equals(getCpfUsuario(), that.getCpfUsuario())
                && Objects.equals(getCpfCnpjProcurado(), that.getCpfCnpjProcurado())
                && Objects.equals(getNomeProcurado(), that.getNomeProcurado())
                && Objects.equals(getUrlAcesso(), that.getUrlAcesso())
                && Objects.equals(getUsuarioSistema(), that.getUsuarioSistema());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentificacaoNavegacao(), getDataOperacao(), getTipoOperacao(), getDetalheNavegacao(),
                getCpfUsuario(), getCpfCnpjProcurado(), getNomeProcurado(), getUrlAcesso(), getUsuarioSistema());
    }

    @Override
    public String toString() {
        return "HistoricoNavegacao{"
                + "identificacaoNavegacao=" + identificacaoNavegacao
                + ", dataOperacao=" + dataOperacao
                + ", tipoOperacao=" + tipoOperacao
                + ", detalheNavegacao='" + detalheNavegacao + '\''
                + ", usuarioSistema=" + usuarioSistema
                + ", cpfUsuario='" + cpfUsuario + '\''
                + ", cpfCnpjProcurado='" + cpfCnpjProcurado + '\''
                + ", nomeProcurado='" + nomeProcurado + '\''
                + ", urlAcesso='" + urlAcesso + '\''
                + '}';
    }
}
