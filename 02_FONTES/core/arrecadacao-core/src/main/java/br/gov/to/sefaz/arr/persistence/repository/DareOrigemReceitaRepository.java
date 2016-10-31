package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.DareOrigemReceita;
import br.gov.to.sefaz.arr.persistence.entity.DareOrigemReceitaPK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link DareOrigemReceita}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/09/2016 15:48:00
 */
@Repository
public class DareOrigemReceitaRepository extends BaseRepository<DareOrigemReceita, DareOrigemReceitaPK> {
}
