package br.gov.to.sefaz.par.gestao.business.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;

import java.util.Collection;

/**
 * Serviços para manipulação de {@link Municipio}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 21/05/2016 16:21:44
 */
public interface MunicipioService extends CrudService<Municipio, Integer> {

    /**
     * Consulta a lista de {@link Municipio} por {@link Municipio#unidadeFederacao}.
     *
     * @param uf unidade federação do estado, exemplo TO
     * @return lista de municípios
     */
    Collection<Municipio> findByUF(String uf);
}
