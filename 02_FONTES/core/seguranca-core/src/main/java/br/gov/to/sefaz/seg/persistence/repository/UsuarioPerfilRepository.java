package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class UsuarioPerfilRepository extends BaseRepository<UsuarioPerfil, Long> {

    /**
     * Seleciona todos os perfis do sistema de um usuario.
     *
     * @param usuarioId id do usuario
     * @return todos os perfis de um usuario
     */
    public Set<UsuarioPerfil> findAllByUsuarioSistema(String usuarioId,  SituacaoUsuarioEnum situacao) {
        return find("up", select -> select
                .innerJoinFetch("up.perfisSistema", "pes")
                .innerJoinFetch("pes.perfilPapel", "pepa")
                .innerJoinFetch("pepa.papelSistema", "pas")
                .innerJoinFetch("pas.papelOpcao", "po")
                .innerJoinFetch("po.opcaoAplicacao", "oa")
                .where().equal("up.cpfUsuario", usuarioId)
                .and().equal("up.situacaoPerfil", situacao))
                .stream().collect(Collectors.toSet());
    }

    /**
     * Seleciona todos os perfis do sistema de um usuario.
     *
     * @param usuarioId id do usuario
     * @return todos os perfis de um usuario
     */
    public Set<UsuarioPerfil> findAllByUsuarioSistema(String usuarioId) {
        return find("up", select -> select
                .innerJoin("up.perfisSistema", "pes")
                .where().equal("up.cpfUsuario", usuarioId))
                .stream().collect(Collectors.toSet());
    }

    /**
     * Seleciona todos os perfis do sistema de um usuario.
     *
     * @param usuariosId ids dos usuarios
     * @return todos os perfis de um usuario
     */
    public Set<UsuarioPerfil> findAllByUsuariosSistema(List<String> usuariosId) {
        return find("up", select -> select
                .innerJoin("up.perfisSistema", "pes")
                .where().in("up.cpfUsuario", usuariosId))
                .stream().collect(Collectors.toSet());
    }

    /**
     * Busca todos os {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil} pelo ID do perfil.
     * @param identificacaoPerfil identificação do {@link br.gov.to.sefaz.seg.persistence.entity.PerfilSistema}.
     * @return Lista de UsuarioPerfil.
     */
    public Collection<UsuarioPerfil> findAllUsuariosPerfilByPerfilId(Long identificacaoPerfil) {
        return find("up", select -> select
                .innerJoin("up.usuarioSistema")
                .where()
                .equal("up.identificacaoPerfil", identificacaoPerfil));
    }

    /**
     * Busca todos os perfils do usuário.
     * @param cpfUsuario cpf do usuário.
     * @return lista de {@link UsuarioPerfil}.
     */
    public Set<UsuarioPerfil> findAllPerfilByUsuario(String cpfUsuario) {
        return find("up", select -> select.leftJoin("up.usuarioSistema","us").where().equal("us.cpfUsuario",
                cpfUsuario)).stream().collect(Collectors.toSet());
    }
}