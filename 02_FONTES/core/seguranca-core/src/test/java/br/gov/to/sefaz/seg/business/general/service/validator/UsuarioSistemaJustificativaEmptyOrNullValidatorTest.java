package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioSistemaJustificativaEmptyOrNullValidator;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
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
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link UsuarioSistemaJustificativaEmptyOrNullValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/07/2016 11:43:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class UsuarioSistemaJustificativaEmptyOrNullValidatorTest {

    @Mock
    private UsuarioSistema usuarioSistema;

    @InjectMocks
    private UsuarioSistemaJustificativaEmptyOrNullValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenClassIsNotUsuarioSistema() {
        // then
        assertFalse(validator.support(Object.class, ValidationContext.SAVE));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // then
        assertFalse(validator.support(UsuarioSistema.class, StringUtils.EMPTY));
    }

    @Test
    public void shouldSupportWhenContextIsSaveUsuarioJustificativaContext() {
        // then
        assertTrue(validator.support(UsuarioSistema.class, ValidationContext.SAVE));
    }

    @Test
    public void shouldValidateWhenJustificativaIsEmptyOrNull() {
        // when
        when(usuarioSistema.getJustificacaoCriacao()).thenReturn(null);

        // then
        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);
        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenJustificativaIsNotEmptyOrNull() {
        // when
        when(usuarioSistema.getJustificacaoCriacao()).thenReturn("Justificativa");

        // then
        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);
        assertTrue(violationSet.isEmpty());
    }
}