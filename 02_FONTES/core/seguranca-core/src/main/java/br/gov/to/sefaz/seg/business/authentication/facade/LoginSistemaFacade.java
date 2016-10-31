package br.gov.to.sefaz.seg.business.authentication.facade;

import br.gov.to.sefaz.seg.business.authentication.domain.LoginDto;
import br.gov.to.sefaz.seg.business.authentication.domain.ResetPasswordDto;
import br.gov.to.sefaz.seg.business.authentication.domain.UsuarioSistemaAuthentication;
import br.gov.to.sefaz.seg.business.authentication.service.SecurityException;

/**
 * Contrato de acesso da fachada de Histórico de Logins do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:34:29
 */
public interface LoginSistemaFacade {

    /**
     * Realiza a autenticação do usuário e senha no AD (Active Directory) via protocolo LDAP.
     *
     * @param dto dto com as informações de cpf e password, necessárias para o login no AD
     * @return Usuário do Sistema Logado
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    UsuarioSistemaAuthentication ldapAuthenticate(LoginDto dto) throws SecurityException;

    /**
     * Realiza o reset da senha do usuário no ldap e envia e-mail ao mesmo com a senha criada.
     *
     * @param dto dto com as informações de cpf e e-mail, necessárias para a troca de senha
     */
    void ldapResetPassword(ResetPasswordDto dto);

    /**
     * Realiza a autenticação do usuário através de seu certificado digital.
     *
     * @param username cpf do usuário obtido através do certificado digital.
     * @return Usuário do Sistema Logado
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    UsuarioSistemaAuthentication certAuthenticate(String username) throws SecurityException;

}
