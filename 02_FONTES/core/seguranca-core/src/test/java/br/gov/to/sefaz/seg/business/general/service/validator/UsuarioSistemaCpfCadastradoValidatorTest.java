package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioSistemaCpfCadastradoValidator;
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
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link UsuarioSistemaCpfCadastradoValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 15/06/2016 15:31:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class UsuarioSistemaCpfCadastradoValidatorTest {

    @Mock
    private UsuarioSistema usuarioSistema;

    @InjectMocks
    private UsuarioSistemaCpfCadastradoValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenClassIsNotUsuarioSistema() {
        // then
        assertFalse(validator.support(Object.class, "LOGIN"));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // then
        assertFalse(validator.support(UsuarioSistema.class, StringUtils.EMPTY));
    }

    @Test
    public void shouldSupportWhenContextIsSave() {
        // then
        assertTrue(validator.support(UsuarioSistema.class, "LOGIN"));
    }

    @Test
    public void shouldPassWhenContainsUsuarioSistemaWithCpf() {
        // when
        when(usuarioSistema.getCpfUsuario()).thenReturn("11111111111");

        // then
        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);
        assertTrue(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenContainsUsuarioSistemaWithoutCpf() {
        // when
        when(usuarioSistema.getCpfUsuario()).thenReturn(null);

        // then
        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);
        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenUsuarioSistemaIsNull() {
        // then
        Set<CustomViolation> violationSet = validator.validate(null);
        assertFalse(violationSet.isEmpty());
    }

}