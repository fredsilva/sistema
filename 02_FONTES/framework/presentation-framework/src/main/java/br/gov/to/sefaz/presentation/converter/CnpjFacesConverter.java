package br.gov.to.sefaz.presentation.converter;

import java.text.DecimalFormat;
import java.text.ParseException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.swing.text.MaskFormatter;

/**
 * Conversor de {@link java.time.LocalDate} para os campos de data do JSF.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 14/05/2016 10:00:00
 */
@FacesConverter("cnpjFacesConverter")
public class CnpjFacesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {

        if (null == stringValue || stringValue.isEmpty()) {
            return null;
        }

        stringValue = stringValue.replaceAll("[\\.\\-_/ ]", "");

        return stringValue;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object cnpj) {
        if (null == cnpj) {
            return "";
        }

        DecimalFormat df = new DecimalFormat("00000000000000");
        if (cnpj instanceof String) {
            cnpj = Long.valueOf(cnpj.toString());
        }

        String retorno = df.format(cnpj);
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
            mask.setValueContainsLiteralCharacters(false);
            retorno = mask.valueToString(retorno);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Erro ao formatar o cnpj " + cnpj.toString(), e);
        }

        return retorno;
    }
}
