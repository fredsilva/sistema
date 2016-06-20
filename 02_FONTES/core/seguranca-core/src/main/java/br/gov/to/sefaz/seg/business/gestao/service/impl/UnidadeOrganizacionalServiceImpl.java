package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;
import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UnidadeOrganizacionalFilter;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.repository.UnidadeOrganizacionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    public UnidadeOrganizacionalServiceImpl(UnidadeOrganizacionalRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "identificacaoUnidOrganizac")));
    }

    @Override
    protected UnidadeOrganizacionalRepository getRepository() {
        return (UnidadeOrganizacionalRepository) super.getRepository();
    }

    @Override
    public List<UnidadeOrganizacional> findAll(UnidadeOrganizacionalFilter filter) {
        return getRepository()
                .findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                        .like("nomeUnidOrganizac", filter.getNomeUnidOrganizac())
                        .like("unidOrganizacPai", filter.getUnidOrganizacPai())
                        .build());
    }

    @Override
    public Optional<UnidadeOrganizacional> delete(
            @ValidationSuite(context = ValidationContext.DELETE, clazz = UnidadeOrganizacional.class) Long id) {
        UnidadeOrganizacional unidadeOrganizacional = findOne(id);
        validateDelete(unidadeOrganizacional);
        return super.delete(id);
    }

    /**
     * Classe para deleção de Unidade Organizacional.
     * @param unidadeOrganizacional unidade organizacional para remover pelo ID.
     */
    @SuppressWarnings("PMD")
    private void validateDelete(@ValidationSuite(context = ValidationContext.DELETE) UnidadeOrganizacional
            unidadeOrganizacional){
        //Método que serve pra validar UnidadeOrganizacional unidadeOrganizacional.
    }
}
