package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.BancosFacade;
import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação da fachada da entidade {@link Bancos}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Component
public class BancosFacadeImpl extends DefaultCrudFacade<Bancos, Integer>
        implements BancosFacade {

    @Autowired
    public BancosFacadeImpl(BancosService service) {
        super(service);
    }
}
