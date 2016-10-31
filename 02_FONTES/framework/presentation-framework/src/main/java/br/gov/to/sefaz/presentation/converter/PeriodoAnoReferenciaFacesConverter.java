package br.gov.to.sefaz.presentation.converter;

import br.gov.to.sefaz.util.formatter.FormatterUtil;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Conversor de período mês e ano para os campos de período de referência do JSF.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 18/10/2016 16:26:00
 */
@FacesConverter("periodoAnoReferenciaConverter")
public class PeriodoAnoReferenciaFacesConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            YearMonth.parse(value, DateTimeFormatter.ofPattern("MM/yyyy"));
        } catch (DateTimeParseException e) {
            MessageUtil.addMessage(FacesMessage.SEVERITY_ERROR,
                    "presentation.converter.periodoAnoReferenciaConverter.dataFormatada");
            return null;
        }

        return Integer.valueOf(value.replaceAll("(\\w+)/(\\w+)", "$2$1"));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (Objects.isNull(value)) {
            return StringUtils.EMPTY;
        }

        return FormatterUtil.formatPeriodoMesAno((Integer) value);
    }
}
