package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.BancosFacade;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos bancos de arrecadação.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@ManagedBean(name = "bancosMB")
@ViewScoped
public class BancosMB extends DefaultCrudMB<Bancos, Integer> {

    @Autowired
    public BancosMB() {
        super(Bancos::new);
    }

    @Autowired
    public void setFacade(BancosFacade facade) {
        super.setFacade(facade);
    }

}
