package br.gov.to.sefaz.common.service.iface;

import br.gov.to.sefaz.common.model.SerializableEntity;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface padrão dos Serviços CRUD.
 *
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public interface CrudService<E extends SerializableEntity<I>, I extends Serializable> extends Service {

    /**
     * Consulta um registro pelo id.
     *
     * @param id ID da entidade
     * @return entidade
     */
    E findOne(I id);

    /**
     * Consulta todos os registros da base de dados.
     *
     * @return lista de entidades
     */
    @Transactional(readOnly = true)
    Collection<E> findAll();

    /**
     * Consulta uma lista de registros pelo id.
     *
     * @param list lista de id
     * @return lista de entidades
     */
    @Transactional(readOnly = true)
    Collection<E> findAll(Iterable<I> list);

    /**
     * Salva ou atualiza a entidade na base de dados.
     *
     * @param entity entidade
     * @return entidade
     */
    E save(E entity);

    /**
     * Salva ou atualiza uma lista entidades na base de dados.
     * 
     * @param list lista de entidades
     * @return lista de entidades
     */
    Collection<E> save(Iterable<E> list);

    /**
     * Remove um registro da base de dados.
     *
     * @param id ID da entidade
     */
    void delete(I id);

    /**
     * Remove uma lista de registros da base de dados.
     *
     * @param list lista de entidades
     */
    void delete(Iterable<E> list);

}
