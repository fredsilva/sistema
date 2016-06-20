package br.gov.to.sefaz.seg.configuration;

import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.filter.LogNavegacaoFilterUtil;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;
import br.gov.to.sefaz.seg.persistence.repository.OpcaoAplicacaoRepository;
import br.gov.to.sefaz.seg.provider.LdapAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    private LdapAuthenticationProvider authenticationProvider;
    @Autowired
    private OpcaoAplicacaoRepository opcaoAplicacaoRepository;
    @Autowired
    private LogNavegacaoFilterUtil navegacaoFilterUtil;

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
                        navegacaoFilterUtil.saveLogNavegacao(request, cpfUsuario, localDateTime,
                                TipoOperacaoEnum.TENTATIVA_NEGADA, elapsedTime);
                    }
                    response.sendRedirect(request.getContextPath() + "/protected/views/home.jsf");
                })
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/protected/views/home.jsf")
                .failureUrl("/public/login.jsf?erro")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/public/login.jsf?logout");
    }

    @Override
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    private void setOpcoesRules(HttpSecurity http) throws Exception {
        for (OpcaoAplicacao opcaoAplicacao : opcaoAplicacaoRepository.findAll()) {
            http.authorizeRequests()
                    .antMatchers("/protected/views/" + opcaoAplicacao.getOpcaoUrl())
                    .hasRole(opcaoAplicacao.getId().toString());
        }
    }
}
