package br.gov.to.sefaz.common.controller.iface;

import br.gov.to.sefaz.common.model.SerializableEntity;

import org.springframework.data.domain.Page;

import java.io.Serializable;

/**
 * Interface de acesso às operações controle de  com paginação e ordenação.
 *
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public interface PagingAndSortingController<E extends SerializableEntity<I>, I extends Serializable> {

    /**
     * Retorna a quantidade de elementos da página ou tabela.
     *
     * @return quantidade de elementos da página ou tabela
     */
    Integer getPageSize();

    /**
     * Define a quantidade de elementos da página ou tabela.
     *
     * @param pageSize quantidade de elementos da página ou tabela
     */
    void setPageSize(Integer pageSize);

    /**
     * Retorna a página da tabela.
     *
     * @return página da tabela
     */
    Page<E> getPage();

    /**
     * Carrega a página da tabela.
     */
    void loadPage();

    /**
     * Carrega a página da tabela.
     *
     * @param pageNumber número da página
     */
    void loadPage(Integer pageNumber);

}
