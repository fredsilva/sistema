package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.filter.AtribuirPerfilFilter;
import br.gov.to.sefaz.seg.business.gestao.service.validator.AtribuirPerfilFilterCantHaveNullAttributesValidator;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste para a classe {@link AtribuirPerfilFilterCantHaveNullAttributesValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/08/2016 11:14:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class AtribuirPerfilFilterCantHaveNullAttributesValidatorTest {

    @InjectMocks
    private AtribuirPerfilFilterCantHaveNullAttributesValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotAtribuirPerfilFilter() {
        // given
        Object atribuirPerfilFilter = new Object();

        assertFalse(validator.support(atribuirPerfilFilter.getClass(), "ATRIBUIR_PERFIL_FILTER_CONTEXT"));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        AtribuirPerfilFilter atribuirPerfilFilter = getFilterMock();

        assertFalse(validator.support(atribuirPerfilFilter.getClass(), ""));
    }

    @Test
    public void shouldFailWhenFilterAttributesNullOrEmpty() {

        AtribuirPerfilFilter atribuirPerfilFilter = new AtribuirPerfilFilter();

        Set<CustomViolation> violationSet = validator.validate(atribuirPerfilFilter);

        assertFalse(violationSet.isEmpty());
    }


    private AtribuirPerfilFilter getFilterMock() {
        return mock(AtribuirPerfilFilter.class);
    }
}