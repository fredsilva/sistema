package br.gov.to.sefaz.exception;

import br.gov.to.sefaz.util.application.ApplicationUtil;
import br.gov.to.sefaz.util.exception.ExceptionUtils;
import br.gov.to.sefaz.util.json.JsonMapperUtils;
import br.gov.to.sefaz.util.message.SourceBundle;
import br.gov.to.sefaz.util.properties.AppProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;

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
        logDir = AppProperties.getAppProperty("exception.log.directory").get();
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
            ExceptionQueuedEventContext eventContext = (ExceptionQueuedEventContext) event.getSource();

            if (eventContext.getException() instanceof ViewExpiredException) {
                try {
                    HttpServletRequest request =
                            ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect(request.getContextPath() + "/public/login.jsf");
                } catch (IOException e) {
                    String eventId = UUID.randomUUID().toString();

                    logUnknownException(eventContext, eventId);
                    eventIds.add(eventId);
                }

                i.remove();
            } else if (ExceptionUtils.isCausedByUnknownException(eventContext.getException())) {
                String eventId = UUID.randomUUID().toString();

                logUnknownException(eventContext, eventId);
                eventIds.add(eventId);
                i.remove();
            }
        }

        eventIds.forEach(this::addUnexpectedEvent);

        getWrapped().handle();
    }

    private void logUnknownException(ExceptionQueuedEventContext context, String eventId) {
        String cpf = ApplicationUtil.getSafeNameAuthenticatedUser();
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
            throw new InterceptedErrorException("Erro ao gerar log de erro!", e);
        }
    }

    private void addUnexpectedEvent(String eventId) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        String message = SourceBundle.getMessage("satExceptionHandler.unexpectedErrorException");
        InterceptedErrorException unexpectedException = new InterceptedErrorException(String.format(message, eventId));
        ExceptionQueuedEventContext eventContext = new ExceptionQueuedEventContext(facesContext, unexpectedException);

        getUnhandledExceptionQueuedEvents().add(new ExceptionQueuedEvent(eventContext));
    }

}