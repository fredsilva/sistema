package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.BeanFactoryMB;
import br.gov.to.sefaz.seg.business.authentication.domain.ChangePasswordDto;
import br.gov.to.sefaz.seg.business.gestao.facade.UsuarioSistemaFacade;
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
@ManagedBean(name = "changePasswordMB")
@ViewScoped
public class ChangePasswordMB {

    @ManagedProperty("#{springBeanFactoryMB}")
    private BeanFactoryMB beanFactoryMB;

    private UsuarioSistemaFacade facade;

    private ChangePasswordDto dto;

    public ChangePasswordMB() {
        dto = new ChangePasswordDto();
    }

    @PostConstruct
    protected void injectDependencies() {
        beanFactoryMB.injectBeans(this);
    }

    public BeanFactoryMB getBeanFactoryMB() {
        return beanFactoryMB;
    }

    public void setBeanFactoryMB(BeanFactoryMB beanFactoryMB) {
        this.beanFactoryMB = beanFactoryMB;
    }

    @Autowired
    public void setFacade(UsuarioSistemaFacade facade) {
        this.facade = facade;
    }

    public ChangePasswordDto getDto() {
        return dto;
    }

    public void setDto(ChangePasswordDto dto) {
        this.dto = dto;
    }

    /**
     * Executa a alteração da senha do usuário.
     */
    public void executeChangePasswd() {
        facade.changePassword(dto);
        MessageUtil.addMessage(MessageUtil.SEG, "change.password.sucesso");
    }

}
