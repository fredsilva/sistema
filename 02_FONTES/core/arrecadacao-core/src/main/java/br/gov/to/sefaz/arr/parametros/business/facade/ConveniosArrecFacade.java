package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.parametros.business.service.filter.ConveniosArrecFilter;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.business.facade.CrudFacade;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de Convenios Arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:08:16
 */
public interface ConveniosArrecFacade extends CrudFacade<ConveniosArrec, Long> {

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.BancosService} para obter todos os Bancos
     * registrados.
     *
     * @return todos os {@link br.gov.to.sefaz.arr.persistence.entity.Bancos} registrados.
     */
    Collection<Bancos> getAllActiveBancos();

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.BancosService} para obter as
     * {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias} por um determinado
     * {@link br.gov.to.sefaz.arr.persistence.entity.Bancos#idBanco}.
     *
     * @param idBanco codigo do banco para realizar a consulta
     * @return uma lista com todas as agências que pertencem ao banco.
     */
    Collection<BancoAgencias> getAllActiveBancoAgenciasFromIdBanco(Integer idBanco);


    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ReceitasService} para obter todos as Receitas
     * registrados com a situação {@link br.gov.to.sefaz.persistence.enums.SituacaoEnum#ATIVO}.
     *
     * @return todos as {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} registradas.
     */
    Collection<Receitas> getAllActiveReceitas();

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService} para obter as
     * {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas} por um
     * determinado {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#idConvenio}.
     *
     * @param idConvenio código do convenio para realizar a consulta
     * @return um lista com todas as tarifas que pertencem a um convenio
     */
    Collection<ConveniosTarifas> getAllConveniosTarifasByIdConvenioArrec(Long idConvenio);

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ReceitasService} para obter as
     * {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} por um
     * determinado {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#idConvenio}.
     *
     * @param idConvenio código do convenio para realizar a consulta
     * @return um lista com todas as tarifas que pertencem a um convenio
     */
    Collection<Receitas> getAllReceitasByIdConvenio(Long idConvenio);

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService} para validar se
     * há uma duplicidade de uma {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas} em
     * {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#conveniosTarifas}.
     *
     * @param conveniosArrec   convenio que contém a lista de tarifas
     * @param conveniosTarifas tarifa a ser validada
     */
    void validateTarifa(ConveniosArrec conveniosArrec, ConveniosTarifas conveniosTarifas);


    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService} para validar se
     * há uma duplicidade de uma {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} em
     * {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#receitas}.
     *
     * @param conveniosArrec convenio que contém a lista de receitas
     * @param receita        receita a ser validada
     */
    void validateReceita(ConveniosArrec conveniosArrec, Receitas receita);

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService} para pesquisar na base
     * de dados os {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} que satisfazem o filtro
     * passado.
     *
     * @param filter que contém os campos a serem pesquisados
     * @return todos os {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} que se enquandram na
     *     consulta do filtro.
     */
    List<ConveniosArrec> find(ConveniosArrecFilter filter);
}