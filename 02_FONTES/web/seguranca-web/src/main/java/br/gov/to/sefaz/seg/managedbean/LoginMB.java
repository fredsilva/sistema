package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.AutowiredMB;
import br.gov.to.sefaz.seg.business.authentication.domain.LoginDto;
import br.gov.to.sefaz.seg.business.authentication.facade.LoginSistemaFacade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean da tela de login.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/06/2016 11:13:00
 */
@ManagedBean(name = "loginMB")
@RequestScoped
public class LoginMB extends AutowiredMB {

    @Autowired
    private LoginSistemaFacade loginSistemaFacade;
    private final LoginDto dto;

    public LoginMB() {
        this.dto = new LoginDto();
    }

    /**
     * Realiza o login no sistema.
     */
    public void doLogin() {
        loginSistemaFacade.ldapAuthenticate(dto);
    }

    public LoginDto getDto() {
        return dto;
    }
}
