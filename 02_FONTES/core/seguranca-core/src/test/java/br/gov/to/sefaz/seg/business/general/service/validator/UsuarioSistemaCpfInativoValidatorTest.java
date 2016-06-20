package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioSistemaCpfInativoValidator;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
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
 * Teste da classe {@link UsuarioSistemaCpfInativoValidator}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 15/06/2016 15:31:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class UsuarioSistemaCpfInativoValidatorTest {

    @Mock
    private UsuarioSistema usuarioSistema;

    @InjectMocks
    private UsuarioSistemaCpfInativoValidator validator;

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
        assertFalse(validator.support(UsuarioSistema.class, ""));
    }

    @Test
    public void shouldSupportWhenContextIsSave() {
        // then
        assertTrue(validator.support(UsuarioSistema.class, "LOGIN"));
    }

    @Test
    public void shouldPassWhenUsuarioIsAtivo() {
        // when
        when(usuarioSistema.getSituacaoUsuario()).thenReturn(SituacaoUsuarioEnum.ATIVO);

        // then
        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);
        assertTrue(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenUsuarioIsInativo() {
        // when
        when(usuarioSistema.getSituacaoUsuario()).thenReturn(SituacaoUsuarioEnum.INATIVO);

        // then
        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);
        assertFalse(violationSet.isEmpty());
    }
}