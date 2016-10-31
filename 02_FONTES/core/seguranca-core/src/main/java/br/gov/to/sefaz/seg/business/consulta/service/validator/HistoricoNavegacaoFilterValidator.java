package br.gov.to.sefaz.seg.business.consulta.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.consulta.service.filter.HistoricoNavegacaoFilter;
import br.gov.to.sefaz.seg.business.consulta.service.impl.HistoricoNavegacaoServiceImpl;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Validador para os campos filtros utilizados na consulta de históricos de navegação.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 08/09/2016 11:10:06:00
 */
@Component
public class HistoricoNavegacaoFilterValidator implements ServiceValidator<HistoricoNavegacaoFilter> {


    private static final int DAYS_DIFF_BETWEEN_DATES = 60;

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(HistoricoNavegacaoFilter.class)
                && HistoricoNavegacaoServiceImpl.FILTER_VALIDATION_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(HistoricoNavegacaoFilter target) {

        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (StringUtils.isEmpty(target.getCpfUsuario())
                || Objects.isNull(target.getTipoOperacao())
                || Objects.isNull(target.getDataInicialAjustada())
                || Objects.isNull(target.getDataFinalAjustada())) {

            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.consulta.consultaHistoricoAcesso.pesquisa.filtro.invalido");
            customViolations.add(new CustomViolation(codigoCadastrado));

        } else if (target.getDataInicialAjustada().isAfter(target.getDataFinalAjustada())) {

            String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                    "seg.consulta.consultaHistoricoAcesso.pesquisa.filtro.invalido");
            customViolations.add(new CustomViolation(codigoCadastrado));

        } else {
            long daysBetweenDates = ChronoUnit.DAYS.between(target.getDataInicialAjustada(), target
                    .getDataFinalAjustada());

            if (daysBetweenDates > DAYS_DIFF_BETWEEN_DATES) {
                String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                        "seg.consulta.consultaHistoricoAcesso.pesquisa.filtro.invalido");
                customViolations.add(new CustomViolation(codigoCadastrado));
            }
        }

        return customViolations;
    }
}