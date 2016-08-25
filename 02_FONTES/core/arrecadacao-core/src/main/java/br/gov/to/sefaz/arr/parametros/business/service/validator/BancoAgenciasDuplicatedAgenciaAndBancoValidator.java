package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.repository.BancoAgenciasRepository;
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
 * Validação para garantir que a {@link BancoAgencias#idAgencia} não seja cadastrado repetidamente para o mesmo
 * {@link Bancos}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 25/05/2016 09:01:14
 */
@Component
public class BancoAgenciasDuplicatedAgenciaAndBancoValidator implements ServiceValidator<BancoAgencias> {

    private final BancoAgenciasRepository repository;

    @Autowired
    public BancoAgenciasDuplicatedAgenciaAndBancoValidator(
            BancoAgenciasRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(BancoAgencias.class) && context.equals(ValidationContext.SAVE);
    }

    @Override
    public Set<CustomViolation> validate(BancoAgencias target) {

        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (repository.findExitsIdAgenciaAndIdBanco(target.getIdAgencia(), target.getIdBanco())) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.bancoAgencias.agencia.ja.cadastrada");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    @Override
    public Set<CustomViolation> validateAll(Collection<BancoAgencias> target) {

        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (validateDuplicatedIdAgencia(target)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.bancoAgencias.agencia.ja.adicionada");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    /**
     * Valida se o {@link BancoAgencias} já possui um registro com o mesmo {@link BancoAgencias#idAgencia} e
     * {@link BancoAgencias#idBanco}.
     *
     * @param target bancoAgencias que será validada
     * @return se válido ou inválido (true ou false)
     */
    public Boolean validateDuplicatedIdAgencia(Collection<BancoAgencias> target) {

        Set<Integer> listIdAgencia = target.stream()
                .map(BancoAgencias::getIdAgencia)
                .collect(Collectors.toSet());

        if (listIdAgencia.size() != target.size()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

}
