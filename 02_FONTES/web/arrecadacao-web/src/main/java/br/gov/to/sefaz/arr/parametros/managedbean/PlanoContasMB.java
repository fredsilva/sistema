package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.PlanoContasFacade;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean dos bancos de arrecadação.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@ManagedBean(name = "planoContasMB")
@ViewScoped
public class PlanoContasMB extends DefaultCrudMB<PlanoContas, Long> {

    @Autowired
    public PlanoContasMB() {
        super(PlanoContas::new);
    }

    @Autowired
    protected void setFacade(PlanoContasFacade facade) {
        super.setFacade(facade);
    }

    @Override
    protected PlanoContasFacade getFacade() {
        return (PlanoContasFacade) super.getFacade();
    }

    public void search() {
        setResultList(new ArrayList<>());
    }
}
