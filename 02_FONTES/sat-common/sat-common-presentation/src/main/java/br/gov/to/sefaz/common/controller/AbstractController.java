package br.gov.to.sefaz.common.controller;

import br.gov.to.sefaz.common.service.Service;

/**
 * Classe genérica de acesso às operações.
 *
 * @param <S> Serviço utilizado
 * @author cristiano.luis@ntconsult.com.br
 */
@SuppressWarnings("PMD")
public abstract class AbstractController<S extends Service> {

    protected final S service;

    public AbstractController(S service) {
        this.service = service;
    }

}
