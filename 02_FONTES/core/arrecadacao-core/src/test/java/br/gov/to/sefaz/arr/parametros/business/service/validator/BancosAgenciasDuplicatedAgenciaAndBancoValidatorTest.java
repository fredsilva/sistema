package br.gov.to.sefaz.arr.parametros.business.service.validator;


import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.repository.BancoAgenciasRepository;
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

import static org.mockito.Mockito.when;


/**
 * Classe de testes da classe {@link BancoAgenciasDuplicatedAgenciaAndBancoValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/06/2016 10:51:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class BancosAgenciasDuplicatedAgenciaAndBancoValidatorTest {

    private static final Long CNPJ_AGENCIA = 1000000272L;
    private static final String DIGITO_AGENCIA = "71";
    private static final String NOME_AGENCIA = "nome agencia";
    private static final SituacaoEnum SITUACAO = SituacaoEnum.ATIVO;
    private static final Boolean CENTRALIZADORA = false;
    private static final Long NUMERO_CONTA_CORRENTE = 65L;
    private static final String DIGITO_CONTA_CORRENTE = "72";
    private static final Integer ID_MUNICIPIO = 21;
    private static final String EMAIL = "agencia@banco.com";

    @Mock
    BancoAgenciasRepository repository;
    @InjectMocks
    private BancoAgenciasDuplicatedAgenciaAndBancoValidator validator;

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
        BancoAgencias agencia = createAgencia(1, 2);

        Assert.assertFalse(validator.support(agencia.getClass(), ""));
    }

    @Test
    public void shouldFailWhenAgenciaHasNoId() {
        BancoAgencias agencia = createAgencia(null, 1);

        when(repository.findExitsIdAgenciaAndIdBanco(agencia.getIdAgencia(), agencia.getIdBanco())).thenReturn(true);

        Set<CustomViolation> violations = validator.validate(agencia);
        Assert.assertFalse(violations.isEmpty());

    }

    @Test
    public void shouldFailWhenBancoHasNoId() {

        BancoAgencias agencia = createAgencia(1, null);

        when(repository.findExitsIdAgenciaAndIdBanco(agencia.getIdAgencia(), agencia.getIdBanco())).thenReturn(true);
        Set<CustomViolation> violations = validator.validate(agencia);

        Assert.assertFalse(violations.isEmpty());

    }

    @Test
    public void shouldFailWhenCollectionHasTwoEqualsIdAgenciaCallingValidateAll() {

        BancoAgencias agencia = createAgencia(1, 1);
        BancoAgencias agencia2 = createAgencia(1, 1);
        ArrayList<BancoAgencias> agenciaList = new ArrayList<>();

        agenciaList.add(agencia);
        agenciaList.add(agencia2);

        Set<CustomViolation> violations = validator.validateAll(agenciaList);

        Assert.assertFalse(violations.isEmpty());

    }

    @Test
    public void shouldFailWhenCollectionHasTwoEqualsIdAgenciaCallingValidateDuplicatedIdAgencia() {

        BancoAgencias agencia = createAgencia(1, 1);
        BancoAgencias agencia2 = createAgencia(1, 1);
        ArrayList<BancoAgencias> agenciaList = new ArrayList<>();

        agenciaList.add(agencia);
        agenciaList.add(agencia2);

        Boolean haveDuplicatedIdAgencia = validator.validateDuplicatedIdAgencia(agenciaList);

        Assert.assertTrue(haveDuplicatedIdAgencia);

    }

    private BancoAgencias createAgencia(Integer idBanco, Integer idAgencia) {
        Bancos bancos = new Bancos();

        return new BancoAgencias(idBanco, idAgencia, DIGITO_AGENCIA, NOME_AGENCIA,
                CNPJ_AGENCIA, SITUACAO, CENTRALIZADORA, NUMERO_CONTA_CORRENTE,
                DIGITO_CONTA_CORRENTE, ID_MUNICIPIO, EMAIL, bancos);
    }

}