package br.gov.to.sefaz.seg.configuration;

import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupKey;
import br.gov.to.sefaz.seg.business.authentication.domain.RoleGroupType;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.facade.ProcuracaoUsuarioFacade;
import br.gov.to.sefaz.seg.filter.LogNavegacaoFilterUtil;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoUsuario;
import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;
import br.gov.to.sefaz.seg.persistence.repository.OpcaoAplicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.time.LocalDateTime;

/**
 * Configurações do spring security para as regras de acesso e segurança do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 19/05/2016 17:56:00
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private OpcaoAplicacaoRepository opcaoAplicacaoRepository;

    @Autowired
    private LogNavegacaoFilterUtil navegacaoFilterUtil;

    @Autowired
    private ProcuracaoUsuarioFacade procuracaoUsuarioFacade;

    @Override
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    protected void configure(HttpSecurity http) throws Exception {
        setOpcoesRules(http);
        http.authorizeRequests()
                .antMatchers("/index.html", "/public/**", "/org.richfaces.resources/**",
                        "/javax.faces.resource/**", "/resources/**")
                .permitAll()
                .antMatchers("/protected/views/**").authenticated();
        http.csrf().disable()
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    if (AuthenticatedUserHandler.isAuthenticated()) {
                        long startTime = System.currentTimeMillis();
                        String cpfUsuario = AuthenticatedUserHandler.getCpf();
                        LocalDateTime localDateTime = LocalDateTime.now();
                        long elapsedTime = System.currentTimeMillis() - startTime;
                        String cpfCnpjProcurador = retrieveCpfCnpjProcurado();

                        navegacaoFilterUtil.saveLogNavegacao(request, cpfUsuario, localDateTime,
                                TipoOperacaoEnum.TENTATIVA_NEGADA, elapsedTime, cpfCnpjProcurador, request
                                        .getRequestURL().toString());
                    }
                    response.sendRedirect(request.getContextPath() + "/protected/views/home.jsf?acesso-negado");
                })
                .and()
                .formLogin().disable()
                .logout()
                .logoutUrl("/public/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/public/login.jsf");
    }

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    private void setOpcoesRules(HttpSecurity http) throws Exception {
        for (OpcaoAplicacao opcaoAplicacao : opcaoAplicacaoRepository.findAll()) {
            http.authorizeRequests()
                    .antMatchers(opcaoAplicacao.getOpcaoUrl())
                    .hasRole(opcaoAplicacao.getId().toString());
        }
    }

    private String retrieveCpfCnpjProcurado() {
        String cpfCnpjProcurador = null;
        RoleGroupKey activeGroup = AuthenticatedUserHandler.getActiveGroup().orElse(null);
        if (activeGroup != null && activeGroup.getType() == RoleGroupType.PROCURACAO) {
            ProcuracaoUsuario procuracaoUsuario = procuracaoUsuarioFacade.findOne(activeGroup.getId());
            cpfCnpjProcurador = procuracaoUsuario.getCpfProcurado();
        }
        return cpfCnpjProcurador;
    }

}
