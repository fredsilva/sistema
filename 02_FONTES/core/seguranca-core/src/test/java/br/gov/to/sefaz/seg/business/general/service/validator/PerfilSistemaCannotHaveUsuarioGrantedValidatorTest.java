package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.PerfilSistemaCannotHaveUsuarioGrantedValidator;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.repository.PerfilSistemaRepository;
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
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link PerfilSistemaCannotHaveUsuarioGrantedValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 02/08/2016 11:01:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PerfilSistemaCannotHaveUsuarioGrantedValidatorTest {

    @Mock
    private PerfilSistemaRepository repository;

    @InjectMocks
    private PerfilSistemaCannotHaveUsuarioGrantedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotPerfilSistema() {
        // given
        Object perfilSistema = new Object();

        assertFalse(validator.support(perfilSistema.getClass(), ValidationContext.DELETE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        PerfilSistema perfilMocked = createPerfilSistemaMocked();

        assertFalse(validator.support(perfilMocked.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldFailWhenPerfilSistemaHaveUsuarioGranted() {
        // given
        PerfilSistema perfilSistema = createPerfilSistemaMocked();

        when(perfilSistema.getIdentificacaoPerfil()).thenReturn(1L);
        when(repository.existsUsuarioByPerfil(perfilSistema.getIdentificacaoPerfil())).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(perfilSistema.getIdentificacaoPerfil());

        assertFalse(violationSet.isEmpty());
    }

    private PerfilSistema createPerfilSistemaMocked() {
        return mock(PerfilSistema.class);
    }

}