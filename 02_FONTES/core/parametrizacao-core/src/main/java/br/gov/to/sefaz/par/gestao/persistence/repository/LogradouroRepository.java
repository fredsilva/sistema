package br.gov.to.sefaz.par.gestao.persistence.repository;

import br.gov.to.sefaz.par.gestao.persistence.entity.Logradouro;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para manipulação de {@link Logradouro}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 14/07/2016 17:46:00
 */
@Repository
public interface LogradouroRepository extends PagingAndSortingRepository<Logradouro, String>,
        JpaSpecificationExecutor<Logradouro> {

}
