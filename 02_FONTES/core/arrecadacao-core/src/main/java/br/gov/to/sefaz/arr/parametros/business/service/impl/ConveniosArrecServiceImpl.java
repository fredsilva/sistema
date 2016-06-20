package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.ConveniosArrecFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosReceitas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ConveniosArrecRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:17:29
 */
@Service
@Transactional
public class ConveniosArrecServiceImpl extends DefaultCrudService<ConveniosArrec, Long>
        implements ConveniosArrecService {

    private final ConveniosTarifasService conveniosTarifasService;
    private final ConveniosReceitasService receitasService;

    @Autowired
    public ConveniosArrecServiceImpl(
            ConveniosArrecRepository repository,
            ConveniosTarifasService conveniosTarifasService, ConveniosReceitasService receitasService) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idConvenio")));
        this.conveniosTarifasService = conveniosTarifasService;
        this.receitasService = receitasService;
    }

    @Override
    public ConveniosArrecRepository getRepository() {
        return (ConveniosArrecRepository) super.getRepository();
    }

    @Override
    @Transactional
    public ConveniosArrec save(@ValidationSuite(context = ValidationContext.SAVE) ConveniosArrec entity) {
        final ConveniosArrec save = super.save(entity);

        saveAllConveniosTarifas(entity);
        saveAllConveniosReceitas(entity);

        return save;
    }

    @Override
    @Transactional
    public ConveniosArrec update(@ValidationSuite(context = ValidationContext.UPDATE) ConveniosArrec entity) {
        final ConveniosArrec update = super.update(entity);

        saveAllConveniosTarifas(entity);
        saveAllConveniosReceitas(entity);

        return update;
    }

    @Override
    public Optional<ConveniosArrec> delete(Long id) {
        Optional<ConveniosArrec> conveniosArrec;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            conveniosArrec = Optional.of(getRepository().findOne(id));

        } else {
            conveniosTarifasService.deleteAllByIdConvenio(id);
            receitasService.deleteAllByIdConvenio(id);
            super.delete(id);
            conveniosArrec = Optional.empty();

        }

        return conveniosArrec;
    }

    @Override
    public List<ConveniosArrec> find(ConveniosArrecFilter filter) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .like("idConvenio", filter.getIdConvenio())
                .like("descricaoConvenio", filter.getDescricaoConvenio())
                .equalsTo("idBanco", filter.getIdBanco())
                .equalsTo("tipoConvenio", filter.getTipoConvenio())
                .equalsTo("tipoBarra", filter.getTipoBarra())
                .build(), getDefaultSort());
    }

    private void saveAllConveniosTarifas(ConveniosArrec entity) {
        entity.getConveniosTarifas().stream()
                .forEach(conveniosTarifas -> conveniosTarifas.setIdConveniosArrec(entity.getIdConvenio()));

        conveniosTarifasService.deleteAllByIdConvenio(entity.getIdConvenio());
        conveniosTarifasService.save(entity.getConveniosTarifas());
    }

    private void saveAllConveniosReceitas(ConveniosArrec entity) {
        Collection<ConveniosReceitas> conveniosReceitas = new ArrayList<>();
        entity.getReceitas().stream().forEach(receitas -> conveniosReceitas
                .add(new ConveniosReceitas(receitas.getIdReceita(), entity.getIdConvenio())));

        receitasService.deleteAllByIdConvenio(entity.getIdConvenio());
        receitasService.save(conveniosReceitas);
    }
}
