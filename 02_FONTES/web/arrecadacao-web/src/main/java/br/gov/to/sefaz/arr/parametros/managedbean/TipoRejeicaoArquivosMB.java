package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.parametros.business.facade.TipoRejeicaoArquivosFacade;
import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;

import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean responsavel por operações de gerenciamento de tipos de rejeição de arquivos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@ManagedBean(name = "tipoRejeicaoArquivosMB")
@ViewScoped
public class TipoRejeicaoArquivosMB extends DefaultCrudMB<TipoRejeicaoArquivos, Integer> {

    public TipoRejeicaoArquivosMB() {
        super(TipoRejeicaoArquivos::new);
    }

    /**
     * /** {@link DefaultCrudMB#setFacade(CrudFacade)}.
     *
     * @param facade fachado de TipoRejeicaoArquivos
     */
    @Autowired
    public void setFacade(TipoRejeicaoArquivosFacade facade) {
        super.setFacade(facade);
    }
}
