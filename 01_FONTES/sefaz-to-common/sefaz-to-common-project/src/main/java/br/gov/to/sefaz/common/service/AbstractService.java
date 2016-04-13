package br.gov.to.sefaz.common.service;

import br.gov.to.sefaz.common.service.iface.Service;
import org.springframework.data.repository.Repository;

import javax.validation.Validator;

/**
 * Classe genérica de serviços.
 *
 * @param <R> repositorio que persiste e gerencia informações no banco de dados.
 * @author cristiano.luis@ntconsult.com.br
 */
@SuppressWarnings("PMD")
public abstract class AbstractService<R extends Repository<?, ?>> implements Service {

    protected final R repository;
    protected final Validator validator;

    public AbstractService(R repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

}
