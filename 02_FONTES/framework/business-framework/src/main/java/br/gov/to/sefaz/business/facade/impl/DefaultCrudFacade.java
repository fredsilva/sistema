package br.gov.to.sefaz.business.facade.impl;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.business.service.CrudService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * Implementação default de uma {@link br.gov.to.sefaz.business.facade.CrudFacade}.
 *
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
public class DefaultCrudFacade<E, I extends Serializable> implements CrudFacade<E, I> {

    private final CrudService<E, I> service;

    public DefaultCrudFacade(CrudService<E, I> service) {
        this.service = service;
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public E findOne(I id) {
        return service.findOne(id);
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<E> findAll() {
        return service.findAll();
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<E> findAll(Iterable<I> list) {
        return service.findAll(list);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public E save(E entity) {
        return service.save(entity);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Collection<E> save(Collection<E> list) {
        return service.save(list);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public E update(E entity) {
        return service.update(entity);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Optional<E> delete(I id) {
        return service.delete(id);
    }

    @Override
    public void delete(Iterable<I> ids) {
        service.delete(ids);
    }

    protected CrudService<E, I> getService() {
        return service;
    }
}
