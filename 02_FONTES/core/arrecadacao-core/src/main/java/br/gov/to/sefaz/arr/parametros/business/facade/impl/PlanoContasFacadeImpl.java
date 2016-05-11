package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.PlanoContasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoContaEnum;
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

    private final PlanoContasService service;

    @Autowired
    public PlanoContasFacadeImpl(PlanoContasService service) {
        super(service);
        this.service = service;
    }

    public List<PlanoContas> find(String codigoPlano, String nomePlano, String codigoContabil,
            TipoContaEnum tipoConta) {
        return service.find(codigoPlano, nomePlano, codigoContabil, tipoConta);
    }

}
