package br.gov.to.sefaz.arr.managedbean;

import br.gov.to.sefaz.arr.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.business.service.TipoGruposCnaeService;
import br.gov.to.sefaz.arr.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.persistence.entity.TipoGruposCnae;
import br.gov.to.sefaz.common.controller.AbstractPagingAndSortingController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean dos bancos de arrecadação.
 *
 * @author roger.gouveia
 */
@ManagedBean(name = "planoContasMB")
@RequestScoped
@Controller
public class PlanoContasMB extends AbstractPagingAndSortingController<PlanoContasService, PlanoContas, String> {

    private static final int PAGES_PADDING = 2;

    @Autowired
    private TipoGruposCnaeService tipoGruposCnaeService;

    @Autowired
    public PlanoContasMB(PlanoContasService service) {
        super(service);
        dto = new PlanoContas();
    }

    public List<TipoGruposCnae> getListTipoGruposCnae() {
        return new ArrayList<TipoGruposCnae>(tipoGruposCnaeService.findAll());
    }

    /**
     * Gera uma lista com os números das paginas que devem ser exibidas para o
     * usuário navegar.
     */
    public List<Integer> createPageList() {
        List<Integer> pages = new ArrayList<>();

        int firstPage = Math.max(getPageNumber() - PAGES_PADDING, 1);

        int lastPage = Math.min(getPageNumber() + PAGES_PADDING, page.getTotalPages());

        for (int i = firstPage; i <= lastPage; i++) {
            pages.add(i);
        }

        return pages;
    }

}
