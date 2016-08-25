package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioSistemaContadorCrcEmptyOrNullValidator;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link br.gov.to.sefaz.seg.business.gestao.service.validator.AtribuirPerfilFilterCantHaveNullAttributesValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/08/2016 11:14:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class UsuarioSistemaContadorCrcEmptyOrNullValidatorTest {

    @InjectMocks
    private UsuarioSistemaContadorCrcEmptyOrNullValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotUsuarioSistema() {
        // given
        Object usuarioSistema = new Object();

        assertFalse(validator.support(usuarioSistema.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextDoesNotExists() {
        // given
        UsuarioSistema usuarioSistema = getFilterMock();

        assertFalse(validator.support(usuarioSistema.getClass(), ""));
    }

    @Test
    public void shouldFailWhenUsuarioSistemaCrcIsEmpty() {

        UsuarioSistema usuarioSistema = getFilterMock();
        when(usuarioSistema.getCodigoTipoUsuario()).thenReturn(3);
        when(usuarioSistema.getCrc()).thenReturn(StringUtils.EMPTY);

        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);

        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenUsuarioSistemaCrcIsNull() {

        UsuarioSistema usuarioSistema = getFilterMock();
        when(usuarioSistema.getCodigoTipoUsuario()).thenReturn(3);
        when(usuarioSistema.getCrc()).thenReturn(null);

        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);

        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldSucceedWhenUsuarioSistemaCrcIsFilled() {

        UsuarioSistema usuarioSistema = getFilterMock();
        when(usuarioSistema.getCodigoTipoUsuario()).thenReturn(3);
        when(usuarioSistema.getCrc()).thenReturn("TesteCRC");

        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);

        assertTrue(violationSet.isEmpty());
    }


    private UsuarioSistema getFilterMock() {
        return mock(UsuarioSistema.class);
    }
}