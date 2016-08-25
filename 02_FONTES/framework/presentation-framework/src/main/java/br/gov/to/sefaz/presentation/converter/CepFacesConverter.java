package br.gov.to.sefaz.presentation.converter;

import br.gov.to.sefaz.util.message.SourceBundle;

import java.text.DecimalFormat;
import java.text.ParseException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.swing.text.MaskFormatter;

/**
 * Conversor de Cep para os campos de data do JSF.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 21/06/2016 18:13:00
 */
@FacesConverter("cepFacesConverter")
public class CepFacesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {

        if (null == stringValue || stringValue.isEmpty()) {
            return null;
        }

        stringValue = stringValue.replaceAll("[\\.\\-_/ ]", "");

        Long cep;

        try {
            cep = Long.valueOf(stringValue);
        } catch (NumberFormatException e) {
            String message = SourceBundle.getMessage("presentation.converter.cnpjFacesConverter.format");
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""), e);
        }

        return cep;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object cep) {
        if (null == cep) {
            return "";
        }

        DecimalFormat df = new DecimalFormat("00000000");
        if (cep instanceof String) {
            cep = Long.valueOf(cep.toString());
        }

        String retorno = df.format(cep);
        try {
            MaskFormatter mask = new MaskFormatter("#####-###");
            mask.setValueContainsLiteralCharacters(false);
            retorno = mask.valueToString(retorno);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Erro ao formatar o cep " + cep.toString(), e);
        }

        return retorno;
    }
}
