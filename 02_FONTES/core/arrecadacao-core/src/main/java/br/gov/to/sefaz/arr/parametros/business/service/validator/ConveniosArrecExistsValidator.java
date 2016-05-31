package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.parametros.persistence.repository.ConveniosArrecRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verifica se o {@link br.gov.to.sefaz.arr.parametros.persistence.entity.ConveniosArrec#idConvenio} já
 * possui um registro na base de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 14/05/2016 14:24:00
 */
@Component
public class ConveniosArrecExistsValidator implements ServiceValidator<ConveniosArrec> {

    private final ConveniosArrecRepository conveniosArrecRepository;

    @Autowired
    public ConveniosArrecExistsValidator(ConveniosArrecRepository conveniosArrecRepository) {
        this.conveniosArrecRepository = conveniosArrecRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ConveniosArrec.class) && context.equals(ValidationContext.SAVE);
    }

    @Override
    public Set<CustomViolation> validate(ConveniosArrec conveniosArrec) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (conveniosArrecRepository.exists(conveniosArrec.getIdConvenio())) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.convenioArrec.idConvenio.cadastrado");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }

}
