package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.DetalheStr;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link br.gov.to.sefaz.arr.persistence.entity.DetalheStr}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 11/07/2016 10:36:00
 */
@Repository
public class DetalhesStrRepository extends BaseRepository<DetalheStr, Long> {
}
