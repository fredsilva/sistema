package br.gov.to.sefaz.presentation.converter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Objects;

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
@FacesConverter("cpfFacesConverter")
public class CpfFacesConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {

        if (null == stringValue || stringValue.isEmpty()) {
            return null;
        }

        return stringValue.replaceAll("[\\.\\-_/ ]", "");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object cpf) {
        if (Objects.isNull(cpf)) {
            return "";
        }

        DecimalFormat df = new DecimalFormat("00000000000");
        String retorno = df.format(cpf);
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###-##");
            mask.setValueContainsLiteralCharacters(false);
            retorno = mask.valueToString(retorno);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Erro ao formatar o cpf " + cpf.toString(), e);
        }

        return retorno;
    }
}
