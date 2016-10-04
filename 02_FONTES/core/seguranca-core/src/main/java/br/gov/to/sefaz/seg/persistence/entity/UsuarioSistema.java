package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.business.service.validation.custom.Cpf;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.persistence.converter.YesOrNoBooleanConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.seg.persistence.converter.SituacaoUsuarioEnumConverter;
import br.gov.to.sefaz.seg.persistence.converter.TipoUsuarioConverter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoSolicitacaoEnum;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Cpf
    @NotEmpty(message = "#{seg_msg['usuarioSistema.cpfUsuario.obrigatorio']}")
    @Size(max = 11, message = "#{seg_msg['usuarioSistema.cpfUsuario.tamanho']}")
    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @NotEmpty(message = "#{seg_msg['usuarioSistema.nomeCompletoUsuario.obrigatorio']}")
    @Size(max = 120, message = "#{seg_msg['usuarioSistema.nomeCompleto.tamanho']}")
    @Column(name = "NOME_COMPLETO_USUARIO")
    private String nomeCompletoUsuario;

    @NotEmpty(message = "#{seg_msg['usuarioSistema.cep.obrigatorio']}")
    @Size(max = 8, message = "#{seg_msg['usuarioSistema.cep.tamanho']}")
    @Column(name = "CEP")
    private String cep;

    @NotEmpty(message = "#{seg_msg['usuarioSistema.codigoLogradouro.obrigatorio']}")
    @Size(max = 3, message = "#{seg_msg['usuarioSistema.codigoLogradouro.tamanho']}")
    @Column(name = "CODIGO_LOGRADOURO")
    private String codigoLogradouro;

    @NotEmpty(message = "#{seg_msg['usuarioSistema.endereco.obrigatorio']}")
    @Size(max = 100, message = "#{seg_msg['usuarioSistema.endereco.tamanho']}")
    @Column(name = "ENDERECO")
    private String endereco;

    @NotNull(message = "#{seg_msg['usuarioSistema.numeroEndereco.obrigatorio']}")
    @Max(value = 99999999, message = "#{seg_msg['usuarioSistema.numeroEndereco.tamanho.maximo']}")
    @Min(value = 0, message = "#{seg_msg['usuarioSistema.numeroEndereco.tamanho.minimo']}")
    @Column(name = "NUMERO_ENDERECO")
    private Integer numeroEndereco;

    @Max(value = 99999999, message = "#{seg_msg['usuarioSistema.apartamento.tamanho.maximo']}")
    @Min(value = 0, message = "#{seg_msg['usuarioSistema.apartamento.tamanho.minimo']}")
    @Column(name = "APARTAMENTO")
    private Integer apartamento;

    @NotEmpty(message = "#{seg_msg['usuarioSistema.bairro.obrigatorio']}")
    @Size(max = 100, message = "#{seg_msg['usuarioSistema.bairro.tamanho']}")
    @Column(name = "BAIRRO")
    private String bairro;

    @Size(max = 30, message = "#{seg_msg['usuarioSistema.complemento.tamanho']}")
    @Column(name = "COMPLEMENTO")
    private String complemento;

    @NotEmpty(message = "#{seg_msg['usuarioSistema.codigoEstado.obrigatorio']}")
    @Size(max = 2, message = "#{seg_msg['usuarioSistema.codigoEstado.tamanho']}")
    @Column(name = "CODIGO_ESTADO")
    private String codigoEstado;

    @Max(value = 9999999, message = "#{seg_msg['usuarioSistema.codigoMunicipio.tamanho']}")
    @NotNull(message = "#{seg_msg['usuarioSistema.codigoMunicipio.obrigatorio']}")
    @Column(name = "CODIGO_MUNICIPIO")
    private Integer codigoMunicipio;

    @JoinColumn(name = "CODIGO_MUNICIPIO", referencedColumnName = "CODIGO_IBGE",
            insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Municipio municipio;

    @Size(max = 20, message = "#{seg_msg['usuarioSistema.telefoneResidencial.tamanho']}")
    @Column(name = "TELEFONE_RESIDENCIAL")
    private String telefoneResidencial;

    @Size(max = 300, message = "#{seg_msg['usuarioSistema.outroEndereco.tamanho']}")
    @Column(name = "OUTRO_ENDERECO_CONTATO")
    private String outroEnderecoContato;

    @NotEmpty(message = "#{seg_msg['usuarioSistema.correioEletronico.obrigatorio']}")
    @Size(max = 50, message = "#{seg_msg['usuarioSistema.correioEletronico.tamanho']}")
    @Column(name = "CORREIO_ELETRONICO")
    private String correioEletronico;

    @Size(max = 10, message = "#{seg_msg['usuarioSistema.telefoneCelular.tamanho']}")
    @Column(name = "TELEFONE_CELULAR")
    private String telefoneCelular;

    @Size(max = 9, message = "#{seg_msg['usuarioSistema.crc.tamanho']}")
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

    @NotNull(message = "#{seg_msg['usuarioSistema.tipoUsuario.obrigatorio']}")
    @Column(name = "CODIGO_TIPO_USUARIO")
    private Integer codigoTipoUsuario;

    @Column(name = "CODIGO_TIPO_USUARIO", insertable = false, updatable = false)
    @Convert(converter = TipoUsuarioConverter.class)
    private TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "usuarioSistema")
    private Set<HistoricoLoginSistema> historicoLoginSistema;

    @OneToOne(mappedBy = "usuarioSistema")
    @JoinColumn(name = "CPF_USUARIO", referencedColumnName = "CPF", insertable = false, updatable = false)
    private SolicitacaoUsuario solicitacaoUsuario;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPF_USUARIO", referencedColumnName = "CPF_USUARIO",
            insertable = false, updatable = false)
    @Where(clause = "REGISTRO_EXCLUIDO = 'N'")
    private List<UsuarioPostoTrabalho> listUsuarioPostoTrabalho;

    @JoinColumn(name = "CODIGO_LOGRADOURO", referencedColumnName = "CODIGO_LOGRADOURO", insertable = false,
            updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Logradouro logradouro;

    @OneToMany(mappedBy = "usuarioSistema", fetch = FetchType.LAZY)
    private Set<UsuarioPerfil> usuarioPerfil;

    @Transient
    private String usuarioPerfis;

    @Transient
    private LocalDateTime ultimoLogin;

    public UsuarioSistema() {
        // Construtor para inicialização por reflexão.
        municipio = new Municipio();
        logradouro = new Logradouro();
    }

    public UsuarioSistema(String nomeCompletoUsuario, String cpfUsuario, String descricaoTipoUsuario,
            SituacaoUsuarioEnum situacaoUsuario, String nomeMunicipio, String usuarioInsercao, LocalDateTime
            dataInsercao, LocalDateTime dataHoraLogin) {
        super();
        this.nomeCompletoUsuario = nomeCompletoUsuario;
        this.cpfUsuario = cpfUsuario;
        this.situacaoUsuario = situacaoUsuario;
        this.municipio.setNomeMunicipio(nomeMunicipio);
        this.usuarioInsercao = usuarioInsercao;
        this.dataInsercao = dataInsercao;
        this.historicoLoginSistema = new HashSet<>();

        HistoricoLoginSistema historicoLoginSistema = new HistoricoLoginSistema();
        historicoLoginSistema.setDataHoraLogin(dataHoraLogin);

        this.historicoLoginSistema.add(historicoLoginSistema);
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
        this.codigoTipoUsuario = codigoTipoUsuario;
        this.tipoUsuario = tipoUsuario;
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

    /**
     * Seta o código do logradouro.
     * @param codigoLogradouro código do logradouro
     */
    public void setCodigoLogradouro(String codigoLogradouro) {
        this.codigoLogradouro = codigoLogradouro;
        this.logradouro.setCodigoLogradouro(codigoLogradouro);
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

    /**
     * Seta o código do município.
     * @param codigoMunicipio código ibge do município
     */
    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
        this.municipio.setCodigoIbge(codigoMunicipio);
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

    public String getDescricaoTipoUsuario() {
        return Objects.isNull(tipoUsuario) ? StringUtils.EMPTY : tipoUsuario.getDescricaoTipoUsuario();
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getUnidadeFederacao() {
        return municipio != null ? municipio.getUnidadeFederacao() : StringUtils.EMPTY;
    }

    public String getNomeMunicipio() {
        return municipio != null ? municipio.getNomeMunicipio() : StringUtils.EMPTY;
    }

    public LocalDateTime getHistoricoLoginSistema() {
        return getUltimoLogin();
    }

    public void setHistoricoLoginSistema(Set<HistoricoLoginSistema> historicoLoginSistema) {
        this.historicoLoginSistema = historicoLoginSistema;
    }

    /**
     * Retorna a solicitação do usuário.
     * @return {@link SolicitacaoUsuario}.
     */
    public SolicitacaoUsuario getSolicitacaoUsuario() {
        if (solicitacaoUsuario == null ) {
            solicitacaoUsuario = new SolicitacaoUsuario();
        }
        return solicitacaoUsuario;
    }

    public void setSolicitacaoUsuario(SolicitacaoUsuario solicitacaoUsuario) {
        this.solicitacaoUsuario = solicitacaoUsuario;
    }

    /**
     * Retorna a referência de {@link PostoTrabalho} do {@link UsuarioSistema}.
     * O {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho} é uma entidade que reflete uma tabela
     * associativa na base de dados. A cardinalidade do relacionamento entre o
     * {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema} e a referida entitidade é '1 para 1'. Contudo,
     * para atender ao requisito de exclusão lógica da arquitetura do sistema foi necessário o mapeamento da lista,
     * utilizando o recurso {@link org.hibernate.annotations.Where} para eliminar os registros excluídos logicamente.
     *
     * @return {@link UsuarioPostoTrabalho}.
     */
    public UsuarioPostoTrabalho getUsuarioPostoTrabalho() {
        if (Objects.nonNull(listUsuarioPostoTrabalho) && !listUsuarioPostoTrabalho.isEmpty()) {
            return listUsuarioPostoTrabalho.get(0);
        } else {
            return new UsuarioPostoTrabalho();
        }
    }

    public List<UsuarioPostoTrabalho> getListUsuarioPostoTrabalho() {
        return listUsuarioPostoTrabalho;
    }

    /**
     * Inclui o {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho} na lista.
     * O {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho} é uma entidade que reflete uma tabela
     * associativa na base de dados, a cardinalidade do relacionamento entre o
     * {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema} e a referida entitidade é '1 para 1'. Contudo,
     * para atender ao requisito de exclusão lógica da arquitetura do sistema foi necessário o mapeamento da lista,
     * utilizando o recurso {@link org.hibernate.annotations.Where} para eliminar os registros excluídos logicamente.
     */
    public void setUsuarioPostoTrabalho(UsuarioPostoTrabalho usuarioPostoTrabalho) {
        listUsuarioPostoTrabalho = new ArrayList<>();
        listUsuarioPostoTrabalho.add(usuarioPostoTrabalho);
    }

    public void setListUsuarioPostoTrabalho(List<UsuarioPostoTrabalho> listUsuarioPostoTrabalho) {
        this.listUsuarioPostoTrabalho = listUsuarioPostoTrabalho;
    }

    public Integer getIdentificacaoPostoTrabalho() {
        return getUsuarioPostoTrabalho().getIdentificacaoPostoTrabalho();
    }

    /**
     * Altera a identificação do PostoTrabalho.
     * @param identificacaoPostoTrabalho identificação nova.
     */
    public void setIdentificacaoPostoTrabalho(Integer identificacaoPostoTrabalho) {
        getUsuarioPostoTrabalho().setIdentificacaoPostoTrabalho(identificacaoPostoTrabalho);
    }

    /**
     * Altera a identificação da {@link UnidadeOrganizacional} do {@link PostoTrabalho} do {@link UsuarioSistema}.
     * @param identificUnidOrganizac nova identificação.
     */
    public void setUnidadeOrganizacionalPostoTrabalho(Long identificUnidOrganizac) {
        this.getUsuarioPostoTrabalho().getPostoTrabalho().setIdentificacaoUnidOrganizac(identificUnidOrganizac);
    }

    public Long getUnidadeOrganizacionalPostoTrabalho() {
        return getUsuarioPostoTrabalho().getPostoTrabalho().getIdentificacaoUnidOrganizac();
    }

    /**
     * Altera a identificação do {@link PostoTrabalho} do {@link UsuarioSistema}.
     * @param identificacaoPostoTrabalho nova identificação.
     */
    public void setPostoTrabalho(Integer identificacaoPostoTrabalho) {
        UsuarioPostoTrabalho usuarioPostoTrabalho = new UsuarioPostoTrabalho();
        usuarioPostoTrabalho.setIdentificacaoPostoTrabalho(identificacaoPostoTrabalho);
        setUsuarioPostoTrabalho(usuarioPostoTrabalho);
    }

    /**
     * Retorna o status da solicitação.
     * @return situação da solicitação.
     */
    public SituacaoSolicitacaoEnum getStatusSolicitacao() {
        boolean isNull = Objects.isNull(solicitacaoUsuario);
        return  isNull ? null : SituacaoSolicitacaoEnum.getValue(
                getSolicitacaoUsuario().getSituacaoSolicitacao().getCode());
    }

    public LocalDateTime getDataSolicitacao() {
        return Objects.isNull(solicitacaoUsuario) ? null : getSolicitacaoUsuario().getDataInsercao();
    }

    public String getNomeEstado() {
        return Objects.isNull(municipio) || Objects.isNull(municipio.getEstado())
                ? StringUtils.EMPTY : municipio.getEstado().getNomeEstado();
    }

    public String getNomeCidade() {
        return municipio.getNomeMunicipio();
    }

    /**
     * Busca o nome da Unidade Organizacional referente ao usuário.
     * @return nome da Unidade Organizacional.
     */
    public String getNomeUnidOrganizac() {
        if (Objects.isNull(getUsuarioPostoTrabalho()) || Objects.isNull(getUsuarioPostoTrabalho().getPostoTrabalho())
                || Objects.isNull(getUsuarioPostoTrabalho().getPostoTrabalho().getUnidadeOrganizacional())) {
            return StringUtils.EMPTY;
        } else {
            return getUsuarioPostoTrabalho().getPostoTrabalho().getUnidadeOrganizacional()
                    .getNomeUnidOrganizac();
        }
    }

    /**
     * Busca o nome do Posto de Trabalho referente ao usuário.
     * @return nome do Posto de Trabalho.
     */
    public String getNomePostoDeTrabalho() {
        UsuarioPostoTrabalho usuarioPostoTrabalho = getUsuarioPostoTrabalho();
        if (Objects.nonNull(usuarioPostoTrabalho) && Objects.nonNull(usuarioPostoTrabalho.getPostoTrabalho())) {
            return usuarioPostoTrabalho.getPostoTrabalho().getNomePostoTrabalho();
        } else {
            return StringUtils.EMPTY;
        }
    }

    /**
     * Altera o nome do {@link PostoTrabalho}.
     * @param nomePostoDeTrabalho novo nome.
     */
    public void setNomePostoDeTrabalho(String nomePostoDeTrabalho) {
        UsuarioPostoTrabalho usuarioPostoTrabalho = getUsuarioPostoTrabalho();
        if (Objects.nonNull(usuarioPostoTrabalho) && Objects.nonNull(usuarioPostoTrabalho.getPostoTrabalho())) {
            usuarioPostoTrabalho.getPostoTrabalho().setNomePostoTrabalho(nomePostoDeTrabalho);
        }
    }

    private LocalDateTime getUltimoLogin() {
        return historicoLoginSistema.stream()
                .map(HistoricoLoginSistema::getDataHoraLogin)
                .sorted((o1, o2) -> o1.compareTo(o2) * -1)
                .findFirst()
                .orElse(null);
    }

    public String getInscricaoEstadualNegocio() {
        return Objects.isNull(solicitacaoUsuario) ? StringUtils.EMPTY
                : getSolicitacaoUsuario().getInscricaoEstadualNegocio();
    }

    /**
     * Altera a {@link SolicitacaoUsuario#inscricaoEstadualNegocio}.
     * @param inscricaoEstadualNegocio nova inscrição.
     */
    public void setInscricaoEstadualNegocio(String inscricaoEstadualNegocio) {
        getSolicitacaoUsuario().setInscricaoEstadualNegocio(inscricaoEstadualNegocio);
    }

    public String getCnpjNegocio() {
        return Objects.isNull(solicitacaoUsuario) ? null : getSolicitacaoUsuario().getCnpjNegocio();
    }

    /**
     * Altera o {@link SolicitacaoUsuario#cnpjNegocio}.
     * @param cnpjNegocio novo CNPJ.
     */
    public void setCnpjNegocio(String cnpjNegocio) {
        getSolicitacaoUsuario().setCnpjNegocio(cnpjNegocio);
    }

    public String getUsuarioPerfis() {
        return usuarioPerfis;
    }

    public void setUsuarioPerfis(String usuarioPerfis) {
        this.usuarioPerfis = usuarioPerfis;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Set<UsuarioPerfil> getUsuarioPerfil() {
        return usuarioPerfil;
    }

    public void setUsuarioPerfil(Set<UsuarioPerfil> usuarioPerfil) {
        this.usuarioPerfil = usuarioPerfil;
    }

    public String getListPerfisCommaSeparated() {
        return Objects.isNull(usuarioPerfil) ? StringUtils.EMPTY : usuarioPerfil.stream()
                .map(UsuarioPerfil::getNomePerfilSistema)
                .collect(Collectors.joining(", "));
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
