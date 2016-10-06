package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.par.gestao.business.service.ParametroGeralService;
import br.gov.to.sefaz.seg.business.gestao.converter.TipoUnidadeConverter;
import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UnidadeOrganizacionalFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUnidade;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.repository.UnidadeOrganizacionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço da entidade UnidadeOrganizacional.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/06/2016 09:22:00
 */
@Service
public class UnidadeOrganizacionalServiceImpl extends DefaultCrudService<UnidadeOrganizacional, Long> implements
        UnidadeOrganizacionalService {

    private static final String LISTAGEM_TIPO_UNIDADE = "LISTAGEM_TIPO_UNIDADE";

    private final ParametroGeralService parametroGeralService;

    @Autowired
    public UnidadeOrganizacionalServiceImpl(UnidadeOrganizacionalRepository repository, ParametroGeralService
            parametroGeralService) {
        super(repository);
        this.parametroGeralService = parametroGeralService;
    }

    @Override
    protected UnidadeOrganizacionalRepository getRepository() {
        return (UnidadeOrganizacionalRepository) super.getRepository();
    }

    @Override
    public List<UnidadeOrganizacional> findByFilter(UnidadeOrganizacionalFilter filter) {
        return getRepository().find("uo", select -> select
                .leftJoinFetch("uo.unidadeOrganizacionalPai")
                .where().opt().like("uo.nomeUnidOrganizac", filter.getNomeUnidOrganizac())
                .and().opt().equal("uo.unidOrganizacPai", filter.getUnidOrganizacPai())
                .and().opt().equal("uo.codigoTipoUnidade", filter.getTipoUnidade()));
    }

    @Override
    public UnidadeOrganizacional save(@ValidationSuite(context = ValidationContext.SAVE)
            UnidadeOrganizacional entity) {
        entity = super.save(entity);
        return findOne(entity.getId());
    }

    @Override
    public UnidadeOrganizacional update(@ValidationSuite(context = ValidationContext.UPDATE)
            UnidadeOrganizacional entity) {
        entity = super.update(entity);
        return findOne(entity.getId());
    }

    @Override
    public Optional<UnidadeOrganizacional> delete(
            @ValidationSuite(context = ValidationContext.DELETE, clazz = UnidadeOrganizacional.class) Long id) {
        UnidadeOrganizacional unidadeOrganizacional = findOne(id);
        validateDelete(unidadeOrganizacional);
        return super.delete(id);
    }

    @Override
    public List<TipoUnidade> findTiposUnidades() {
        return parametroGeralService.findCodeData(new TipoUnidadeConverter(), LISTAGEM_TIPO_UNIDADE);
    }

    /**
     * Classe para deleção de Unidade Organizacional.
     *
     * @param unidadeOrganizacional unidade organizacional para remover pelo ID.
     */
    @SuppressWarnings("PMD")
    private void validateDelete(@ValidationSuite(context = ValidationContext.DELETE, onlyCustom = true)
            UnidadeOrganizacional unidadeOrganizacional) {
        //Método que serve pra validar UnidadeOrganizacional unidadeOrganizacional.
    }
}
