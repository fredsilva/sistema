package br.gov.to.sefaz.business.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * Interface padrão dos serviços CRUD com paginação e ordenação.
 *
 * @param <E> Entidade que reflete o presente service.
 * @param <I> Id ou pk da entidade.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface CrudService<E, I extends Serializable> {

    /**
     * Consulta um registro pelo id.
     *
     * @param id ID da entidade
     * @return entidade
     */
    E findOne(I id);

    /**
     * Consulta todos os registros da base de dados.
     *
     * @return lista de entidades
     */
    Collection<E> findAll();

    /**
     * Consulta uma lista de registros pelo id.
     *
     * @param list lista de id
     * @return lista de entidades
     */
    Collection<E> findAll(Iterable<I> list);

    /**
     * Salva a entidade na base de dados.
     *
     * @param entity entidade
     * @return entidade
     */
    E save(E entity);

    /**
     * Salva ou atualiza uma lista entidades na base de dados.
     *
     * @param list lista de entidades
     * @return lista de entidades
     */
    Collection<E> save(Collection<E> list);

    /**
     * Atualiza a entidade na base de dados.
     *
     * @param entity entidade
     * @return entidade
     */
    E update(E entity);

    /**
     * Remove um registro da base de dados ou atualiza um registro. Se o retorno for um {@link java.util.Optional#EMPTY}
     * o registro deve ser removido da base, Caso o retorno for a entidade e estiver presente no
     * {@link java.util.Optional}, será realizada a atualização desta entidade. Por padrão o retorno é
     * {@link java.util.Optional#EMPTY} e o registro é removido da base de dados.
     *
     * @param id ID da entidade
     * @return Entidade a ser atualizada ou um {@link java.util.Optional#EMPTY} para remover o registro da base de
     *         dados.
     */
    Optional<E> delete(I id);

    /**
     * Remove uma lista de registros da base de dados.
     *
     * @param ids lista de entidades
     */
    void delete(Iterable<I> ids);
}
