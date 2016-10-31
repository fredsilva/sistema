package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Repositório de acesso à base dados da entidade {@link PerfilSistema}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class PerfilSistemaRepository extends BaseRepository<PerfilSistema, Long> {
    /**
     * Busca uma lista de {@link PerfilSistema} de acordo com o {@link PapelSistema#identificacaoPapel}.
     * @param idPapel {@link PapelSistema#identificacaoPapel}.
     * @return Lista de {@link PapelSistema}.
     */
    public List<PerfilSistema> findAllPerfilSistemaByPapel(Long idPapel) {
        return find("ps", select ->
                select
                        .innerJoinFetch("ps.perfilPapel","pp")
                        .where()
                        .equal("pp.identificacaoPapel",idPapel));
    }

    /**
     * Busca todos os perfis pela descrição.
     * @param nomePerfil descrição do filtro.
     * @return lista de Perfis.
     */
    public List<PerfilSistema> findAllPerfilSistema(String nomePerfil) {
        return find("ps", select -> select
                .columns("new PerfilSistema( ps.identificacaoPerfil, "
                        + " ps.nomePerfil, ps.descricaoPerfil, "
                        + " COUNT( distinct up.cpfUsuario ) AS totalUsuarios, "
                        + " COUNT( distinct tpp.identificacaoPapel ) AS totalPapeis)")
                .leftJoin("ps.usuarioPerfil","up")
                .leftJoin("ps.perfilPapel", "tpp")
                .where().opt().like("ps.nomePerfil", nomePerfil)
                .groupBy("ps.identificacaoPerfil", "ps.nomePerfil", "ps.descricaoPerfil"))
                .stream()
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Busca um {@link PerfilSistema} com suas listas setadas.
     * @param id identificação do Perfil
     * @return perfil selecionado.
     */
    public PerfilSistema findOneComplete(Long id) {
        return findOne("ps", select -> select
                .leftJoin("ps.usuarioPerfil","up")
                .leftJoin("ps.perfilPapel", "tpp")
                .leftJoin("up.usuarioSistema","us")
                .leftJoin("us.solicitacaoUsuario","su")
                .leftJoin("us.usuarioPostoTrabalho","upt")
                .leftJoin("upt.postoTrabalho","pt")
                .leftJoin("pt.unidadeOrganizacional","ou")
                .where().equal("ps.identificacaoPerfil", id));
    }

    /**
     * Verifica a existência de um {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema} utilizando o perfil
     * a ser deletado.
     * @param id identificação do {@link PerfilSistema}.
     * @return Verdadeiro ou Falso.
     */
    public boolean existsUsuarioByPerfil(Long id) {
        return exists("ps", select -> select
                .innerJoin("ps.usuarioPerfil", "up")
                .where()
                .equal("up.identificacaoPerfil",id));
    }
}
