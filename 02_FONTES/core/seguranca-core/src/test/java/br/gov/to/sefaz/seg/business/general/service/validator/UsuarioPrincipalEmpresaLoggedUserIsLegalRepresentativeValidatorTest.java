package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioPrincipalEmpresaLoggedUserIsLegalRepresentativeValidator;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPrincipalEmpresaRepository;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe
 * {@link br.gov.to.sefaz.seg.business.gestao.
 * service.validator.UsuarioPrincipalEmpresaLoggedUserIsLegalRepresentativeValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/08/2016 11:14:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SourceBundle.class,AuthenticatedUserHandler.class})
public class UsuarioPrincipalEmpresaLoggedUserIsLegalRepresentativeValidatorTest {

    @Mock
    private UsuarioPrincipalEmpresaRepository usuarioPrincipalEmpresaRepository;

    @InjectMocks
    private UsuarioPrincipalEmpresaLoggedUserIsLegalRepresentativeValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
        mockStatic(AuthenticatedUserHandler.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotUsuarioPrincipalEmpresa() {
        // given
        Object notUsuarioPrincipalEmpresa = new Object();

        assertFalse(validator.support(notUsuarioPrincipalEmpresa.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        UsuarioPrincipalEmpresa usuarioPrincipalEmpresa = getUsuarioPrincipalEmpresaMock();

        assertFalse(validator.support(usuarioPrincipalEmpresa.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldFailWhenLoggedUsuarioNotCompanyRepresentative() {

        UsuarioPrincipalEmpresa usuarioPrincipalEmpresa = getUsuarioPrincipalEmpresaMock();

        when(AuthenticatedUserHandler.isAuthenticated()).thenReturn(true);
        when(AuthenticatedUserHandler.getCpf()).thenReturn("11111111111");
        when(usuarioPrincipalEmpresa.getCnpjEmpresa()).thenReturn("0000000000000");
        when(usuarioPrincipalEmpresaRepository.isLegalRepresentative(usuarioPrincipalEmpresa.getCnpjEmpresa()
                .substring(0,8), "22222222222")).thenReturn(false);

        Set<CustomViolation> violationSet = validator.validate(usuarioPrincipalEmpresa);

        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenLoggedUsuarioIsCompanyRepresentative() {

        UsuarioPrincipalEmpresa usuarioPrincipalEmpresa = getUsuarioPrincipalEmpresaMock();

        when(AuthenticatedUserHandler.isAuthenticated()).thenReturn(true);
        when(AuthenticatedUserHandler.getCpf()).thenReturn("11111111111");
        when(usuarioPrincipalEmpresa.getCnpjEmpresa()).thenReturn("0000000000000");
        when(usuarioPrincipalEmpresaRepository.isLegalRepresentative(usuarioPrincipalEmpresa.getCnpjEmpresa()
                .substring(0,8), "11111111111")).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(usuarioPrincipalEmpresa);

        assertTrue(violationSet.isEmpty());
    }


    private UsuarioPrincipalEmpresa getUsuarioPrincipalEmpresaMock() {
        return mock(UsuarioPrincipalEmpresa.class);
    }
}