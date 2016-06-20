package br.gov.to.sefaz.exception;

import br.gov.to.sefaz.seg.business.authentication.handler.AuthenticatedUserHandler;
import br.gov.to.sefaz.util.exception.ExceptionUtils;
import br.gov.to.sefaz.util.json.JsonMapperUtils;
import br.gov.to.sefaz.util.message.SourceBundle;
import br.gov.to.sefaz.util.properties.AppProperties;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

/**
 * Tratador de exceções do sistema, intercepta todas as exceções que não são subclasses de
 * {@link HandledSystemException} e gera logs com dados necessários para identificação do erro e sua causa.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 31/05/2016 15:24:00
 */
public class SatExceptionHandler extends ExceptionHandlerWrapper {

    public static final String LOG_NAME_PATTERN = "LogErro-%s.log";
    private final ExceptionHandler wrapped;
    private String logDir;

    public SatExceptionHandler(
            ExceptionHandler wrapped) {
        this.wrapped = wrapped;
        logDir = AppProperties.getProperty("exception.log.directory").get();
        if (StringUtils.isEmpty(logDir)) {
            logDir = System.getProperty("jboss.server.log.dir");
        }
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public Collection<ExceptionQueuedEvent> getUnhandledExceptionQueuedEvents() {
        return (Collection<ExceptionQueuedEvent>) super.getUnhandledExceptionQueuedEvents();
    }

    @Override
    public void handle() throws FacesException {
        final Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator();

        List<String> eventIds = new ArrayList<>();
        while (i.hasNext()) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

            if (ExceptionUtils.isCausedByUnknownException(context.getException())) {
                String eventId = UUID.randomUUID().toString();

                logUnknownException(context, eventId);
                eventIds.add(eventId);
                i.remove();
            }
        }

        eventIds.forEach(this::addUnexpectedEvent);

        getWrapped().handle();
    }

    private void logUnknownException(ExceptionQueuedEventContext context, String eventId) {
        String cpf = "00000000000";
        if (AuthenticatedUserHandler.isAuthenticated()) {
            cpf = AuthenticatedUserHandler.getCpf();
        }

        LocalDateTime dateTime = LocalDateTime.now();
        Throwable exception = context.getException();

        String applicationServer = AppProperties.getServerInfo();
        List<String> systemStack = ExceptionUtils.getSystemStackTrace(exception);
        Set<String> systemModules = ExceptionUtils.getSystemModules(systemStack);
        String stackTrace = ExceptionUtils.stackTraceToString(exception);

        UnhandledError unhandledError = new UnhandledError(cpf, dateTime, applicationServer, systemModules, systemStack,
                stackTrace);
        String value = JsonMapperUtils.objectToJson(unhandledError);

        String logName = String.format(LOG_NAME_PATTERN, eventId);

        try {
            Files.createDirectories(Paths.get(logDir));
            Files.write(Paths.get(logDir).resolve(logName), value.getBytes());
        } catch (IOException e) {
            throw new UnexpectedErrorException("Erro ao gerar log de erro!", e);
        }
    }

    private void addUnexpectedEvent(String eventId) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        String message = SourceBundle.getMessage("satExceptionHandler.unexpectedErrorException");
        UnexpectedErrorException unexpectedException = new UnexpectedErrorException(String.format(message, eventId));
        ExceptionQueuedEventContext eventContext = new ExceptionQueuedEventContext(facesContext, unexpectedException);

        getUnhandledExceptionQueuedEvents().add(new ExceptionQueuedEvent(eventContext));
    }

}