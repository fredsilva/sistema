package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.exception.BusinessException;
import br.gov.to.sefaz.exception.UnexpectedErrorException;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.authentication.domain.ChangePasswordDto;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.authentication.handler.LdapHandler;
import br.gov.to.sefaz.seg.business.authentication.provider.LdapProvider;
import br.gov.to.sefaz.seg.business.authentication.service.SecurityException;
import br.gov.to.sefaz.seg.business.gestao.service.SolicitacaoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPerfilService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPostoTrabalhoService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtivarInativarPerfilFilter;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtribuirPerfilFilter;
import br.gov.to.sefaz.seg.business.gestao.service.filter.ManterUsuarioSistemaFilter;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UsuarioSistemaFilter;
import br.gov.to.sefaz.seg.business.mail.builder.CorreioEletronicoBuilder;
import br.gov.to.sefaz.seg.business.mail.domain.Anexo;
import br.gov.to.sefaz.seg.business.mail.service.MailService;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.SolicitacaoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioSistemaRepository;
import br.gov.to.sefaz.util.formatter.FormatterUtil;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import br.gov.to.sefaz.util.report.PdfRender;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.mail.MessagingException;

/**
 * Implementação do serviço da entidade UsuarioSistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:38
 */
@SuppressWarnings("PMD.TooManyMethods")
@Service
public class UsuarioSistemaServiceImpl extends DefaultCrudService<UsuarioSistema, String>
        implements UsuarioSistemaService {

    public static final String LOGIN_CONTEXT = "LOGIN";
    public static final String SOLICITACAO_AUTORIZACAO_SENHA_CONTEXT = "SOLICITACAO_AUTORIZACAO_SENHA_CONTEXT";
    public static final String CHANGE_PASSWD_CONTEXT = "CHANGE_PASSWD";
    public static final String ATIVAR_INATIVAR_PERFIL_FILTER_CONTEXT = "ATIVAR_INATIVAR_PERFIL_FILTER_CONTEXT";
    public static final String ATRIBUIR_PERFIL_FILTER_CONTEXT = "MANTER_USUARIO_SISTEMA_FILTER_CONTEXT";
    public static final String MANTER_USUARIO_SISTEMA_FILTER_CONTEXT = "MANTER_USUARIO_SISTEMA_FILTER_CONTEXT";
    public static final String ATRIBUIR_PERFIL_USUARIO_CONTEXT = "ATRIBUIR_PERFIL_USUARIO_CONTEXT";
    private static final String HTML_NEW_LINE = "<br />";
    public static final String TERMO_RESPONSABILIDADE_PDF = "termo_de_responsabilidade_de_acesso.pdf";

    private final SolicitacaoUsuarioService solicitacaoUsuarioService;
    private final UsuarioPostoTrabalhoService usuarioPostoTrabalhoService;
    private final MailService mailService;
    private final LdapProvider ldapProvider;
    private final UsuarioPerfilService usuarioPerfilService;

    @Autowired
    public UsuarioSistemaServiceImpl(UsuarioSistemaRepository repository, SolicitacaoUsuarioService
            solicitacaoUsuarioService, UsuarioPostoTrabalhoService usuarioPostoTrabalhoService,
            MailService mailService, LdapProvider ldapProvider, UsuarioPerfilService usuarioPerfilService) {
        super(repository);
        this.solicitacaoUsuarioService = solicitacaoUsuarioService;
        this.usuarioPostoTrabalhoService = usuarioPostoTrabalhoService;
        this.mailService = mailService;
        this.ldapProvider = ldapProvider;
        this.usuarioPerfilService = usuarioPerfilService;
    }

    @Override
    protected UsuarioSistemaRepository getRepository() {
        return (UsuarioSistemaRepository) super.getRepository();
    }

    @Override
    public Collection<UsuarioSistema> findAll() {
        return getRepository().find(sb -> sb.orderBy("nomeCompletoUsuario", Order.ASC));
    }

    @Override
    public Collection<UsuarioSistema> findAllByCpfAndName(Long cpf, String nome) {
        return getRepository().find(sb -> sb.where()
                .opt().like("str(cpfUsuario)", cpf)
                .and().opt().like("nomeCompletoUsuario", nome)
                .and().opt().equal("codigoTipoUsuario", 4)
                .and().opt().equal("situacaoUsuario", SituacaoUsuarioEnum.ATIVO));
    }

    @Override
    public void validateLogin(@ValidationSuite(context = LOGIN_CONTEXT) UsuarioSistema usuarioSistema) {
        // Método que valida as ações de LOGIN de uma agência através da anotação de atributo.
    }

    @Transactional
    @Override
    public void blockUser(String cpf) {
        getRepository().updateEstaBloqueado(true, LocalDateTime.now().plusHours(2), cpf);
    }

    @Override
    public UsuarioSistema resetPassword(UsuarioSistema usuarioSistema) {
        String novaSenha = LdapHandler.getRandomPassword(8);

        try {
            ldapProvider.resetPassword(usuarioSistema.getCpfUsuario(), novaSenha);
            mailService.sendMail(new CorreioEletronicoBuilder()
                    .mailTo(usuarioSistema)
                    .subject(SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.subject"))
                    .body(getMailBodyResetPasswdMessage(usuarioSistema, novaSenha), true)
                    .build());
        } catch (SecurityException e) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "reset.password.ldap.error");
            throw new BusinessException(message, e);
        } catch (MessagingException e) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "reset.password.enviar.email");
            throw new UnexpectedErrorException(message, e);
        }

        usuarioSistema.setDataUltimaAlteracaoSenha(LocalDateTime.now());
        usuarioSistema.setAlterarSenhaProximoAcesso(Boolean.TRUE);
        usuarioSistema = save(usuarioSistema);
        return usuarioSistema;
    }

    @Override
    @Transactional
    public void changePassword(@ValidationSuite(context = CHANGE_PASSWD_CONTEXT) ChangePasswordDto dto) {
        String cpf = AuthenticatedUserHandler.getCpf();
        getRepository().updateDataUltimaAlteracaoSenha(LocalDateTime.now(), cpf);

        try {
            ldapProvider.authenticate(cpf, dto.getSenhaAtual());
        } catch (SecurityException e) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "change.password.senhaAtual.invalida");
            throw new BusinessException(message, e);
        }

        try {
            ldapProvider.resetPassword(cpf, dto.getNovaSenha());
        } catch (SecurityException e) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "change.password.ldap.error");
            throw new BusinessException(message, e);
        }
    }

    @Transactional
    @Override
    public void unblockUser(String cpf) {
        getRepository().updateEstaBloqueado(false, null, cpf);
    }

    @Override
    public List<UsuarioSistema> findAllUsuarioSistema(UsuarioSistemaFilter filter) {
        return getRepository().findAllUsuarioSistema(filter.getCpfUsuario(), filter.getNomeCompletoUsuario(), filter
                .getSituacaoUsuario(), filter.getTipoUsuario(), filter.getCodigoEstado(), filter.getCodigoMunicipio());
    }

    @Transactional
    @Override
    public void saveNewUsuarioSistemaSolicitacaoSenha(
            @ValidationSuite(context = SOLICITACAO_AUTORIZACAO_SENHA_CONTEXT) UsuarioSistema
                    usuarioSistema) {

        SolicitacaoUsuario solicitacaoUsuario = usuarioSistema.getSolicitacaoUsuario();

        // Salva usuário na base de dados
        usuarioSistema.setAlterarSenhaProximoAcesso(true);
        usuarioSistema.setSituacaoUsuario(SituacaoUsuarioEnum.INATIVO);
        usuarioSistema = save(usuarioSistema);

        // Salva a solicitação do usuário
        usuarioSistema.setSolicitacaoUsuario(solicitacaoUsuario);
        solicitacaoUsuarioService.save(usuarioSistema);

        // Cria o usuário no Ldap
        String randomPassword = LdapHandler.getRandomPassword(8);
        ldapProvider.createUser(usuarioSistema.getCpfUsuario(), randomPassword);

        // Envia e-mial de sucesso
        enviarEmailNewUsuarioSistemaSolicitacaoSenha(usuarioSistema, randomPassword);
    }

    private void enviarEmailNewUsuarioSistemaSolicitacaoSenha(UsuarioSistema
            usuarioSistema, String password) {

        String subject = SourceBundle.getMessage(MessageUtil.SEG, "seg.gestao.solicitacaoUsuario.form.mail.subject"
                + ".cadastroCompleto");
        String mailBody = getMailBodyCreateCadastroCompleto(usuarioSistema, password);
        Anexo anexo = null;

        // Se o tipo de usuário for contador, tipo = 1, o cadastro é incompleto
        if (usuarioSistema.getCodigoTipoUsuario().compareTo(1) == 0) {
            subject = SourceBundle.getMessage(MessageUtil.SEG, "seg.gestao.solicitacaoUsuario.form.mail.subject"
                    + ".cadastroIncompleto");
            mailBody = getMailBodyCreateCadastroIncompleto(usuarioSistema);
            anexo = new Anexo(TERMO_RESPONSABILIDADE_PDF, buildReport(usuarioSistema));
        }

        try {
            CorreioEletronicoBuilder correioEletronico = new CorreioEletronicoBuilder()
                    .mailTo(usuarioSistema).subject(subject).body(mailBody, true);
            if (Objects.nonNull(anexo)) {
                correioEletronico.attachments(anexo);
            }
            mailService.sendMail(correioEletronico.build());
        } catch (MessagingException | IOException e) {
            String message = SourceBundle.getMessage(MessageUtil.SEG, "reset.password.enviar.email");
            throw new UnexpectedErrorException(message, e);
        }
    }

    private byte[] buildReport(UsuarioSistema usuarioSistema) {
        SolicitacaoUsuario solicitacao = usuarioSistema.getSolicitacaoUsuario();

        URL url = getClass().getResource("/report/termo_responsabilidade_acesso.html");

        Map<String, Object> params = new HashMap<>();
        LocalDateTime dataInsercao = usuarioSistema.getDataInsercao();
        params.put("dataCriacao", FormatterUtil.formatDateTime(dataInsercao));
        params.put("ano", dataInsercao.getYear());
        params.put("id", solicitacao.getId());

        params.put("cpf", FormatterUtil.formatCpf(usuarioSistema.getCpfUsuario()));
        params.put("crc", usuarioSistema.getCrc());
        params.put("nome", usuarioSistema.getNomeCompletoUsuario());
        params.put("cep", FormatterUtil.formatCep(usuarioSistema.getCep()));
        params.put("logradouro", usuarioSistema.getLogradouro().getDescricaoLogradouro());
        params.put("endereco", usuarioSistema.getEndereco());
        params.put("numero", usuarioSistema.getNumeroEndereco());
        params.put("apartamento", usuarioSistema.getApartamento());
        params.put("complemento", usuarioSistema.getComplemento());
        params.put("bairro", usuarioSistema.getBairro());
        params.put("estado", usuarioSistema.getCodigoEstado());
        params.put("cidade", usuarioSistema.getMunicipio().getNomeMunicipio());
        params.put("telefone", usuarioSistema.getTelefoneResidencial());
        params.put("outroEndereco", usuarioSistema.getOutroEnderecoContato());
        params.put("email", usuarioSistema.getCorreioEletronico());
        params.put("celular", usuarioSistema.getTelefoneCelular());

        params.put("cnpj", StringUtils.isEmpty(usuarioSistema.getCnpjNegocio()) ? StringUtils.EMPTY : FormatterUtil
                .formatCnpj(usuarioSistema.getCnpjNegocio()));
        params.put("inscricao", usuarioSistema.getInscricaoEstadualNegocio());

        return PdfRender.createBytes(url, params);
    }

    @Override
    public UsuarioSistema save(@ValidationSuite(context = ValidationContext.SAVE) UsuarioSistema entity) {

        SolicitacaoUsuario solicitacaoUsuario = entity.getSolicitacaoUsuario();
        entity.setSolicitacaoUsuario(null);

        UsuarioPostoTrabalho usuarioPostoTrabalho = entity.getUsuarioPostoTrabalho();
        entity.setUsuarioPostoTrabalho(null);

        UsuarioSistema usuarioSistemaSaved = super.save(entity);

        saveSolicitacaoUsuario(solicitacaoUsuario, usuarioSistemaSaved);
        saveUsuarioPostoTrabalho(usuarioPostoTrabalho, usuarioSistemaSaved);

        return findOne(usuarioSistemaSaved.getId());
    }

    @Override
    public UsuarioSistema update(@ValidationSuite(context = ValidationContext.UPDATE) UsuarioSistema entity) {

        SolicitacaoUsuario solicitacaoUsuario = entity.getSolicitacaoUsuario();
        entity.setSolicitacaoUsuario(null);

        UsuarioPostoTrabalho usuarioPostoTrabalho = entity.getUsuarioPostoTrabalho();
        entity.setUsuarioPostoTrabalho(null);

        UsuarioSistema usuarioSistemaUpdated = super.update(entity);

        saveSolicitacaoUsuario(solicitacaoUsuario, usuarioSistemaUpdated);
        saveUsuarioPostoTrabalho(usuarioPostoTrabalho, usuarioSistemaUpdated);

        return findOne(usuarioSistemaUpdated.getId());
    }

    private String getMailBodyResetPasswdMessage(UsuarioSistema usuario, String newPassword) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append(SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.body.title",
                usuario.getNomeCompletoUsuario()))
                .append(HTML_NEW_LINE + HTML_NEW_LINE)
                .append(SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.body.description", newPassword))
                .append(HTML_NEW_LINE)
                .append(SourceBundle.getMessage(MessageUtil.SEG, "reset.password.email.body.ass"));
        return emailBody.toString();
    }

    private String getMailBodyCreateCadastroCompleto(UsuarioSistema usuario, String newPassword) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append(SourceBundle.getMessage(MessageUtil.SEG, "seg.gestao.solicitacaoUsuario.form.mail.body.title",
                usuario.getNomeCompletoUsuario()))
                .append(HTML_NEW_LINE)
                .append(SourceBundle.getMessage(MessageUtil.SEG,
                        "seg.gestao.solicitacaoUsuario.form.mail.body.description.cadastroCompleto", usuario
                                .getCpfUsuario(), newPassword))
                .append(HTML_NEW_LINE)
                .append(SourceBundle.getMessage(MessageUtil.SEG,
                        "seg.gestao.solicitacaoUsuario.form.mail.body.boaNavegacao"))
                .append(HTML_NEW_LINE)
                .append(SourceBundle.getMessage(MessageUtil.SEG,
                        "seg.gestao.solicitacaoUsuario.form.mail.body.signature"));
        return emailBody.toString();
    }

    private String getMailBodyCreateCadastroIncompleto(UsuarioSistema usuario) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append(SourceBundle.getMessage(MessageUtil.SEG, "seg.gestao.solicitacaoUsuario.form.mail.body.title",
                usuario.getNomeCompletoUsuario()))
                .append(HTML_NEW_LINE)
                .append(SourceBundle.getMessage(MessageUtil.SEG,
                        "seg.gestao.solicitacaoUsuario.form.mail.body.description.cadastroIncompleto"))
                .append(HTML_NEW_LINE)
                .append(SourceBundle.getMessage(MessageUtil.SEG,
                        "seg.gestao.solicitacaoUsuario.form.mail.body.signature"));
        return emailBody.toString();
    }

    @Override
    public List<UsuarioSistema> findAllUsuarioSistemaPerfil(@ValidationSuite(context =
            ATIVAR_INATIVAR_PERFIL_FILTER_CONTEXT)
            AtivarInativarPerfilFilter filter) {
        return getRepository().findAllUsuarioSistemaPerfil(filter.getCpfUsuario(), filter.getNomeCompletoUsuario(),
                filter.getCodigoUnidadeOrganizacional(), filter.getCodigoPostoTrabalho());
    }

    @Override
    public List<UsuarioSistema> findAllUsuarioSistemaManutencao(@ValidationSuite(context =
            MANTER_USUARIO_SISTEMA_FILTER_CONTEXT) ManterUsuarioSistemaFilter filter) {
        return getRepository().findAllUsuarioSistemaManutencao(filter.getNomeCompletoUsuario(), filter.getCpfUsuario(),
                filter.getLocalDataCriacao(), filter.getSituacaoUsuario(), filter.getCodigoUnidadeOrganizacional(),
                filter.getCodigoPostoTrabalho());
    }

    @Override
    public UsuarioSistema updateStatusUsuario(@ValidationSuite(context = ValidationContext.UPDATE) UsuarioSistema
            usuarioSistema) {
        usuarioSistema = findOne(usuarioSistema.getId());
        if (SituacaoUsuarioEnum.ATIVO.equals(usuarioSistema.getSituacaoUsuario())) {
            usuarioSistema.setSituacaoUsuario(SituacaoUsuarioEnum.INATIVO);
            ldapProvider.disableUser(usuarioSistema.getCpfUsuario());
        } else {
            usuarioSistema.setSituacaoUsuario(SituacaoUsuarioEnum.ATIVO);
            ldapProvider.enableUser(usuarioSistema.getCpfUsuario());
        }
        return update(usuarioSistema);
    }

    @Override
    public void enableUser(UsuarioSistema usuarioSistema) {
        ldapProvider.enableUser(usuarioSistema.getCpfUsuario());
    }

    private UsuarioPostoTrabalho saveUsuarioPostoTrabalho(UsuarioPostoTrabalho usuarioPostoTrabalho,
            UsuarioSistema usuarioSistema) {
        if (Objects.nonNull(usuarioPostoTrabalho)
                && Objects.nonNull(usuarioPostoTrabalho.getIdentificacaoPostoTrabalho())) {
            return usuarioPostoTrabalhoService.saveOrUpdate(usuarioSistema.getCpfUsuario(),
                    usuarioPostoTrabalho.getIdentificacaoPostoTrabalho());
        } else {
            usuarioPostoTrabalhoService.removeUsuarioPostoTrabalho(usuarioSistema.getCpfUsuario(),
                    usuarioPostoTrabalho.getIdentificacaoPostoTrabalho());
            return null;
        }
    }

    private SolicitacaoUsuario saveSolicitacaoUsuario(SolicitacaoUsuario solicitacaoUsuario,
            UsuarioSistema usuarioSistema) {
        if (Objects.nonNull(solicitacaoUsuario)) {
            solicitacaoUsuario.setCpfUsuario(usuarioSistema.getCpfUsuario());
            return solicitacaoUsuarioService.save(solicitacaoUsuario);
        } else {
            return null;
        }
    }

    @Override
    public Long countByTipoUsuario(TipoUsuario tipoUsuario) {
        return getRepository().countByTipoUsuario(tipoUsuario);
    }

    @Override
    public Collection<UsuarioSistema> findAllByFilter(@ValidationSuite(clazz = AtribuirPerfilFilter.class,
            context = ATRIBUIR_PERFIL_FILTER_CONTEXT) AtribuirPerfilFilter filter) {
        return getRepository().findAllByFilterParameters(filter.getCpfUsuario(),
                filter.getNomeCompletoUsuario(),
                filter.getCodigoTipoUsuario(),
                filter.getCodigoPerfil(),
                filter.getCodigoUnidadeOrganizacional(),
                filter.getCodigoPostoTrabalho());
    }

    @Override
    public String findNomeByCpf(String usuarioCpf) {
        return getRepository().findOneColumn("nomeCompletoUsuario", usuarioCpf);
    }

    @Override
    public void updateAtribuirUsuarioPerfil(@ValidationSuite(context = ATRIBUIR_PERFIL_USUARIO_CONTEXT)
            UsuarioSistema dto) {
        usuarioPerfilService.updateAtribuirUsuarioPerfil(dto.getUsuarioPerfil(), dto.getCpfUsuario());
    }

}
