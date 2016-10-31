package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.PapelSistemaCannotHavePerfilGrantedValidator;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.repository.PapelSistemaRepository;
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
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe {@link PapelSistemaCannotHavePerfilGrantedValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 29/07/2016 10:30:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PapelSistemaCannotHavePerfilGrantedValidatorTest {

    @Mock
    private PapelSistema papelSistema;

    @Mock
    private PapelSistemaRepository papelSistemaRepository;

    @InjectMocks
    private PapelSistemaCannotHavePerfilGrantedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenClassIsNotPapelSistema() {
        // then
        assertFalse(validator.support(Object.class, ValidationContext.DELETE));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // then
        assertFalse(validator.support(PapelSistema.class, StringUtils.EMPTY));
    }

    @Test
    public void shouldFailWhenPapelHavePerfilGranted() {
        // when
        when(papelSistema.getIdentificacaoPapel()).thenReturn(1L);
        when(papelSistemaRepository.existsPerfilByPapel(1L)).thenReturn(true);

        // then
        Set<CustomViolation> violationSet = validator.validate(papelSistema.getIdentificacaoPapel());
        assertFalse(violationSet.isEmpty());
    }

}