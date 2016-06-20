package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.BancosRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementação do serviço da entidade Bancos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Service
public class BancosServiceImpl extends DefaultCrudService<Bancos, Integer> implements BancosService {

    @Autowired
    public BancosServiceImpl(
            BancosRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idBanco")));
    }

    @Override
    protected BancosRepository getRepository() {
        return (BancosRepository) super.getRepository();
    }

    @Override
    public Bancos save(@ValidationSuite(context = ValidationContext.SAVE) Bancos entity) {
        return super.save(entity);
    }

    @Override
    public Bancos update(@ValidationSuite Bancos entity) {
        return super.update(entity);
    }

    @Override
    @Transactional
    public Optional<Bancos> delete(@ValidationSuite Integer id) {
        Optional<Bancos> banco;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacaoAgencias(id, SituacaoEnum.CANCELADO);
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            banco = Optional.of(getRepository().findOne(id));
        } else {
            super.delete(id);
            banco = Optional.empty();
        }

        return banco;
    }

    @Override
    public Collection<Bancos> findAllActiveBancos() {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .equalsTo("situacao", SituacaoEnum.ATIVO)
                .build(), getDefaultSort());
    }
}
