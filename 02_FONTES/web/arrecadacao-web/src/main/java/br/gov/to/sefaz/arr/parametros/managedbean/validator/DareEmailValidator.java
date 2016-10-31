package br.gov.to.sefaz.arr.parametros.managedbean.validator;

import br.gov.to.sefaz.business.service.validation.custom.EmailValidatorHandler;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Validação para a view de envio de Email do DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/10/2016 14:10:00
 */
public class DareEmailValidator {

    /**
     * Validação referente a adição de destinatário de email para envio de DARE-e.
     *
     * @param destinatario contém as informações as quais serão validadas.
     */
    public boolean isValidDestinatario(String destinatario) {
        if (StringUtils.isEmpty(destinatario)) {
            MessageUtil.addErrorMessage(MessageUtil.ARR, "arr.par.dareEletronicoConsolidado.email.form.email.empty");
            return false;
        }

        if (!EmailValidatorHandler.isValid(destinatario)) {
            MessageUtil.addErrorMessage(MessageUtil.ARR,
                    "arr.par.dareEletronicoConsolidado.email.form.email.incorreto");
            return false;
        }

        return true;
    }
}
