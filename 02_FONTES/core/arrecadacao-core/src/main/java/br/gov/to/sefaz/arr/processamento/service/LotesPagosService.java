package br.gov.to.sefaz.arr.processamento.service;

import br.gov.to.sefaz.arr.persistence.entity.LotesPagos;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosPK;
import br.gov.to.sefaz.business.service.CrudService;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 11:45:00
 */
public interface LotesPagosService extends CrudService<LotesPagos, LotesPagosPK> {
}
