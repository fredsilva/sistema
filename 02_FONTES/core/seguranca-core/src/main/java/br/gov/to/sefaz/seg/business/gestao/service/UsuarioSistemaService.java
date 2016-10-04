package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.business.authentication.domain.ChangePasswordDto;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtivarInativarPerfilFilter;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtribuirPerfilFilter;
import br.gov.to.sefaz.seg.business.gestao.service.filter.ManterUsuarioSistemaFilter;
import br.gov.to.sefaz.seg.business.gestao.service.filter.UsuarioSistemaFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de Usuários do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:46
 */
public interface UsuarioSistemaService extends CrudService<UsuarioSistema, String> {

    /**
     * Recupera uma lista de usuários baseado no nome e no CPF (ou em apenas um caso o outro esteja ausente).
     *
     * @param cpf CPF dos usuários que se deseja encontrar
     * @param nome Nome dos usuários que se deseja encontrar
     * @return Uma lista com todos os resultados da base dadados que fizerem match com LIKE e AND ao nome e cpf
     *         informado
     */
    Collection<UsuarioSistema> findAllByCpfAndName(Long cpf, String nome);

    /**
     * Método que executa os custons validators de Usuário Sistema.
     *
     * @param usuarioSistema Usuário a ser validado
     */
    void validateLogin(UsuarioSistema usuarioSistema);

    /**
     * Bloqueia um usuario no sistema e seta a data de bloqueio para 2 horas no futuro.
     * @param cpf cpf do ususario
     */
    void blockUser(String cpf);

    /**
     * Desbloqueia um usuario no sistema e seta a data de bloqueio para null.
     * @param cpf cpf do ususario
     */
    void unblockUser(String cpf);

    /**
     * Busca todos os usuários.
     * @param filter filtro passado em tela.
     * @return lista de {@link UsuarioSistema}.
     */
    List<UsuarioSistema> findAllUsuarioSistema(UsuarioSistemaFilter filter);

    /**
     * Salva um novo usuário no sistema.
     * @param usuarioSistema a ser adicionado.
     */
    void saveNewUsuarioSistemaSolicitacaoSenha(UsuarioSistema usuarioSistema);

    /**
     * Realiza o reset da senha do usuário no ldap e envia e-mail ao mesmo com a nova senha criada.
     *
     * @param usuarioSistema usuarioSistema a ser resetado a senha
     */
    UsuarioSistema resetPassword(UsuarioSistema usuarioSistema);

    /**
     * Realiza a alteração da senha do usuário logado no ldap.
     *
     * @param dto dto de alteração de senha
     */
    void changePassword(ChangePasswordDto dto);

    /**
     * Busca todos os usuários para a tela de Ativar Inativar perfil.
     * @param filter filtro da tela.
     * @return lista de {@link UsuarioSistema}
     */
    List<UsuarioSistema> findAllUsuarioSistemaPerfil(AtivarInativarPerfilFilter filter);

    /**
     * Busca todos os usuários para a tela de Manutenção de Usuário.
     * @param filter filtro da tela.
     * @return lista de {@link UsuarioSistema}
     */
    List<UsuarioSistema> findAllUsuarioSistemaManutencao(ManterUsuarioSistemaFilter filter);

    /**
     * Atualiza o status do usuário.
     * @param usuarioSistema a ser atualizado.
     */
    UsuarioSistema updateStatusUsuario(UsuarioSistema usuarioSistema);

    /**
     * Método para habilitar usuário no Ldap.
     * @param usuarioSistema usuário a ser habilitado.
     */
    void enableUser(UsuarioSistema usuarioSistema);

    /**
     * Consulta a quantidade de usuarios por {@link br.gov.to.sefaz.seg.persistence.domain.TipoUsuario}.
     *
     * @param tipoUsuario tipo de usuário
     * @return quantidade de usuários
     */
    Long countByTipoUsuario(TipoUsuario tipoUsuario);

    /**
     * Busca todos os {@link UsuarioSistema} de acordo com o filtro passado em tela.
     * @param filter filtro passado em tela.
     * @return todos os {@link UsuarioSistema}.
     */
    Collection<UsuarioSistema> findAllByFilter(AtribuirPerfilFilter filter);

    /**
     * Retorna o nome do usuario baseado no CPF.
     *
     * @param usuarioCpf cpf do usuario
     * @return nome do usuario
     * @throws br.gov.to.sefaz.exception.BusinessException se o usuario não existe na base de dados
     */
    String findNomeByCpf(String usuarioCpf);

    /**
     * Atualiza o {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema#getUsuarioPerfil()}, validando se o
     * mesmo está vazio.
     * @param dto {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema} passado em tela.
     */
    void updateAtribuirUsuarioPerfil(UsuarioSistema dto);

}
