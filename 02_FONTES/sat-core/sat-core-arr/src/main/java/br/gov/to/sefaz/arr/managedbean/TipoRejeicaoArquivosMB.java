package br.gov.to.sefaz.arr.managedbean;

import br.gov.to.sefaz.arr.business.service.TipoRejeicaoArquivosService;
import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.common.controller.AbstractPagingAndSortingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * ManagedBean responsavel por operações de gerenciamento de tipos de rejeição de arquivos.
 *
 * @author gabriel.dias
 */
@ManagedBean(name = "tipoRejeicaoArquivosMB")
@RequestScoped
@Controller
public class TipoRejeicaoArquivosMB
        extends AbstractPagingAndSortingController<TipoRejeicaoArquivosService, TipoRejeicaoArquivos, Integer> {

    private static final int PAGES_PADDING = 2;

    @Autowired
    public TipoRejeicaoArquivosMB(TipoRejeicaoArquivosService service) {
        super(service);
        dto = new TipoRejeicaoArquivos();
    }

    /**
     * Gera uma lista com os números das paginas que devem ser exibidas para o usuário navegar.
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
