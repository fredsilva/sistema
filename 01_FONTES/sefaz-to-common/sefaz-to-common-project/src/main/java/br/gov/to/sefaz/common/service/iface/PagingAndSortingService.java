package br.gov.to.sefaz.common.service.iface;

import br.gov.to.sefaz.common.model.SerializableEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface padrão dos serviços CRUD com paginação e ordenação.
 *
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public interface PagingAndSortingService
        <E extends SerializableEntity<I>, I extends Serializable> extends CrudService<E, I> {

    /**
     * Método que realiza uma consulta ordenada.
     * Use {@link #setSort} para setar a ordenação desejada.
     * 
     * @return Lista de entidades
     */
    Collection<E> findAll();

    /**
     * Método que realiza uma consulta paginada conforme pageRequest.
     *
     * @param pageRequest dados referentes a estrutura da pagina retornada
     * @return Página com lista de entidades
     */
    Page<E> find(PageRequest pageRequest);

    /**
     * Método que realiza uma consulta paginada.
     *
     * @param page número da página
     * @param maxResults quantidade de registros/linhas da página
     * @return Página com lista de entidades
     */
    Page<E> find(Integer page, Integer maxResults);

    /**
     * Define a ordenação das doncultas.
     *
     * @param sort ordenação
     */
    void setSort(Sort sort);

}
