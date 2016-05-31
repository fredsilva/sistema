package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgenciasPK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link DelegaciaAgencias}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 24/05/2016 18:39:00
 */
@Repository
public interface DelegaciaAgenciasRepository extends BaseRepository<DelegaciaAgencias, DelegaciaAgenciasPK> {
}
