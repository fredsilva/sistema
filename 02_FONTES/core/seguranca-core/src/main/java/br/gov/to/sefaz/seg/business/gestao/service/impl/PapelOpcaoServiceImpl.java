package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.business.gestao.service.PapelOpcaoService;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcao;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcaoPK;
import br.gov.to.sefaz.seg.persistence.repository.PapelOpcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/** Implementação do {@link PapelOpcaoService}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 25/07/2016 17:10:00
 */
@Service
public class PapelOpcaoServiceImpl extends DefaultCrudService<PapelOpcao, PapelOpcaoPK>
        implements PapelOpcaoService {

    @Autowired
    public PapelOpcaoServiceImpl(BaseRepository<PapelOpcao, PapelOpcaoPK>  repository) {
        super(repository);
    }

    @Override
    protected PapelOpcaoRepository getRepository() {
        return (PapelOpcaoRepository) super.getRepository();
    }

    @Override
    public Set<PapelOpcao> findAllPapelOpcao(Long id) {
        return getRepository().findAllWithDescription(id);
    }

    @Override
    public void removeAllPapelOpcaoByPapelId(Long id) {
        getRepository().deleteAllPapelOpcaoByPapelId(id);
    }
}
