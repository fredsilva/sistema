package br.gov.to.sefaz.persistence.repository;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Interface base para repositorios, possui todos os básicos necessários para o sistema.
 *
 * @param <E> tipo da entidade que o repositório gerência
 * @param <I> o tipo de ID da entidade que o repositório gerência
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 12/05/2016 15:14:00
 */
public interface BaseRepository<E extends AbstractEntity<I>, I extends Serializable>
        extends PagingAndSortingRepository<E, I>, JpaSpecificationExecutor<E> {
}
