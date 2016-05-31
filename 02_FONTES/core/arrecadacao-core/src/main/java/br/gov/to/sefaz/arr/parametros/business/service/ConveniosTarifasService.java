package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link ConveniosTarifas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 06/05/2016 10:47:47
 */
public interface ConveniosTarifasService extends CrudService<ConveniosTarifas, Integer> {

    /**
     * Serviço para obter as {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas} por um
     * determinado {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec#idConvenio}.
     *
     * @param idConvenio código do convenio para realizar a consulta
     * @return um lista com todas as tarifas que pertencem a um convenio
     */
    Collection<ConveniosTarifas> getAllConveniosTarifasByIdConvenioArrec(Long idConvenio);

    /**
     * Serviço para remover as {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas} por um
     * determinado {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec#idConvenio}.
     *
     * @param idConvenio código do convenio para realizar a remoção
     */
    void deleteAllByIdConvenio(Long idConvenio);

    /**
     * Valida se a {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas} não está duplicada
     * conforme a regra de négocio, onde a
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas#getFormaPagamento()} e a
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas#getDataInicio()} não devem ser iguais
     * para um registro presente em
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec#conveniosTarifas}.
     *
     * @param conveniosArrec convênio arrecadação a ser validado.
     * @param conveniosTarifas tarifa a ser validada.
     */
    void validateDuplicatedTarifa(ConveniosArrec conveniosArrec, ConveniosTarifas conveniosTarifas);

    /**
     * Valida se a {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas#dataFim} é superior a
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosTarifas#dataInicio}.
     *
     * @param conveniosTarifas tarifa a ser validada.
     */
    void validateDataFimTarifa(ConveniosTarifas conveniosTarifas);
}
