package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.exception.BusinessException;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.facade.ProcuracaoUsuarioFacade;
import br.gov.to.sefaz.seg.business.gestao.service.OpcaoAplicacaoService;
import br.gov.to.sefaz.seg.business.gestao.service.ProcuracaoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Implementação de um {@link ProcuracaoUsuarioFacade}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/08/2016 17:39:00
 */
@Component
public class ProcuracaoUsuarioFacadeImpl extends DefaultCrudFacade<ProcuracaoUsuario, Long>
        implements ProcuracaoUsuarioFacade {

    private final OpcaoAplicacaoService opcaoService;
    private final UsuarioSistemaService usuarioService;

    @Autowired
    public ProcuracaoUsuarioFacadeImpl(ProcuracaoUsuarioService service, OpcaoAplicacaoService opcaoService,
            UsuarioSistemaService usuarioService) {
        super(service);
        this.opcaoService = opcaoService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected ProcuracaoUsuarioService getService() {
        return (ProcuracaoUsuarioService) super.getService();
    }

    @Override
    public List<OpcaoAplicacao> getOpcoesFromUsuario() {
        return opcaoService.findAllFromPerfilUsuario();
    }

    @Override
    public UsuarioSistema findUsuarioSistema() {
        return AuthenticatedUserHandler.getUsuarioSistema();
    }

    @Override
    public String findUsuarioNomeById(String usuarioCpf) {
        String nomeByCpf = usuarioService.findNomeByCpf(usuarioCpf);
        if (!Objects.isNull(nomeByCpf)) {
            return nomeByCpf;
        }

        String message = SourceBundle.getMessage(MessageUtil.SEG, "seg.gestao.manterProcuracao.procuradorNaoExiste");
        throw new BusinessException(message);
    }

    @Override
    public ProcuracaoUsuario findProcuracaoByCpf(String cpfOrigem, String procuradorCpf) {
        return getService().findProcuracaoByCpf(cpfOrigem, procuradorCpf);
    }

    @Override
    public ProcuracaoUsuario findProcuracaoByCnpj(String cnpjOrigem, String procuradorCpf) {
        return getService().findProcuracaoByCnpj(cnpjOrigem, procuradorCpf);
    }
}
