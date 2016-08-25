package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de Agências Bancárias.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface BancoAgenciasService extends CrudService<BancoAgencias, BancoAgenciasPK> {

    /**
     * Serviço para obter as {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias} que possuem a
     * {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias#situacao}
     * {@link br.gov.to.sefaz.persistence.enums.SituacaoEnum#ATIVO} por um determinado
     * {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias#idBanco}.
     *
     * @param idBanco codigo do banco para realizar a consulta
     * @return uma lista com todas as agências que pertencem ao banco
     */
    Collection<BancoAgencias> getAllActiveBancoAgenciasFromIdBanco(Integer idBanco);

    /**
     * Realiza as validações de {@link BancoAgencias} ao salvar. Valida atributos obrigatórios, tamanho e faixa de
     * valores dos atributos, duplicidade de Agências, duplicidade de CNPJ, CNPJ Raiz conforme o banco da agência,
     * unicidade de agência centralizadora e atributos específicos como CNPJ.
     *
     * @param agencia Agência a ser salva
     */
    void validateSave(BancoAgencias agencia);

    /**
     * Realiza as validações de {@link BancoAgencias} ao salvar. Valida atributos obrigatórios, tamanho e faixa de
     * valores dos atributos, duplicidade de Agências, duplicidade de CNPJ, CNPJ Raiz conforme o banco da agência,
     * unicidade de agência centralizadora e atributos específicos como CNPJ.
     *
     * @param list Lista de Agências a serem salvas
     */
    void validateSave(Collection<BancoAgencias> list);

    /**
     * Realiza as validações de {@link BancoAgencias} ao atualizar. Valida atributos obrigatórios, tamanho e faixa de
     * valores dos atributos, duplicidade de Agências, duplicidade de CNPJ, CNPJ Raiz conforme o banco da agência,
     * unicidade de agência centralizadora e atributos específicos como CNPJ.
     *
     * @param agencia Agência a ser atualizada
     */
    void validateUpdate(BancoAgencias agencia);

    /**
     * Realiza as validações de {@link BancoAgencias} ao atualizar. Valida atributos obrigatórios, tamanho e faixa de
     * valores dos atributos, duplicidade de Agências, duplicidade de CNPJ, CNPJ Raiz conforme o banco da agência,
     * unicidade de agência centralizadora e atributos específicos como CNPJ.
     *
     * @param list Lista de Agências a serem salvas
     */
    void validateUpdate(Collection<BancoAgencias> list);

    /**
     * Serviço para obter as {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias} por um determinado
     * {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias#idBanco}.
     *
     * @param idBanco codigo do banco para realizar a consulta
     * @return uma lista com todas as agências que pertencem ao banco
     */
    Collection<BancoAgencias> findByIdBanco(Integer idBanco);

    /**
     * Busca a {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias} correspondente aos parâmetros.
     *
     * @param cnpjAgenteBancarioCreditado cnpj raiz do banco.
     * @param agencia                     código da agência.
     * @return a {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias} correspondente aos parâmetros.
     */
    List<BancoAgencias> findByCtaBanco(Integer cnpjAgenteBancarioCreditado, Integer agencia);
}
