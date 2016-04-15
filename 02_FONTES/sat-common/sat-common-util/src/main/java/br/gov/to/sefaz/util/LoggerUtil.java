package br.gov.to.sefaz.util;

import java.text.MessageFormat;

/**
 * Classe utilitaria para manipular o log da aplicacao.
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public class LoggerUtil {

    /**
     * Instancia real do logger.
     */
    private org.apache.log4j.Logger logger;

    /**
     * Retorna a instancia do Logger. O parametro deste metodo deve ser
     * <b>getClass().getName()</b>.
     *
     * @param className Nome da classe que esta querendo o Logger
     * @return Instancia do Logger
     */
    public static LoggerUtil getLogger(String className) {
        LoggerUtil result = new LoggerUtil();
        result.logger = org.apache.log4j.Logger.getLogger(className);
        return result;
    }

    /**
     * Retorna a instancia do Logger. O parametro deste metodo deve ser
     * <b>getClass()</b>.
     *
     * @param clazz Classe que esta querendo o Logger
     * @return Instancia do Logger
     */
    public static LoggerUtil getLogger(Class<?> clazz) {
        LoggerUtil result = new LoggerUtil();
        result.logger = org.apache.log4j.Logger.getLogger(clazz);
        return result;
    }

    /**
     * Escreve uma informacao de debug no log.
     *
     * @param message Mensagem que deve ser escrita
     */
    public void debug(Object message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    /**
     * Escreve uma informacao de debug no log.
     *
     * @param message Mensagem que deve ser escrita
     */
    public void debug(Object message, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(formatLogMessage(message, params));
        }
    }

    /**
     * Escreve uma informacao de debug no log.
     *
     * @param message Mensagem que deve ser escrita
     * @param t       Excecao que tambem deve ser logada
     */
    public void debug(Object message, Throwable t, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(formatLogMessage(message, params), t);
        }
    }

    /**
     * Verifica se o debug esta habilitado. Essa checagem e importante para que o
     * log seja feito de maneira mais eficiente. Deve sempre ser feita antes de
     * escrever uma linha de debug, principalmente se concatenacao de Strings
     * estiver envolvida na mensagem a ser logada.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Escreve uma informacao de erro no log.
     *
     * @param message Mensagem que deve ser escrita
     */
    public void error(Object message, Object... params) {
        logger.error(formatLogMessage(message, params));
    }

    /**
     * Escreve uma informacao de erro no log. Uma excecao associada tambem pode
     * ser escrita.
     *
     * @param message Mensagem que deve ser escrita
     * @param t       Excecao que tambem deve ser logada
     */
    public void error(Object message, Throwable t, Object... params) {
        logger.error(formatLogMessage(message, params), t);
    }

    /**
     * Escreve uma mensagem do tipo INFO no log.
     *
     * @param message Mensagem do tipo INFO a ser escrita
     */
    public void info(Object message, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(formatLogMessage(message, params));
        }
    }

    /**
     * Verifica se a escrita de mensagens do tipo INFO esta ativada.
     *
     * @return Se a escrita do tipo INFO esta ativada
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Escreve uma mensagem do tipo WARN no log.
     *
     * @param message Mensagem do tipo WARN a ser escrita
     */
    public void warn(Object message) {
        logger.warn(message);
    }

    /**
     * Escreve uma mensagem do tipo WARN no log.
     *
     * @param message Mensagem do tipo WARN a ser escrita
     * @param params  Parametros da mensagem
     */
    public void warn(Object message, Object... params) {
        logger.warn(formatLogMessage(message, params));
    }

    private static String formatLogMessage(Object msg, Object... params) {

        String str = msg.toString();

        if (params != null) {
            str = MessageFormat.format(str, params);
        }

        return str;
    }

}
