package br.gov.to.sefaz.business.service.handler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Componente para manipulação e tratamento de logicas de paginação.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Component
public class PaginationHandler {

    public boolean shouldExecuteSameQueryInLastPage(Page<?> result) {
        return isUserAfterOrOnLastPage(result) && hasDatanDataBase(result);
    }

    private boolean isUserAfterOrOnLastPage(Page<?> result) {
        return result.getNumber() >= result.getTotalPages();
    }

    private boolean hasDatanDataBase(Page<?> result) {
        return result.getTotalElements() > 0;
    }

    public PageRequest getLastPage(Page<?> result) {
        return new PageRequest(result.getTotalPages() - 1, result.getSize(), result.getSort());
    }
}
