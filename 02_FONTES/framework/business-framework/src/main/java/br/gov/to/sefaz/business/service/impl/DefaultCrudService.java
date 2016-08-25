package br.gov.to.sefaz.business.service.impl;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * Classe genérica dos serviços de CRUD com operações de paginação e ordenação.
 *
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public class DefaultCrudService<E extends AbstractEntity<I>, I extends Serializable>
        implements CrudService<E, I> {

    private final BaseRepository<E, I> repository;

    public DefaultCrudService(BaseRepository<E, I> repository) {
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
        return repository.findAll();
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
    public Collection<E> save(@ValidationSuite(context = ValidationContext.SAVE) Collection<E> list) {
        return repository.save(list);
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
    public Collection<E> update(@ValidationSuite(context = ValidationContext.UPDATE) Collection<E> list) {
        return repository.save(list);
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
        repository.delete(ids);
    }

    protected BaseRepository<E, I> getRepository() {
        return repository;
    }

}
