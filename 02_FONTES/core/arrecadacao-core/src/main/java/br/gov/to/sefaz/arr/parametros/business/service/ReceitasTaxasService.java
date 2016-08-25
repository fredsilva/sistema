package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxasPK;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:17:00
 */
public interface ReceitasTaxasService extends CrudService<ReceitasTaxas, ReceitasTaxasPK> {

    /**
     * Remove todas as {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas} a partir do
     * {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas#idReceita} passado.
     *
     * @param idReceita codigo da receita para remoção das taxas.
     */
    void deleteAllTaxasByIdReceita(Integer idReceita);

    /**
     * Serviço para obter as {@link br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas} por um
     * determinado {@link br.gov.to.sefaz.arr.persistence.entity.Receitas#idReceita}.
     *
     * @param idReceita código da receita para realizar a consulta
     * @return um lista com todas as taxas que pertencem a uma receita
     */
    Collection<ReceitasTaxas> getReceitasTaxasByIdReceita(Integer idReceita);
}
