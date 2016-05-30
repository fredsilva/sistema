package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnaePK;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Serviço para operações do relacionamento entre CNAE's e os Tipos de grupos CNAE's.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 17/05/2016 14:23:00
 */
public interface GruposCnaeService extends CrudService<GruposCnae, GruposCnaePK> {

    void validateDuplicated(Collection<GruposCnae> gruposCnaes);

    void deleteByGrupo(Integer idGrupoCnae);
}
