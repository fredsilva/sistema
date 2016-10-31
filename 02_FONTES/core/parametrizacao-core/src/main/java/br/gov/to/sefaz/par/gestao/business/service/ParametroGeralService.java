package br.gov.to.sefaz.par.gestao.business.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.par.gestao.business.service.filter.ParametroGeralFilter;
import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;
import br.gov.to.sefaz.persistence.domain.CodeData;

import java.util.List;
import java.util.function.Function;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:16:11
 */
public interface ParametroGeralService extends CrudService<ParametroGeral, Integer> {

    /**
     * Pesquisar na base de dados os {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral} que
     * representam o filtro passado.
     *
     * @param filter que contém os campos a serem pesquisados
     * @return todos os {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral} que se enquandram na
     *      consulta do filtro
     */
    List<ParametroGeral> find(ParametroGeralFilter filter);

    /**
     * Pesquisa o parâmetro geral por nome e transforma o resultado da busca em um CodeData.
     *
     * @param nomeParametro nome do Parâmetro Geral
     * @param params parametros para consulta Dinâmica
     * @return lista de codeData
     */
    List<CodeData> findCodeData(String nomeParametro, String... params);

    /**
     * Pesquisa o parâmetro geral por nome e transforma o resultado da busca em um CodeData específico, ou seja, uma
     * classe que extende {@link br.gov.to.sefaz.persistence.domain.CodeData}).
     *
     * @param converter function para converter para um CodeData específico, ou seja, uma classe que extende
     * {@link br.gov.to.sefaz.persistence.domain.CodeData})
     * @param nomeParametro nome do Parâmetro Geral
     * @param params parametros para consulta Dinâmica
     * @return lista de codeData
     */
    <R extends CodeData> List<R> findCodeData(Function<List<CodeData>, List<R>> converter, String
            nomeParametro, String... params);

    /**
     * Pesquisa o parâmetro geral por nome.
     *
     * @param nomeParametro nome do Parâmetro Geral
     * @return parâmetro geral
     */
    ParametroGeral findByNomeParametro(String nomeParametro);

}
