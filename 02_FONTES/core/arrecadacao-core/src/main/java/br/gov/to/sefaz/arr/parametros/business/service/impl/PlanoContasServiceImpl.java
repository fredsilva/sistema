package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.PlanoContasFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PlanoContasRepository;
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
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço da entidade PlanoContas.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Service
public class PlanoContasServiceImpl extends DefaultCrudService<PlanoContas, Long> implements PlanoContasService {

    @Autowired
    public PlanoContasServiceImpl(
            PlanoContasRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "codigoPlanoContas")));
    }

    @Override
    protected PlanoContasRepository getRepository() {
        return (PlanoContasRepository) super.getRepository();
    }

    @Override
    public PlanoContas save(@ValidationSuite(context = ValidationContext.SAVE) PlanoContas entity) {
        PlanoContas planoContas = super.save(entity);

        return planoContas;
    }

    @Override
    public PlanoContas update(@ValidationSuite(context = ValidationContext.UPDATE) PlanoContas entity) {
        PlanoContas planoContas = super.update(entity);

        return planoContas;
    }

    @Override
    @Transactional
    public Optional<PlanoContas> delete(Long id) {
        Optional<PlanoContas> planoContas;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            planoContas = Optional.of(getRepository().findOne(id));

        } else {
            super.delete(id);
            planoContas = Optional.empty();

        }

        return planoContas;
    }

    @Override
    public List<PlanoContas> find(PlanoContasFilter filter) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .like("codigoPlanoContas", filter.getCodigoPlanoContas())
                .like("nomeConta", filter.getNomeConta())
                .like("codigoContabil", filter.getCodigoContabil())
                .equalsTo("tipoConta", filter.getTipoConta())
                .fetch("gruposCnaes")
                .build(), getDefaultSort());
    }

    @Override
    public Collection<PlanoContas> findAll() {

        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .fetch("gruposCnaes")
                .build(), getDefaultSort());
    }
}
