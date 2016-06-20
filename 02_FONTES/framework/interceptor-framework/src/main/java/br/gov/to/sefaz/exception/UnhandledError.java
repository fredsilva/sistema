package br.gov.to.sefaz.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Objeto para representação das informações de um erro não tratado pela aplicação.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 03/06/2016 19:30:00
 */
public class UnhandledError {

    private final String cpf;
    private final LocalDateTime dateTime;
    private final String applicationServer;
    private final Set<String> systemModules;
    private final List<String> systemStack;
    private final String stackTrace;

    public UnhandledError(String cpf, LocalDateTime dateTime, String applicationServer,
            Set<String> systemModules, List<String> systemStack, String stackTrace) {
        this.cpf = cpf;
        this.dateTime = dateTime;
        this.applicationServer = applicationServer;
        this.systemModules = systemModules;
        this.systemStack = systemStack;
        this.stackTrace = stackTrace;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getApplicationServer() {
        return applicationServer;
    }

    public Set<String> getSystemModules() {
        return systemModules;
    }

    public List<String> getSystemStack() {
        return systemStack;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    @Override
    public String toString() {
        return "UnhandledError{"
                + "cpf='" + cpf + '\''
                + ", dateTime=" + dateTime
                + ", applicationServer='" + applicationServer + '\''
                + ", systemModules='" + systemModules + '\''
                + ", systemStacks=" + systemStack
                + ", stackTrace='" + stackTrace + '\''
                + '}';
    }
}
