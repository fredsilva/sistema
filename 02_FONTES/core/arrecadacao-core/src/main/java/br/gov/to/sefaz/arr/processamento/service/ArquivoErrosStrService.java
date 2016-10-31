package br.gov.to.sefaz.arr.processamento.service;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoErrosStr;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoErrosStrPK;
import br.gov.to.sefaz.business.service.CrudService;

/**
 * Contrato de acesso do serviço de Erros de Arquivos STR.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 06/07/2016 18:48:00
 */
public interface ArquivoErrosStrService extends CrudService<ArquivoErrosStr, ArquivoErrosStrPK> {

}
