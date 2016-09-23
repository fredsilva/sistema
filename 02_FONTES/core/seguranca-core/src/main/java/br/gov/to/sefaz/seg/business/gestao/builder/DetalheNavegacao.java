package br.gov.to.sefaz.seg.business.gestao.builder;

import java.util.Map;
import java.util.Objects;

/**
 * POJO que representa o arquivo JSON para histórico de navegação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 16/06/2016 14:21:00
 */
public class DetalheNavegacao {
    private String nomeUsuario;
    private String atuacaoNomeDe;
    private String serverName;
    private String userAgent;
    private String language;
    private String country;
    private String httpMethod;
    private String pathInfo;
    private Map<String, String[]> requestParameters;
    private String localAddress;
    private String remoteAddress;
    private String resource;
    private String timeStamp;
    private long timeEllapsed;

    public DetalheNavegacao(String nomeUsuario, String atuacaoNomeDe, String serverName,
            String userAgent, String language, String country, String httpMethod,
            String pathInfo, Map<String, String[]> requestParameters, String localAddress,
            String remoteAddress, String resource, String timeStamp, long timeEllapsed) {

        this.nomeUsuario = nomeUsuario;
        this.serverName = serverName;
        this.userAgent = userAgent;
        this.language = language;
        this.httpMethod = httpMethod;
        this.country = country;
        this.pathInfo = pathInfo;
        this.requestParameters = requestParameters;
        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
        this.resource = resource;
        this.timeStamp = timeStamp;
        this.timeEllapsed = timeEllapsed;
        this.atuacaoNomeDe = atuacaoNomeDe;
    }

    public DetalheNavegacao() {
        // Construtor Vazio.
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getTimeEllapsed() {
        return timeEllapsed;
    }

    public void setTimeEllapsed(long timeEllapsed) {
        this.timeEllapsed = timeEllapsed;
    }

    public String getAtuacaoNomeDe() {
        return atuacaoNomeDe;
    }

    public void setAtuacaoNomeDe(String atuacaoNomeDe) {
        this.atuacaoNomeDe = atuacaoNomeDe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DetalheNavegacao that = (DetalheNavegacao) o;
        return timeEllapsed == that.timeEllapsed
                && Objects.equals(nomeUsuario, that.nomeUsuario)
                && Objects.equals(serverName, that.serverName)
                && Objects.equals(userAgent, that.userAgent)
                && Objects.equals(language, that.language)
                && Objects.equals(httpMethod, that.httpMethod)
                && Objects.equals(country, that.country)
                && Objects.equals(pathInfo, that.pathInfo)
                && Objects.equals(requestParameters, that.requestParameters)
                && Objects.equals(localAddress, that.localAddress)
                && Objects.equals(remoteAddress, that.remoteAddress)
                && Objects.equals(resource, that.resource)
                && Objects.equals(timeStamp, that.timeStamp)
                && Objects.equals(atuacaoNomeDe, that.atuacaoNomeDe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomeUsuario, serverName, userAgent, language, httpMethod, country, pathInfo,
                requestParameters, localAddress, remoteAddress, resource, timeStamp, timeEllapsed, atuacaoNomeDe);
    }

    @Override
    public String toString() {
        return "DetalheNavegacao{"
                + "nomeUsuario='" + nomeUsuario + '\''
                + ", serverName='" + serverName + '\''
                + ", userAgent='" + userAgent + '\''
                + ", language='" + language + '\''
                + ", country='" + country + '\''
                + ", httpMethod='" + httpMethod + '\''
                + ", pathInfo='" + pathInfo + '\''
                + ", requestParameters=" + requestParameters
                + ", localAddress='" + localAddress + '\''
                + ", remoteAddress='" + remoteAddress + '\''
                + ", resource='" + resource + '\''
                + ", timeStamp='" + timeStamp + '\''
                + ", timeEllapsed=" + timeEllapsed + '\''
                + ", atuacaoNomeDe=" + atuacaoNomeDe
                + '}';
    }
}
