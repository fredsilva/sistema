package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.converter.YesOrNoBooleanConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.seg.persistence.converter.SituacaoUsuarioEnumConverter;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_USUARIO_SISTEMA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_USUARIO_SISTEMA", schema = "SEFAZ_SEG")
@SuppressWarnings("PMD")
public class UsuarioSistema extends AbstractEntity<String> {

    private static final long serialVersionUID = 8788036872437894647L;

    @Id
    @NotNull(message = "#{seg_msg['usuarioSistema.cpfUsuario.obrigatorio']}")
    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @NotNull(message = "#{seg_msg['usuarioSistema.nomeCompletoUsuario.obrigatorio']}")
    @Column(name = "NOME_COMPLETO_USUARIO")
    private String nomeCompletoUsuario;

    @NotNull(message = "#{seg_msg['usuarioSistema.cep.obrigatorio']}")
    @Column(name = "CEP")
    private String cep;

    @NotNull(message = "#{seg_msg['usuarioSistema.codigoLogradouro.obrigatorio']}")
    @Column(name = "CODIGO_LOGRADOURO")
    private String codigoLogradouro;

    @NotNull(message = "#{seg_msg['usuarioSistema.endereco.obrigatorio']}")
    @Column(name = "ENDERECO")
    private String endereco;

    @NotNull(message = "#{seg_msg['usuarioSistema.numeroEndereco.obrigatorio']}")
    @Column(name = "NUMERO_ENDERECO")
    private Integer numeroEndereco;

    @Column(name = "APARTAMENTO")
    private Integer apartamento;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @NotNull(message = "#{seg_msg['usuarioSistema.bairro.obrigatorio']}")
    @Column(name = "BAIRRO")
    private String bairro;

    @NotNull(message = "#{seg_msg['usuarioSistema.codigoEstado.obrigatorio']}")
    @Column(name = "CODIGO_ESTADO")
    private String codigoEstado;

    @NotNull(message = "#{seg_msg['usuarioSistema.codigoMunicipio.obrigatorio']}")
    @Column(name = "CODIGO_MUNICIPIO")
    private Integer codigoMunicipio;

    @Column(name = "TELEFONE_RESIDENCIAL")
    private String telefoneResidencial;

    @Column(name = "OUTRO_ENDERECO_CONTATO")
    private String outroEnderecoContato;

    @NotNull(message = "#{seg_msg['usuarioSistema.correioEletronico.obrigatorio']}")
    @Column(name = "CORREIO_ELETRONICO")
    private String correioEletronico;

    @Column(name = "TELEFONE_CELULAR")
    private String telefoneCelular;

    @Column(name = "CRC")
    private String crc;

    @NotNull
    @Column(name = "SITUACAO_USUARIO")
    @Convert(converter = SituacaoUsuarioEnumConverter.class)
    private SituacaoUsuarioEnum situacaoUsuario = SituacaoUsuarioEnum.ATIVO;

    @NotNull
    @Column(name = "ALTERAR_SENHA_PROXIMO_ACESSO")
    @Convert(converter = YesOrNoBooleanConverter.class)
    private Boolean alterarSenhaProximoAcesso = Boolean.FALSE;

    @Column(name = "DATA_ULTIMA_ALTERACAO_SENHA")
    private LocalDateTime dataUltimaAlteracaoSenha;

    @Column(name = "JUSTIFICACAO_CRIACAO")
    private String justificacaoCriacao;

    @NotNull
    @Column(name = "ESTA_BLOQUEADO")
    @Convert(converter = YesOrNoBooleanConverter.class)
    private Boolean estaBloqueado = Boolean.FALSE;

    @Column(name = "DATA_DESBLOQUEIO")
    private LocalDateTime dataDesbloqueio;

    @Column(name = "CODIGO_TIPO_USUARIO")
    private Integer codigoTipoUsuario;

    @JoinColumn(name = "CODIGO_TIPO_USUARIO", referencedColumnName = "CODIGO_TIPO_USUARIO", insertable = false,
            updatable = false)
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;

    public UsuarioSistema() {
        // Construtor para inicialização por reflexão.
    }

