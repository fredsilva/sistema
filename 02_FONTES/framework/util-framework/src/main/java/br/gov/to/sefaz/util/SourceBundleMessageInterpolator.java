package br.gov.to.sefaz.util;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import java.util.Locale;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.validation.MessageInterpolator;

/**
 * Classe que interpola as mensagens de validação dos beans.
 * Transforma uma expression language em uma mensagem tratada e cadastrada no(s) resouce(s) bungle(s).
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 29/04/2016 10:41:35
 */
public class SourceBundleMessageInterpolator extends ResourceBundleMessageInterpolator implements MessageInterpolator {

    @Override
    public String interpolate(String message, Context context, Locale locale) {
        if (message != null && message.startsWith("#{")) {
            return this.interpolateMessage(message);
        } else {
            return super.interpolate(message, context, locale);
        }
    }

    /**
     * Transforma uma expression language em uma mensagem tratada e cadastrada no(s) resouce(s) bungle(s).
     *
     * @param key Chave do message baundle no formato EL - Expression Language
     * @return Mensagem tratada
     */
    private String interpolateMessage(String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExpressionFactory elFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression valueExp = elFactory.createValueExpression(facesContext.getELContext(), key, Object.class);
        Object value = valueExp.getValue(facesContext.getELContext());
        return value == null ? key : value.toString();
    }
}
