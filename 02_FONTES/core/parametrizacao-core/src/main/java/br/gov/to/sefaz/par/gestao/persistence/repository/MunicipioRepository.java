package br.gov.to.sefaz.par.gestao.persistence.repository;

import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repositorio para manipulação de {@link Municipio}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 21/05/2016 16:17:32
 */
@Repository
public class MunicipioRepository extends BaseRepository<Municipio, Integer> {

    /**
     * Busca a lista de Municípios por UF em ordem alfabética.
     *
     * @param uf para ser buscados os municípios.
     * @return Lista de Municipio.
     */
    public Collection<Municipio> findByUF(String uf) {
        return find(select -> select.where()
                .equal("unidadeFederacao", uf)
                .orderBy("nomeMunicipio", Order.ASC));
    }
}
