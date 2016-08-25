package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Repositório de acesso à base dados da entidade {@link UsuarioSistema} para a tela de atribuição de perfil.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 02/08/2016 17:14:00
 */
@Repository
public class AtribuirPerfilRepository extends BaseRepository<UsuarioSistema, String> {

    /**
     * Busca todos os {@link UsuarioSistema} selecionado pelo filtro da tela de atribuição de perfil.
     * @param cpfUsuario cpf.
     * @param nomeCompletoUsuario nome completo.
     * @param codigoTipoUsuario código do tipo de usuário.
     * @param codigoPerfil código do perfil.
     * @param codigoUnidadeOrganizacional código da Unidade Organizacional.
     * @param codigoPostoTrabalho código do posto de trabalho.
     * @return Lista de Usuários.
     */
    public Collection<UsuarioSistema> findAllByFilterParameters(String cpfUsuario, String nomeCompletoUsuario,
            Integer codigoTipoUsuario, Long codigoPerfil, Long codigoUnidadeOrganizacional,
            Integer codigoPostoTrabalho) {
        return find("us", select -> select
                .leftJoin("us.tipoUsuario", "tu")
                .leftJoin("us.usuarioPostoTrabalho", "upt")
                .leftJoin("us.solicitacaoUsuario", "su")
                .leftJoin("upt.postoTrabalho", "pt")
                .leftJoin("us.usuarioPerfil", "up")
                .where().opt().equal("us.cpfUsuario", cpfUsuario)
                .and().opt().like("lower(us.nomeCompletoUsuario)", nomeCompletoUsuario)
                .and().opt().equal("us.codigoTipoUsuario", codigoTipoUsuario)
                .and().opt().equal("up.identificacaoPerfil", codigoPerfil)
                .and().opt().equal("pt.identificacaoUnidOrganizac", codigoUnidadeOrganizacional)
                .and().opt().equal("pt.identificacaoPostoTrabalho", codigoPostoTrabalho));
    }
}