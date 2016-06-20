package br.gov.to.sefaz.presentation.managedbean;

import br.gov.to.sefaz.util.properties.AppProperties;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Managed Bean responsavel pela resolução das rotas do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 13/06/2016 09:36:00
 */
@ManagedBean(name = "pathResolverMB")
public class PathResolverMB {

    /**
     * Resolve a rota para o diretorio de views privadas.
     *
     * @param route complemento da rota
     * @return rota completa para o diretorio de views privadas
     */
    public String protectedViewPath(String route) {
        return getContextPath() + "/protected/views/" + route;
    }

    /**
     * Resolve a rota para o diretorio de CSS.
     *
     * @param route complemento da rota
     * @return rota completa para o diretorio de CSS
     */
    public String cssPath(String route) {
        return getContextPath() + "/resources/css/" + route;
    }

    public String getLogoutPath() {
        return getContextPath() + "/logout";
    }

    public String getContextPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public String getHomePath() {
        return protectedViewPath("home.jsf");
    }

    public String getCertificadoPath() {
        return "https://"
                + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName()
                + ":" + AppProperties.getProperty("certificado.port").orElse("8844")
                + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                + "/public/certificado.jsf";
    }
}
