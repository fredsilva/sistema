package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosReceitas;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.repository.ReceitasRepository;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Assert;
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

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * Teste para a classe {@link ConveniosArrecDuplicatedReceitaValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/06/2016 15:30:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class ConveniosArrecDuplicatedReceitaValidatorTest {

    @Mock
    ReceitasRepository repository;

    @InjectMocks
    ConveniosArrecDuplicatedReceitaValidator validator;

    @Before
    public void setUp() {
        mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotReceitas() {
        // given
        Object receitas = new Object();

        Assert.assertFalse(validator.support(receitas.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        Receitas receita = createReceitaMockado();

        Assert.assertFalse(validator.support(receita.getClass(), ""));
    }

    @Test
    public void validateAllDuplicatedReceita() {

        // given
        ConveniosReceitas receita = createConveniosReceitasMockado();

        given(receita.getIdReceita()).willReturn(2);
        List<Receitas> receitasList = listaReceitasMockado();
        when(repository.findAllReceitasByIdConvenio(receita.getIdConvenio())).thenReturn(
                receitasList);

        Set<CustomViolation> violationSet = validator.validate(receita);

        Assert.assertFalse(violationSet.isEmpty());

    }

    @Test
    public void validateOneDuplicatedReceita() {

        // given
        List<Receitas> receitasList = listaReceitasMockado();

        Set<CustomViolation> violationSet = validator.validateDuplicatedReceita(receitasList, 1);

        Assert.assertFalse(violationSet.isEmpty());

    }

    private List<Receitas> listaReceitasMockado() {
        List<Receitas> listaRetorno = new ArrayList<>();

        Receitas receita1 = mock(Receitas.class);
        Receitas receita2 = mock(Receitas.class);
        when(receita1.getIdReceita()).thenReturn(1);
        when(receita2.getIdReceita()).thenReturn(2);

        listaRetorno.add(receita1);
        listaRetorno.add(receita2);

        return listaRetorno;
    }

    private Receitas createReceitaMockado() {
        return mock(Receitas.class);
    }

    private ConveniosReceitas createConveniosReceitasMockado() {
        return mock(ConveniosReceitas.class);
    }

}