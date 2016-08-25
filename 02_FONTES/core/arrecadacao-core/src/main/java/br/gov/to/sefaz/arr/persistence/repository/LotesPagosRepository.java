package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.LotesPagos;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosPK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório de dados da entidade {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagos}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 18/07/2016 16:37:00
 */
@Repository
public class LotesPagosRepository extends BaseRepository<LotesPagos, LotesPagosPK> {
}
