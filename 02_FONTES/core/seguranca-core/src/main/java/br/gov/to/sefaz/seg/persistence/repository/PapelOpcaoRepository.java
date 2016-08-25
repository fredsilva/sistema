package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcao;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcaoPK;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Repositório de acesso à base dados da entidade {@link PapelOpcao}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class PapelOpcaoRepository extends BaseRepository<PapelOpcao, PapelOpcaoPK> {

    /**
     * Busca todas as {@link PapelOpcao} para a tela de PerfilSistema.
     * @return Lista de PapelOpcao.
     */
    public Set<PapelOpcao> findAllWithDescription(Long idPapel) {
        return find("po", select -> select
                .innerJoinFetch("po.opcaoAplicacao", "oa")
                .innerJoinFetch("oa.aplicacaoModulo", "am")
                .innerJoinFetch("am.moduloSistema", "ms")
                .where().opt().equal("po.identificacaoPapel", idPapel)
                .orderBy("ms.abreviacaoModulo", Order.ASC).andBy("oa.descripcaoOpcao", Order.ASC))
                .stream()
                .collect(Collectors.toSet());
    }

    /**
     * Remove todos os {@link PapelOpcao} pelo ID do Papel.
     * @param id do {@link br.gov.to.sefaz.seg.persistence.entity.PapelSistema}
     */
    public void deleteAllPapelOpcaoByPapelId(Long id) {
        delete("po", delete -> delete.where().equal("identificacaoPapel",id));
    }
}
