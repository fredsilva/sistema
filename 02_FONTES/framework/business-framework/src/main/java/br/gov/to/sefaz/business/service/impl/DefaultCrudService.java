package br.gov.to.sefaz.business.service.impl;

import br.gov.to.sefaz.business.service.CrudService;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe genérica dos serviços de CRUD com operações de paginação e ordenação.
 *
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public class DefaultCrudService<E, I extends Serializable> implements CrudService<E, I> {

    private final CrudRepository<E, I> repository;

    public DefaultCrudService(CrudRepository<E, I> repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public E findOne(I id) {
        return repository.findOne(id);
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<E> findAll() {
        return IteratorUtils.toList(repository.findAll().iterator());
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<E> findAll(Iterable<I> list) {
        return IteratorUtils.toList(repository.findAll(list).iterator());
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Collection<E> save(Iterable<E> list) {
        return IteratorUtils.toList(repository.save(list).iterator());
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public E update(E entity) {
        return repository.save(entity);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Optional<E> delete(I id) {
        repository.delete(id);

        return Optional.empty();
    }

    @Transactional
    @Override
    public void delete(Iterable<I> ids) {
        for (I id : ids) {
            repository.delete(id);
        }
    }

    protected CrudRepository<E, I> getRepository() {
        return repository;
    }
}
