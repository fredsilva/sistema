package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repositório de acesso à base dados da entidade {@link PostoTrabalho}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class PostoTrabalhoRepository extends BaseRepository<PostoTrabalho, Integer> {

    /**
     * Busca todos os {@link PostoTrabalho} referentes à Unidade passada por parâmetro.
     * @param identificUnidOrganizac identificação da
     * {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional}
     * @return lista de {@link br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho}.
     */
    public Collection<PostoTrabalho> findAllByUnidadeOrganizacional(Long identificUnidOrganizac) {
        return find(select -> select.where().equal("identificacaoUnidOrganizac", identificUnidOrganizac));
    }
}
