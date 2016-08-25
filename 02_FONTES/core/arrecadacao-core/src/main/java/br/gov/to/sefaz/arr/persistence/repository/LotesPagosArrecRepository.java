package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de dados correspondente a entidade
 * {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 18/07/2016 16:30:00
 */
@Repository
public class LotesPagosArrecRepository extends BaseRepository<LotesPagosArrec, Long> {
}
