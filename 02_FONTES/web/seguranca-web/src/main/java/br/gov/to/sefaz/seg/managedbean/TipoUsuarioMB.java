package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.BeanFactoryMB;
import br.gov.to.sefaz.seg.business.gestao.facade.TipoUsuarioFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de usuários.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/06/2016 14:27:00
 */
@ManagedBean(name = "tipoUsuarioMB")
@ViewScoped
public class TipoUsuarioMB {

    @ManagedProperty("#{springBeanFactoryMB}")
    private BeanFactoryMB beanFactoryMB;

    private TipoUsuarioFacade facade;

    private final TipoUsuarioFilter filter;

    private List<TipoUsuario> resultList;

    public TipoUsuarioMB() {
        filter = new TipoUsuarioFilter();
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
    public void setFacade(TipoUsuarioFacade facade) {
        this.facade = facade;
    }

    protected TipoUsuarioFacade getFacade() {
        return facade;
    }

    public TipoUsuarioFilter getFilter() {
        return filter;
    }

    /**
     * Filtra os Tipo Usuarios de acordo com os dados informados em tela.
     */
    public void search() {
        resultList = getFacade().find(filter);
        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }
    }

    /**
     * Carrega todos os Tipo Usuarios existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista das unidades.
     */
    public Collection<TipoUsuario> getResultList() {
        return resultList;
    }

    /**
     * Carrega todos os Tipo Usuarios existentes no Banco de Dados.
     */
    public void loadAllTipoUsuarios() {
        resultList = getFacade().findAllTipoUsuario();
    }

}
