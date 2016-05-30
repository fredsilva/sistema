package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoAreasFaixaValorService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasFaixaValor;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoAreasFaixaValorRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Implementação de um {@link PedidoAreasFaixaValorService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 25/05/2016 16:39:00
 */
@Service
public class PedidoAreasFaixaValorServiceImpl extends DefaultCrudService<PedidoAreasFaixaValor, Integer>
        implements PedidoAreasFaixaValorService {

    @Autowired
    public PedidoAreasFaixaValorServiceImpl(PedidoAreasFaixaValorRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idPedidoArea")));
    }
}
