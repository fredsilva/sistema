package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.service.OpcaoAplicacaoService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.OpcaoAplicacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.repository.OpcaoAplicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrato de serviço da entidade {@link OpcaoAplicacao}.
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 19/07/2016 15:04:00
 */
@Service
public class OpcaoAplicacaoServiceImpl extends DefaultCrudService<OpcaoAplicacao, Long> implements
        OpcaoAplicacaoService {

    @Autowired
    public OpcaoAplicacaoServiceImpl(OpcaoAplicacaoRepository repository) {
        super(repository);
    }

    @Override
    protected OpcaoAplicacaoRepository getRepository() {
        return (OpcaoAplicacaoRepository) super.getRepository();
    }

    @Override
    public List<OpcaoAplicacao> findByFilter(final OpcaoAplicacaoFilter filter) {

        return getRepository().find("oa", select -> select
                .innerJoinFetch("oa.aplicacaoModulo", "am")
                .innerJoinFetch("am.moduloSistema", "ms")
                .where()
                    .opt().equal("am.identificacaoAplicacaoModulo", filter.getIdentificacaoAplicacao())
                    .and().opt().equal("ms.identificacaoModuloSistema", filter.getIdentificacaoModulo())

                .orderBy("ms.abreviacaoModulo", Order.ASC).andBy("oa.descripcaoOpcao", Order.ASC));
    }


    @Override
    public List<OpcaoAplicacao> findAllOpcoes() {
        return getRepository().findAllOpcaoAplicacao();
    }

    @Override
    public List<OpcaoAplicacao> findAllFromPerfilUsuario() {
        List<Long> roles = AuthenticatedUserHandler
                .getRolesByType(RoleGroupType.PERFIL).stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        return getRepository().findByIds(roles);
    }
}
