package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link org.springframework.stereotype.Repository} responsavel por operações de persistencia de
 * tipos de rejeição de arquivos.
 *
 * @author gabriel.dias
 */
@Repository
public interface TipoRejeicaoArquivosRepository
        extends PagingAndSortingRepository<TipoRejeicaoArquivos, Integer> {
}
