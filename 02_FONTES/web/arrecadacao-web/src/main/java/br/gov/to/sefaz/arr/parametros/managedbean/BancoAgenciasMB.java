package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.BancoAgenciasFacade;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;

import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean responsável por operações de manutenção de agências bancárias.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 03/05/2016 11:26:08
 */
@ManagedBean(name = "bancoAgenciasMB")
@ViewScoped
public class BancoAgenciasMB extends DefaultCrudMB<BancoAgencias, BancoAgenciasPK> {

    public BancoAgenciasMB() {
        super(BancoAgencias::new);
    }

    @Autowired
    public void setFacade(BancoAgenciasFacade facade) {
        super.setFacade(facade);
    }
}
