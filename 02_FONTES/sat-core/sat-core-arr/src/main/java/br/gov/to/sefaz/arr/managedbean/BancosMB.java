package br.gov.to.sefaz.arr.managedbean;

import br.gov.to.sefaz.arr.business.service.BancosService;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
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
@ManagedBean(name = "bancosMB")
@RequestScoped
@Controller
public class BancosMB extends AbstractPagingAndSortingController<BancosService, Bancos, Integer> {

    private static final int PAGES_PADDING = 2;

    @Autowired
    public BancosMB(BancosService service) {
        super(service);
        dto = new Bancos();
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
