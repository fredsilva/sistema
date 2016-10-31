package br.gov.to.sefaz.par.managebean;

import br.gov.to.sefaz.par.gestao.business.facade.ParametroGeralFacade;
import br.gov.to.sefaz.par.gestao.business.service.filter.ParametroGeralFilter;
import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da manutenção de {@link br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral}.
 *
 * @author <a href="mailto:carlos.junior@ntconsult.com.br">carlos.junior</a>
 * @since 23/06/2016 15:15:00
 */
@ManagedBean(name = "parametroGeralMB")
@ViewScoped
public class ParametroGeralMB extends DefaultCrudMB<ParametroGeral, Integer> {

    private final ParametroGeralFilter filter;

    @Autowired
    public ParametroGeralMB() {
        super(ParametroGeral::new);
        filter = new ParametroGeralFilter();
    }

    /**
     * {@link br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB
     * #setFacade(br.gov.to.sefaz.business.facade.CrudFacade)}.
     *
     * @param facade fachada de ParametroGeral
     */
    @Autowired
    public void setFacade(ParametroGeralFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected ParametroGeralFacade getFacade() {
        return (ParametroGeralFacade) super.getFacade();
    }

    @Override
    public Collection<ParametroGeral> getResultList() {
        return resultList;
    }

    /**
     * Retorna o filtro de pesquisa com seu s respectivos valores.
     *
     * @return filtro de pesquisa
     */
    public ParametroGeralFilter getFilter() {
        return filter;
    }

    /**
     * Busca uma lista de {@link ParametroGeral} e carrega no {@link #setResultList(Collection)} baseado no
     * {@link #filter}.
     */
    public void search() {
        List<ParametroGeral> resultList = getFacade().find(filter);
        if (resultList.isEmpty()) {
            MessageUtil.addMessage("mensagem.pesquisa.vazia");
        }
        setResultList(resultList);
    }

    @Override
    public void save() {
        getFacade().save(getDto());
        showSaveMessage();
        clearDto();
    }

    @Override
    protected void showLogicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.PAR, "par.gestao.manutencaoParametros.table.excluir.sucesso");
    }

    @Override
    protected void showPhysicalDeleteMessage() {
        MessageUtil.addMessage(MessageUtil.PAR, "par.gestao.manutencaoParametros.table.excluir.sucesso");
    }

    @Override
    protected void showSaveMessage() {
        MessageUtil.addMessage(MessageUtil.PAR, "par.gestao.manutencaoParametros.sucesso.operacao");
    }

    @Override
    protected void showUpdateMessage() {
        MessageUtil.addMessage(MessageUtil.PAR, "par.gestao.manutencaoParametros.sucesso.operacao");
    }

}
