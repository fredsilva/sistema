package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.BeanFactoryMB;
import br.gov.to.sefaz.seg.business.authentication.domain.ResetPasswordDto;
import br.gov.to.sefaz.seg.business.authentication.facade.LoginSistemaFacade;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da tela de reset de senha.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 15/06/2016 10:25:59
 */
@ManagedBean(name = "resetPasswordMB")
@ViewScoped
public class ResetPasswordMB {

    @ManagedProperty("#{springBeanFactoryMB}")
    private BeanFactoryMB beanFactoryMB;

    private LoginSistemaFacade loginSistemaFacade;

    private ResetPasswordDto dto;

    public ResetPasswordMB() {
        dto = new ResetPasswordDto();
    }

    @PostConstruct
    protected void injectDependencies() {
        beanFactoryMB.injectBeans(this);
    }

    @Autowired
    public void setLoginSistemaFacade(LoginSistemaFacade loginSistemaFacade) {
        this.loginSistemaFacade = loginSistemaFacade;
    }

    public BeanFactoryMB getBeanFactoryMB() {
        return beanFactoryMB;
    }

    public void setBeanFactoryMB(BeanFactoryMB beanFactoryMB) {
        this.beanFactoryMB = beanFactoryMB;
    }

    public ResetPasswordDto getDto() {
        return dto;
    }

    public void setDto(ResetPasswordDto dto) {
        this.dto = dto;
    }

    /**
     * Realização reset da senha no AD (Active Directory).
     */
    public void resetPassword() {
        loginSistemaFacade.ldapResetPassword(dto);
        MessageUtil.addMessage(MessageUtil.SEG, "reset.password.message.sucesso");
    }

}
