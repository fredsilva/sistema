package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.PlanoContasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.PlanoContasFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementação do serviço da entidade PlanoContas.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Component
public class PlanoContasFacadeImpl extends DefaultCrudFacade<PlanoContas, Long> implements PlanoContasFacade {

    @Autowired
    public PlanoContasFacadeImpl(PlanoContasService service) {
        super(service);
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

}
