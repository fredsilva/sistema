package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_OPERACAO_AUDITADA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_OPERACAO_AUDITADA", schema = "SEFAZ_SEG")
public class OperacaoAuditada extends AbstractEntity<Long> {

    private static final long serialVersionUID = 7332874989520996348L;

    @Id
    @NotNull
    @Column(name = "IDENTIFICACAO_AUDITORIA")
    private Long identificacaoAuditoria;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOME_TABELA")
    private String nomeTabela;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOME_COMPUTADOR")
    private String nomeComputador;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USUARIO_SISTEMA_OPERACIONAL")
    private String usuarioSistemaOperacional;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USUARIO_BANCO_DADOS")
    private String usuarioBancoDados;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "APLICACAO_USADA")
    private String aplicacaoUsada;

    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ENDERECO_IP")
    private String enderecoIp;

    @NotNull
    @Column(name = "DATA_OPERACAO")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataOperacao;

    @NotNull
    @Lob
    @Column(name = "COLUNAS_ALTERADAS")
    private String colunasAlteradas;

    public OperacaoAuditada() {
        // Construtor para inicialização por reflexão.
    }

    public OperacaoAuditada(Long identificacaoAuditoria, String nomeTabela, String nomeComputador,
            String usuarioSistemaOperacional, String usuarioBancoDados, String aplicacaoUsada, String enderecoIp,
            LocalDateTime dataOperacao, String colunasAlteradas) {
        this.identificacaoAuditoria = identificacaoAuditoria;
        this.nomeTabela = nomeTabela;
        this.nomeComputador = nomeComputador;
        this.usuarioSistemaOperacional = usuarioSistemaOperacional;
        this.usuarioBancoDados = usuarioBancoDados;
        this.aplicacaoUsada = aplicacaoUsada;
        this.enderecoIp = enderecoIp;
        this.dataOperacao = dataOperacao;
        this.colunasAlteradas = colunasAlteradas;
    }

    @Override
    public Long getId() {
        return identificacaoAuditoria;
    }

    public Long getIdentificacaoAuditoria() {
        return identificacaoAuditoria;
    }

    public void setIdentificacaoAuditoria(Long identificacaoAuditoria) {
        this.identificacaoAuditoria = identificacaoAuditoria;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }

    public void setNomeTabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
    }

    public String getNomeComputador() {
        return nomeComputador;
    }

    public void setNomeComputador(String nomeComputador) {
        this.nomeComputador = nomeComputador;
    }

    public String getUsuarioSistemaOperacional() {
        return usuarioSistemaOperacional;
    }

    public void setUsuarioSistemaOperacional(String usuarioSistemaOperacional) {
        this.usuarioSistemaOperacional = usuarioSistemaOperacional;
    }

    public String getUsuarioBancoDados() {
        return usuarioBancoDados;
    }

    public void setUsuarioBancoDados(String usuarioBancoDados) {
        this.usuarioBancoDados = usuarioBancoDados;
    }

    public String getAplicacaoUsada() {
        return aplicacaoUsada;
    }

    public void setAplicacaoUsada(String aplicacaoUsada) {
        this.aplicacaoUsada = aplicacaoUsada;
    }

    public String getEnderecoIp() {
        return enderecoIp;
    }

    public void setEnderecoIp(String enderecoIp) {
        this.enderecoIp = enderecoIp;
    }

    public LocalDateTime getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(LocalDateTime dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public String getColunasAlteradas() {
        return colunasAlteradas;
    }

    public void setColunasAlteradas(String colunasAlteradas) {
        this.colunasAlteradas = colunasAlteradas;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoAuditoria, nomeTabela, nomeComputador, usuarioSistemaOperacional,
                usuarioBancoDados, aplicacaoUsada, enderecoIp, dataOperacao, colunasAlteradas);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OperacaoAuditada that = (OperacaoAuditada) obj;
        return Objects.equals(this.identificacaoAuditoria, that.identificacaoAuditoria)
                && Objects.equals(this.nomeTabela, that.nomeTabela)
                && Objects.equals(this.nomeComputador, that.nomeComputador)
                && Objects.equals(this.usuarioSistemaOperacional, that.usuarioSistemaOperacional)
                && Objects.equals(this.usuarioBancoDados, that.usuarioBancoDados)
                && Objects.equals(this.aplicacaoUsada, that.aplicacaoUsada)
                && Objects.equals(this.enderecoIp, that.enderecoIp)
                && Objects.equals(this.dataOperacao, that.dataOperacao)
                && Objects.equals(this.colunasAlteradas, that.colunasAlteradas);
    }

    @Override
    public String toString() {
        return "OperacaoAuditada [identificacaoAuditoria=" + identificacaoAuditoria + ", nomeTabela=" + nomeTabela
                + ", nomeComputador=" + nomeComputador + ", usuarioSistemaOperacional=" + usuarioSistemaOperacional
                + ", usuarioBancoDados=" + usuarioBancoDados + ", aplicacaoUsada=" + aplicacaoUsada + ", enderecoIp="
                + enderecoIp + ", dataOperacao=" + dataOperacao + ", colunasAlteradas=" + colunasAlteradas + "]";
    }

}
