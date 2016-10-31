package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.PapelSistema}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class PapelSistemaRepository extends BaseRepository<PapelSistema, Long> {

    /**
     * Busca todos os {@link PapelSistema}.
     * @param nomePapel filtro.
     * @return Lista de PapelSistema.
     */
    public List<PapelSistema> findAllCounted(String nomePapel) {
        return find("tps", select -> select
                .columns("new PapelSistema( tps.identificacaoPapel, "
                + " tps.nomePapel, tps.descricaoPapel, "
                + " COUNT( distinct tpo.identificacaoOpcaoAplicacao ) AS totalOpcoes, "
                + " COUNT( distinct tpp.identificacaoPerfil ) AS vezesAtribuido)")
                .leftJoin("tps.papelOpcao","tpo")
                .leftJoin("tps.perfilPapel", "tpp")
                .where().opt().like("tps.nomePapel", nomePapel)
                .groupBy("tps.identificacaoPapel", "tps.nomePapel", "tps.descricaoPapel"));
    }

    /**
     * Busca um {@link PapelSistema}.
     * @param id identificação do PapelSistema.
     * @return PapelSistema.
     */
    public PapelSistema findOneCounted(Long id) {
        return findOne("tps", select -> select
                .columns("new PapelSistema( tps.identificacaoPapel, "
                + " tps.nomePapel, tps.descricaoPapel, "
                + " COUNT( distinct tpo.identificacaoOpcaoAplicacao ) AS totalOpcoes, "
                + " COUNT( distinct tpp.identificacaoPerfil ) AS vezesAtribuido)")
                .leftJoin("tps.papelOpcao","tpo")
                .leftJoin("tps.perfilPapel", "tpp")
                .where().equal("tps.identificacaoPapel", id)
                .groupBy("tps.identificacaoPapel", "tps.nomePapel", "tps.descricaoPapel"));
    }

    /**
     * Verifica se existe um perfil para um {@link PapelSistema}.
     * @param id identificação do Papel.
     * @return verdadeiro ou falso.
     */
    public boolean existsPerfilByPapel(Long id) {
        return exists("tps", select -> select.innerJoin("tps.perfilPapel", "pp").where().equal("pp.identificacaoPapel",
                id));
    }

    /**
     * Busca que retorna todos os {@link PapelSistema} de acordo com o filtro passado.
     * @return Lista de Papel Sistema.
     */
    public List<PapelSistema> findAllWithPapeis() {
        return find("ps",select -> select.innerJoinFetch("ps.perfilPapel","pp"))
                .stream().collect(Collectors.toSet())
                .stream().collect(Collectors.toList());
    }

    /**
     * Busca que retorna todos os {@link PapelSistema} de acordo com o id do perfil.
     * @param id do {@link br.gov.to.sefaz.seg.persistence.entity.PerfilSistema}
     * @return lista de PapelSistema.
     */
    public Collection<PapelSistema> findAllByPerfilId(Long id) {
        return find("ps",select -> select
                                    .innerJoinFetch("ps.perfilPapel","pp")
                                    .where()
                                    .equal("pp.identificacaoPerfil", id))
                .stream().collect(Collectors.toSet())
                .stream().collect(Collectors.toList());
    }
}
