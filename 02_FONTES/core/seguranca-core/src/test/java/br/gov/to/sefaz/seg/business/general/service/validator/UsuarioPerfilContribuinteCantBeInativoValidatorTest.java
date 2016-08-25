package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.UsuarioPerfilContribuinteCantBeInativoValidator;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
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
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link UsuarioPerfilContribuinteCantBeInativoValidatorTest}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:13:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class UsuarioPerfilContribuinteCantBeInativoValidatorTest {

    @InjectMocks
    private UsuarioPerfilContribuinteCantBeInativoValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotUnidadeOrganizacional() {
        // given
        Object usuarioPerfil = new Object();

        assertFalse(validator.support(usuarioPerfil.getClass(), ValidationContext.UPDATE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        UsuarioPerfil usuarioPerfil = createUsuarioPerfilMocked();

        assertFalse(validator.support(usuarioPerfil.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldFailWhenChangingPerfilContribuinteToInativo() {
        // given
        UsuarioPerfil usuarioPerfil = createUsuarioPerfilMocked();

        when(usuarioPerfil.getIdentificacaoPerfil()).thenReturn(1L);
        when(usuarioPerfil.getSituacaoPerfil()).thenReturn(SituacaoUsuarioEnum.INATIVO);

        Set<CustomViolation> violationSet = validator.validate(usuarioPerfil);

        assertFalse(violationSet.isEmpty());
    }

    private UsuarioPerfil createUsuarioPerfilMocked() {
        return mock(UsuarioPerfil.class);
    }

}