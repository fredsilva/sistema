package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.business.service.filter.ReceitasFilter;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.Receitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:14:00
 */
public interface ReceitasService extends CrudService<Receitas, Integer> {

    /**
     * Busca todos as {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} com
     * {@link br.gov.to.sefaz.arr.persistence.entity.Receitas#situacao}
     * {@link br.gov.to.sefaz.persistence.enums.SituacaoEnum#ATIVO}.
     *
     * @return lista de receitas ativas
     */
    Collection<Receitas> findAllActiveReceitas();


    /**
     * Serviço para obter as {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} por um
     * determinado {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#idConvenio}.
     *
     * @param idConvenio código do convenio para realizar a consulta
     * @return um lista com todas as receitas que pertencem a um convenio
     */
    Collection<Receitas> getAllReceitasByIdConvenio(Long idConvenio);

    /**
     * Pesquisar na base de dados as {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} que
     * representam o filtro passado.
     *
     * @param filter que contém os campos a serem pesquisados
     * @return todos as {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} que se enquandram na
     *      consulta do filtro
     */
    List<Receitas> find(ReceitasFilter filter);
}
