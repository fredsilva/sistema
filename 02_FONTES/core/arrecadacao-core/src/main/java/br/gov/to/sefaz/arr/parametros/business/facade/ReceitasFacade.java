package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.parametros.business.service.filter.ReceitasFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.business.facade.CrudFacade;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço {@link br.gov.to.sefaz.arr.parametros.business.service.ReceitasService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:26:00
 */
public interface ReceitasFacade extends CrudFacade<Receitas, Integer> {

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ReceitasService} para pesquisar na base
     * de dados as {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas} que satisfazem o filtro
     * passado.
     *
     * @param filter que contém os campos a serem pesquisados
     * @return todas as {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas} que se enquandram na
     *     consulta do filtro.
     */
    List<Receitas> find(ReceitasFilter filter);

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.PlanoContasService} para pesquisar na base
     * de dados todos os {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas}.
     *
     * @return todos os {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas}
     */
    List<PlanoContas> getAllPlanoContas();

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ReceitasTaxasService} para obter as
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas} por um
     * determinado {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas#idReceita}.
     *
     * @param idReceita código da receita para realizar a consulta
     * @return um lista com todas as taxas que pertencem a uma receita
     */
    Collection<ReceitasTaxas> getReceitasTaxasByIdReceita(Integer idReceita);

    /**
     * Acessa o {@link br.gov.to.sefaz.arr.parametros.business.service.ReceitasRepasseService} para obter as
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse} por um
     * determinado {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas#idReceita}.
     *
     * @param idReceita código da receita para realizar a consulta
     * @return um lista com todos os repasses que pertencem a uma receita
     */
    Collection<ReceitasRepasse> getReceitasRepasseByIdReceita(Integer idReceita);
}
