package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.GruposCnaeService;
import br.gov.to.sefaz.arr.parametros.business.service.TipoGruposCnaesService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.TipoGruposCnaesFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.arr.parametros.persistence.repository.TipoGruposCnaesRepository;
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

import java.util.ArrayList;
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
public class TipoGruposCnaesServiceImpl extends DefaultCrudService<TipoGruposCnaes, Integer>
        implements TipoGruposCnaesService {

    private final GruposCnaeService gruposCnaeService;

    @Autowired
    public TipoGruposCnaesServiceImpl(
            TipoGruposCnaesRepository repository, GruposCnaeService gruposCnaeService) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idGrupoCnae")));
        this.gruposCnaeService = gruposCnaeService;
    }

    @Override
    protected TipoGruposCnaesRepository getRepository() {
        return (TipoGruposCnaesRepository) super.getRepository();
    }

    @Override
    public TipoGruposCnaes save(@ValidationSuite(context = ValidationContext.SAVE) TipoGruposCnaes entity) {
        TipoGruposCnaes save = saveOrUpdate(entity);

        return save;
    }

    @Override
    public TipoGruposCnaes update(@ValidationSuite(context = ValidationContext.UPDATE) TipoGruposCnaes entity) {
        TipoGruposCnaes save = saveOrUpdate(entity);

        return save;
    }

    @Override
    public List<TipoGruposCnaes> findAll(TipoGruposCnaesFilter filter) {
        return getRepository()
                .findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                        .like("idGrupoCnae", filter.getIdGrupoCnae())
                        .like("descricaoGrupo", filter.getDescricaoGrupo())
                        .equalsTo("gruposCnae.cnaeFiscal", filter.getCnaeFiscal())
                        .build());
    }

    @Override
    @Transactional
    public Optional<TipoGruposCnaes> delete(Integer id) {
        Optional<TipoGruposCnaes> tipoRejeicao;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            tipoRejeicao = Optional.of(getRepository().findOne(id));

            MessageUtil.addMesage(MessageUtil.ARR, "parametros.delecao.logica");
        } else {
            gruposCnaeService.deleteByGrupo(id);
            getRepository().delete(id);
            tipoRejeicao = Optional.empty();

            MessageUtil.addMesage(MessageUtil.ARR, "parametros.delecao.fisica");
        }

        return tipoRejeicao;
    }

    private TipoGruposCnaes saveOrUpdate(@ValidationSuite(context = ValidationContext.UPDATE) TipoGruposCnaes entity) {
        // A entidade Pai não pode ser salva com os filhos dentro
        Collection<GruposCnae> gruposCnae = entity.getGruposCnae();
        entity.setGruposCnae(new ArrayList<>());

        // Salva apenas a entidade pai
        TipoGruposCnaes save = getRepository().save(entity);

        // Força o Id do pai nos filhos antes de salva-los
        gruposCnae.stream().forEach(gc -> gc.setIdGrupoCnae(save.getIdGrupoCnae()));
        gruposCnaeService.save(gruposCnae);

        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");

        return save;
    }
}