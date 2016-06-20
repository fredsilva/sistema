package br.gov.to.sefaz.seg.business.mail.facade.impl;

import br.gov.to.sefaz.seg.business.mail.domain.CorreioEletronico;
import br.gov.to.sefaz.seg.business.mail.facade.MailFacade;
import br.gov.to.sefaz.seg.business.mail.service.MailService;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import javax.mail.MessagingException;

/**
 * Implementação da fachada para acesso ao serviço de email {@link MailService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 03/06/2016 15:10:00
 */
@Component
public class MailFacadeImpl implements MailFacade {

    private final MailService mailService;

    @Autowired
    public MailFacadeImpl(MailService mailService) {
        this.mailService = mailService;
    }

    @Override
    public void sendMail(CorreioEletronico correioEletronico) throws MessagingException, SQLException {
        List<CorreioContribuinte> correiosContribuinte = correioEletronico.getCorreioContribuinte();
        mailService.sendMail(correiosContribuinte, correioEletronico.isHtmlBody());
    }
}
