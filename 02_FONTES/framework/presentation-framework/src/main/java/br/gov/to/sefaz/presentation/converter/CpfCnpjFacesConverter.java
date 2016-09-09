package br.gov.to.sefaz.presentation.converter;

import br.gov.to.sefaz.util.formatter.FormatterUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Conversor JSF para campos de CPF/CNPJ.
 *
 * @author <a href="mailto:volceri.daviila@ntconsult.com.br">volceri.davila</a>
 * @since 30/08/2016 17:00:00
 */
@FacesConverter("cpfCnpjFacesConverter")
public class CpfCnpjFacesConverter implements Converter {

    private static final String NON_NUMERIC_PATTERN = "\\D";
    private static final int CNPJ_NUMBER_OD_DIGITS = 14;
    private static final int CPF_NUMBER_OD_DIGITS = 11;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String stringValue) {

        if (null == stringValue || stringValue.isEmpty()) {
            return null;
        }

        return stringValue.replaceAll(NON_NUMERIC_PATTERN, StringUtils.EMPTY);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object cpfCnpj) {

        if (Objects.isNull(cpfCnpj)) {
            return StringUtils.EMPTY;
        }

        String strCpfCnpj = cpfCnpj.toString().replaceAll(NON_NUMERIC_PATTERN, StringUtils.EMPTY);

        if (strCpfCnpj.length() == CNPJ_NUMBER_OD_DIGITS) {
            return FormatterUtil.formatCnpj(strCpfCnpj);

        } else if (strCpfCnpj.length() == CPF_NUMBER_OD_DIGITS) {
            return FormatterUtil.formatCpf(strCpfCnpj);

        } else {
            return strCpfCnpj;
        }
    }
}
