package br.gov.to.sefaz.seg.business.authentication.service;

import br.gov.to.sefaz.seg.business.authentication.domain.LoginDto;
import br.gov.to.sefaz.seg.business.authentication.domain.ResetPasswordDto;
import br.gov.to.sefaz.seg.business.authentication.domain.UsuarioSistemaAuthentication;

/**
 * Contrato de acesso do serviço de Login do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:46
 */
public interface LoginSistemaService {

    /**
     * Realiza a autenticação do usuário e senha no AD (Active Directory) via protocolo LDAP. Após sutenticado valida o
     * usuário na base de dados.
     *
     * @param dto dto com as informações de cpf e password, necessárias para o login no AD
     * @return Usuário do Sistema Logado
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    UsuarioSistemaAuthentication authenticate(LoginDto dto) throws SecurityException;

    /**
     * Realiza a autenticação do usuário através de seu certificado digital.
     *
     * @param username cpf do usuário obtido através do certificado digital.
     * @return Usuário do Sistema Logado
     * @throws SecurityException exceção de segurança no caso de login inválido.
     */
    UsuarioSistemaAuthentication certAuthenticate(String username) throws SecurityException;

    /**
     * Realiza o reset da senha do usuário no ldap e envia e-mail ao mesmo com a nova senha criada.
     *
     * @param dto dto com as informações de cpf e e-mail, necessárias para a troca de senha
     */
    void resetPassword(ResetPasswordDto dto);

}
