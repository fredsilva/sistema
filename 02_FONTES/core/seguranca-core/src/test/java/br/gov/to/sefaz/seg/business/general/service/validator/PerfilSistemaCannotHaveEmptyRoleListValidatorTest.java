package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.PerfilSistemaCannotHaveEmptyRoleListValidator;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste da classe
 * {@link br.gov.to.sefaz.seg.business.gestao.service.validator.PerfilSistemaCannotHaveEmptyRoleListValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/08/2016 11:09:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PerfilSistemaCannotHaveEmptyRoleListValidatorTest {

    @Mock
    private PerfilSistema perfilSistema;

    @InjectMocks
    private PerfilSistemaCannotHaveEmptyRoleListValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenClassIsNotPapelSistema() {
        // then
        assertFalse(validator.support(Object.class, ValidationContext.SAVE));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // then
        assertFalse(validator.support(PerfilSistema.class, StringUtils.EMPTY));
    }

    @Test
    public void shouldFailWhenPapelHaveEmptyOptionList() {
        // when
        when(perfilSistema.getPerfilPapel()).thenReturn(new HashSet<>());

        // then
        Set<CustomViolation> violationSet = validator.validate(perfilSistema);
        assertFalse(violationSet.isEmpty());
    }

}