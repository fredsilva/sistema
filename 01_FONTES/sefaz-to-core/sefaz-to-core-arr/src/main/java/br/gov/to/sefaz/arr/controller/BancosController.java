package br.gov.to.sefaz.arr.controller;

import br.gov.to.sefaz.arr.model.entity.Bancos;
import br.gov.to.sefaz.arr.service.iface.BancosService;
import br.gov.to.sefaz.common.controller.AbstractPagingAndSortingController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author roger.gouveia
 */
@ManagedBean(name = "bancosController")
@RequestScoped
@Controller
public class BancosController extends AbstractPagingAndSortingController<BancosService, Bancos, Integer> {

    private static final int PAGES_PADDING = 2;

    @Autowired
    public BancosController(BancosService service) {
        super(service);
        dto = new Bancos();
    }

    public int getPages() {
        return page == null ? 1 : page.getNumber() + 1;
    }

    /**
     * Gera uma lista com os números das paginas que devem ser exibidas para o usuário navegar.
     */
    public List<Integer> createPageList() {
        List<Integer> pages = new ArrayList<>();

        int firstPage = Math.max(getPages() - PAGES_PADDING, 1);

        int lastPage = Math.min(getPages() + PAGES_PADDING, page.getTotalPages());

        for (int i = firstPage; i <= lastPage; i++) {
            pages.add(i);
        }

        return pages;
    }

}
