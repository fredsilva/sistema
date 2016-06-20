package br.gov.to.sefaz.seg.business.mail.domain;

import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;

import java.util.List;
import java.util.Objects;

/**
 * POJO que representa um correio eletronico que será enviado e posteriormente
 * armazenado no Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 03/06/2016 14:06:00
 */
public class CorreioEletronico {

    private final List<CorreioContribuinte> correioContribuinte;
    private final boolean htmlBody;

    public CorreioEletronico(List<CorreioContribuinte> correioContribuinte, boolean htmlBody) {
        this.correioContribuinte = correioContribuinte;
        this.htmlBody = htmlBody;
    }

    public List<CorreioContribuinte> getCorreioContribuinte() {
        return correioContribuinte;
    }

    public boolean isHtmlBody() {
        return htmlBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CorreioEletronico that = (CorreioEletronico) o;
        return htmlBody == that.htmlBody
                && Objects.equals(correioContribuinte, that.correioContribuinte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correioContribuinte, htmlBody);
    }

    @Override
    public String toString() {
        return "CorreioEletronico{"
                + "correioContribuinte=" + correioContribuinte
                + ", htmlBody=" + htmlBody
                + '}';
    }
}
