package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.view.NotaAvulsa;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * * Repositório da view {@link br.gov.to.sefaz.arr.persistence.view.NotaAvulsa}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:46:00
 */
@Repository
public class NotaAvulsaRepository extends BaseRepository<NotaAvulsa, Long> {
}
