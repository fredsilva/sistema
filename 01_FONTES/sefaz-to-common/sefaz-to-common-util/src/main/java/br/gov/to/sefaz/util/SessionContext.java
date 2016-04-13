package br.gov.to.sefaz.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Utilitário para manipular atributos do FacesContext.
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public final class SessionContext {

    protected SessionContext() {
        // Construtor private para impedir esta classe seja instanciada.
        // A instância da mesma deve ser obtida pelo méto getInstance().
    }

    /**
     * Recupera a instância do SessionContext.
     * Haverá apenas uma instância desta classe na memória.
     *
     * @return instância do FacesContextUtil
     */
    public static SessionContext getInstance() {
        return SessionContextHolder.INSTANCE;
    }

    private ExternalContext currentExternalContext() {
        return FacesContext.getCurrentInstance().getExternalContext();
    }

    /**
     * Encerra a sessão do SessionContext.
     */
    public void encerrarSessao() {
        currentExternalContext().invalidateSession();
    }

    /**
     * Recupera o valor do atributo. Caso o atributo não exista retorna null.
     *
     * @param nome do atributo da sessão
     * @return o valor do atributo da sessão
     */
    public Object getAttribute(String nome) {
        return currentExternalContext().getSessionMap().get(nome);
    }

    /**
     * Atritbui um valor para o atributo. Se o atributo existe altera o valor, caso contrário cria um novo atributo.
     *
     * @param nome do atributo
     * @param valor do atributo
     */
    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }

    /**
     * Classe que gerência a instancia do {@link SessionContext}.
     */
    private static class SessionContextHolder {
        private static final SessionContext INSTANCE = new SessionContext();
    }
}
