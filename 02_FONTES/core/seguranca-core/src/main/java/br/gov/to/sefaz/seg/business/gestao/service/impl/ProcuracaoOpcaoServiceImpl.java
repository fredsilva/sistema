package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.seg.business.gestao.service.ProcuracaoOpcaoService;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoOpcao;
import br.gov.to.sefaz.seg.persistence.repository.ProcuracaoOpcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação de um {@link ProcuracaoOpcaoService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/08/2016 17:40:00
 */
@Service
public class ProcuracaoOpcaoServiceImpl extends DefaultCrudService<ProcuracaoOpcao, Long>
        implements ProcuracaoOpcaoService {

    @Autowired
    public ProcuracaoOpcaoServiceImpl(ProcuracaoOpcaoRepository repository) {
        super(repository);
    }

    @Override
    public void deleteByProcuracao(Long identificacaoProcurUsuario) {
        getRepository().delete(delete -> delete
                .where().equal("identificacaoProcurUsuario", identificacaoProcurUsuario));
    }
}
