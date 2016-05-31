package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepassePK;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:16:00
 */
public interface ReceitasRepasseService extends CrudService<ReceitasRepasse, ReceitasRepassePK> {

    /**
     * Remove todas as {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse} a partir do
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse#idReceita} passado.
     *
     * @param idReceita codigo da receita para remoção dos repasses.
     */
    void deleteAllRepassesByIdReceita(Integer idReceita);

    /**
     * Serviço para obter as {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse} por um
     * determinado {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas#idReceita}.
     *
     * @param idReceita código da receita para realizar a consulta
     * @return um lista com todos os repasses que pertencem a uma receita
     */
    Collection<ReceitasRepasse> getReceitasRepasseByIdReceita(Integer idReceita);
}
