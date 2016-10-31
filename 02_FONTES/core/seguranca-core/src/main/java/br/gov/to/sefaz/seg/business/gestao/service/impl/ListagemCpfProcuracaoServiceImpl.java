package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.seg.business.gestao.service.ListagemCpfProcuracaoService;
import br.gov.to.sefaz.seg.persistence.entity.ListagemCpfProcuracao;
import br.gov.to.sefaz.seg.persistence.repository.ListagemCpfProcuracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação de um {@link ListagemCpfProcuracaoService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/09/2016 14:58:00
 */
@Service
public class ListagemCpfProcuracaoServiceImpl implements ListagemCpfProcuracaoService {

    private final ListagemCpfProcuracaoRepository repository;

    @Autowired
    public ListagemCpfProcuracaoServiceImpl(ListagemCpfProcuracaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ListagemCpfProcuracao> findByCpf(String cpf) {
        return repository.find(select -> select.where().equal("cpfFiltro", cpf));
    }
}
