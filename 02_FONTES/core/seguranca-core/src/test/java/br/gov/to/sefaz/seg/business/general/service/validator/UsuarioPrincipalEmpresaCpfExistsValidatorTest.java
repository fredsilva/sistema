package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioPrincipalEmpresaCpfExistsValidator;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioSistemaRepository;
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
 * {@link br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioPrincipalEmpresaCnpjExistsValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/08/2016 11:14:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SourceBundle.class,AuthenticatedUserHandler.class})
public class UsuarioPrincipalEmpresaCpfExistsValidatorTest {

    private static final String CPF = "11111111111";

    @Mock
    private UsuarioSistemaRepository usuarioSistemaRepository;

    @InjectMocks
    private UsuarioPrincipalEmpresaCpfExistsValidator validator;

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
    public void shouldFailWhenUsuarioPrincipalEmpresaCpfExists() {

        UsuarioPrincipalEmpresa usuarioPrincipalEmpresa = getUsuarioPrincipalEmpresaMock();

        when(usuarioPrincipalEmpresa.getCpfUsuario()).thenReturn(CPF);
        when(usuarioSistemaRepository.findOne(CPF)).thenReturn(new UsuarioSistema());

        Set<CustomViolation> violationSet = validator.validate(usuarioPrincipalEmpresa);

        assertTrue(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenUsuarioPrincipalEmpresaCpfDoesNotExists() {

        UsuarioPrincipalEmpresa usuarioPrincipalEmpresa = getUsuarioPrincipalEmpresaMock();

        when(usuarioPrincipalEmpresa.getCpfUsuario()).thenReturn(CPF);
        when(usuarioSistemaRepository.findOne(CPF)).thenReturn(null);

        Set<CustomViolation> violationSet = validator.validate(usuarioPrincipalEmpresa);

        assertFalse(violationSet.isEmpty());
    }

    private UsuarioPrincipalEmpresa getUsuarioPrincipalEmpresaMock() {
        return mock(UsuarioPrincipalEmpresa.class);
    }
}