package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasFaixaValor;
import br.gov.to.sefaz.business.service.CrudService;
import org.springframework.stereotype.Service;

/**
 * Serviço para operações de gerenciamento de {@link PedidoAreasFaixaValor}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 25/05/2016 16:36:00
 */
@Service
public interface PedidoAreasFaixaValorService extends CrudService<PedidoAreasFaixaValor, Integer> {
}
