package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 26/07/2016 11:35:00
 */
@Repository
public class OpcaoAplicacaoRepository extends BaseRepository<OpcaoAplicacao, Long> {

    /**
     * Busca todos os {@link OpcaoAplicacao}, {@link br.gov.to.sefaz.seg.persistence.entity.AplicacaoModulo},
     * {@link br.gov.to.sefaz.seg.persistence.entity.ModuloSistema} para mostrar suas descrições em tela.
     * @return Lista de OpcaoAplicacao.
     */
    public List<OpcaoAplicacao> findAllOpcaoAplicacao() {
        return find("oa", select -> select
            .innerJoinFetch("oa.aplicacaoModulo", "am")
            .innerJoinFetch("am.moduloSistema", "ms")
            .orderBy("ms.abreviacaoModulo", Order.ASC).andBy("oa.descripcaoOpcao", Order.ASC))
            .stream().collect(Collectors.toSet())
            .stream().collect(Collectors.toList());
    }

    /**
     * Seleciona as opções da aplicação a partir dos IDs.
     *
     * @param ids das opções
     * @return os objetos referentes aos ids
     */
    public List<OpcaoAplicacao> findByIds(Collection<Long> ids) {
        return find("oa", select -> select
            .innerJoinFetch("oa.aplicacaoModulo", "am")
            .innerJoinFetch("am.moduloSistema", "ms")
            .where().in("oa.identificacaoOpcaoAplicacao", ids));
    }
}