package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.impl.UsuarioSistemaServiceImpl;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioSistemaCannotHaveEmptyUsuarioPerfilListValidator;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link br.gov.to.sefaz.seg.business.gestao.service.
 * validator.AtribuirPerfilFilterCantHaveNullAttributesValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/08/2016 11:14:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class UsuarioSistemaCannotHaveEmptyUsuarioPerfilListValidatorTest {

    @InjectMocks
    private UsuarioSistemaCannotHaveEmptyUsuarioPerfilListValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotUsuarioSistema() {
        // given
        Object usuarioSistema = new Object();

        assertFalse(validator.support(usuarioSistema.getClass(),
                UsuarioSistemaServiceImpl.ATRIBUIR_PERFIL_USUARIO_CONTEXT));

    }

    @Test
    public void shouldFailSupportWhenContextDoesNotExists() {
        // given
        UsuarioSistema usuarioSistema = getFilterMock();

        assertFalse(validator.support(usuarioSistema.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldFailWhenUsuarioSistemaUsuarioPerfilListIsEmpty() {

        UsuarioSistema usuarioSistema = getFilterMock();
        when(usuarioSistema.getUsuarioPerfil()).thenReturn(new HashSet<>());

        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);

        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldSucceedWhenUsuarioSistemaUsuarioPerfilListIsNotEmpty() {

        UsuarioSistema usuarioSistema = getFilterMock();
        Set<UsuarioPerfil> usuarioPerfil = new HashSet<>();
        usuarioPerfil.add(mock(UsuarioPerfil.class));
        when(usuarioSistema.getUsuarioPerfil()).thenReturn(usuarioPerfil);

        Set<CustomViolation> violationSet = validator.validate(usuarioSistema);

        assertTrue(violationSet.isEmpty());
    }


    private UsuarioSistema getFilterMock() {
        return mock(UsuarioSistema.class);
    }
}