package br.gov.to.sefaz.cat.persistence.repository;

import br.gov.to.sefaz.cat.persistence.entity.Municipio;
import br.gov.to.sefaz.persistence.repository.BaseRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repositorio para manipulação de {@link Municipio}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 21/05/2016 16:17:32
 */
@Repository
public interface MunicipioRepository extends BaseRepository<Municipio, Integer> {

    @Query("SELECT municipio FROM Municipio municipio WHERE municipio.unidadeFederacao = :uf")
    Collection<Municipio> findByUF(@Param("uf") String uf);
}
