package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.PostoTrabalhoFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PostoTrabalhoFilter;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos bancos de arrecadação.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/06/2016 16:39:00
 */
@ManagedBean(name = "postoTrabalhoMB")
@ViewScoped
public class PostoTrabalhoMB extends DefaultCrudMB<PostoTrabalho, Integer> {

    private final PostoTrabalhoFilter filter;
    private Collection<UnidadeOrganizacional> allUnidadeOrganizacionais;

    public PostoTrabalhoMB() {
        super(PostoTrabalho::new);
        filter = new PostoTrabalhoFilter();
    }

    public PostoTrabalhoFilter getFilter() {
        return filter;
    }

    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     *
     * @param facade fachado de PostoTrabalho
     */
    @Autowired
    public void setFacade(PostoTrabalhoFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected PostoTrabalhoFacade getFacade() {
        return (PostoTrabalhoFacade) super.getFacade();
    }

    /**
     * Filtra as Unidades Organizacionais de acordo com os dados informados em tela.
     */
    public void search() {
        Collection<PostoTrabalho> resultList = getFacade().find(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    /**
     * Carrega todas as Unidades Organizacionais existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista das unidades.
     */
    public Collection<UnidadeOrganizacional> getAllUnidadeOrganizacionais() {

        if (allUnidadeOrganizacionais == null) {
            loadAllUnidadeOrganizacionais();
        }
        return allUnidadeOrganizacionais;
    }

    /**
     * Carrega todas as Unidades Organizacionais existentes no Banco de Dados.
     */
    public void loadAllUnidadeOrganizacionais() {
        allUnidadeOrganizacionais = getFacade().findAllUnidadeOrganizacional();
    }

    @Override
    public Collection<PostoTrabalho> getResultList() {
        return resultList;
    }

    @Override
    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.PostoTrabalho.form.sucesso.operacao");
    }

    @Override
    protected void showUpdateMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.PostoTrabalho.form.sucesso.update");
    }

    @Override
    protected void showPhysicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.PostoTrabalho.tabela.excluir.sucesso");
    }

    @Override
    protected void showLogicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.SEG, "seg.gestao.PostoTrabalho.tabela.excluir.sucesso");
    }
}
