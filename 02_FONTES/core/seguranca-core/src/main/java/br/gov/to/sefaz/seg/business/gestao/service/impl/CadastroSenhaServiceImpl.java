package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.par.gestao.business.service.LogradouroService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.gestao.service.CadastroSenhaService;
import br.gov.to.sefaz.seg.business.gestao.service.SolicitacaoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.CadastroSenhaFilter;
import br.gov.to.sefaz.seg.persistence.entity.SolicitacaoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoSolicitacaoEnum;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Implementação do serviço da entidade UsuarioSistema para a tela de Cadastro de Senha.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 16:12:00
 */
@Service
public class CadastroSenhaServiceImpl extends DefaultCrudService<UsuarioSistema, String>
        implements CadastroSenhaService {

    private final UsuarioSistemaService usuarioSistemaService;
    private final SolicitacaoUsuarioService solicitacaoUsuarioService;
    private final LogradouroService logradouroService;

    @Autowired
    public CadastroSenhaServiceImpl(UsuarioSistemaRepository repository, UsuarioSistemaService usuarioSistemaService,
            SolicitacaoUsuarioService solicitacaoUsuarioService, LogradouroService logradouroService) {
        super(repository);
        this.usuarioSistemaService = usuarioSistemaService;
        this.solicitacaoUsuarioService = solicitacaoUsuarioService;
        this.logradouroService = logradouroService;
    }

    @Override
    public Collection<UsuarioSistema> findAll() {
        return getRepository().find(select -> select.orderBy("nomeCompletoUsuario", Order.ASC));
    }

    @Override
    public Collection<UsuarioSistema> findAll(CadastroSenhaFilter filter) {
        return getRepository().find("us", select -> select
                .leftJoinFetch("us.listUsuarioPostoTrabalho", "upt")
                .leftJoinFetch("upt.postoTrabalho", "pt")
                .leftJoinFetch("pt.unidadeOrganizacional","uo")
                .where().opt()
                .like("us.nomeCompletoUsuario", filter.getNomeCompletoUsuario())
                .and().opt().equal("us.cpfUsuario", filter.getCpfUsuario())
                .and().opt().equal("us.solicitacaoUsuario.situacaoSolicitacao", filter.getSituacaoSolicitacao())
                .and().opt().equal("trunc(us.solicitacaoUsuario.dataInsercao)", filter.getDate())
                .orderBy("us.nomeCompletoUsuario", Order.ASC));
    }

    @Override
    public void resetPassword(UsuarioSistema usuarioSistema) {
        usuarioSistemaService.resetPassword(usuarioSistema);
    }

    @Override
    public void authorizeUser(UsuarioSistema usuarioSistema) {

        SolicitacaoUsuario solicitacaoUsuario = usuarioSistema.getSolicitacaoUsuario();

        usuarioSistema.setSituacaoUsuario(SituacaoUsuarioEnum.ATIVO);
        usuarioSistema.setDataDesbloqueio(LocalDateTime.now());
        solicitacaoUsuario.setSituacaoSolicitacao(SituacaoSolicitacaoEnum.CRIADO);

        usuarioSistemaService.update(usuarioSistema);
        usuarioSistemaService.enableUser(usuarioSistema);
        solicitacaoUsuarioService.save(solicitacaoUsuario);
    }

    @Override
    public UsuarioSistema findOneUsuarioSistema(String cpfUsuario) {
        return usuarioSistemaService.findOne(cpfUsuario);
    }

    @Override
    public Collection<Logradouro> findAllLogradouros() {
        return logradouroService.findAll();
    }
}
