package br.gov.to.sefaz.seg.provider;

import br.gov.to.sefaz.seg.business.authentication.domain.LoginDto;
import br.gov.to.sefaz.seg.business.authentication.facade.LoginSistemaFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Classe que provê autenticação do sistema por AD (Active Directory) utilizando protocolo LDAP.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 20/05/2016 16:05:37
 */
@Component
public class LdapAuthenticationProvider implements AuthenticationProvider {

    private final LoginSistemaFacade loginSistemaFacade;

    @Autowired
    public LdapAuthenticationProvider(LoginSistemaFacade loginSistemaFacade) {
        this.loginSistemaFacade = loginSistemaFacade;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        LoginDto dto = new LoginDto();
        String cpf = null;
        if (!Objects.isNull(authentication.getName())) {
            cpf = authentication.getName().replace(".", "").replace("-", "");
        }
        String password = null;
        if (!Objects.isNull(authentication.getCredentials())) {
            password = authentication.getCredentials().toString();
        }
        dto.setCpf(cpf);
        dto.setPasswd(password);
        return loginSistemaFacade.ldapAuthenticate(dto);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)
                && authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}