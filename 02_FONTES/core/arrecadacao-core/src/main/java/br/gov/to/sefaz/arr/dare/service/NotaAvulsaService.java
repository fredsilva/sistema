package br.gov.to.sefaz.arr.dare.service;

import br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter;
import br.gov.to.sefaz.arr.persistence.view.NotaAvulsa;

import java.util.List;

/**
 * Interface que define os métodos de serviço para a view
 * {@link br.gov.to.sefaz.arr.persistence.view.NotaAvulsa}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:47:00
 */
public interface NotaAvulsaService {

    /**
     * Busca uma lista de {@link br.gov.to.sefaz.arr.persistence.view.NotaAvulsa} conforme o
     * {@link br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter}.
     *
     * @param debitoParcialFilter contém os filtros para identificação de
     *                            {@link br.gov.to.sefaz.arr.persistence.view.NotaAvulsa}.
     * @return a lista com as notas avulsas encontradas.
     */
    List<NotaAvulsa> searchNotasAvulsasByDebitoParcialFilter(DebitoParcialFilter debitoParcialFilter);
}
