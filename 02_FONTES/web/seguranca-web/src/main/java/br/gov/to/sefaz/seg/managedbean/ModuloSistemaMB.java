package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.ModuloSistemaFacade;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da manutenção das Cadastro de Sistemas.
 *
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 05/07/2016 16:19:00
 */
@ManagedBean(name = "moduloSistemaMB")
@ViewScoped
public class ModuloSistemaMB extends DefaultCrudMB<ModuloSistema, Long> {

    public ModuloSistemaMB() {
        super(ModuloSistema::new);
    }

    /**
     * {@link DefaultCrudMB#setFacade(br.gov.to.sefaz.business.facade.CrudFacade)}.
     *
     * @param facade fachada de ModuloSistema
     */
    @Autowired
    public void setFacade(ModuloSistemaFacade facade) {
        super.setFacade(facade);
    }

    @Override
    public void update() {
        getFacade().update(getDto());
        getResultList().removeIf(e -> e.getId().equals(getDto().getId()));
        showUpdateMessage();
        getResultList().add(getFacade().findOne(getDto().getId()));
        clearDto();
    }

    @Override
    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manutencaoCadastroSistema.form.sucesso.operacao");
    }

    /**
     * Mostra a mensagem de exclusão específica para Sistemas Cadastrados.
    */
    @Override
    protected void showPhysicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manutencaoCadastroSistema.tabela.excluir.sucesso");
    }

    /**
     * Mostra a mensagem de exclusão específica para Sistemas Cadastrados.
     */
    @Override
    protected void showLogicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.manutencaoCadastroSistema.tabela.excluir.sucesso");
    }

}
