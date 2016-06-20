package br.gov.to.sefaz.seg.filter;

import br.gov.to.sefaz.util.properties.AppProperties;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Classe que representa o filtro de controle de acesso a aplicação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 14/06/2016 17:32:00
 */
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // não é necessário para o filtro.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        String serverName = req.getServerName();
        String hostPort = AppProperties.getProperty("host.port").orElse("8443");
        String host = "https://" + serverName + ":" + hostPort;

        response.setHeader("Access-Control-Allow-Origin", host);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "content-type");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // não é necessário para o filtro.
    }

}