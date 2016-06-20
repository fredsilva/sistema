package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UnidadeOrganizacionalFilter;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;

import java.util.List;

/**
 * Contrato de acesso do serviço de Bancos.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
public interface UnidadeOrganizacionalFacade extends CrudFacade<UnidadeOrganizacional, Long> {

    /**
     * Filtro da tela de Unidades Organizacionais.
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    List<UnidadeOrganizacional> find(UnidadeOrganizacionalFilter filter);
}

