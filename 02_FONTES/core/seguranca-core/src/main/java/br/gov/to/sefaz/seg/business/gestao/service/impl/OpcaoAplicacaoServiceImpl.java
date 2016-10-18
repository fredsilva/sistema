package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.service.OpcaoAplicacaoService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.OpcaoAplicacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.AplicacaoModulo;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.repository.AplicacaoModuloRepository;
import br.gov.to.sefaz.seg.persistence.repository.OpcaoAplicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Contrato de serviço da entidade {@link OpcaoAplicacao}.
 *
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 19/07/2016 15:04:00
 */
@Service
public class OpcaoAplicacaoServiceImpl extends DefaultCrudService<OpcaoAplicacao, Long> implements
        OpcaoAplicacaoService {

    private final AplicacaoModuloRepository aplicacaoModuloRepository;

    @Autowired
    public OpcaoAplicacaoServiceImpl(OpcaoAplicacaoRepository repository,
            AplicacaoModuloRepository aplicacaoModuloRepository) {
        super(repository);
        this.aplicacaoModuloRepository = aplicacaoModuloRepository;
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

    @Override
    public OpcaoAplicacao save(@ValidationSuite(context = ValidationContext.SAVE) OpcaoAplicacao entity) {
        AplicacaoModulo aplicacaoModulo = entity.getAplicacaoModulo();
        entity.setAplicacaoModulo(null);

        if (Objects.isNull(entity.getIdentificacaoAplicacaoModulo())) {
            aplicacaoModulo = saveAplicacaoModulo(entity, aplicacaoModulo);
        }

        entity = super.save(entity);
        entity.setAplicacaoModulo(aplicacaoModulo);
        return entity;
    }

    @Override
    public OpcaoAplicacao update(@ValidationSuite(context = ValidationContext.UPDATE) OpcaoAplicacao opcaoAplicacao) {
        AplicacaoModulo aplicacaoModulo = opcaoAplicacao.getAplicacaoModulo();
        opcaoAplicacao.setAplicacaoModulo(null);

        if (Objects.isNull(opcaoAplicacao.getIdentificacaoAplicacaoModulo())) {
            aplicacaoModulo = saveAplicacaoModulo(opcaoAplicacao, aplicacaoModulo);
        }

        opcaoAplicacao = super.update(opcaoAplicacao);
        opcaoAplicacao.setAplicacaoModulo(aplicacaoModulo);
        return opcaoAplicacao;
    }

    private AplicacaoModulo saveAplicacaoModulo(@ValidationSuite(context = ValidationContext.SAVE)
            OpcaoAplicacao opcaoAplicacao, AplicacaoModulo aplicacaoModulo) {
        ModuloSistema moduloSistema = aplicacaoModulo.getModuloSistema();
        AplicacaoModulo persistedAplicacaoModulo = aplicacaoModuloRepository.save(aplicacaoModulo);

        persistedAplicacaoModulo.setModuloSistema(moduloSistema);
        opcaoAplicacao.setIdentificacaoAplicacaoModulo(persistedAplicacaoModulo.getIdentificacaoAplicacaoModulo());

        return persistedAplicacaoModulo;
    }
}
