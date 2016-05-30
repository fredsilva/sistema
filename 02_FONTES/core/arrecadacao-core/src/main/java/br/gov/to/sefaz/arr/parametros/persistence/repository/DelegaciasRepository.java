package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Delegacias;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para gerencia de dados de {@link Delegacias}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 20/05/2016 19:53:00
 */
@Repository
public interface DelegaciasRepository extends BaseRepository<Delegacias, Integer> {
}
