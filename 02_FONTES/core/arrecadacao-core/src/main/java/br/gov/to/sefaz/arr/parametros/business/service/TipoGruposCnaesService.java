package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.business.service.filter.TipoGruposCnaesFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.List;

/**
 * Contrato de acesso do serviço de Tipos de Rejeições de Arquivos.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface TipoGruposCnaesService extends CrudService<TipoGruposCnaes, Integer> {

    /**
     * Retorna todos os registros de {@link TipoGruposCnaes} que fazem match com os criterios passados pelo filtro.
     *
     * @param filter filtro de registros
     * @return lista de grupos cnaes que batem com o filtro passado
     */
    List<TipoGruposCnaes> findAll(TipoGruposCnaesFilter filter);
}
