package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.BancoAgenciasRepository;
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
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.Set;

import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Teste para a classe {@link BancoAgenciasDuplicatedCnpjValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/06/2016 14:26:00
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class BancoAgenciasDuplicatedCnpjValidatorTest {

    private static final Integer ID_BANCO = 1;
    private static final Integer ID_AGENCIA = 1;
    private static final String DIGITO_AGENCIA = "71";
    private static final String NOME_AGENCIA = "nome agencia";
    private static final SituacaoEnum SITUACAO = SituacaoEnum.ATIVO;
    private static final Boolean CENTRALIZADORA = false;
    private static final Long NUMERO_CONTA_CORRENTE = 65L;
    private static final String DIGITO_CONTA_CORRENTE = "72";
    private static final Integer ID_MUNICIPIO = 21;
    private static final String EMAIL = "agencia@banco.com";

    @Mock
    private BancoAgenciasRepository repository;
    @InjectMocks
    private BancoAgenciasDuplicatedCnpjValidator validator;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(SourceBundle.class);
    }

    @Test
    public void shouldFailSupportWhenObjectNotBancoAgencia() {
        // given
        Object agencia = new Object();

        Assert.assertFalse(validator.support(agencia.getClass(), ValidationContext.SAVE));

    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        BancoAgencias agencia = createAgencia(1L);

        Assert.assertFalse(validator.support(agencia.getClass(), ""));
    }

    @Test
    public void shouldFailWhenAgenciaCnpjAlreadyExists() {

        BancoAgencias agencia = createAgencia(12345678901234L);

        when(repository.findExitsCnpj(ID_AGENCIA,agencia.getCnpjAgencia())).thenReturn(true);
        Set<CustomViolation> violationSet = validator.validate(agencia);

        Assert.assertFalse(violationSet.isEmpty());
    }

    @Test
    public void shouldFailWhenTwoAgenciasSameCnpj() {
        BancoAgencias agencia = createAgencia(12345678901234L);
        BancoAgencias agencia2 = createAgencia(12345678901234L);

        ArrayList<BancoAgencias> agenciasArrayList = new ArrayList<>();
        agenciasArrayList.add(agencia);
        agenciasArrayList.add(agencia2);

        Set<CustomViolation> violationSet = validator.validateAll(agenciasArrayList);

        Assert.assertFalse(violationSet.isEmpty());

    }

    private BancoAgencias createAgencia(Long cnpjAgencia) {
        Bancos bancos = new Bancos();

        return new BancoAgencias(ID_BANCO, ID_AGENCIA, DIGITO_AGENCIA, NOME_AGENCIA,
                cnpjAgencia, SITUACAO, CENTRALIZADORA, NUMERO_CONTA_CORRENTE,
                DIGITO_CONTA_CORRENTE, ID_MUNICIPIO, EMAIL, bancos);
    }
}