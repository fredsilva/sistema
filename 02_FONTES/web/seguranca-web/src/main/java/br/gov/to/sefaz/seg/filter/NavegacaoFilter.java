package br.gov.to.sefaz.seg.filter;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.facade.ProcuracaoUsuarioFacade;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;
import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * {@link javax.servlet.Filter} utilizado para armazenar informações de navegação do usuário.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 16/06/2016 11:53:00
 */
@Component
public class NavegacaoFilter implements Filter {

    @Autowired
    private LogNavegacaoFilterUtil navegacaoFilterUtil;

    @Autowired
    private ProcuracaoUsuarioFacade procuracaoUsuarioFacade;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // não é necessário para o filtro.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        HttpServletRequest request = (HttpServletRequest) req;
        long startTime = System.currentTimeMillis();

        if (AuthenticatedUserHandler.isAuthenticated()) {
            String cpfUsuario = AuthenticatedUserHandler.getCpf();
            LocalDateTime localDateTime = LocalDateTime.now();
            chain.doFilter(req, res);
            long elapsedTime = System.currentTimeMillis() - startTime;
            String cpfCnpjProcurador = retrieveCpfCnpjProcurado();

            navegacaoFilterUtil.saveLogNavegacao(request, cpfUsuario, localDateTime, TipoOperacaoEnum.NAVEGACAO,
                    elapsedTime, cpfCnpjProcurador, request.getRequestURL().toString());
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        // não é necessário para o filtro.
    }

    private String retrieveCpfCnpjProcurado() {
        String cpfCnpjProcurador = null;
        RoleGroupKey activeGroup = AuthenticatedUserHandler.getActiveGroup().orElse(null);
        if (activeGroup != null && activeGroup.getType() == RoleGroupType.PROCURACAO) {
            ProcuracaoUsuario procuracaoUsuario = procuracaoUsuarioFacade.findOne(activeGroup.getId());
            cpfCnpjProcurador = procuracaoUsuario.getCpfOrigem();
            if (Objects.isNull(cpfCnpjProcurador)) {
                cpfCnpjProcurador = procuracaoUsuario.getCnpjOrigem();
            }
        }
        return cpfCnpjProcurador;
    }
}
