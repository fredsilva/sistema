package br.gov.to.sefaz.par.gestao.business.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.par.gestao.persistence.entity.ParametroGeral;
import br.gov.to.sefaz.par.gestao.persistence.repository.ParametroGeralRepository;
import br.gov.to.sefaz.persistence.query.builder.ParamsBuilder;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.PersistenceException;

/**
 * Validação que verifica o {@link ParametroGeral#tipoParametroGeral}.
 * Caso seja estático valida se {@link ParametroGeral#conteudoValores} esteja de acordo com o pattern
 * "codigo,valor|codigo,valor|...".
 * Caso seja dinâmico valida se {@link ParametroGeral#conteudoValores} é uma query sql executável na base de dados do
 * sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 04/07/2016 10:15:31
 */
@Component
public class ParametroGeralTipoParametroValidator implements ServiceValidator<ParametroGeral> {

    private static final String CONTEUDO_VALORES_PATTERN = "[^,|]+,[^,|]+(\\|[^,|]+,[^,|]+)*";

    private final ParametroGeralRepository repository;

    @Autowired
    public ParametroGeralTipoParametroValidator(ParametroGeralRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ParametroGeral.class) && (ValidationContext.SAVE.equals(context)
                || ValidationContext.UPDATE.equals(context));
    }

    @Override
    public Set<CustomViolation> validate(ParametroGeral parametroGeral) {
        switch (parametroGeral.getTipoParametroGeral()) {
            case ESTATICO:
                return validateConteudoValoresEstatico(parametroGeral.getConteudoValores());
            case DINAMICO:
                return validateConteudoValoresDinamico(parametroGeral.getConteudoValores());
            default:
                return new HashSet<>();
        }
    }

    private Set<CustomViolation> validateConteudoValoresEstatico(String conteudoValores) {
        Set<CustomViolation> customViolations = new HashSet<>();

        if (!conteudoValores.matches(CONTEUDO_VALORES_PATTERN)) {
            String message = SourceBundle.getMessage(MessageUtil.PAR,
                    "gestao.manutencaoParametros.tipoParametroGeral.estatico.validator");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

    private Set<CustomViolation> validateConteudoValoresDinamico(String conteudoValores) {
        Set<CustomViolation> customViolations = new HashSet<>();

        // Objeto de parâmetros bind da consulta dinâmica
        ParamsBuilder paramsBuilder = ParamsBuilder.empty();
        conteudoValores = conteudoValores.replaceAll(";\\s*\\Z", StringUtils.EMPTY);
        // Captura todos os parâmetros bind da consulta dinâmica
        Matcher matcher = Pattern.compile("(:)(\\w+)").matcher(conteudoValores);
        // Itera os parâmetros bind da consulta dinâmica
        while (matcher.find()) {
            // Atribui os valores para parâmetro bind da consulta dinâmica
            paramsBuilder.put(matcher.group(2), "0");
        }

        // Realiza a consulta do parêmetroGeral Dinâmico
        try {
            repository.findPArametroGeralDinamico(conteudoValores, paramsBuilder);
        } catch (PersistenceException e) {
            String message = SourceBundle.getMessage(MessageUtil.PAR, "gestao.manutencaoParametros.conteudoValores.sql"
                    + ".invalido");
            customViolations.add(new CustomViolation(message));
        }

        return customViolations;
    }

}
