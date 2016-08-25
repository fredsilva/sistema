package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.business.gestao.facade.TipoUsuarioFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de usuários.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/06/2016 14:27:00
 */
@ManagedBean(name = "tipoUsuarioMB")
@ViewScoped
public class TipoUsuarioMB extends DefaultCrudMB<TipoUsuario, Integer> {

    private final TipoUsuarioFilter filter;

    public TipoUsuarioMB() {

        super(TipoUsuario::new);
        filter = new TipoUsuarioFilter();
    }

    public TipoUsuarioFilter getFilter() {
        return filter;
    }

    /**
     * {@link DefaultCrudMB#setFacade(CrudFacade)}.
     *
     * @param facade fachado de TipoUsuario
     */
    @Autowired
    public void setFacade(TipoUsuarioFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected TipoUsuarioFacade getFacade() {
        return (TipoUsuarioFacade) super.getFacade();
    }

    /**
     * Filtra os Tipo Usuarios de acordo com os dados informados em tela.
     */
    public void search() {
        resultList = getFacade().find(filter);
        if (resultList.isEmpty()) {
            MessageUtil.addMesage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }
    }

    /**
     * Carrega todos os Tipo Usuarios existentes no Banco de Dados. - Utilizada para recarregar tabela.
     *
     * @return Lista das unidades.
     */
    @Override
    public Collection<TipoUsuario> getResultList() {
        return resultList;
    }

    /**
     * Carrega todos os Tipo Usuarios existentes no Banco de Dados.
     */
    public void loadAllTipoUsuarios() {
        resultList = getFacade().find(null);
    }
}
