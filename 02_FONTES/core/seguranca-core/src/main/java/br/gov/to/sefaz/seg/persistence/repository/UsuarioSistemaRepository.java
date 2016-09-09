package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoLoginSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static br.gov.to.sefaz.persistence.query.builder.QueryBuilder.hqlSelect;

/**
 * Repositório de acesso à base dados da entidade {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public class UsuarioSistemaRepository extends BaseRepository<UsuarioSistema, String> {

    private static final String US_SOLICITACAO_USUARIO = "us.solicitacaoUsuario";
    private static final String US_CPF_USUARIO = "us.cpfUsuario";
    private static final String US_USUARIO_POSTO_TRABALHO = "us.usuarioPostoTrabalho";
    private static final String UPT_POSTO_TRABALHO = "upt.postoTrabalho";
    private static final String US_LOGRADOURO = "us.logradouro";
    private static final String LO = "lo";
    private static final String SU = "su";
    private static final String PT = "pt";
    private static final String UPT = "upt";

    /**
     * Altera o estado de bloqueado do ususurio, incluindo a data de bloqueio.
     *
     * @param bloqueado       true se o ususario esta bloqueado
     * @param dataDesbloqueio até quando o ususario deve ficar bloqueado ou null caso esteja sendo desbloqueado
     * @param cpf             cpf do usaurio
     */
    public void updateEstaBloqueado(boolean bloqueado, LocalDateTime dataDesbloqueio, String cpf) {
        update(update -> update.set("estaBloqueado", bloqueado).set("dataDesbloqueio", dataDesbloqueio).whereId(cpf));
    }

    /**
     * Verifica se existe algum usuario com o cpf e email passados por parametro.
     *
     * @param cpf   cpf do usuario
     * @param email correio eletronico do usuario
     * @return true se existir um ususario com o mesmo cpf e email informados
     */
    public boolean existsByCpfAndEmail(String cpf, String email) {
        return exists(select -> select.whereId(cpf).and().equal("correioEletronico", email));
    }

    /**
     * Busca todos os usuários ativos no sistema.
     *
     * @param cpfUsuario          do Usuário a ser filtrado.
     * @param nomeCompletoUsuario do Usuário a ser filtrado.
     * @param situacaoUsuario     do Usuário a ser filtrado.
     * @param tipoUsuario         do Usuário a ser filtrado.
     * @param codigoEstado        do Usuário a ser filtrado.
     * @param codigoMunicipio     do Usuário a ser filtrado.
     * @return lista de usuários.
     */
    public List<UsuarioSistema> findAllUsuarioSistema(String cpfUsuario, String nomeCompletoUsuario,
            SituacaoUsuarioEnum situacaoUsuario, Integer tipoUsuario, String codigoEstado, Integer codigoMunicipio) {
        return find("us", select -> select
                .innerJoinFetch("us.municipio", "mu")
                .innerJoinFetch("mu.estado", "es")
                .leftJoinFetch("us.historicoLoginSistema", "hls")
                .where().opt().equal(US_CPF_USUARIO, cpfUsuario)
                .and().opt().like("us.nomeCompletoUsuario", nomeCompletoUsuario)
                .and().opt().equal("us.situacaoUsuario", situacaoUsuario)
                .and().opt().equal("us.codigoTipoUsuario", tipoUsuario)
                .and().opt().equal("us.codigoEstado", codigoEstado)
                .and().opt().equal("us.codigoMunicipio", codigoMunicipio)
                .and().condition(where -> where
                        .isNull("hls.dataHoraLogin")
                        .or().in("hls.dataHoraLogin", hqlSelect(HistoricoLoginSistema.class, "hls1")
                                .columns("MAX(hls1.dataHoraLogin)")
                                .where().equalColumns("hls1.usuarioSistema.cpfUsuario", US_CPF_USUARIO)))
        );
    }

    /**
     * Busca todos os usuários para a tela de Ativar inativar perfil.
     *
     * @return Lista de usuários.
     */
    public List<UsuarioSistema> findAllUsuarioSistemaPerfil(String cpfUsuario, String nomeCompletoUsuario,
            Long codigoUnidadeOrganizacional, Integer codigoPostoTrabalho) {
        return find("us", select -> select
                .innerJoinFetch(US_LOGRADOURO, LO)
                .leftJoinFetch(US_SOLICITACAO_USUARIO, SU)
                .leftJoinFetch(US_USUARIO_POSTO_TRABALHO, UPT)
                .leftJoinFetch(UPT_POSTO_TRABALHO, PT)
                .leftJoinFetch("pt.unidadeOrganizacional", "uo")
                .where()
                .opt().like("str(us.cpfUsuario)", cpfUsuario)
                .and().opt().equal(" us.codigoTipoUsuario", 4)
                .and().opt().like(" us.nomeCompletoUsuario", nomeCompletoUsuario)
                .and().opt().equal(" pt.identificacaoUnidOrganizac", codigoUnidadeOrganizacional)
                .and().opt().equal(" pt.identificacaoPostoTrabalho", codigoPostoTrabalho));
    }

    /**
     * Busca o usuário pelo CPF.
     *
     * @param cpf do usuário
     * @return {@link UsuarioSistema}.
     */
    @Override
    public UsuarioSistema findOne(String cpf) {
        return findOne("us", select -> select
                .leftJoinFetch(US_SOLICITACAO_USUARIO, SU)
                .leftJoinFetch(US_USUARIO_POSTO_TRABALHO, UPT)
                .leftJoinFetch(UPT_POSTO_TRABALHO, PT)
                .leftJoinFetch("pt.unidadeOrganizacional", "uo")
                .innerJoinFetch("us.municipio", "mu")
                .innerJoinFetch(US_LOGRADOURO, LO)
                .whereId(cpf));
    }

    /**
     * Atualiza a {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema#dataUltimaAlteracaoSenha}.
     *
     * @param dataUltimaAlteracaoSenha data a ser atualizado
     * @param cpf                      cpf do usaurio
     */
    public void updateDataUltimaAlteracaoSenha(LocalDateTime dataUltimaAlteracaoSenha, String cpf) {
        update(update -> update.set("dataUltimaAlteracaoSenha", dataUltimaAlteracaoSenha).whereId(cpf));
    }

    /**
     * Busca todos os usuários para a tela de Ativar inativar perfil.
     *
     * @return Lista de usuários.
     */
    public List<UsuarioSistema> findAllUsuarioSistemaManutencao(String nomeCompletoUsuario, String cpf,
            LocalDateTime dataCriacao, SituacaoUsuarioEnum situacao, Long identificacaoUnidOrganizac,
            Integer identificacaoPostoTrabalho) {
        return find("us", select -> select
                .leftJoinFetch(US_SOLICITACAO_USUARIO, SU)
                .leftJoinFetch(US_USUARIO_POSTO_TRABALHO, UPT)
                .leftJoinFetch(UPT_POSTO_TRABALHO, PT)
                .leftJoinFetch("pt.unidadeOrganizacional", "uo")
                .leftJoinFetch("us.municipio", "mu")
                .innerJoinFetch("us.logradouro", "lo")
                .innerJoinFetch("mu.estado", "es")
                .where().opt().equal(US_CPF_USUARIO, cpf)
                .and().opt().like("us.nomeCompletoUsuario", nomeCompletoUsuario)
                .and().opt().equal("trunc(us.dataInsercao)", dataCriacao)
                .and().opt().equal("us.situacaoUsuario", situacao)
                .and().opt().equal("pt.identificacaoUnidOrganizac", identificacaoUnidOrganizac)
                .and().opt().equal("pt.identificacaoPostoTrabalho", identificacaoPostoTrabalho));
    }

    /**
     * Consulta a quantidade de usuarios por {@link br.gov.to.sefaz.seg.persistence.domain.TipoUsuario}.
     *
     * @param tipoUsuario tipo de usuário
     * @return quantidade de usuários
     */
    public Long countByTipoUsuario(TipoUsuario tipoUsuario) {
        return count("us", select -> select.where().opt().equal("us.codigoTipoUsuario", tipoUsuario.getCode()));
    }

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
                .leftJoinFetch(US_USUARIO_POSTO_TRABALHO, UPT)
                .leftJoinFetch(US_SOLICITACAO_USUARIO, SU)
                .leftJoinFetch(UPT_POSTO_TRABALHO, PT)
                .leftJoinFetch("us.usuarioPerfil", "up")
                .where().opt().equal(US_CPF_USUARIO, cpfUsuario)
                .and().opt().like("lower(us.nomeCompletoUsuario)", nomeCompletoUsuario)
                .and().opt().equal("us.codigoTipoUsuario", codigoTipoUsuario)
                .and().opt().equal("up.identificacaoPerfil", codigoPerfil)
                .and().opt().equal("pt.identificacaoUnidOrganizac", codigoUnidadeOrganizacional)
                .and().opt().equal("pt.identificacaoPostoTrabalho", codigoPostoTrabalho));
    }
}
