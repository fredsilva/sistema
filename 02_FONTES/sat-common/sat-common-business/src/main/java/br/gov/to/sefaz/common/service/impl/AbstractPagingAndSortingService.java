package br.gov.to.sefaz.common.service.impl;

import br.gov.to.sefaz.common.model.SerializableEntity;
import br.gov.to.sefaz.common.service.PagingAndSortingService;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import javax.validation.Validator;

/**
 * Classe genérica dos serviços de CRUD com operações de paginação e ordenação.
 *
 * @param <R> Reposito de acesso a base de dados.
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public abstract class AbstractPagingAndSortingService
        <R extends PagingAndSortingRepository<E, I>, E extends SerializableEntity<I>, I extends Serializable>
        extends AbstractCrudService<R, E, I> implements PagingAndSortingService<E, I> {

    private Sort sort;

    public AbstractPagingAndSortingService(R repository, Validator validator) {
        super(repository, validator);
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    public Collection<E> findAll() {
        return IteratorUtils.toList(repository.findAll(this.sort).iterator());
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    public Page<E> find(PageRequest pageRequest) {
        PageRequest request = pageRequest;
        if (request.getSort() == null && this.sort != null) {
            request = new PageRequest(request.getPageNumber(), request.getPageSize(), this.sort);
        }
        return repository.findAll(request);
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    public Page<E> find(Integer page, Integer maxResults) {
        Page<E> result = executeQueryFindAll(page, maxResults, this.sort);
        if (shouldExecuteSameQuerynLastPage(page, result)) {
            Integer newPage = result.getTotalPages() - 1;
            result = executeQueryFindAll(newPage, maxResults, this.sort);
        }
        return result;
    }

    private Page<E> executeQueryFindAll(Integer page, Integer maxResults, Sort sort) {
        final PageRequest pageRequest = new PageRequest(page - 1, maxResults, sort);
        return this.find(pageRequest);
    }

    private boolean shouldExecuteSameQuerynLastPage(Integer page, Page<E> result) {
        return isUserAfterOrOnLastPage(page, result) && hasDatanDataBase(result);
    }

    private boolean isUserAfterOrOnLastPage(Integer page, Page<E> result) {
        return page > result.getTotalPages();
    }

    private boolean hasDatanDataBase(Page<E> result) {
        return result.getTotalElements() > 0;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }
}