    public UsuarioSistema(
            String cpfUsuario, String nomeCompletoUsuario, String cep, String codigoLogradouro,
            String endereco, Integer numeroEndereco, Integer apartamento, String complemento, String bairro,
            String codigoEstado, Integer codigoMunicipio, String telefoneResidencial, String outroEnderecoContato,
            String correioEletronico, String telefoneCelular, String crc, SituacaoUsuarioEnum situacaoUsuario,
            Boolean alterarSenhaProximoAcesso, LocalDateTime dataUltimaAlteracaoSenha, String justificacaoCriacao,
            Boolean estaBloqueado, LocalDateTime dataDesbloqueio, TipoUsuario tipoUsuario,
            Integer codigoTipoUsuario) {
        this.cpfUsuario = cpfUsuario;
        this.nomeCompletoUsuario = nomeCompletoUsuario;
        this.cep = cep;
        this.codigoLogradouro = codigoLogradouro;
        this.endereco = endereco;
        this.numeroEndereco = numeroEndereco;
        this.apartamento = apartamento;
        this.complemento = complemento;
        this.bairro = bairro;
        this.codigoEstado = codigoEstado;
        this.codigoMunicipio = codigoMunicipio;
        this.telefoneResidencial = telefoneResidencial;
        this.outroEnderecoContato = outroEnderecoContato;
        this.correioEletronico = correioEletronico;
        this.telefoneCelular = telefoneCelular;
        this.crc = crc;
        this.situacaoUsuario = situacaoUsuario;
        this.alterarSenhaProximoAcesso = alterarSenhaProximoAcesso;
        this.dataUltimaAlteracaoSenha = dataUltimaAlteracaoSenha;
        this.justificacaoCriacao = justificacaoCriacao;
        this.estaBloqueado = estaBloqueado;
        this.dataDesbloqueio = dataDesbloqueio;
        this.tipoUsuario = tipoUsuario;
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    @Override
    public String getId() {
        return cpfUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomeCompletoUsuario() {
        return nomeCompletoUsuario;
    }

    public void setNomeCompletoUsuario(String nomeCompletoUsuario) {
        this.nomeCompletoUsuario = nomeCompletoUsuario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCodigoLogradouro() {
        return codigoLogradouro;
    }

    public void setCodigoLogradouro(String codigoLogradouro) {
        this.codigoLogradouro = codigoLogradouro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(Integer numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public Integer getApartamento() {
        return apartamento;
    }

    public void setApartamento(Integer apartamento) {
        this.apartamento = apartamento;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getOutroEnderecoContato() {
        return outroEnderecoContato;
    }

    public void setOutroEnderecoContato(String outroEnderecoContato) {
        this.outroEnderecoContato = outroEnderecoContato;
    }

    public String getCorreioEletronico() {
        return correioEletronico;
    }

    public void setCorreioEletronico(String correioEletronico) {
        this.correioEletronico = correioEletronico;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public SituacaoUsuarioEnum getSituacaoUsuario() {
        return situacaoUsuario;
    }

    public void setSituacaoUsuario(SituacaoUsuarioEnum situacaoUsuario) {
        this.situacaoUsuario = situacaoUsuario;
    }

    public Boolean getAlterarSenhaProximoAcesso() {
        return alterarSenhaProximoAcesso;
    }

    public void setAlterarSenhaProximoAcesso(Boolean alterarSenhaProximoAcesso) {
        this.alterarSenhaProximoAcesso = alterarSenhaProximoAcesso;
    }

    public LocalDateTime getDataUltimaAlteracaoSenha() {
        return dataUltimaAlteracaoSenha;
    }

    public void setDataUltimaAlteracaoSenha(LocalDateTime dataUltimaAlteracaoSenha) {
        this.dataUltimaAlteracaoSenha = dataUltimaAlteracaoSenha;
    }

    public String getJustificacaoCriacao() {
        return justificacaoCriacao;
    }

    public void setJustificacaoCriacao(String justificacaoCriacao) {
        this.justificacaoCriacao = justificacaoCriacao;
    }

    public Boolean getEstaBloqueado() {
        return estaBloqueado;
    }

    public void setEstaBloqueado(Boolean estaBloqueado) {
        this.estaBloqueado = estaBloqueado;
    }

    public LocalDateTime getDataDesbloqueio() {
        return dataDesbloqueio;
    }

    public void setDataDesbloqueio(LocalDateTime dataDesbloqueio) {
        this.dataDesbloqueio = dataDesbloqueio;
    }

    public Integer getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(Integer codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioSistema that = (UsuarioSistema) o;
        return numeroEndereco == that.numeroEndereco
                && codigoMunicipio == that.codigoMunicipio
                && Objects.equals(cpfUsuario, that.cpfUsuario)
                && Objects.equals(nomeCompletoUsuario, that.nomeCompletoUsuario)
                && Objects.equals(cep, that.cep)
                && Objects.equals(codigoLogradouro, that.codigoLogradouro)
                && Objects.equals(endereco, that.endereco)
                && Objects.equals(apartamento, that.apartamento)
                && Objects.equals(complemento, that.complemento)
                && Objects.equals(bairro, that.bairro)
                && Objects.equals(codigoEstado, that.codigoEstado)
                && Objects.equals(telefoneResidencial, that.telefoneResidencial)
                && Objects.equals(outroEnderecoContato, that.outroEnderecoContato)
                && Objects.equals(correioEletronico, that.correioEletronico)
                && Objects.equals(telefoneCelular, that.telefoneCelular)
                && Objects.equals(crc, that.crc)
                && Objects.equals(situacaoUsuario, that.situacaoUsuario)
                && Objects.equals(alterarSenhaProximoAcesso, that.alterarSenhaProximoAcesso)
                && Objects.equals(dataUltimaAlteracaoSenha, that.dataUltimaAlteracaoSenha)
                && Objects.equals(justificacaoCriacao, that.justificacaoCriacao)
                && Objects.equals(estaBloqueado, that.estaBloqueado)
                && Objects.equals(dataDesbloqueio, that.dataDesbloqueio)
                && Objects.equals(codigoTipoUsuario, that.codigoTipoUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfUsuario, nomeCompletoUsuario, cep, codigoLogradouro, endereco, numeroEndereco,
                apartamento, complemento, bairro, codigoEstado, codigoMunicipio, telefoneResidencial,
                outroEnderecoContato, correioEletronico, telefoneCelular, crc, situacaoUsuario,
                alterarSenhaProximoAcesso, dataUltimaAlteracaoSenha, justificacaoCriacao, estaBloqueado,
                dataDesbloqueio, codigoTipoUsuario);
    }

    @Override
    public String toString() {
        return "UsuarioSistema{"
                + "cpfUsuario='" + cpfUsuario + '\''
                + ", nomeCompletoUsuario='" + nomeCompletoUsuario + '\''
                + ", cep='" + cep + '\''
                + ", codigoLogradouro='" + codigoLogradouro + '\''
                + ", endereco='" + endereco + '\''
                + ", numeroEndereco=" + numeroEndereco
                + ", apartamento=" + apartamento
                + ", complemento='" + complemento + '\''
                + ", bairro='" + bairro + '\''
                + ", codigoEstado='" + codigoEstado + '\''
                + ", codigoMunicipio=" + codigoMunicipio
                + ", telefoneResidencial='" + telefoneResidencial + '\''
                + ", outroEnderecoContato='" + outroEnderecoContato + '\''
                + ", correioEletronico='" + correioEletronico + '\''
                + ", telefoneCelular='" + telefoneCelular + '\''
                + ", crc='" + crc + '\''
                + ", situacaoUsuario=" + situacaoUsuario
                + ", alterarSenhaProximoAcesso=" + alterarSenhaProximoAcesso
                + ", dataUltimaAlteracaoSenha=" + dataUltimaAlteracaoSenha
                + ", justificacaoCriacao='" + justificacaoCriacao + '\''
                + ", estaBloqueado=" + estaBloqueado
                + ", dataDesbloqueio=" + dataDesbloqueio
                + ", codigoTipoUsuario=" + codigoTipoUsuario
                + '}';
    }
}
