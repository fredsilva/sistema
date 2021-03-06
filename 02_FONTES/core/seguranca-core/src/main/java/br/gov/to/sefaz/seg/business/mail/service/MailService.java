package br.gov.to.sefaz.seg.business.mail.service;

import br.gov.to.sefaz.seg.business.mail.domain.CorreioEletronico;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;

import java.util.Collection;
import javax.mail.MessagingException;

/**
 * Serviço para enviar email e armazenar no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 02/06/2016 14:27:00
 */
public interface MailService {

    /**
     * Recebe o {@link br.gov.to.sefaz.seg.business.mail.domain.CorreioEletronico} contendo o conteúdo do email a ser
     * enviado para o {@link MailService}.
     *
     * @param correioEletronico conteúdo do email a ser enviado para o serviço
     * @throws MessagingException exceção de envio de e-mail
     */
    void sendMail(CorreioEletronico correioEletronico) throws MessagingException;

    /**
     * Envia email para um conjunto de destinatários, que possuem o mesmo conteúdo e salva a mensagem enviada no
     * banco de dados.
     *
     * @param correioContribuintes contém os destinatários, assim como as informações que compoem o email
     * @param isHtmlBody           se o corpo do email é html ou não
     * @throws MessagingException caso o email não seja enviado com sucesso
     */
    void sendMail(Collection<CorreioContribuinte> correioContribuintes, boolean isHtmlBody) throws MessagingException;
}
