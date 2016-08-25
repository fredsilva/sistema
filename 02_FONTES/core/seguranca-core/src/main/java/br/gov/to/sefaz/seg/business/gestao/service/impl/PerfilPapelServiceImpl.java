package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.business.gestao.service.PerfilPapelService;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapel;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapelPK;
import br.gov.to.sefaz.seg.persistence.repository.PerfilPapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementação do {@link PerfilPapelService}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 25/07/2016 17:10:00
 */
@Service
public class PerfilPapelServiceImpl extends DefaultCrudService<PerfilPapel, PerfilPapelPK>
        implements PerfilPapelService {

    @Autowired
    public PerfilPapelServiceImpl(BaseRepository<PerfilPapel, PerfilPapelPK>  repository) {
        super(repository);
    }

    @Override
    protected PerfilPapelRepository getRepository() {
        return (PerfilPapelRepository) super.getRepository();
    }

    @Override
    public void deleteAllWithPerfilId(Long id) {
        getRepository().delete("pp", delete -> delete.where().equal("pp.identificacaoPerfil", id));
    }

    @Override
    public Collection<PerfilPapel> findAllPerfilPapelByPerfil(Long id) {
        return getRepository().find("pp", select -> select.where().equal("pp.identificacaoPerfil", id));
    }
}
