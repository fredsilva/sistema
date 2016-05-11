package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.TipoGruposCnaeService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.TipoGruposCnaes;
import br.gov.to.sefaz.arr.parametros.persistence.repository.TipoGruposCnaeRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço da entidade PlanoContas.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Service
public class TipoGruposCnaeServiceImpl
        extends DefaultCrudService<TipoGruposCnaes, Integer>
        implements TipoGruposCnaeService {

    @Autowired
    public TipoGruposCnaeServiceImpl(TipoGruposCnaeRepository repository) {
        super(repository);
    }

}
