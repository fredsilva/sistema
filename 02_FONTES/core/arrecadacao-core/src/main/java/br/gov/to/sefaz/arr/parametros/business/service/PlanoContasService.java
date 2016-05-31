package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.business.service.filter.PlanoContasFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.List;

/**
 * Contrato de acesso do serviço de Planos de Contas.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface PlanoContasService extends CrudService<PlanoContas, Long> {

    List<PlanoContas> find(PlanoContasFilter filter);

}
