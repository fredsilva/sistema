package br.gov.to.sefaz.util.exception;

import br.gov.to.sefaz.exception.HandledSystemException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Classe utilitária para manipulações de exceção.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 07/06/2016 10:49:00
 */
public class ExceptionUtils {

    /**
     * Verifica se uma exceção foi causada por uma {@link HandledSystemException}.
     *
     * @param throwable exceção ha ser verificada.
     * @return se a exceção foi causada por uma exceção tratada do sistema.
     */
    public static boolean isCausedByUnknownException(Throwable throwable) {
        Throwable cause = throwable.getCause();

        while (cause != null) {
            if (cause instanceof HandledSystemException) {
                return false;
            }

            cause = cause.getCause();
        }

        return true;
    }

    /**
     * Retorna uma lista com todos os passos (classe, método e linha) que a axceção fez entre as classes do sistema.
     *
     * @param exception exceção a ser analisada.
     * @return passos feitos entre classes do sistema.
     */
    public static List<String> getSystemStackTrace(Throwable exception) {
        return getSystemStackTrace(exception, new ArrayList<>());
    }

    /**
     * Método recursivo para listar todos os passos (classe, método e linha) que a axceção fez entre as classes
     * do sistema.
     *
     * @param exception exceção a ser analisada.
     * @param systemStack lista de passos.
     * @return passos feitos entre classes do sistema.
     */
    private static List<String> getSystemStackTrace(Throwable exception, List<String> systemStack) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        Matcher matcher = Pattern.compile("^(\\w+\\.){4}").matcher(ExceptionUtils.class.getPackage().getName());
        matcher.find();
        String sysPackage = matcher.group();

        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().startsWith(sysPackage)) {
                systemStack.add(element.toString());
            }
        }

        if (!Objects.isNull(exception.getCause())) {
            return getSystemStackTrace(exception.getCause(), systemStack);
        }

        return systemStack;
    }

    /**
     * Identifica os módulos do sistema a partir de uma lista de pacotes do sistema.
     *
     * @param systemPackages pacotes das classes cujo os módulos serão identificados
     * @return módulos dos pacotes informados
     */
    public static Set<String> getSystemModules(List<String> systemPackages) {
        return systemPackages.stream()
                .map(s -> s.split("\\.")[4].toUpperCase())
                .collect(Collectors.toSet());
    }

    /**
     * Extrai o stacktrace de uma exceção em formato string.
     *
     * @param exception exceção ao qual o stacktrace será extraido
     * @return stack trace em formato string da exceção informada
     */
    public static String stackTraceToString(Throwable exception) {
        StringWriter errors = new StringWriter();
        exception.printStackTrace(new PrintWriter(errors));

        return errors.toString();
    }
}
