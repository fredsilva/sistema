package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.persistence.repository.ConveniosArrecRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Validação que verfica se o {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#tipoConvenio} já
 * existe para o mesmo tipo cadastrado com o mesmo
 * {@link br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec#bancoAgencias}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 14/05/2016 14:08:00
 */
@Component
public class ConveniosArrecTipoConvenioDuplicatedValidator implements ServiceValidator<ConveniosArrec> {

    private final ConveniosArrecRepository conveniosArrecRepository;

    @Autowired
    public ConveniosArrecTipoConvenioDuplicatedValidator(
            ConveniosArrecRepository conveniosArrecRepository) {
        this.conveniosArrecRepository = conveniosArrecRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ConveniosArrec.class)
                && (context.equals(ValidationContext.SAVE) || context.equals(ValidationContext.UPDATE));
    }

    @Override
    public Set<CustomViolation> validate(ConveniosArrec target) {
        HashSet<CustomViolation> customViolations = new HashSet<>();
        TipoConvenioEnum tipoConvenio = target.getTipoConvenio();
        Integer idBanco = target.getIdBanco();
        Integer idAgencia = target.getIdAgencia();

        Collection<Long> listConvenio = conveniosArrecRepository
                .findIdConvenioArrecByTipoConvenioAndAgencia(tipoConvenio, idBanco, idAgencia);

        if (!CollectionUtils.isEmpty(listConvenio)
                && listConvenio.stream().filter(c -> !c.equals(target.getId())).findAny().isPresent()) {
            String message = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.convenioArrec.tipoConvenio.bancoAgencias.cadastrado");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}
