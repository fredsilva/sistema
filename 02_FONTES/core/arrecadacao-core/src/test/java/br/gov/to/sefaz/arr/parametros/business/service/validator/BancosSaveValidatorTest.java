package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.repository.BancosRepository;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

/**
 * Teste para a classe {@link BancosSaveValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/06/2016 15:03:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class BancosSaveValidatorTest {

    private static final Integer ID_BANCO = 1;
    private static final String NOME_BANCO = "Teste";
    private static final SituacaoEnum SITUACAO = SituacaoEnum.ATIVO;
    private static final Integer CNPJ_RAIZ = 0;

    @Mock
    private BancosRepository repository;
    @InjectMocks
    private BancosSaveValidator validator;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotBanco() {
        // given
        Object banco = new Object();

        Assert.assertFalse(validator.support(banco.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        Bancos banco = createBanco();

        Assert.assertFalse(validator.support(banco.getClass(), ""));
    }

    @Test
    public void shouldFailDuplicatedBanco() {
        Bancos banco = createBanco();

        Mockito.when(repository.exists(ID_BANCO)).thenReturn(true);

        Set<CustomViolation> violationSet = validator.validate(banco);

        Assert.assertFalse(violationSet.isEmpty());

    }

    private Bancos createBanco() {

        return new Bancos(ID_BANCO, NOME_BANCO, SITUACAO, CNPJ_RAIZ);

    }

}