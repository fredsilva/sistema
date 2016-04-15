package br.gov.to.sefaz.common.model;

import java.io.Serializable;

/**
 * Interface das entidades do projeto.
 *
 * @author cristiano.luis@ntconsult.com.br
 * @param <I> Tipo do ID da entidade.
 */
public interface SerializableEntity<I extends Serializable> {

    /**
     * Retorna o id da entidade.
     *
     * @return id da entidade
     */
    I getId();

}
