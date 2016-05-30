package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.repository.BancoAgenciasRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Validação para garantir que não exista {@link BancoAgencias} com o mesmo {@link BancoAgencias#cnpjAgencia}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 25/05/2016 09:04:41
 */
@Component
public class BancoAgenciasDuplicatedCnpjValidator implements ServiceValidator<BancoAgencias> {

    private final BancoAgenciasRepository repository;

    @Autowired
    public BancoAgenciasDuplicatedCnpjValidator(
            BancoAgenciasRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(BancoAgencias.class)
                && (context.equals(ValidationContext.SAVE) || context.equals(ValidationContext.UPDATE));
    }

    @Override
    public Set<CustomViolation> validate(BancoAgencias target) {

        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (repository.findExitsCnpj(target.getIdAgencia(), target.getCnpjAgencia())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.bancoAgencias.cnpjAgencia.ja.existe");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    @Override
    public Set<CustomViolation> validateAll(Collection<BancoAgencias> target) {

        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (validateDuplicatedCnpj(target)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.bancoAgencias.cnpjAgencia.ja.existe");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    /**
     * Valida se o {@link BancoAgencias} já possui um registro com o mesmo {@link BancoAgencias#cnpj}.
     *
     * @param target bancoAgencias que será validada
     * @return se válido ou inválido (true ou false)
     */
    private Boolean validateDuplicatedCnpj(Collection<BancoAgencias> target) {

        Set<Long> listCnpj = target.stream()
                .map(BancoAgencias::getCnpjAgencia)
                .collect(Collectors.toSet());

        if (listCnpj.size() != target.size()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
