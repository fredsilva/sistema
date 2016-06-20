package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.TipoUsuarioUsuarioSistemaAssignedValidator;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.repository.TipoUsuarioRepository;
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
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a Classe {@link TipoUsuarioUsuarioSistemaAssignedValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 16/06/2016 17:42:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class TipoUsuarioUsuarioSistemaAssignedValidatorTest {
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private TipoUsuarioUsuarioSistemaAssignedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotTipoUsuario() {
        // given
        Object tipoUsuario = new Object();

        assertFalse(validator.support(tipoUsuario.getClass(), ValidationContext.DELETE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        TipoUsuario postoTrabalho = createTipoUsuarioMocked();

        assertFalse(validator.support(postoTrabalho.getClass(), ""));
    }

    @Test
    public void shouldFailWhenUnidadePaiUsed() {
        // given
        TipoUsuario tipoUsuario = createTipoUsuarioMocked();

        when(tipoUsuario.getCodigoTipoUsuario()).thenReturn(2);
        when(tipoUsuarioRepository.existsLockReferenceUsuario(tipoUsuario.getCodigoTipoUsuario()))
                .thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(tipoUsuario.getCodigoTipoUsuario());

        assertFalse(violationSet.isEmpty());
    }

    private TipoUsuario createTipoUsuarioMocked() {
        return mock(TipoUsuario.class);
    }
}