package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.persistence.repository.ConveniosArrecRepository;
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
 * Teste para a classe {@link ConveniosArrecTipoConvenioDuplicatedValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 06/06/2016 09:28:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ConveniosArrecTipoConvenioDuplicatedValidatorTest {

    @Mock
    ConveniosArrecRepository repository;

    @InjectMocks
    ConveniosArrecTipoConvenioDuplicatedValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotConveniosArrec() {
        // given
        Object convenioArrec = new Object();

        assertFalse(validator.support(convenioArrec.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        ConveniosArrec conveniosArrec = createConvenioArrecMocked();

        assertFalse(validator.support(conveniosArrec.getClass(), ""));
    }

    @Test
    public void shouldFailWhenDuplicatedTipoConvenio() {
        // Given
        ConveniosArrec conveniosTarifas = createConvenioArrecMocked();

        when(conveniosTarifas.getTipoConvenio()).thenReturn(TipoConvenioEnum.ICMS);
        when(repository.findIdConvenioArrecByTipoConvenioAndAgencia(conveniosTarifas.getTipoConvenio(),
                conveniosTarifas.getIdBanco(), conveniosTarifas.getIdAgencia()))
                        .thenReturn(createListIdConvenioMocked());

        Set<CustomViolation> violationSet = validator.validate(conveniosTarifas);

        assertFalse(violationSet.isEmpty());
    }

    private List<Long> createListIdConvenioMocked() {

        List<Long> listaRetorno = new ArrayList<>();
        listaRetorno.add(1L);
        listaRetorno.add(2L);

        return listaRetorno;
    }

    private ConveniosArrec createConvenioArrecMocked() {
        return mock(ConveniosArrec.class);
    }

}