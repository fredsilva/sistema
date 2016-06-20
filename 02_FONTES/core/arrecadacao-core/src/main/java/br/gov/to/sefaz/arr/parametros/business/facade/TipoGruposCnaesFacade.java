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

    /**
     * Busca os TipoGruposCnaes.
     * @param filter filtro preenchido em tela.
     * @return lista de TipoGruposCnaes.
     */
    List<TipoGruposCnaes> find(TipoGruposCnaesFilter filter);

    /**
     * Busca todos os Cnaes.
     * @return lista de AtividadeEconomica.
     */
    Collection<AtividadeEconomica> findAllCnaes();

    /**
     * Busca todos os Cnaes pela identificação do Grupo.
     * @param idGrupoCnae identificação do GrupoCnae.
     * @return Lista de AtividadeEconomica.
     */
    Collection<AtividadeEconomica> findAllCnaesByGrupo(Integer idGrupoCnae);

    /**
     * Remove Cnae do Grupo.
     * @param idGrupoCnae identificação de GrupoCnae.
     * @param codigoCnae identificação do Cnae.
     */
    void removeCnaeFromGrupo(Integer idGrupoCnae, String codigoCnae);

    /**
     * Valida os grupos.
     * @param gruposCnaes Lista de GruposCnaes.
     */
    void validateGruposCnaes(Collection<GruposCnae> gruposCnaes);
}
