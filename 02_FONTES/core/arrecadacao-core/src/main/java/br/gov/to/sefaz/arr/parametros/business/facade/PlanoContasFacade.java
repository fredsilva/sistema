package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.parametros.business.service.filter.PlanoContasFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.business.facade.CrudFacade;

import java.util.List;

/**
 * Contrato de acesso do serviço de Tipos de Rejeições de Arquivos.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface PlanoContasFacade extends CrudFacade<PlanoContas, Long> {

    /**
     * Busca PlanoContas filtrado.
     * @param filter filtro preenchido em tela.
     * @return Lista de PlanoContas.
     */
    List<PlanoContas> find(PlanoContasFilter filter);
}
