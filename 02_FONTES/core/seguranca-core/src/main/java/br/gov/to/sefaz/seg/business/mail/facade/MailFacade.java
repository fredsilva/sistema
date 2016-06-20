package br.gov.to.sefaz.seg.business.mail.facade;

import br.gov.to.sefaz.seg.business.mail.domain.CorreioEletronico;
import br.gov.to.sefaz.seg.business.mail.service.MailService;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

/**
 * Fachada para acesso ao serviço de email {@link MailService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 03/06/2016 15:09:00
 */
public interface MailFacade {

    /**
     * Recebe o {@link CorreioEletronico} contendo o conteúdo do email a ser enviado para o {@link MailService}.
     *
     * @param correioEletronico conteúdo do email a ser enviado para o serviço
     * @throws IOException exceção ao gravar anexos
     * @throws MessagingException exceção de envio de e-mail
     * @throws SQLException exeção ao persistir log do e-mail enviado
     */
    void sendMail(CorreioEletronico correioEletronico) throws IOException, MessagingException, SQLException;
}
