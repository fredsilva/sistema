package br.gov.to.sefaz.arr.repository;

import br.gov.to.sefaz.arr.model.entity.TipoRejeicaoArquivos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gabriel.dias
 */
@Repository
public interface TipoRejeicaoArquivosRepository
        extends PagingAndSortingRepository<TipoRejeicaoArquivos, Integer> {
}
