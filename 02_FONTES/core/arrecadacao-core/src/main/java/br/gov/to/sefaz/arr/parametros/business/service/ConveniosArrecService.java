package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.business.service.filter.ConveniosArrecFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link ConveniosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:16:11
 */
public interface ConveniosArrecService extends CrudService<ConveniosArrec, Long> {

    /**
     * Pesquisar na base de dados os {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec} que
     * representam o filtro passado.
     *
     * @param filter que contém os campos a serem pesquisados
     * @return todos os {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec} que se enquandram na
     *      consulta do filtro
     */
    List<ConveniosArrec> find(ConveniosArrecFilter filter);

}
