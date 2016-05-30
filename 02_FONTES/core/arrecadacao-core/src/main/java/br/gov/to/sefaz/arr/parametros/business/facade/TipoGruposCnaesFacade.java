package br.gov.to.sefaz.arr.parametros.business.facade;

import br.gov.to.sefaz.arr.parametros.business.service.filter.TipoGruposCnaesFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.cat.persistence.entity.AtividadeEconomica;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de Tipos de Rejeições de Arquivos.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface TipoGruposCnaesFacade extends CrudFacade<TipoGruposCnaes, Integer> {

    List<TipoGruposCnaes> find(TipoGruposCnaesFilter filter);

    Collection<AtividadeEconomica> findAllCnaes();

    Collection<AtividadeEconomica> findAllCnaesByGrupo(Integer idGrupoCnae);

    void removeCnaeFromGrupo(Integer idGrupoCnae, String codigoCnae);

    void validateGruposCnaes(Collection<GruposCnae> gruposCnaes);
}
