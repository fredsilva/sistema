package br.gov.to.sefaz.arr.parametros.business.service.validator;


import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.BancosRepository;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Set;

import static org.mockito.Mockito.when;



/**
 * Classe de testes da classe {@link BancoAgenciasCnpjRaizValidator}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 02/06/2016 15:20:00
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(SourceBundle.class)
public class BancoAgenciasCnpjRaizValidatorTest {

    private static final Integer ID_BANCO = 55;
    private static final Integer ID_AGENCIA = 56;
    private static final String DIGITO_AGENCIA = "71";
    private static final String NOME_AGENCIA = "nome agencia";
    private static final SituacaoEnum SITUACAO = SituacaoEnum.ATIVO;
    private static final Boolean CENTRALIZADORA = false;
    private static final Long NUMERO_CONTA_CORRENTE = 65L;
    private static final String DIGITO_CONTA_CORRENTE = "72";
    private static final Integer ID_MUNICIPIO = 21;
    private static final String EMAIL = "agencia@banco.com";

    @Mock
    private BancosRepository repository;
    @InjectMocks
    private BancoAgenciasCnpjRaizValidator validator;

    @Test
    public void shouldPassValidationWhenCnpjRaizEqualsToBanco() {
        // given 00000000
        BancoAgencias agencia = createAgencia(272L);
        PowerMockito.mockStatic(SourceBundle.class);
        when(repository.findCnpjRaizByIdBanco(ID_BANCO)).thenReturn(0);

        // when
        Set<CustomViolation> violations = validator.validate(agencia);

        // then
        Assert.assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenCnpjRaizDifferentToBanco() {
        // given
        BancoAgencias agencia = createAgencia(1000000272L);
        PowerMockito.mockStatic(SourceBundle.class);
        when(repository.findCnpjRaizByIdBanco(ID_BANCO)).thenReturn(0);

        // when

        Set<CustomViolation> violations = validator.validate(agencia);

        // then
        Assert.assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailSupportWhenObjectNotBancoAgencia() {
        // given
        Object agencia = new Object();
        PowerMockito.mockStatic(SourceBundle.class);

        Assert.assertFalse(validator.support(agencia.getClass(), ValidationContext.SAVE));
    }

    @Test
    public void shouldFailSupportWhenContextNotExists() {
        // given
        BancoAgencias agencia = createAgencia(1000000272L);
        PowerMockito.mockStatic(SourceBundle.class);

        Assert.assertFalse(validator.support(agencia.getClass(), ""));
    }

    private BancoAgencias createAgencia(Long cnpjAgencia) {
        Bancos bancos = new Bancos();

        return new BancoAgencias(ID_BANCO, ID_AGENCIA, DIGITO_AGENCIA, NOME_AGENCIA,
                cnpjAgencia, SITUACAO, CENTRALIZADORA, NUMERO_CONTA_CORRENTE,
                DIGITO_CONTA_CORRENTE, ID_MUNICIPIO, EMAIL, bancos);
    }

}
