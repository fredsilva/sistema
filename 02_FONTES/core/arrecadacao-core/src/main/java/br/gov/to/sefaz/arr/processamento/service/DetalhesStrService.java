package br.gov.to.sefaz.arr.processamento.service;

import br.gov.to.sefaz.arr.persistence.entity.DetalheStr;
import br.gov.to.sefaz.business.service.CrudService;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Serviço para gerência de dados de {@link br.gov.to.sefaz.arr.persistence.entity.DetalheStr}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 08/07/2016 09:42:00
 */
public interface DetalhesStrService extends CrudService<DetalheStr, Long> {

    /**
     * Retorna a soma total dos valores informativos
     * {@link br.gov.to.sefaz.arr.persistence.entity.DetalheStr#valorInformativo} para a data de
     * arrecadação, banco e convênio correspodente.
     *
     * @param dataArrecadacao data de arrecadação.
     * @param idBanco         identificador do banco.
     * @param idConvenio      identificador do convênio.
     * @return a soma total dos valores informativos.
     */
    BigDecimal sumValorInformativo(LocalDate dataArrecadacao, Integer idBanco, Long idConvenio);
}
