package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UnidadeOrganizacionalFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUnidade;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link UnidadeOrganizacional}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
public interface UnidadeOrganizacionalService extends CrudService<UnidadeOrganizacional, Long> {

    /**
     * Busca todos os {@link UnidadeOrganizacional} cadastrados.
     *
     * @param filter filtros da busca
     * @return lista de UnidadeOrganizacional ativos
     */
    List<UnidadeOrganizacional> findByFilter(UnidadeOrganizacionalFilter filter);

    /**
     * Consulta os tipos de Unidades Organizacionais.
     *
     * @return lista com os tipos de Unidades Organizacionais
     */
    List<TipoUnidade> findTiposUnidades();


    /**
     * Busca uma lista {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional} conforme os
     * {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional#codigoTipoUnidade}s.
     *
     * @param characters representa os
     *                   {@link br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional#codigoTipoUnidade}s com
     *                   que será realizada a busca
     * @return lista com as unidades encontradas.
     */
    List<UnidadeOrganizacional> findAllByTipoUnidade(Character... characters);
}
