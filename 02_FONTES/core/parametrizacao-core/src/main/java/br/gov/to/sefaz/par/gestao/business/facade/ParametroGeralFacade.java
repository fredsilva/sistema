package br.gov.to.sefaz.par.gestao.business.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.par.gestao.business.service.filter.ParametroGeralFilter;
import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;

import java.util.List;

/**
 * Contrato de acesso do serviço de Parametros Gerais.
 *
 * @author <a href="mailto:carlos.junior@ntconsult.com.br">carlos.junior</a>
 * @since 23/06/2016 16:42:00
 */
public interface ParametroGeralFacade extends CrudFacade<ParametroGeral, Integer> {
    /**
     * Método de pesquisa {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral}.
     *
     * @param filter valor de filtro
     * @return {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral}
     */
    List<ParametroGeral> find(ParametroGeralFilter filter);

}
