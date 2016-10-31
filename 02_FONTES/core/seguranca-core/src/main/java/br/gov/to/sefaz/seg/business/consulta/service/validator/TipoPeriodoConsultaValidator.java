package br.gov.to.sefaz.seg.business.consulta.service.validator;

import br.gov.to.sefaz.business.service.validation.ServiceValidator;
import br.gov.to.sefaz.business.service.validation.violation.CustomViolation;
import br.gov.to.sefaz.seg.business.consulta.service.filter.ConsultaComunicacaoSistemaFilter;
import br.gov.to.sefaz.seg.business.consulta.service.impl.ComunicacaoContribuinteServiceImpl;
import br.gov.to.sefaz.seg.persistence.enums.TipoPeriodoConsultaComunicacaoEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import br.gov.to.sefaz.util.message.SourceBundle;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Aplica regra de validação para os períodos de consulta de comunicações do sistema de acordo com o tipo de período
 * informado, conforme segue:
 * <ul>
 *      <li>
 *          <code><b>{@link TipoPeriodoConsultaComunicacaoEnum#PERIODO}</b></code>
 *          <ol>
 *              <li>As datas inicial e final devem ser informadas</li>
 *              <li>A data inicial não deve ser superior a final</li>
 *              <li>A diferença em dias entre a data inicial e final não deve ser superios a 60 dias</li>
 *          </ol>
 *      </li>
 *      <li>
 *          Para os demais tipos de período definido em {@link TipoPeriodoConsultaComunicacaoEnum} não existe regra
 *          específica para os filtros data inicial e final, uma vez que as mesmas serão calculadas conforme o tipo
 *          de periodo informado.
 *      </li>
 * </ul>
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 30/08/2016 09:30:06:00
 */
@Component
public class TipoPeriodoConsultaValidator implements ServiceValidator<ConsultaComunicacaoSistemaFilter> {


    private static final int DAYS_DIFF_BETWEEN_DATES = 60;

    @Override
    public boolean support(Class<?> clazz, String context) {
        return clazz.equals(ConsultaComunicacaoSistemaFilter.class)
                && ComunicacaoContribuinteServiceImpl.FILTER_VALIDATION_CONTEXT.equals(context);
    }

    @Override
    public Set<CustomViolation> validate(ConsultaComunicacaoSistemaFilter target) {

        HashSet<CustomViolation> customViolations = new HashSet<>();

        if (!Objects.isNull(target.getTipoPeriodo())
                && target.getTipoPeriodo() == TipoPeriodoConsultaComunicacaoEnum.PERIODO) {

            if (target.getDataInicial() == null || target.getDataFinal() == null) {
                String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                        "seg.consulta.consultaComunicacaoSistema.pesquisa.peridoPesquisa.obrigatorio");
                customViolations.add(new CustomViolation(codigoCadastrado));

            } else if (target.getDataInicial().isAfter(target.getDataFinal())) {
                String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                        "seg.consulta.consultaComunicacaoSistema.pesquisa.peridoPesquisa.invalido");
                customViolations.add(new CustomViolation(codigoCadastrado));

            } else {
                long daysBetweenDates = ChronoUnit.DAYS.between(target.getDataInicial(), target.getDataFinal());

                if (daysBetweenDates > getDaysDiffBetweenDatesForValidation()) {
                    String codigoCadastrado = SourceBundle.getMessage(MessageUtil.SEG,
                            "seg.consulta.consultaComunicacaoSistema.pesquisa.peridoPesquisa.invalido");
                    customViolations.add(new CustomViolation(codigoCadastrado));
                }
            }
        }

        return customViolations;
    }

    private int getDaysDiffBetweenDatesForValidation() {
        return DAYS_DIFF_BETWEEN_DATES;
    }
}