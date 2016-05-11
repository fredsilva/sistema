package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.BancoAgenciasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.AgenciasService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação da fachada de Agências Bancárias.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/05/2016 11:59:00
 */
@Component
public class BancoAgenciasFacadeImpl extends DefaultCrudFacade<BancoAgencias, BancoAgenciasPK>
        implements BancoAgenciasFacade {

    @Autowired
    public BancoAgenciasFacadeImpl(AgenciasService service) {
        super(service);
    }

}
