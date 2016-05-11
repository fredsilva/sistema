package br.gov.to.sefaz.arr.parametros.business.service.validator;

import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.TipoRejeicaoArquivosRepository;
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
 * Validador customizado para adição de {@link TipoRejeicaoArquivos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 29/04/2016 10:52:19
 */
@Component
public class TipoRejeicaoArquivosSaveValidator implements ServiceValidator<TipoRejeicaoArquivos> {

    private final TipoRejeicaoArquivosRepository tipoRejeicaoArquivosRepository;

    @Autowired
    public TipoRejeicaoArquivosSaveValidator(TipoRejeicaoArquivosRepository tipoRejeicaoArquivosRepository) {
        this.tipoRejeicaoArquivosRepository = tipoRejeicaoArquivosRepository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(TipoRejeicaoArquivos.class) && context.equals(ValidationContext.SAVE);
    }

    @Override
    public Set<CustomViolation> validate(TipoRejeicaoArquivos tipoRejeicaoArquivos) {
        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (tipoRejeicaoArquivosRepository.exists(tipoRejeicaoArquivos.getIdCodigoRejeicao())) {
            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.ARR,
                    "parametros.validator.codigo.cadastrado");
            customViolations.add(new CustomViolation(codigoCadastrado));
        }

        return customViolations;
    }

}
