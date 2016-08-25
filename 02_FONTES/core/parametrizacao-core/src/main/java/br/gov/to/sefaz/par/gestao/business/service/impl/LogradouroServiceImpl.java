package br.gov.to.sefaz.par.gestao.business.service.impl;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.par.gestao.business.service.LogradouroService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import br.gov.to.sefaz.par.gestao.persistence.repository.LogradouroRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementação do Serviço de {@link Logradouro}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 14/07/2016 17:47:00
 */
@Service
public class LogradouroServiceImpl implements LogradouroService {

    private final LogradouroRepository repository;
    private final Sort defaultSort;

    @Autowired
    public LogradouroServiceImpl(LogradouroRepository repository) {
        this.repository = repository;
        this.defaultSort = new Sort(new Sort.Order(Sort.Direction.ASC, "codigoLogradouro"));
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public Logradouro findOne(String id) {
        return repository.findOne(id);
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<Logradouro> findAll() {
        return IterableUtils.toList(repository.findAll(defaultSort));
    }

    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly = true)
    public Collection<Logradouro> findAll(Iterable<String> list) {
        return IterableUtils.toList(repository.findAll(list));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Logradouro save(@ValidationSuite(context = ValidationContext.SAVE) Logradouro entity) {
        return repository.save(entity);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Collection<Logradouro> save(Collection<Logradouro> list) {
        return IterableUtils.toList(repository.save(list));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Logradouro update(@ValidationSuite(context = ValidationContext.UPDATE) Logradouro entity) {
        return repository.save(entity);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Collection<Logradouro> update(Collection<Logradouro> list) {
        return IterableUtils.toList(repository.save(list));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Optional<Logradouro> delete(String id) {
        repository.delete(id);
        return Optional.empty();
    }

    @Transactional
    @Override
    public void delete(Iterable<String> ids) {
        for (String id : ids) {
            repository.delete(id);
        }
    }

    protected LogradouroRepository getRepository() {
        return repository;
    }
}
