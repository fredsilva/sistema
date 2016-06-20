package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositório de acesso à base dados da entidade {@link ModuloSistema}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public interface ModuloSistemaRepository extends BaseRepository<ModuloSistema, Long> {

    String FIND_ALL = "SELECT ms FROM ModuloSistema ms "
            + "  INNER JOIN FETCH ms.aplicacaoModulos am "
            + "  INNER JOIN FETCH am.opcoesAplicacao oa ";

    /**
     * Busca todos os registros e dependencias em uma unica consulta com INNER JOIN.
     *
     * @return todos os registros do banco
     */
    @Query(FIND_ALL)
    Set<ModuloSistema> findAllFetched();
}
