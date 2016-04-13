package br.gov.to.sefaz.seg.controller;

import br.gov.to.sefaz.seg.domain.Breadcrumb;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Controller do componente de breadcrumb, que serve para exibir a hierarquia de
 * navegação do usuário até a pagina atual.
 *
 * @author gabriel.dias@ntconsult.com.br
 */
@ManagedBean(name = "breadcrumbController")
@RequestScoped
public class BreadcrumbController {

    /**
     * Retorna todos os passos do breadcrumb até chegar na pagina atual.
     */
    public List<Breadcrumb> routes() {
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        Breadcrumb home = new Breadcrumb(false, contextPath + "/public/home.xhtml", "Home");
        Breadcrumb arr = new Breadcrumb(false, contextPath + "/public/arr.xhtml", "Arr");
        Breadcrumb example = new Breadcrumb(true, contextPath + "/public/example.xhtml", "Exemplo");

        return Arrays.asList(home, arr, example);
    }
}
