package br.gov.to.sefaz.business.service.impl;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.domain.Sort;
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
public class DefaultCrudService<E extends AbstractEntity<I>, I extends Serializable>
        implements CrudService<E, I> {

    private final BaseRepository<E, I> repository;
    private final Sort defaultSort;

    public DefaultCrudService(
            BaseRepository<E, I> repository, Sort sort) {
        this.repository = repository;
        this.defaultSort = sort;
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
        return IterableUtils.toList(repository.findAll(defaultSort));
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<E> findAll(Iterable<I> list) {
        return IterableUtils.toList(repository.findAll(list));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public E save(@ValidationSuite(context = ValidationContext.SAVE) E entity) {
        return repository.save(entity);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Collection<E> save(Collection<E> list) {
        return IterableUtils.toList(repository.save(list));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public E update(@ValidationSuite(context = ValidationContext.UPDATE) E entity) {
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

    protected BaseRepository<E, I> getRepository() {
        return repository;
    }

    public Sort getDefaultSort() {
        return defaultSort;
    }
}
