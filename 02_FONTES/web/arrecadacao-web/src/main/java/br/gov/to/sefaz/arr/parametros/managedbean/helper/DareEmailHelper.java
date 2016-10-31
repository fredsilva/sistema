package br.gov.to.sefaz.arr.parametros.managedbean.helper;

import br.gov.to.sefaz.arr.dare.service.domain.DareEmail;
import br.gov.to.sefaz.arr.parametros.managedbean.validator.DareEmailValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * Auxiliador para tratamento de ações de envio de e-mail de DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 27/09/2016 14:58:00
 */
public class DareEmailHelper {

    private DareEmail dareEmail;
    private final DareEmailValidator dareEmailValidator;

    public DareEmailHelper() {
        this.dareEmail = new DareEmail();
        dareEmailValidator = new DareEmailValidator();
    }

    public DareEmail getDareEmail() {
        return dareEmail;
    }

    public void setDareEmail(DareEmail dareEmail) {
        this.dareEmail = dareEmail;
    }

    /**
     * Adiciona um e-mail à {@link DareEmail#getDestinatarios()}.
     */
    public void addEmail() {
        String destinatario = dareEmail.getDestinatario();
        if (dareEmailValidator.isValidDestinatario(destinatario)) {
            getDareEmail().getDestinatarios().add(destinatario);
            dareEmail.setDestinatario(StringUtils.EMPTY);
        }
    }

    /**
     * Remove um e-mail da {@link DareEmail#getDestinatarios()}.
     */
    public void removeEmail() {
        getDareEmail().getDestinatarios().remove(getDareEmail().getSelectedDestinario());
    }
}
