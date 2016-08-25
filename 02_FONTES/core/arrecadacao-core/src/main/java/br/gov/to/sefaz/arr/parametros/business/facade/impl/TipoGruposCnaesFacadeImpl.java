package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.TipoGruposCnaesFacade;
import br.gov.to.sefaz.arr.parametros.business.service.GruposCnaeService;
import br.gov.to.sefaz.arr.parametros.business.service.TipoGruposCnaesService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.TipoGruposCnaesFilter;
import br.gov.to.sefaz.arr.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.persistence.entity.GruposCnaePK;
import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.par.gestao.business.service.AtividadeEconomicaService;
import br.gov.to.sefaz.par.gestao.persistence.entity.AtividadeEconomica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Implementação da fachada da entidade PlanoContas.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Component
public class TipoGruposCnaesFacadeImpl extends DefaultCrudFacade<TipoGruposCnaes, Integer>
        implements TipoGruposCnaesFacade {

    private final AtividadeEconomicaService cnaeService;
    private final GruposCnaeService gruposCnaeService;

    @Autowired
    public TipoGruposCnaesFacadeImpl(
            TipoGruposCnaesService service, AtividadeEconomicaService cnaeService,
            GruposCnaeService gruposCnaeService) {
        super(service);
        this.cnaeService = cnaeService;
        this.gruposCnaeService = gruposCnaeService;
    }

    @Override
    protected TipoGruposCnaesService getService() {
        return (TipoGruposCnaesService) super.getService();
    }

    @Override
    public List<TipoGruposCnaes> find(TipoGruposCnaesFilter filter) {
        return getService().findAll(filter);
    }

    @Override
    public Collection<AtividadeEconomica> findAllCnaes() {
        return cnaeService.findAll();
    }

    @Override
    public Collection<AtividadeEconomica> findAllCnaesByGrupo(Integer idGrupoCnae) {
        return cnaeService.findAllCnaesByGrupo(idGrupoCnae);
    }

    @Override
    public void removeCnaeFromGrupo(Integer idGrupoCnae, String codigoCnae) {
        gruposCnaeService.delete(new GruposCnaePK(idGrupoCnae, codigoCnae));
    }

    @Override
    public void validateGruposCnaes(Collection<GruposCnae> gruposCnaes) {
        gruposCnaeService.validateDuplicated(gruposCnaes);
    }
}
