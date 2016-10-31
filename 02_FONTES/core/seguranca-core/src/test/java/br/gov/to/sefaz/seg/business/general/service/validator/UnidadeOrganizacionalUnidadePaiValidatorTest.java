package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UnidadeOrganizacionalUnidadePaiValidator;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.seg.persistence.repository.UnidadeOrganizacionalRepository;
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
 * Teste para a classe {@link UnidadeOrganizacionalUnidadePaiValidatorTest}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:13:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class UnidadeOrganizacionalUnidadePaiValidatorTest {

    @Mock
    private UnidadeOrganizacionalRepository unidadeOrganizacionalRepository;

    @InjectMocks
    private UnidadeOrganizacionalUnidadePaiValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotUnidadeOrganizacional() {
        // given
        Object unidadeOrganizacional = new Object();

        assertFalse(validator.support(unidadeOrganizacional.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        UnidadeOrganizacional unidadeOrganizacional = createUnidadeOrganizacionalMocked();

        assertFalse(validator.support(unidadeOrganizacional.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldFailWhenUnidadePaiUsed() {
        // given
        UnidadeOrganizacional unidadeOrganizacional = createUnidadeOrganizacionalMocked();

        when(unidadeOrganizacional.getUnidOrganizacPai()).thenReturn(2L);
        when(unidadeOrganizacional.getId()).thenReturn(3L);
        when(unidadeOrganizacionalRepository.existsLockReferencePai(unidadeOrganizacional.getUnidOrganizacPai()))
                .thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(unidadeOrganizacional.getUnidOrganizacPai());

        assertFalse(violationSet.isEmpty());
    }

    private UnidadeOrganizacional createUnidadeOrganizacionalMocked() {
        return mock(UnidadeOrganizacional.class);
    }

}