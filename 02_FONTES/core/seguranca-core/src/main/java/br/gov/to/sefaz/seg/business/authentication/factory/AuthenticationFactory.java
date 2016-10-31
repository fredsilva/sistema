package br.gov.to.sefaz.seg.business.authentication.factory;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupManager;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.domain.UsuarioSistemaAuthentication;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapel;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.seg.persistence.repository.ProcuracaoUsuarioRepository;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPerfilRepository;
import br.gov.to.sefaz.util.formatter.FormatterUtil;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Factory para criação de usuário sistema authenticado.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/06/2016 10:54:00
 */
@Component
public class AuthenticationFactory {

    private final UsuarioPerfilRepository usuarioPerfilRepository;
    private final ProcuracaoUsuarioRepository procuracaoUsuarioRepository;
    public static final String PROCURACAO_PATTERN = "%s (%s: %s)";

    @Autowired
    public AuthenticationFactory(UsuarioPerfilRepository usuarioPerfilRepository,
            ProcuracaoUsuarioRepository procuracaoUsuarioRepository) {
        this.usuarioPerfilRepository = usuarioPerfilRepository;
        this.procuracaoUsuarioRepository = procuracaoUsuarioRepository;
    }

    /**
     * Cria um usuário sistema autenticado.
     *
     * @param password       senha do usuário
     * @param usuarioSistema entidade com os detalhes do usuário
     * @return dados refentes ao usuario autenticado e a própria autenticação
     * @see br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema
     * @see org.springframework.security.core.Authentication
     */
    public UsuarioSistemaAuthentication create(String password, UsuarioSistema usuarioSistema) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String requestIp = attr.getRequest().getRemoteAddr();
        RoleGroupManager roleGroupManager = new RoleGroupManager();

        Set<UsuarioPerfil> usuarioPerfis = usuarioPerfilRepository
                .findAllByUsuarioSistema(usuarioSistema.getCpfUsuario(), SituacaoUsuarioEnum.ATIVO);

        List<PerfilSistema> perfis = usuarioPerfis.stream()
                .map(UsuarioPerfil::getPerfisSistema)
                .collect(Collectors.toList());
        Set<ProcuracaoUsuario> procuracaoUsuarios = procuracaoUsuarioRepository.findAllByCpfProcurado(usuarioSistema
                .getCpfUsuario());

        fillRoleGroupManager(roleGroupManager, perfis);
        fillProcuracaoGroupManager(roleGroupManager, procuracaoUsuarios);

        return new UsuarioSistemaAuthentication(password, requestIp, roleGroupManager, usuarioSistema);
    }

    private void fillRoleGroupManager(RoleGroupManager roleGroupManager, List<PerfilSistema> perfis) {
        Map<RoleGroupKey, List<String>> groups = perfis.stream().collect(Collectors.toMap(
                this::createRoleGroupKey, this::extractRoles));

        groups.entrySet()
                .forEach(g -> roleGroupManager.addRoles(g.getKey(), g.getValue()));

        if (perfis.size() == 1) {
            roleGroupManager.setActiveGroup(RoleGroupType.PERFIL, perfis.get(0).getId());
        }
    }

    private void fillProcuracaoGroupManager(RoleGroupManager roleGroupManager,
            Set<ProcuracaoUsuario> procuracaoUsuarios) {
        Map<RoleGroupKey, List<String>> groups = procuracaoUsuarios.stream().collect(Collectors.toMap(
                this::createProcuracaoGroupKey, this::extractProcuracoes));

        groups.entrySet()
                .forEach(g -> roleGroupManager.addRoles(g.getKey(), g.getValue()));
    }

    private RoleGroupKey createRoleGroupKey(PerfilSistema perfilSistema) {
        return new RoleGroupKey(RoleGroupType.PERFIL, perfilSistema.getId(),
                perfilSistema.getDescricaoPerfil());
    }

    private  List<String> extractRoles(PerfilSistema perfilSistema) {
        return perfilSistema.getPerfilPapel().stream()
                .map(PerfilPapel::getPapelSistema)
                .flatMap(papelSistema -> papelSistema.getPapelOpcao().stream())
                .map(papelOpcao -> papelOpcao.getOpcaoAplicacao().getId().toString())
                .collect(Collectors.toList());
    }

    private RoleGroupKey createProcuracaoGroupKey(ProcuracaoUsuario procuracaoUsuario) {
        String idType;
        String nomeCompletoUsuario;
        String formatCpfCnpj;

        if (StringUtils.isNotEmpty(procuracaoUsuario.getCpfOrigem())) {
            nomeCompletoUsuario = getNomeCpfProcurador(procuracaoUsuario);
            formatCpfCnpj = FormatterUtil.formatCpf(procuracaoUsuario.getCpfOrigem());
            idType = SourceBundle.getMessage(MessageUtil.SEG, "seg.geral.procuracaoUsuario.cpf");

        } else {
            nomeCompletoUsuario = procuracaoUsuario.getCnpjOrigemProcuracao().getNome();
            formatCpfCnpj = FormatterUtil.formatCpf(procuracaoUsuario.getCnpjOrigem());
            idType = SourceBundle.getMessage(MessageUtil.SEG, "seg.geral.procuracaoUsuario.cnpj");

        }

        String description = String.format(PROCURACAO_PATTERN,
                StringUtils.trimToEmpty(nomeCompletoUsuario), idType, formatCpfCnpj);

        return new RoleGroupKey(RoleGroupType.PROCURACAO, procuracaoUsuario.getId(), description);
    }

    private String getNomeCpfProcurador(ProcuracaoUsuario procuracaoUsuario) {
        String nomeCompletoUsuario;
        nomeCompletoUsuario = SourceBundle.getMessage(MessageUtil.SEG, "seg.geral.procuracaoUsuario.voceMesmo");
        if (!isProcuradorSameAsProcurado(procuracaoUsuario)) {
            nomeCompletoUsuario = procuracaoUsuario.getCpfOrigemProcuracao().getNome();
        }
        return nomeCompletoUsuario;
    }

    private boolean isProcuradorSameAsProcurado(ProcuracaoUsuario procuracaoUsuario) {
        return procuracaoUsuario.getCpfProcurado().equals(procuracaoUsuario.getCpfOrigem());
    }

    private  List<String> extractProcuracoes(ProcuracaoUsuario procuracaoUsuario) {
        return procuracaoUsuario.getProcuracaoOpcoes().stream().map(po -> po.getIdentificacaoOpcaoAplicacao()
                .toString()).collect(Collectors.toList());
    }
}