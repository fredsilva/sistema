package br.gov.to.sefaz.seg.business.authentication.facade.impl;

import br.gov.to.sefaz.seg.business.authentication.domain.LoginDto;
import br.gov.to.sefaz.seg.business.authentication.domain.ResetPasswordDto;
import br.gov.to.sefaz.seg.business.authentication.domain.UsuarioSistemaAuthentication;
import br.gov.to.sefaz.seg.business.authentication.facade.LoginSistemaFacade;
import br.gov.to.sefaz.seg.business.authentication.service.LoginSistemaService;
import br.gov.to.sefaz.seg.exception.SecurityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação da fachada de Usuários do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:34:12
 */
@Component
public class LoginSistemaFacadeImpl implements LoginSistemaFacade {

    private final LoginSistemaService loginSistemaService;

    @Autowired
    public LoginSistemaFacadeImpl(LoginSistemaService loginSistemaService) {
        this.loginSistemaService = loginSistemaService;
    }

    @Override
    public UsuarioSistemaAuthentication ldapAuthenticate(LoginDto dto) throws SecurityException {
        return loginSistemaService.authenticate(dto);
    }

    @Override
    public void ldapResetPassword(ResetPasswordDto dto) {
        loginSistemaService.resetPassword(dto);
    }

    @Override
    public UsuarioSistemaAuthentication certAuthenticate(String username) throws SecurityException {
        return loginSistemaService.certAuthenticate(username);
    }

}
