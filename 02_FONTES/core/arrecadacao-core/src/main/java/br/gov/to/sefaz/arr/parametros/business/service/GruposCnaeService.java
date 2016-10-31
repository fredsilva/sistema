package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.persistence.entity.GruposCnaePK;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Serviço para operações do relacionamento entre CNAE's e os Tipos de grupos CNAE's.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 17/05/2016 14:23:00
 */
public interface GruposCnaeService extends CrudService<GruposCnae, GruposCnaePK> {

    /**
     * Método para validação de grupos CNAEs duplicados.
     * @param gruposCnaes lista de gruposCnaes.
     */
    void validateDuplicated(Collection<GruposCnae> gruposCnaes);

    /**
     * Remove grupo inteiro pela Id.
     * @param idGrupoCnae id do grupo cnae.
     */
    void deleteByGrupo(Integer idGrupoCnae);
}
