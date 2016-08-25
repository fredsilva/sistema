package br.gov.to.sefaz.util.mail.impl;

import br.gov.to.sefaz.util.mail.MailSender;
import br.gov.to.sefaz.util.properties.AppProperties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Implementação do serviço de envio de email.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 31/05/2016 11:42:00
 */
@Component
public class MailSenderImpl implements MailSender {

    private static final String ATTACHMENT_FILENAME = "anexos.zip";
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderImpl(
            JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String subject, String mailBody, boolean isHtmlBody, String... mailTo)
            throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        buildMessageHelper(mimeMessage, subject, mailBody, isHtmlBody, mailTo);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendMail(String subject, String mailBody, boolean isHtmlBody,
            byte[] attachedFile, String... mailTo) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = buildMessageHelper(message, subject, mailBody, isHtmlBody, mailTo);
        ByteArrayResource source = new ByteArrayResource(attachedFile);
        helper.addAttachment(ATTACHMENT_FILENAME, source);

        javaMailSender.send(message);
    }

    private MimeMessageHelper buildMessageHelper(MimeMessage mimeMessage, String subject, String mailBody,
            boolean isHtmlBody, String... mailTo) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom(AppProperties.getProperty("email.username").get());

        if (isHtmlBody) {
            helper.setText(mailBody, true);
        } else {
            helper.setText(mailBody);
        }

        helper.setSubject(subject);
        helper.getMimeMessage().addRecipients(Message.RecipientType.TO, StringUtils.join(mailTo, ","));

        return helper;
    }
}
