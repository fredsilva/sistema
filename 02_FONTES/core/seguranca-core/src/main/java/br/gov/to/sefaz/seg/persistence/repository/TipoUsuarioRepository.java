package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório de acesso à base dados da entidade {@link TipoUsuario}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public interface TipoUsuarioRepository extends BaseRepository<TipoUsuario, Integer> {

    String ALL_PROFILES_COUNTED = "SELECT new TipoUsuario(tu.codigoTipoUsuario AS codigoTipoUsuario,"
            + " tu.descricaoTipoUsuario AS descricaoTipoUsuario,"
            + " (SELECT COUNT(us.cpfUsuario) FROM UsuarioSistema us "
            + "  WHERE us.codigoTipoUsuario = tu.codigoTipoUsuario"
            + "  AND us.situacaoUsuario = :situacaoUsuario "
            + "  AND us.registroExcluido = false) AS quantidadeUsuarios )"
            + " FROM TipoUsuario tu"
            + " WHERE LOWER(tu.descricaoTipoUsuario) LIKE LOWER(:descricaoTipoUsuario)"
            + " AND tu.registroExcluido = false"
            + " GROUP BY tu.codigoTipoUsuario, tu.descricaoTipoUsuario"
            + " ORDER BY tu.codigoTipoUsuario";

    String EXISTS_LOCK_REFERENCE_USUARIO =
            "SELECT CASE WHEN (COUNT(TUS.CODIGO_TIPO_USUARIO) > 0) THEN 'true' ELSE 'false' END"
                    + " FROM SEFAZ_SEG.TA_USUARIO_SISTEMA TUS"
                    + " WHERE TUS.CODIGO_TIPO_USUARIO = :id";

    /**
     * Verifica se algum Usuário é do Tipo de Usuário selecionado.
     *
     * @param id Identificação do Tipo de Usuário.
     * @return Boolean se verdadeiro existe referência, se falso não existe.
     */
    @Query(value = EXISTS_LOCK_REFERENCE_USUARIO, nativeQuery = true)
    Boolean existsLockReferenceUsuario(@Param(value = "id") Integer id);

    /**
     * Busca todos os usuários ativos no sistema.
     * @param situacaoUsuario situação do usuário.
     * @param descricaoTipoUsuario descrição do tipo de usuário.
     * @return lista de usuários.
     */
    @Query(value = ALL_PROFILES_COUNTED)
    List<TipoUsuario> allProfilesCounted(@Param(value = "situacaoUsuario") SituacaoUsuarioEnum situacaoUsuario,
            @Param(value = "descricaoTipoUsuario") String descricaoTipoUsuario);
}
