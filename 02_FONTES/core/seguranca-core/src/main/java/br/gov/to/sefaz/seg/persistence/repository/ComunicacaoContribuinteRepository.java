package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à base dados da entidade {@link ComunicacaoContribuinte}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 17:12:56
 */
@Repository
public class ComunicacaoContribuinteRepository extends BaseRepository<ComunicacaoContribuinte, String> {
}
