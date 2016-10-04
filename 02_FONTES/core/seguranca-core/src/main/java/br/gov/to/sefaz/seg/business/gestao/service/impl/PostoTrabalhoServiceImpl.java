package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.gestao.service.PostoTrabalhoService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PostoTrabalhoFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.repository.PostoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço da entidade {@link PostoTrabalho}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/06/2016 09:22:00
 */
@Service
public class PostoTrabalhoServiceImpl extends DefaultCrudService<PostoTrabalho, Integer> implements
        PostoTrabalhoService {

    public static final String POSTO_TRABALHO_FILTER_CONTEXT = "POSTO_TRABALHO_FILTER_CONTEXT";

    @Autowired
    public PostoTrabalhoServiceImpl(PostoTrabalhoRepository repository) {
        super(repository);
    }

    @Override
    protected PostoTrabalhoRepository getRepository() {
        return (PostoTrabalhoRepository) super.getRepository();
    }

    @Override
    public List<PostoTrabalho> findAll(PostoTrabalhoFilter filter) {
        return getRepository().find("pt", sb -> sb
                        .innerJoinFetch("pt.unidadeOrganizacional")
                        .where()
                        .opt().like("pt.nomePostoTrabalho", filter.getNomePostoTrabalho())
                        .and().opt().equal("pt.identificacaoUnidOrganizac", filter.getIdentificacaoUnidOrganizac()));
    }

    @Override
    public Collection<PostoTrabalho> findAllByUnidadeOrganizacional(Long identificUnidOrganizac) {
        return getRepository().findAllByUnidadeOrganizacional(identificUnidOrganizac);
    }

    @Override
    public PostoTrabalho save(@ValidationSuite(context = ValidationContext.SAVE) PostoTrabalho entity) {
        entity = super.save(entity);
        return findOne(entity.getId());
    }

    @Override
    public PostoTrabalho update(@ValidationSuite(context = ValidationContext.UPDATE) PostoTrabalho entity) {
        entity = super.update(entity);
        return findOne(entity.getId());
    }

    @Override
    public Optional<PostoTrabalho> delete(
            @ValidationSuite(context = ValidationContext.DELETE, clazz = PostoTrabalho.class) Integer id) {
        PostoTrabalho postoTrabalho = findOne(id);
        validateDelete(postoTrabalho);
        return super.delete(id);
    }

    /**
     * Classe para deleção de Posto Trabalho.
     * @param postoTrabalho Posto Trabalho para remover pelo ID.
     */
    @SuppressWarnings("PMD")
    private void validateDelete(@ValidationSuite(context = ValidationContext.DELETE, onlyCustom = true) PostoTrabalho
            postoTrabalho){
        //Método que só serve pra validar
    }
}
