package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.cat.persistence.entity.Estado;
import br.gov.to.sefaz.cat.persistence.entity.Municipio;

import java.util.Collection;

/**
 * Contrato de acesso da Fachada de Agencias.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 03/05/2016 10:53:19
 */
public interface BancoAgenciasFacade extends CrudFacade<BancoAgencias, BancoAgenciasPK> {

    /**
     * Retorna uma lista de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias} de um determinado
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias#idBanco}.
     *
     * @param idBanco codigo do banco para realizar a consulta
     * @return uma lista com todas as agências que pertencem ao banco
     */
    Collection<BancoAgencias> findByIdBanco(Integer idBanco);

    /**
     * Retorna a lista com todos os {@link Estado} do banco de dados.
     *
     * @return uma lista com todos os estados do banco de dados.
     */
    Collection<Estado> findAllEstados();

    /**
     * Retorna uma lista de {@link Municipio} de um determinado {@link Estado#unidadeFederacao}.
     *
     * @param uf unidade federativa do estado, exemplo TO
     * @return uma lista com os municípios do estado
     */
    Collection<Municipio> findMunicipiosByUF(String uf);

    /**
     * Realiza as validações de {@link BancoAgencias} ao salvar. Valida atributos obrigatórios, tamanho e faixa de
     * valores dos atributos, duplicidade de Agências, duplicidade de CNPJ, CNPJ Raiz conforme o banco da agência,
     * unicidade de agência centralizadora e atributos específicos como CNPJ.
     *
     * @param list Lista de Agências a ser validada
     */
    void validateSave(BancoAgencias agencia, Collection<BancoAgencias> list);

    /**
     * Realiza as validações de {@link BancoAgencias} ao atualizar. Valida atributos obrigatórios, tamanho e faixa de
     * valores dos atributos, duplicidade de Agências, duplicidade de CNPJ, CNPJ Raiz conforme o banco da agência,
     * unicidade de agência centralizadora e atributos específicos como CNPJ.
     *
     * @param list Lista de Agências a ser validada
     */
    void validateUpdate(BancoAgencias agencia, Collection<BancoAgencias> list);

}
