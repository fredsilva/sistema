package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.TipoGruposCnaeFacade;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos tipos de grupos cnae.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 09/05/2016 18:01:00
 */
@ManagedBean(name = "tipoGruposCnaeMB")
@ViewScoped
public class TipoGruposCnaeMB extends DefaultCrudMB<TipoGruposCnaes, Integer> {

    public TipoGruposCnaeMB() {
        super(TipoGruposCnaes::new);
    }

    @Autowired
    protected void setFacade(TipoGruposCnaeFacade facade) {
        super.setFacade(facade);
    }

}
