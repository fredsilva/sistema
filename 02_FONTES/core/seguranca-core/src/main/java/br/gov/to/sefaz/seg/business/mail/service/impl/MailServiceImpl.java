package br.gov.to.sefaz.seg.business.mail.service.impl;

import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.gestao.service.CorreioContribuinteService;
import br.gov.to.sefaz.seg.business.mail.service.MailService;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import br.gov.to.sefaz.util.mail.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.mail.MessagingException;

/**
 * Implementação do serviço de email.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 02/06/2016 14:33:00
 */
@Service
public class MailServiceImpl implements MailService {

    private final MailSenderService mailSenderService;
    private final CorreioContribuinteService correioContribuinteService;

    @Autowired
    public MailServiceImpl(MailSenderService mailSenderService, CorreioContribuinteService correioContribuinteService) {
        this.mailSenderService = mailSenderService;
        this.correioContribuinteService = correioContribuinteService;
    }

    @Override
    public void sendMail(@ValidationSuite(context = ValidationContext.SAVE) Collection<CorreioContribuinte>
            correioContribuintes, boolean isHtmlBody) throws MessagingException {
        if (!correioContribuintes.isEmpty()) {
            sendMail(isHtmlBody, correioContribuintes);
            correioContribuinteService.save(correioContribuintes);
        }
    }

    private void sendMail(boolean isHtmlBody, Collection<CorreioContribuinte> correioContribuintes) throws
            MessagingException {
        CorreioContribuinte correioContribuinte = correioContribuintes.iterator().next();
        List<String> correiosEletronicos = correioContribuintes.stream()
                .map(CorreioContribuinte::getCorreioEletronico)
                .collect(Collectors.toList());
        String[] mailToArray = correiosEletronicos.toArray(new String[correiosEletronicos.size()]);

        if (correioContribuinte.getAnexo() != null) {
            mailSenderService.sendMail(correioContribuinte.getAssunto(),
                    correioContribuinte.getConteudo(), isHtmlBody,
                    correioContribuinte.getAnexo(), mailToArray);
        } else {
            mailSenderService.sendMail(correioContribuinte.getAssunto(), correioContribuinte.getConteudo(),
                    isHtmlBody, mailToArray);
        }
    }

}
