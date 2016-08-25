package br.gov.to.sefaz.presentation.converter;

<<<<<<< Updated upstream
import br.gov.to.sefaz.util.message.SourceBundle;
=======
import br.gov.to.sefaz.util.message.MessageUtil;
>>>>>>> Stashed changes

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
<<<<<<< Updated upstream
import javax.faces.convert.ConverterException;
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

        } catch (DateTimeParseException e) {
            String message = SourceBundle.getMessage("presentation.converter.localDateTimeFacesConverter"
                    + ".dataFormatada");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""), e);
=======
        } catch (DateTimeParseException e) {
            MessageUtil.addMessage(FacesMessage.SEVERITY_ERROR, "presentation.converter.localDateTimeFacesConverter"
                    + ".dataFormatada");
            throw e;
>>>>>>> Stashed changes
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
