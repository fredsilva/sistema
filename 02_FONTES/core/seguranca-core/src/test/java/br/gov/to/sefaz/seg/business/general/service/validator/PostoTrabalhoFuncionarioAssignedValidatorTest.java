package br.gov.to.sefaz.seg.business.general.service.validator;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.gestao.service.validator.PostoTrabalhoFuncionarioAssignedValidator;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPostoTrabalhoRepository;
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
 * Teste para a classe {@link PostoTrabalhoFuncionarioAssignedValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 14/06/2016 16:46:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class PostoTrabalhoFuncionarioAssignedValidatorTest {

    @Mock
    private UsuarioPostoTrabalhoRepository usuarioPostoTrabalhoRepository;

    @InjectMocks
    private PostoTrabalhoFuncionarioAssignedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotPostoTrabalho() {
        // given
        Object postoTrabalho = new Object();

        assertFalse(validator.support(postoTrabalho.getClass(), ValidationContext.DELETE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        PostoTrabalho postoTrabalho = createPostoTrabalhoMocked();

        assertFalse(validator.support(postoTrabalho.getClass(), StringUtils.EMPTY));
    }

    @Test
    public void shouldFailWhenPostoAssignedToUser() {
        // given
        PostoTrabalho postoTrabalho = createPostoTrabalhoMocked();

        when(postoTrabalho.getIdentificacaoPostoTrabalho()).thenReturn(2);
        when(usuarioPostoTrabalhoRepository.existsLockReferenceFuncionario(postoTrabalho.getIdentificacaoPostoTrabalho()))
                .thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(postoTrabalho.getIdentificacaoPostoTrabalho());

        assertFalse(violationSet.isEmpty());
    }

    private PostoTrabalho createPostoTrabalhoMocked() {
        return mock(PostoTrabalho.class);
    }
}