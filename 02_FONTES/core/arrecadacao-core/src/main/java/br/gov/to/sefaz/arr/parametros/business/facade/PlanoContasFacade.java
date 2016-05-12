package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoContaEnum;
import br.gov.to.sefaz.business.facade.CrudFacade;

import java.util.List;

/**
 * Contrato de acesso do serviço de Tipos de Rejeições de Arquivos.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface PlanoContasFacade extends CrudFacade<PlanoContas, Long> {

    List<PlanoContas> find(String codigoPlano, String nomePlano, String codigoContabil, TipoContaEnum tipoConta);
}