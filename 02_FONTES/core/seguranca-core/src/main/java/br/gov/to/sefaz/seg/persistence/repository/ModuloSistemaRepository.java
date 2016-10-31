package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.ModuloSistema}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class ModuloSistemaRepository extends BaseRepository<ModuloSistema, Long> {

    /**
     * Busca todos os {@link ModuloSistema}.
     * @return todos os registros do banco
     */
    public Set<ModuloSistema> findAllFetched() {
        return find("ms", select -> select
                .leftJoinFetch("ms.aplicacaoModulos", "am")
                .leftJoinFetch("am.opcoesAplicacao", "oa"))
                .stream().collect(Collectors.toSet());
    }

    /**
     * Busca todos os módulos do sistema ordenados por abreviação.
     *
     * @return todos os registros do banco ordenados por abreviação
     */
    public List<ModuloSistema> findAllSortedByAbreviacao() {
        return find("ms", select -> select
                .orderBy("abreviacaoModulo"));
    }
}
