package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao;
import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 09/09/2016 10:12:56
 */
@Repository
public class HistoricoNavegacaoRepository extends BaseRepository<HistoricoNavegacao, Long> {
}
