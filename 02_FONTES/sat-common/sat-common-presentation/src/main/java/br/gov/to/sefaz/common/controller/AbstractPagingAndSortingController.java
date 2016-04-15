package br.gov.to.sefaz.common.controller;

import br.gov.to.sefaz.common.controller.iface.PagingAndSortingController;
import br.gov.to.sefaz.common.model.SerializableEntity;
import br.gov.to.sefaz.common.service.PagingAndSortingService;

import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * Classe genérica de acesso às operações de CRUD com operações de paginação e ordenação.
 *
 * @param <S> Service.
 * @param <E> Entidade
 * @param <I> Id ou pk da entidade
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public abstract class AbstractPagingAndSortingController
        <S extends PagingAndSortingService<E, I>, E extends SerializableEntity<I>, I extends Serializable>
        extends AbstractCrudController<S, E, I> implements PagingAndSortingController<E, I> {

    protected Page<E> page;

    protected Integer pageSize = 10;

    public AbstractPagingAndSortingController(S service) {
        super(service);
    }

    public Integer getPageNumber() {
        return page != null ? page.getNumber() + 1 : 1;
    }

    /**
     * {@inheritDoc}.
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * {@inheritDoc}.
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * {@inheritDoc}.
     */
    public Page<E> getPage() {
        return page;
    }

    /**
     * {@inheritDoc}.
     */
    public void loadPage() {
        findPage(getPageNumber(), pageSize);
    }

    /**
     * {@inheritDoc}.
     */
    public void loadPage(Integer pageNumber) {
        findPage(pageNumber, pageSize);
    }

    private void findPage(Integer pageNumber, Integer pageSize) {
        page = service.find(pageNumber, pageSize);
        super.list = page.getContent();
    }

}
