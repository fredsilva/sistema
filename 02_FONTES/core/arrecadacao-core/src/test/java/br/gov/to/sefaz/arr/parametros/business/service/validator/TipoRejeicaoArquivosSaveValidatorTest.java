package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.arr.persistence.repository.TipoRejeicaoArquivosRepository;
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

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste para a classe {@link TipoRejeicaoArquivosSaveValidator}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 16:53:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class TipoRejeicaoArquivosSaveValidatorTest {

    @Mock
    TipoRejeicaoArquivosRepository repository;

    @InjectMocks
    TipoRejeicaoArquivosSaveValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotTipoRejeicaoArquivos() {
        // given
        Object tipoRejeicaoArquivos = new Object();

        assertFalse(validator.support(tipoRejeicaoArquivos.getClass(), ValidationContext.SAVE));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        TipoRejeicaoArquivos tipoRejeicaoArquivos = createTipoTipoRejeicaoArquivos();

        assertFalse(validator.support(tipoRejeicaoArquivos.getClass(), ""));
    }

    // if (tipoRejeicaoArquivosRepository.exists(tipoRejeicaoArquivos.getIdCodigoRejeicao())) {
    @Test
    public void shouldFailWhenTipoRejeicaoArquivoExists() {
        TipoRejeicaoArquivos tipoRejeicaoArquivos = createTipoTipoRejeicaoArquivos();

        when(tipoRejeicaoArquivos.getIdCodigoRejeicao()).thenReturn(1);
        when(repository.exists(tipoRejeicaoArquivos.getIdCodigoRejeicao())).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(tipoRejeicaoArquivos);
        assertFalse(violationSet.isEmpty());

    }

    private TipoRejeicaoArquivos createTipoTipoRejeicaoArquivos() {
        return mock(TipoRejeicaoArquivos.class);
    }
}