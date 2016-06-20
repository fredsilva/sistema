package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;
import br.gov.to.sefaz.seg.business.gestao.service.PostoTrabalhoService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PostoTrabalhoFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.repository.PostoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço da entidade {@link PostoTrabalho}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/06/2016 09:22:00
 */
@Service
public class PostoTrabalhoServiceImpl extends DefaultCrudService<PostoTrabalho, Long> implements
        PostoTrabalhoService {

    @Autowired
    public PostoTrabalhoServiceImpl(PostoTrabalhoRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "identificacaoUnidOrganizac")));
    }

    @Override
    public List<PostoTrabalho> findAll(PostoTrabalhoFilter filter) {
        return getRepository()
                .findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                        .like("nomePostoTrabalho", filter.getNomePostoTrabalho())
                        .like("identificacaoUnidOrganizac", filter.getIdentificacaoUnidOrganizac())
                        .build());
    }

    @Override
    public Optional<PostoTrabalho> delete(
            @ValidationSuite(context = ValidationContext.DELETE, clazz = PostoTrabalho.class) Long id) {
        PostoTrabalho postoTrabalho = findOne(id);
        validateDelete(postoTrabalho);
        return super.delete(id);
    }

    /**
     * Classe para deleção de Unidade Organizacional.
     * @param postoTrabalho unidade organizacional para remover pelo ID.
     */
    @SuppressWarnings("PMD")
    private void validateDelete(@ValidationSuite(context = ValidationContext.DELETE) PostoTrabalho
            postoTrabalho){
        //Método que só serve pra validar
    }
}
