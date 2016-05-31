package br.gov.to.sefaz.seg.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configurações de segurança do sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 19/05/2016 17:56:00
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/public/**", "/javax.faces.resource/**", "/resources/**").permitAll()
                .antMatchers("/protected/**").authenticated()
                .and()
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .formLogin()
                .loginPage("/public/login.jsf")
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
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("user").roles("ARR");
    }
}
