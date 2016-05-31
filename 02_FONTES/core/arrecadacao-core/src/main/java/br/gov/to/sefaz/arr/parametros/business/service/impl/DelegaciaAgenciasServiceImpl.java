package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.DelegaciaAgenciasService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgenciasPK;
import br.gov.to.sefaz.arr.parametros.persistence.repository.DelegaciaAgenciasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idUnidadeDelegacia"),
                new Sort.Order(Sort.Direction.ASC, "idDelegacia")));
    }

    @Override
    public Collection<DelegaciaAgencias> findAllByDelegacia(Integer idDelegacia) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
        .equalsTo("idDelegacia", idDelegacia)
        .build());
    }
}
