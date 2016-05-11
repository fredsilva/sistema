package br.gov.to.sefaz.presentation.managedbean;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface de acesso às operações controle de CRUD.
 *
 * @param <E> Tipo da entidade gerenciada pelo serviço
 * @param <I> Tipo do ID da entidade
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface CrudMB<E extends AbstractEntity<I>, I extends Serializable> {

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
     * Retorna a lista de entidades, recomenda-se uma implementação lazy.
     *
     * @return lista de entidades
     */
    Collection<E> getResultList();

    /**
     * Persiste o {@link #getDto()} na base de dados.
     */
    void save();

    /**
     * Atualiza uma entidade a partir do {@link #getDto()} na base de dados.
     */
    void update();

    /**
     * Remove ou atualiza o registro da base de dados a partir do {@link #getDto()}.
     */
    void delete();

    /**
     * Reseta o estado do {@link #getDto()}.
     */
    void clearDto();
}
