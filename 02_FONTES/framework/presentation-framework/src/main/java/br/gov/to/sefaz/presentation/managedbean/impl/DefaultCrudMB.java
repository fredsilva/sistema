package br.gov.to.sefaz.presentation.managedbean.impl;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.presentation.managedbean.BeanFactoryMB;
import br.gov.to.sefaz.presentation.managedbean.CrudMB;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 * Implementação default de um {@link CrudMB}.
 *
 * @param <E> Tipo da entidade gerenciada pelo serviço.
 * @param <I> Tipo do ID da entidade.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/04/2016 18:52:00
 */
@ManagedBean
public class DefaultCrudMB<E extends AbstractEntity<I>, I extends Serializable> implements CrudMB<E, I> {

    @ManagedProperty("#{springBeanFactoryMB}")
    private BeanFactoryMB beanFactoryMB;
    private Collection<E> resultList;
    private E dto;
    private CrudFacade<E, I> facade;
    private final Supplier<E> dtoProvider;

    public DefaultCrudMB(Supplier<E> dtoProvider) {
        this.dtoProvider = dtoProvider;
        clearDto();
    }

    @PostConstruct
    protected void injectDependencies() {
        beanFactoryMB.injectBeans(this);
    }

    protected void setFacade(CrudFacade<E, I> facade) {
        this.facade = facade;
    }

    protected CrudFacade<E, I> getFacade() {
        return this.facade;
    }

    protected BeanFactoryMB getBeanFactoryMB() {
        return beanFactoryMB;
    }

    public void setBeanFactoryMB(BeanFactoryMB beanFactoryMB) {
        this.beanFactoryMB = beanFactoryMB;
    }

    public void setDto(E dto) {
        this.dto = dto;
    }

    public E getDto() {
        return dto;
    }

    public void setResultList(Collection<E> resultList) {
        this.resultList = resultList;
    }

    /**
     * {@inheritDoc}.
     */
    public Collection<E> getResultList() {
        if (resultList == null) {
            resultList = getFacade().findAll();
        }

        return resultList;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void save() {
        getFacade().save(getDto());
        getResultList().add(getDto());
        clearDto();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update() {
        getFacade().update(getDto());
        getResultList().removeIf(e -> e.getId().equals(getDto().getId()));
        getResultList().add(getDto());
        clearDto();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void delete() {
        Optional<E> delete = getFacade().delete(getDto().getId());
        getResultList().removeIf(e -> e.getId().equals(getDto().getId()));

        if (delete.isPresent()) {
            getResultList().add(delete.get());
        }

        clearDto();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void clearDto() {
        setDto(dtoProvider.get());
    }
}
