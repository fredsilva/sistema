package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
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

/**
 * Validação para garantir que não exista duas {@link BancoAgencias#centralizadora} para o mesmo {@link Bancos}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 25/05/2016 09:03:32
 */
@Component
@SuppressWarnings("PMD")
public class BancoAgenciasDuplicatedCentralizadoraValidator implements ServiceValidator<BancoAgencias> {

    private final BancoAgenciasRepository repository;

    @Autowired
    public BancoAgenciasDuplicatedCentralizadoraValidator(
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

        if (target.getCentralizadora()
                && repository.findExitsCentralizadoraAndIdBanco(target.getIdBanco(), Boolean.TRUE)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.bancoAgencias.centralizadora.ja.existe");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    @Override
    public Set<CustomViolation> validateAll(Collection<BancoAgencias> target) {

        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!validateDuplicatedCentralizadoraAndIdBanco(target)) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.bancoAgencias.centralizadora.ja.existe");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    /**
     * Valida se o {@link BancoAgencias} já possui um registro com o mesmo {@link BancoAgencias#centralizadora} e
     * {@link BancoAgencias#idBanco}.
     *
     * @param target bancoAgencias que será validada
     * @return se válido ou inválido (true ou false)
     */
    public Boolean validateDuplicatedCentralizadoraAndIdBanco(Collection<BancoAgencias> target) {

        long occurr = target.stream()
                .filter(ag -> ag.getCentralizadora()).count();

        if (occurr > 1L) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
