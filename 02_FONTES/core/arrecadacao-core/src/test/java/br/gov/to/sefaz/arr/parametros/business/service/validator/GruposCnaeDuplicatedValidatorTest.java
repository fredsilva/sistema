package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnae;
import br.gov.to.sefaz.arr.parametros.persistence.entity.GruposCnaePK;
import br.gov.to.sefaz.arr.parametros.persistence.repository.GruposCnaeRepository;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link GruposCnaeDuplicatedValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 09:57:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class GruposCnaeDuplicatedValidatorTest {

    @Mock
    GruposCnaeRepository repository;

    @InjectMocks
    GruposCnaeDuplicatedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotGruposCnae() {
        // given
        Object gruposCnae = new Object();

        assertFalse(validator.support(gruposCnae.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        GruposCnae gruposCnae = createGruposCnaeMock();

        assertFalse(validator.support(gruposCnae.getClass(), ""));
    }

    @SuppressWarnings("Duplicates")
    @Test
    public void shouldFailWhenDuplicatedCnae() {
        GruposCnae gruposCnae = createGruposCnaeMock();

        when(gruposCnae.getId()).thenReturn(mock(GruposCnaePK.class));
        when(repository.exists(gruposCnae.getId())).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(gruposCnae);

        assertFalse(violationSet.isEmpty());

    }

    @Test
    public void shouldFailWhenDuplicatedCnaeFiscal() {
        GruposCnae gruposCnae = createGruposCnaeMock();

        when(gruposCnae.getId()).thenReturn(mock(GruposCnaePK.class));
        when(gruposCnae.getCnaeFiscal()).thenReturn("CnaefiscalTeste");
        when(repository.exists(gruposCnae.getId())).thenReturn(false);
        when(repository.existsCnaeFiscal(gruposCnae.getCnaeFiscal())).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(gruposCnae);

        assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenDuplicatedCnaeFiscalValidateAll() {
        ArrayList<GruposCnae> listGruposCnae = new ArrayList<>();

        GruposCnae gruposCnae = createGruposCnaeMock();
        GruposCnae gruposCnae2 = createGruposCnaeMock();

        when(gruposCnae.getId()).thenReturn(mock(GruposCnaePK.class));
        when(gruposCnae.getCnaeFiscal()).thenReturn("CnaefiscalTeste");
        when(gruposCnae2.getId()).thenReturn(mock(GruposCnaePK.class));
        when(gruposCnae2.getCnaeFiscal()).thenReturn("CnaefiscalTeste");

        listGruposCnae.add(gruposCnae);
        listGruposCnae.add(gruposCnae2);

        Set<CustomViolation> violationSet = validator.validateAll(listGruposCnae);

        assertFalse(violationSet.isEmpty());
    }

    private GruposCnae createGruposCnaeMock() {
        return mock(GruposCnae.class);
    }

}