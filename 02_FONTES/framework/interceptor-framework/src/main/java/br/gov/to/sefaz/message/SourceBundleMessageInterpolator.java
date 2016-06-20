package br.gov.to.sefaz.message;

import br.gov.to.sefaz.util.message.SourceBundle;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import java.util.Locale;
import javax.validation.MessageInterpolator;

/**
 * Classe que interpola as mensagens de validação dos beans. Transforma uma expression language em uma mensagem tratada
 * e cadastrada no(s) resouce(s) bungle(s).
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 29/04/2016 10:41:35
 */
public class SourceBundleMessageInterpolator extends ResourceBundleMessageInterpolator implements MessageInterpolator {

    @Override
    public String interpolate(String message, Context context, Locale locale) {
        if (message != null && message.startsWith("#{")) {
            return SourceBundle.getMessageByExpression(message);
        } else {
            return super.interpolate(message, context, locale);
        }
    }

}
