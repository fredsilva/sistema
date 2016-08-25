package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitas;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitasPK;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.business.service.CrudService;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 10:19:00
 */
public interface ConveniosReceitasService extends CrudService<ConveniosReceitas, ConveniosReceitasPK> {

    /**
     * Serviço para remover as {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitas} por um
     * determinado {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#idConvenio}.
     *
     * @param idConvenio código do convenio para realizar a remoção
     */
    void deleteAllByIdConvenio(Long idConvenio);

    /**
     * Valida se a {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} não está duplicada conforme a
     * regra de négocio, onde ela não deve ser igual para um registro presente em
     * {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#receitas}.
     *
     * @param conveniosArrec Convenio arrecadação
     * @param receita receita
     */
    void validateDuplicatedReceita(ConveniosArrec conveniosArrec, Receitas receita);
}
