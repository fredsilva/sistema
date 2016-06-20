package br.gov.to.sefaz.util.session;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Classe singlelton utilitária para manipular sessão. Seta e consulta valores de atributos da sessão. Encerra a sessão
 * do usuário.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 06/05/2016 14:49:30
 */
public class SessionContextUtil {

    /**
     * Retorna uma instância de {@link SessionContextUtil}.
     *
     * @return Instância de {@link SessionContextUtil}
     */
    public static SessionContextUtil getInstance() {
        return SessionContextHolder.sessionData;
    }

    /**
     * Construtor protected para evitar instância. A instância da presente classe, por ser singlelton, deve ser obtida
     * pelo método {@link #getInstance()}.
     */
    protected SessionContextUtil() {
        // Construtor private classe singlelton
    }

    /**
     * Método private que retorna o {@link ExternalContext}.
     *
     * @return {@link ExternalContext}
     * @throws UnsupportedOperationException caso tenta usar o presente singleton de fora de
     *      uma requisição HTTP.
     */
    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new UnsupportedOperationException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    /**
     * Encerra a Sessão do usuário.
     */
    public void encerrarSessao() {
        currentExternalContext().invalidateSession();
    }

    /**
     * Consulta um atributo na sessão. Caso o atibuto consultado não exista retorn null.
     *
     * @param attribute Nome do atributo na sessão
     * @return Valor do atributo
     */
    public Object getAttribute(String attribute) {
        return currentExternalContext().getSessionMap().get(attribute);
    }

    /**
     * Seta valor para um atributo na sessão. Caso o atributo já exista atualiza o valor do mesmo, caso não exista cria
     * o atributo com o valor insformado.
     *
     * @param attribute Nome do atributo na sessão
     * @param value     Valor do atributo
     */
    public void setAttribute(String attribute, Object value) {
        currentExternalContext().getSessionMap().put(attribute, value);
    }

    /**
     * Holder interno que cria e mantém a instância do singleton {@link SessionContextUtil}.
     *
     * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
     * @since 06/05/2016 15:00:54
     */
    private static class SessionContextHolder {
        private static SessionContextUtil sessionData = new SessionContextUtil();
    }

}
