package br.gov.to.sefaz.arr.repository;

import br.gov.to.sefaz.arr.model.entity.Bancos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author roger.gouveia
 */
@Repository
public interface BancosRepository extends PagingAndSortingRepository<Bancos, Integer> {
}
