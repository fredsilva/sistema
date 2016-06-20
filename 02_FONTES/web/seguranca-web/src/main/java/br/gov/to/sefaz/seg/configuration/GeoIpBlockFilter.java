package br.gov.to.sefaz.seg.configuration;

import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import com.maxmind.geoip.Country;
import com.maxmind.geoip.LookupService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Filtro para bloqueio de acesso a estrangeiros a aplicação.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 17/06/2016 10:57:00
 */
public class GeoIpBlockFilter implements Filter {

    private LookupService lookupService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // não é necessário para o filtro.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        InetAddress inetAddress = InetAddress.getByName(req.getRemoteAddr());

        if (AuthenticatedUserHandler.isAuthenticated()
                || inetAddress.isLoopbackAddress()
                || inetAddress.isSiteLocalAddress()) {
            chain.doFilter(req, res);
        } else {
            Country country = getCountry(inetAddress);

            if ("BR".equals(country.getCode())) {
                chain.doFilter(req, res);
            } else {
                ((HttpServletResponse) res).setStatus(401);
                res.setContentType("text/plain");

                String message = SourceBundle.getMessage(MessageUtil.SEG, "geral.foreign.access");
                String foreignMessage = String.format(message, inetAddress.getHostAddress(),
                        country.getCode(), country.getName());

                res.getWriter().write(foreignMessage);
            }
        }
    }

    private Country getCountry(InetAddress inetAddress) throws IOException {
        if (lookupService == null) {
            String tempFolderPath = System.getProperty("jboss.server.temp.dir");
            InputStream databaseIs = getClass().getResourceAsStream("/GeoIP.dat");
            Path databasePath = Files.createTempFile(Paths.get(tempFolderPath), "SEFAZ_", "_GeoIP.dat");
            Files.copy(databaseIs, databasePath, StandardCopyOption.REPLACE_EXISTING);
            File databaseFile = databasePath.toFile();
            databaseFile.deleteOnExit();

            lookupService = new LookupService(databaseFile,
                    LookupService.GEOIP_MEMORY_CACHE | LookupService.GEOIP_CHECK_CACHE);
        }

        return lookupService.getCountry(inetAddress);
    }

    @Override
    public void destroy() {
        // não é necessário para o filtro.
    }
}
