package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.business.service.filter.ConveniosArrecFilter;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
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
     * Pesquisar na base de dados os {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} que
     * representam o filtro passado.
     *
     * @param filter que contém os campos a serem pesquisados
     * @return todos os {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} que se enquandram na
     *     consulta do filtro
     */
    List<ConveniosArrec> find(ConveniosArrecFilter filter);

    /**
     * Pesquisa na base de dados o {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} de um
     * determinada {@link br.gov.to.sefaz.arr.persistence.entity.BancoAgencias}.
     *
     * @param bancoAgencias agência e banco do convênio de arrecadação
     * @return {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec}
     */
    ConveniosArrec findByBancoAgencias(BancoAgencias bancoAgencias);

    /**
     * Busca o {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} correspondente.
     *
     * @param cnpjRaiz         cnpj raiz do banco {@link br.gov.to.sefaz.arr.persistence.entity.Bancos}.
     * @param tipoConvenioEnum tipo de convênio
     *                         {@link br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum}.
     * @return o {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} correspondente.
     */
    List<ConveniosArrec> findByBancoCnpjAndTipoConvenio(Integer cnpjRaiz, TipoConvenioEnum tipoConvenioEnum);

    /**
     * Busca o {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} correspondente.
     *
     * @param bancoId          código do banco {@link br.gov.to.sefaz.arr.persistence.entity.Bancos}.
     * @param tipoConvenioEnum tipo de convênio
     *                         {@link br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum}.
     * @return o {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec} correspondente.
     */
    ConveniosArrec findByBancoAndTipoConvenio(Integer bancoId, TipoConvenioEnum tipoConvenioEnum);

}
