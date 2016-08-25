package br.gov.to.sefaz.par.gestao.persistence.repository;

import br.gov.to.sefaz.par.gestao.persistence.entity.Estado;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manipulação de {@link Estado}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 21/05/2016 16:17:32
 */
@Repository
public class EstadoRepository extends BaseRepository<Estado, String> {
}
