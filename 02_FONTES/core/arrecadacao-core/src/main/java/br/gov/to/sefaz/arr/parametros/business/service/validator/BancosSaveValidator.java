package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.BancosRepository;
import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.util.MessageUtil;
import br.gov.to.sefaz.util.SourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Validador customizado para adição de {@link Bancos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 02/05/2016 16:04:08
 */
@Component
public class BancosSaveValidator implements ServiceValidator<Bancos> {

    private final BancosRepository bancosRepository;

    @Autowired
    public BancosSaveValidator(BancosRepository bancosRepository) {
        this.bancosRepository = bancosRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(Bancos.class) && context.equals(ValidationContext.SAVE);
    }

    @Override
    public Set<CustomViolation> validate(Bancos bancos) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (bancosRepository.exists(bancos.getIdBanco())) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.bancosSaveValidator.codigoCadastrado");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }

}
