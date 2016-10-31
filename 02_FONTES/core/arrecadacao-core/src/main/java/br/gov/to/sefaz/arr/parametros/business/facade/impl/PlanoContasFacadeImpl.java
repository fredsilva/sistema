package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.PlanoContasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.parametros.business.service.TipoGruposCnaesService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.PlanoContasFilter;
import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Implementação do serviço da entidade PlanoContas.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Component
public class PlanoContasFacadeImpl extends DefaultCrudFacade<PlanoContas, Long> implements PlanoContasFacade {

    private final TipoGruposCnaesService tipoGruposCnaesService;

    @Autowired
    public PlanoContasFacadeImpl(PlanoContasService service, TipoGruposCnaesService tipoGruposCnaesService) {
        super(service);
        this.tipoGruposCnaesService = tipoGruposCnaesService;
    }

    @Override
    protected PlanoContasService getService() {
        return (PlanoContasService) super.getService();
    }

    /**
     * Busca os PlanoContas filtrados.
     * @param filter filtro preenchido em tela.
     * @return lista de PlanoContas.
     */
    public List<PlanoContas> find(PlanoContasFilter filter) {
        return getService().find(filter);
    }

    @Override
    public Collection<TipoGruposCnaes> findAllActiveTipoGruposCnaes() {
        return tipoGruposCnaesService.findAllActive();
    }

}
