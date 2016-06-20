package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ConveniosArrecRepository;
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
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link ConveniosArrecTipoBarraWithReceitasValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 09:13:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ConveniosArrecTipoBarraWithReceitasValidatorTest {

    @Mock
    ConveniosArrecRepository repository;

    @InjectMocks
    ConveniosArrecTipoBarraWithReceitasValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotConveniosArrec() {
        // given
        Object conveniosArrec = new Object();

        assertFalse(validator.support(conveniosArrec.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        ConveniosArrec conveniosArrec = createConvenioArrecMocked();

        assertFalse(validator.support(conveniosArrec.getClass(), ""));
    }

    @Test
    public void shouldFailWhenPagamentoWithoutCodBarras() {

        ConveniosArrec conveniosArrec = createConvenioArrecMocked();
        when(conveniosArrec.getTipoBarra()).thenReturn(TipoCodigoBarraEnum.GNRE);

        List<Receitas> listReceitasVazia = createListConvenioReceitasVazia();

        when(conveniosArrec.getReceitas()).thenReturn(listReceitasVazia);

        Set<CustomViolation> violationSet = validator.validate(conveniosArrec);

        assertFalse(violationSet.isEmpty());

    }

    private List<Receitas> createListConvenioReceitasVazia() {
        ArrayList<Receitas> retorno = new ArrayList<>();
        //Retorna vazio mesmo
        return retorno;
    }

    private ConveniosArrec createConvenioArrecMocked() {
        return mock(ConveniosArrec.class);
    }

}
