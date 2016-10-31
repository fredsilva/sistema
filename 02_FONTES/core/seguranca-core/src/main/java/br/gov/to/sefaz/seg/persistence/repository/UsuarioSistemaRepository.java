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
    private static final String US_USUARIO_POSTO_TRABALHO = "us.listUsuarioPostoTrabalho";
    private static final String UPT_POSTO_TRABALHO = "upt.postoTrabalho";
    private static final String US_LOGRADOURO = "us.logradouro";
    private static final String US_MUNICIPIO = "us.municipio";
    private static final String MU_ESTADO = "mu.estado";
    private static final String US_HISTORICO_LOGIN_SISTEMA = "us.historicoLoginSistema";
    private static final String PT_UNIDADE_ORGANIZACIONAL = "pt.unidadeOrganizacional";
    private static final String US_CODIGO_TIPO_USUARIO = "us.codigoTipoUsuario";
    private static final String US_NOME_COMPLETO_USUARIO = "us.nomeCompletoUsuario";
    private static final String PT_IDENTIFICACAO_UNID_ORGANIZAC = "pt.identificacaoUnidOrganizac";
    private static final String PT_IDENTIFICACAO_POSTO_TRABALHO = "pt.identificacaoPostoTrabalho";
    private static final String US_SITUACAO_USUARIO = "us.situacaoUsuario";
    private static final String US_CODIGO_ESTADO = "us.codigoEstado";
    private static final String US_CODIGO_MUNICIPIO = "us.codigoMunicipio";
    private static final String HLS_DATA_HORA_LOGIN = "hls.dataHoraLogin";
    private static final String US_USUARIO_PERFIL = "us.usuarioPerfil";
    private static final String UP_IDENTIFICACAO_PERFIL = "up.identificacaoPerfil";
    private static final String UP_PERFIS_SISTEMA = "up.perfisSistema";
    private static final String LO = "lo";
    private static final String SU = "su";
    private static final String PT = "pt";
    private static final String UPT = "upt";
    private static final String MU = "mu";
    private static final String ES = "es";
    private static final String HLS = "hls";
    private static final String UO = "uo";
    private static final String UP = "up";
    private static final String PS = "ps";

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
                .innerJoinFetch(US_LOGRADOURO, LO)
                .innerJoinFetch(US_MUNICIPIO, MU)
                .innerJoinFetch(MU_ESTADO, ES)
                .leftJoinFetch(US_HISTORICO_LOGIN_SISTEMA, HLS)
                .leftJoinFetch(US_USUARIO_POSTO_TRABALHO, UPT)
                .leftJoinFetch(UPT_POSTO_TRABALHO, PT)
                .leftJoinFetch(PT_UNIDADE_ORGANIZACIONAL, UO)
                .where().opt().equal(US_CPF_USUARIO, cpfUsuario)
                .and().opt().like(US_NOME_COMPLETO_USUARIO, nomeCompletoUsuario)
                .and().opt().equal(US_SITUACAO_USUARIO, situacaoUsuario)
                .and().opt().equal(US_CODIGO_TIPO_USUARIO, tipoUsuario)
                .and().opt().equal(US_CODIGO_ESTADO, codigoEstado)
                .and().opt().equal(US_CODIGO_MUNICIPIO, codigoMunicipio)
                .and().condition(where -> where
                        .isNull(HLS_DATA_HORA_LOGIN)
                        .or().in(HLS_DATA_HORA_LOGIN, hqlSelect(HistoricoLoginSistema.class, "hls1")
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
                .innerJoinFetch(US_MUNICIPIO, MU)
                .innerJoinFetch(MU_ESTADO, ES)
                .leftJoinFetch(US_SOLICITACAO_USUARIO, SU)
                .leftJoinFetch(US_USUARIO_POSTO_TRABALHO, UPT)
                .leftJoinFetch(UPT_POSTO_TRABALHO, PT)
                .leftJoinFetch(PT_UNIDADE_ORGANIZACIONAL, UO)
                .where()
                .opt().like("str(" + US_CPF_USUARIO + ")", cpfUsuario)
                .and().opt().equal(US_CODIGO_TIPO_USUARIO, 4)
                .and().opt().like(US_NOME_COMPLETO_USUARIO, nomeCompletoUsuario)
                .and().opt().equal(PT_IDENTIFICACAO_UNID_ORGANIZAC, codigoUnidadeOrganizacional)
                .and().opt().equal(PT_IDENTIFICACAO_POSTO_TRABALHO, codigoPostoTrabalho));
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
                .leftJoinFetch(PT_UNIDADE_ORGANIZACIONAL, UO)
                .innerJoinFetch(US_LOGRADOURO, LO)
                .innerJoinFetch(US_MUNICIPIO, MU)
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
                .leftJoinFetch(PT_UNIDADE_ORGANIZACIONAL, UO)
                .innerJoinFetch(US_LOGRADOURO, LO)
                .innerJoinFetch(US_MUNICIPIO, MU)
                .innerJoinFetch(MU_ESTADO, ES)
                .where().opt().equal(US_CPF_USUARIO, cpf)
                .and().opt().like(US_NOME_COMPLETO_USUARIO, nomeCompletoUsuario)
                .and().opt().equal("trunc(us.dataInsercao)", dataCriacao)
                .and().opt().equal(US_SITUACAO_USUARIO, situacao)
                .and().opt().equal(PT_IDENTIFICACAO_UNID_ORGANIZAC, identificacaoUnidOrganizac)
                .and().opt().equal(PT_IDENTIFICACAO_POSTO_TRABALHO, identificacaoPostoTrabalho));
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
                .leftJoinFetch(US_SOLICITACAO_USUARIO, SU)
                .leftJoinFetch(US_USUARIO_PERFIL, UP)
                .leftJoinFetch(UP_PERFIS_SISTEMA, PS)
                .leftJoinFetch(US_USUARIO_POSTO_TRABALHO, UPT)
                .leftJoinFetch(UPT_POSTO_TRABALHO, PT)
                .leftJoinFetch(PT_UNIDADE_ORGANIZACIONAL, UO)
                .innerJoinFetch(US_LOGRADOURO, LO)
                .innerJoinFetch(US_MUNICIPIO, MU)
                .innerJoinFetch(MU_ESTADO, ES)
                .where().opt().equal(US_CPF_USUARIO, cpfUsuario)
                .and().opt().like("lower(" + US_NOME_COMPLETO_USUARIO + ")", nomeCompletoUsuario)
                .and().opt().equal(US_CODIGO_TIPO_USUARIO, codigoTipoUsuario)
                .and().opt().equal(UP_IDENTIFICACAO_PERFIL, codigoPerfil)
                .and().opt().equal(PT_IDENTIFICACAO_UNID_ORGANIZAC, codigoUnidadeOrganizacional)
                .and().opt().equal(PT_IDENTIFICACAO_POSTO_TRABALHO, codigoPostoTrabalho));
    }

}
