package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.authentication.domain.ChangePasswordDto;
import br.gov.to.sefaz.seg.business.gestao.service.validator.ChangePasswordNovaSenhaValidator;
import br.gov.to.sefaz.util.message.SourceBundle;
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
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioSistemaCpfBloqueadoValidator}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 15/06/2016 15:16:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ChangePasswordNovaSenhaValidatorTest {

    @Mock
    private ChangePasswordDto dto;

    @InjectMocks
    private ChangePasswordNovaSenhaValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenClassIsNotChangePasswordDto() {
        // then
        assertFalse(validator.support(Object.class, "CHANGE_PASSWD"));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // then
        assertFalse(validator.support(ChangePasswordDto.class, ""));
    }

    @Test
    public void shouldSupportWhenContextIsCorrect() {
        // then
        assertTrue(validator.support(ChangePasswordDto.class, "CHANGE_PASSWD"));
    }

    @Test
    public void shouldPassWhenNewPasswordAndConfirmIsCorrect() {
        // when
        when(dto.getNovaSenha()).thenReturn("P12345678p");
        when(dto.getConfirmarNovaSenha()).thenReturn("P12345678p");

        // then
        Set<CustomViolation> violationSet = validator.validate(dto);
        assertTrue(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenNovaSenhaIsNotValid() {
        // when
        when(dto.getNovaSenha()).thenReturn("P1");
        when(dto.getNovaSenha()).thenReturn("P1");


        // then
        Set<CustomViolation> violationSet = validator.validate(dto);
        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenNovaSenhaAndConfirmarNovaSenhaIsNotEqual() {
        // when
        when(dto.getNovaSenha()).thenReturn("P12345678p");
        when(dto.getNovaSenha()).thenReturn("P12345678p2");

        // then
        Set<CustomViolation> violationSet = validator.validate(dto);
        assertFalse(violationSet.isEmpty());
    }
}