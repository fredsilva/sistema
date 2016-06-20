package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositório de acesso à base dados da entidade {@link UsuarioPerfil}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public interface UsuarioPerfilRepository extends BaseRepository<UsuarioPerfil, Long> {

    String SELECT_BY_USUARIO = "SELECT up "
            + " FROM UsuarioPerfil up"
            + " INNER JOIN FETCH up.perfisSistema pes"
            + " INNER JOIN FETCH pes.perfilPapel pepa"
            + " INNER JOIN FETCH pepa.papelSistema pas"
            + " INNER JOIN FETCH pas.papelOpcao po"
            + " INNER JOIN FETCH po.opcaoAplicacao oa"
            + " WHERE up.cpfUsuario = :usuarioId "
            + " AND up.situacaoPerfil = 'A'";

    /**
     * Seleciona todos os perfis do sistema de um usuario.
     *
     * @param usuarioId id do usuario
     * @return todos os perfis de um usuario
     */
    @Query(SELECT_BY_USUARIO)
    Set<UsuarioPerfil> findAllByUsuarioSistema(@Param("usuarioId") String usuarioId);
}