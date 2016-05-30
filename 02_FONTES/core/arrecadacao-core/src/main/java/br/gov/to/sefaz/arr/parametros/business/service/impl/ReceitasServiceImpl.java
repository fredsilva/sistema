package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.ReceitasRepasseService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasTaxasService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.ReceitasFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ReceitasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:18:00
 */
@Service
@Transactional
public class ReceitasServiceImpl extends DefaultCrudService<Receitas, Integer>
        implements ReceitasService {

    private final ReceitasTaxasService receitasTaxasService;
    private final ReceitasRepasseService receitasRepasseService;

    @Autowired
    public ReceitasServiceImpl(
            ReceitasRepository repository, ReceitasTaxasService receitasTaxasService,
            ReceitasRepasseService receitasRepasseService) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idReceita")));
        this.receitasTaxasService = receitasTaxasService;
        this.receitasRepasseService = receitasRepasseService;
    }

    @Override
    protected ReceitasRepository getRepository() {
        return (ReceitasRepository) super.getRepository();
    }

    @Override
    public Collection<Receitas> findAllActiveReceitas() {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .equalsTo("situacao", SituacaoEnum.ATIVO)
                .build(), getDefaultSort());
    }

    @Override
    public Collection<Receitas> getAllReceitasByIdConvenio(Long idConvenio) {
        return getRepository().findAllReceitasByIdConvenio(idConvenio);
    }

    @Override
    public List<Receitas> find(ReceitasFilter filter) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .like("idReceita", filter.getIdReceita())
                .like("descricaoReceita", filter.getDescricaoReceita())
                .equalsTo("classificacaoReceita", filter.getClassificacaoReceita())
                .equalsTo("tipoReceita", filter.getTipoReceita())
                .equalsTo("situacao", filter.getSituacao())
                .build(), getDefaultSort());
    }

    @Override
    public Receitas save(@ValidationSuite(context = ValidationContext.SAVE) Receitas receitas) {
        final Receitas savedReceitas = super.save(receitas);
        saveReceitasTaxas(receitas);
        saveReceitasRepasse(receitas);

        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");
        return savedReceitas;
    }

    @Override
    public Receitas update(@ValidationSuite(context = ValidationContext.UPDATE) Receitas receitas) {
        final Receitas updatesReceitas = super.update(receitas);
        saveReceitasTaxas(receitas);
        saveReceitasRepasse(receitas);

        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");
        return updatesReceitas;
    }

    @Override
    public Optional<Receitas> delete(Integer id) {

        Optional<Receitas> receitas;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            receitas = Optional.of(getRepository().findOne(id));

            MessageUtil.addMesage(MessageUtil.ARR, "parametros.delecao.logica");
        } else {
            receitasTaxasService.deleteAllTaxasByIdReceita(id);
            receitasRepasseService.deleteAllRepassesByIdReceita(id);
            super.delete(id);
            receitas = Optional.empty();

            MessageUtil.addMesage(MessageUtil.ARR, "parametros.delecao.fisica");
        }

        return receitas;
    }

    private void saveReceitasRepasse(Receitas receitas) {
        List<ReceitasRepasse> receitasRepasse = receitas.getReceitasRepasse();

        for (ReceitasRepasse repasse : receitasRepasse) {
            repasse.setIdReceita(receitas.getIdReceita());
        }

        receitasRepasseService.deleteAllRepassesByIdReceita(receitas.getIdReceita());
        receitasRepasseService.save(receitasRepasse);
    }

    private void saveReceitasTaxas(Receitas receitas) {
        List<ReceitasTaxas> receitasTaxas = receitas.getReceitasTaxas();

        for (ReceitasTaxas receitasTaxa : receitasTaxas) {
            receitasTaxa.setIdReceita(receitas.getIdReceita());
            receitasTaxa.setSituacao(receitas.getSituacao());
        }

        receitasTaxasService.deleteAllTaxasByIdReceita(receitas.getIdReceita());
        receitasTaxasService.save(receitasTaxas);
    }
}
