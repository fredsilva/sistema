package br.gov.to.sefaz.presentation.managedbean;

import br.gov.to.sefaz.util.properties.AppProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Managed Bean responsavel pela resolução das rotas do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 13/06/2016 09:36:00
 */
@Component
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
     * Retorna true se a url passada é da pagina protected atual atualmente aberta.
     *
     * @param url url a ser comparada com a atual
     * @return true se a url passada é da pagina protected atual atualmente aberta
     */
    public boolean isActualProtectedView(String url) {
        String actualPath = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
        return protectedViewPath(url).equals(getContextPath() + actualPath);
    }

    /**
     * Resolve a rota para o diretorio de views publicas.
     *
     * @param route complemento da rota
     * @return rota completa para o diretorio de views publicas
     */
    public String publicViewPath(String route) {
        return getContextPath() + "/public/" + route;
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
        return getContextPath() + "/public/logout";
    }

    public String getContextPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public String getHomePath() {
        return protectedViewPath("home.jsf");
    }

    public String getLoginPath() {
        return publicViewPath("login.jsf");
    }

    /**
     * Redireciona o usuário para a página passada por parâmetro. Deve-se passar o nome da página com / .
     * @param url da página a ser acessada.
     * @throws IOException exceção de IO.
     */
    public void redirect(String url) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(getContextPath() + url);
    }

    /**
     * Redireciona o usuário para a página Home.
     * @throws IOException exceção de IO.
     */
    public void redirectToHome() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(getHomePath());
    }

    public String getCertificadoLoginPath() {
        return "https://"
                + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName()
                + ":" + AppProperties.getProperty("certificado.port").orElse("8844")
                + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                + "/public/certificado.jsf?login";
    }

    public String getCertificadoSolicitacaoPath() {
        return "https://"
                + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName()
                + ":" + AppProperties.getProperty("certificado.port").orElse("8844")
                + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                + "/public/certificado.jsf?solicitacao";
    }

    public String getCertificadoECnpjPath() {
        return "https://"
                + FacesContext.getCurrentInstance().getExternalContext().getRequestServerName()
                + ":" + AppProperties.getProperty("certificado.port").orElse("8844")
                + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()
                + "/public/certificado.jsf?atuarUsuarioPrincipal";
    }
}
