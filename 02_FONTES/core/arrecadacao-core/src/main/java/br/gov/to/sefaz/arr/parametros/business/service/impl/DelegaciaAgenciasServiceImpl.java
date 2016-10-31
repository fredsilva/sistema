package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.DelegaciaAgenciasService;
import br.gov.to.sefaz.arr.persistence.entity.DelegaciaAgencias;
import br.gov.to.sefaz.arr.persistence.entity.DelegaciaAgenciasPK;
import br.gov.to.sefaz.arr.persistence.repository.DelegaciaAgenciasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementação de um {@link DelegaciaAgenciasService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 24/05/2016 18:42:00
 */
@Service
public class DelegaciaAgenciasServiceImpl extends DefaultCrudService<DelegaciaAgencias, DelegaciaAgenciasPK>
        implements DelegaciaAgenciasService {

    @Autowired
    public DelegaciaAgenciasServiceImpl(DelegaciaAgenciasRepository repository) {
        super(repository);
    }

    @Override
    public Collection<DelegaciaAgencias> findAllByDelegacia(Integer idDelegacia) {
        return getRepository().find(sb -> sb.where().equal("idDelegacia", idDelegacia));
    }
}
