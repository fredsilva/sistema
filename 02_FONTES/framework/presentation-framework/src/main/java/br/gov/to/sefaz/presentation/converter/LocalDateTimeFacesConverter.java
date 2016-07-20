package br.gov.to.sefaz.presentation.converter;

import br.gov.to.sefaz.util.message.SourceBundle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Conversor de {@link java.time.LocalDateTime} para os campos de data do JSF.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 21/06/2016 18:13:00
 */
@FacesConverter("localDateTimeFacesConverter")
public class LocalDateTimeFacesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {

        if (null == stringValue || stringValue.isEmpty()) {
            return null;
        }

        LocalDateTime localDateTime;

        try {
            localDateTime = LocalDateTime.parse(stringValue, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));

        } catch (DateTimeParseException e) {
            String message = SourceBundle.getMessage("presentation.converter.localDateTimeFacesConverter"
                    + ".dataFormatada");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""), e);
        }

        return localDateTime;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object localDateValue) {
        if (null == localDateValue) {
            return "";
        }

        return ((LocalDateTime) localDateValue).format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
    }
}
