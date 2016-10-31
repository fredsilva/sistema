package br.gov.to.sefaz.arr.dare.service.domain;

import br.gov.to.sefaz.business.service.validation.custom.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * ViewBean para a funcionalidade de Envio de e-mail do DARE.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 12/09/2016 09:57:00
 */
public class DareEmail {

    @Email(message = "#{arr_msg['arr.par.dareEletronicoConsolidado.email.form.email.incorreto']}")
    private String destinatario;
    private String selectedDestinario;
    @NotEmpty(message = "#{arr_msg['arr.par.dareEletronicoConsolidado.email.form.subject.empty']}")
    private String assunto;
    @NotEmpty(message = "#{arr_msg['arr.par.dareEletronicoConsolidado.email.form.message.empty']}")
    private String mensagem;
    private Long nossoNumero;
    private Collection<String> destinatarios;

    public DareEmail() {
        destinatarios = new ArrayList<>();
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getSelectedDestinario() {
        return selectedDestinario;
    }

    public void setSelectedDestinario(String selectedDestinario) {
        this.selectedDestinario = selectedDestinario;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Collection<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(Collection<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public void setNossoNumero(Long nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    public Long getNossoNumero() {
        return nossoNumero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DareEmail dareEmail = (DareEmail) o;
        return Objects.equals(assunto, dareEmail.assunto)
                && Objects.equals(mensagem, dareEmail.mensagem)
                && Objects.equals(nossoNumero, dareEmail.nossoNumero)
                && Objects.equals(destinatarios, dareEmail.destinatarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assunto, mensagem, nossoNumero, destinatarios);
    }

    @Override
    public String toString() {
        return "DareEmail{"
                + "assunto='" + assunto + '\''
                + ", mensagem='" + mensagem + '\''
                + ", nossoNumero=" + nossoNumero
                + ", destinatarios=" + destinatarios
                + '}';
    }
}
