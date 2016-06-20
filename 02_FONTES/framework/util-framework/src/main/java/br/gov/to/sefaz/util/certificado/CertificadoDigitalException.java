package br.gov.to.sefaz.util.certificado;

import br.gov.to.sefaz.exception.HandledSystemException;

/**
 * Exceção para o tratamento de Certificado Digital.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 15/06/2016 10:14:00
 */
public class CertificadoDigitalException extends Exception implements HandledSystemException {

    private static final long serialVersionUID = -4526172258475328841L;

    public CertificadoDigitalException(String message) {
        super(message);
    }
}
