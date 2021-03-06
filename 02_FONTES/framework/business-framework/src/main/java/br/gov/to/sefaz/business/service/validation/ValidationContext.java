package br.gov.to.sefaz.business.service.validation;

/**
 * Contexto comum para as validações através do {@link ServiceValidator#support(Class, String)} e
 * {@link ValidationSuite#context()}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 29/04/2016 11:58:56
 */
public class ValidationContext {

    public static final String SAVE = "SAVE";
    public static final String UPDATE = "UPDATE";
    public static final String DELETE = "DELETE";

}
