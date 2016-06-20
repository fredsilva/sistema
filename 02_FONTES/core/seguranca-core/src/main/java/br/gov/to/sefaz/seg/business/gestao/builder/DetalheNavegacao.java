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
    private String cpfUsuario;
    private String serverName;
    private String userAgent;
    private String language;
    private String httpMethod;
    private String country;
    private String pathInfo;
    private Map<String, String[]> parameterMap;
    private String localAddr;
    private String remoteAddr;
    private String resource;
    private String timeStamp;
    private long elapsedTime;

    public DetalheNavegacao(String cpfUsuario, String serverName, String userAgent,
            String language, String httpMethod, String country, String pathInfo,
            Map<String, String[]> parameterMap, String localAddr, String remoteAddr,
            String resource, String timeStamp, long elapsedTime) {
        this.cpfUsuario = cpfUsuario;
        this.serverName = serverName;
        this.userAgent = userAgent;
        this.language = language;
        this.httpMethod = httpMethod;
        this.country = country;
        this.pathInfo = pathInfo;
        this.parameterMap = parameterMap;
        this.localAddr = localAddr;
        this.remoteAddr = remoteAddr;
        this.resource = resource;
        this.timeStamp = timeStamp;
        this.elapsedTime = elapsedTime;
    }

    public DetalheNavegacao() {
        // Construtor Vazio.
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
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

    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public String getLocalAddr() {
        return localAddr;
    }

    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
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

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
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
        return elapsedTime == that.elapsedTime
                && Objects.equals(cpfUsuario, that.cpfUsuario)
                && Objects.equals(serverName, that.serverName)
                && Objects.equals(userAgent, that.userAgent)
                && Objects.equals(language, that.language)
                && Objects.equals(httpMethod, that.httpMethod)
                && Objects.equals(country, that.country)
                && Objects.equals(pathInfo, that.pathInfo)
                && Objects.equals(parameterMap, that.parameterMap)
                && Objects.equals(localAddr, that.localAddr)
                && Objects.equals(remoteAddr, that.remoteAddr)
                && Objects.equals(resource, that.resource)
                && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfUsuario, serverName, userAgent, language, httpMethod, country, pathInfo, parameterMap,
                localAddr, remoteAddr, resource, timeStamp, elapsedTime);
    }

    @Override
    public String toString() {
        return "DetalheNavegacao{"
                + "cpfUsuario='" + cpfUsuario + '\''
                + ", serverName='" + serverName + '\''
                + ", userAgent='" + userAgent + '\''
                + ", language='" + language + '\''
                + ", httpMethod='" + httpMethod + '\''
                + ", country='" + country + '\''
                + ", pathInfo='" + pathInfo + '\''
                + ", parameterMap=" + parameterMap
                + ", localAddr='" + localAddr + '\''
                + ", remoteAddr='" + remoteAddr + '\''
                + ", resource='" + resource + '\''
                + ", timeStamp='" + timeStamp + '\''
                + ", elapsedTime=" + elapsedTime
                + '}';
    }
}
