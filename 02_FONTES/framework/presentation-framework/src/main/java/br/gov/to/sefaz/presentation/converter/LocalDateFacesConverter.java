package br.gov.to.sefaz.presentation.converter;

import br.gov.to.sefaz.util.message.SourceBundle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 * Conversor de {@link java.time.LocalDate} para os campos de data do JSF.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 14/05/2016 10:00:00
 */
@FacesConverter("localDateFacesConverter")
public class LocalDateFacesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {

        if (null == stringValue || stringValue.isEmpty()) {
            return null;
        }

        LocalDate localDate;

        try {
            localDate = LocalDate.parse(stringValue, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        } catch (DateTimeParseException e) {
            String message = SourceBundle.getMessage("presentation.converter.localDateFacesConverter.dataFormatada");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""), e);
        }

        return localDate;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object localDateValue) {
        if (null == localDateValue) {
            return "";
        }

        return ((LocalDate) localDateValue).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
