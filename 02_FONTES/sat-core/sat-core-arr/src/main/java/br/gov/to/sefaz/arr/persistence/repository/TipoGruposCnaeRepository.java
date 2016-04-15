package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnae;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade Bancos.
 *
 * @author roger.gouveia
 */
@Repository
public interface TipoGruposCnaeRepository extends PagingAndSortingRepository<TipoGruposCnae, Integer> {
}
