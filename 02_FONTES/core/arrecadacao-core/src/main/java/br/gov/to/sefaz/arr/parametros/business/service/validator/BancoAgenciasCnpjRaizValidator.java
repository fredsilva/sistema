package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.repository.BancosRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link BancoAgencias#cnpjAgencia} possui a mesma raiz que {@link BancoAgencias#bancos} a
 * que pertence.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 25/05/2016 08:43:35
 */
@Component
public class BancoAgenciasCnpjRaizValidator implements ServiceValidator<BancoAgencias> {

    private final BancosRepository repository;

    @Autowired
    public BancoAgenciasCnpjRaizValidator(
            BancosRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(BancoAgencias.class)
                && (context.equals(ValidationContext.SAVE) || context.equals(ValidationContext.UPDATE));
    }

    @Override
    public Set<CustomViolation> validate(BancoAgencias agencia) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        Integer cnpjRaizBanco = repository.findCnpjRaizByIdBanco(agencia.getIdBanco());
        DecimalFormat dfCnpjRaiz = new DecimalFormat("00000000");
        String cnpjRaiz = dfCnpjRaiz.format(cnpjRaizBanco);

        DecimalFormat dfCnpj = new DecimalFormat("00000000000000");
        String cnpj = dfCnpj.format(agencia.getCnpjAgencia());
        cnpj = cnpj.substring(0, cnpj.length() - 6);

        if (!cnpj.equals(cnpjRaiz)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.bancoAgencias.cnpjAgencia.cnpjRaiz.incorreto");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}
