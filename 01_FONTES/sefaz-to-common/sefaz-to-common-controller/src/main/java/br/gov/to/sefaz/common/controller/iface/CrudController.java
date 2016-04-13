package br.gov.to.sefaz.common.controller.iface;

import br.gov.to.sefaz.common.model.SerializableEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface de acesso às operações controle de CRUD.
 *
 * @param <E> Tipo da entidade gerenciada pelo serviço
 * @param <I> Tipo do ID da entidade
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public interface CrudController<E extends SerializableEntity<I>, I extends Serializable> {

    /**
     * Retorna a entidade que esta ou sera manipulada no momento.
     * 
     * @return entidade
     */
    E getDto();

    /**
     * Define o estado da entidade e seus valores.
     *
     * @param dto entidade que esta ou sera manipulada no momento
     */
    void setDto(E dto);

    /**
     * Carrega carrega em memória uma lista de entidades.
     */
    void loadList();

    /**
     * Retorna a lista de entidades.
     * 
     * @return lista de entidades
     */
    Collection<E> getList();

    /**
     * Persiste a entidade na base de dados.
     */
    void save();

    /**
     * Remove o registro da base de dados a partir do ID.
     *
     * @param id ID da entidade
     */
    void delete(I id);

    /**
     * Seleciona um item da lista de lista de entidades.
     *
     * @param id ID da entidade
     */
    void select(I id);

    /**
     * Limpa o estado da entidade.
     */
    void clear();

}
