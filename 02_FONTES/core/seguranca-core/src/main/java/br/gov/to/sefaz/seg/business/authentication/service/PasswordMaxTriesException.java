package br.gov.to.sefaz.seg.business.authentication.service;

/**
 * Exceção enviada caso um usuário errado a senha mais vezes do que o sistema permite.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/06/2016 16:09:00
 */
public class PasswordMaxTriesException extends Exception {
    private static final long serialVersionUID = 5936562391940242402L;

    public PasswordMaxTriesException(String message) {
        super(message);
    }
}
