package br.gov.to.sefaz.util.mail;

import javax.mail.MessagingException;

/**
 * Serviço para envio de email.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 31/05/2016 11:41:00
 */
public interface MailSenderService {

    /**
     * Envia email conforme os parametros passados.
     *
     * @param subject assunto do email
     * @param mailBody corpo do email
     * @param isHtmlBody o corpo do email é um HTML
     * @param mailTo endereços de email de destinatário
     * @throws MessagingException exceção de envio de e-mail
     */
    void sendMail(String subject, String mailBody, boolean isHtmlBody, String... mailTo) throws MessagingException;

    /**
     * Envia email conforme os parametros passados.
     *
     * @param subject assunto do email
     * @param mailBody corpo do email
     * @param isHtmlBody o corpo do email é um HTML
     * @param attachedFile arquivo que será enviado por anexo
     * @param mailTo endereços de email de destinatário
     * @throws MessagingException exceção de envio de e-mail
     */
    void sendMail(String subject, String mailBody, boolean isHtmlBody, byte[] attachedFile, String... mailTo)
            throws MessagingException;

}
