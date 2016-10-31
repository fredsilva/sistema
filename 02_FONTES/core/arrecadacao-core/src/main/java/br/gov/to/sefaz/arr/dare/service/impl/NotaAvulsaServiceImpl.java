package br.gov.to.sefaz.arr.dare.service.impl;

import br.gov.to.sefaz.arr.dare.service.NotaAvulsaService;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter;
import br.gov.to.sefaz.arr.persistence.repository.NotaAvulsaRepository;
import br.gov.to.sefaz.arr.persistence.view.NotaAvulsa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço que implementa {@link br.gov.to.sefaz.arr.dare.service.NotaAvulsaService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:47:00
 */
@Service
public class NotaAvulsaServiceImpl implements NotaAvulsaService {

    private final NotaAvulsaRepository repository;

    @Autowired
    public NotaAvulsaServiceImpl(NotaAvulsaRepository repository) {
        this.repository = repository;
    }

    public NotaAvulsaRepository getRepository() {
        return repository;
    }

    @Override
    public List<NotaAvulsa> searchNotasAvulsasByDebitoParcialFilter(DebitoParcialFilter debitoParcialFilter) {
        return repository.find(select -> select
                .where()
                .equal("idNfa", debitoParcialFilter.getIdDocumento())
                .and()
                .equal("idEmitente", debitoParcialFilter.getIdContribuinte()));
    }
}
