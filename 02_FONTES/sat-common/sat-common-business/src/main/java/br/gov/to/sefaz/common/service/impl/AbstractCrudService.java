package br.gov.to.sefaz.common.service.impl;

import br.gov.to.sefaz.common.model.SerializableEntity;
import br.gov.to.sefaz.common.service.CrudService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import javax.validation.Validator;

/**
 * Classe genérica de serviços de CRUD.
 *
 * @param <R> Reposito de acesso a base de dados.
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public abstract class AbstractCrudService
        <R extends CrudRepository<E, I>, E extends SerializableEntity<I>, I extends Serializable>
        extends AbstractService<R> implements CrudService<E, I> {

    public AbstractCrudService(R repository, Validator validator) {
        super(repository, validator);
    }

    /**
     * Consulta um registro pelo id.
     *
     * @param id da entidade
     * @return entidade
     */
    @Transactional(readOnly = true)
    public E findOne(I id) {
        return repository.findOne(id);
    }

    /**
     * Consulta todos os registros da base de dados.
     *
     * @return lista de entidades
     */
    @Transactional(readOnly = true)
    public Collection<E> findAll() {
        return IteratorUtils.toList(repository.findAll().iterator());
    }

    /**
     * Consulta uma lista de registros pelo id.
     *
     * @param list lista de id
     * @return lista de entidades
     */
    @Transactional(readOnly = true)
    public Collection<E> findAll(Iterable<I> list) {
        return IteratorUtils.toList(repository.findAll(list).iterator());
    }

    /**
     * Salva ou atualiza a entidade na base de dados.
     *
     * @param entity entidade
     */
    public E save(E entity) {
        validator.validate(entity);
        // TODO: Repassar validações
        return repository.save(entity);
    }

    /**
     * Salva ou atualiza uma lista entidades na base de dados.
     *
     * @param list lista de entidades
     */
    public Collection<E> save(Iterable<E> list) {
        return IteratorUtils.toList(repository.save(list).iterator());
    }

    /**
     * Remove um registro da base de dados.
     *
     * @param id da entidade
     */
    public void delete(I id) {
        repository.delete(id);
    }

    /**
     * Remove um registro da base de dados.
     *
     * @param entity entidade
     */
    public void delete(E entity) {
        this.delete(entity.getId());
    }

    /**
     * Remove uma lista de registros da base de dados.
     *
     * @param list lista de entidades
     */
    public void delete(Iterable<E> list) {
        repository.delete(list);
    }

}
