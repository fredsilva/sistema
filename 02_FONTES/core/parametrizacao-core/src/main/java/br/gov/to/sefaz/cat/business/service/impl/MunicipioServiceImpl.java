package br.gov.to.sefaz.cat.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.cat.business.service.MunicipioService;
import br.gov.to.sefaz.cat.persistence.entity.Municipio;
import br.gov.to.sefaz.cat.persistence.repository.MunicipioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Serviços para manipulação de {@link Municipio}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 21/05/2016 16:26:41
 */
@Service
public class MunicipioServiceImpl extends DefaultCrudService<Municipio, Integer> implements MunicipioService {

    @Autowired
    public MunicipioServiceImpl(MunicipioRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "nomeMunicipio")));
    }

    @Override
    protected MunicipioRepository getRepository() {
        return (MunicipioRepository) super.getRepository();
    }

    @Override
    public Collection<Municipio> findByUF(String uf) {
        return getRepository().findByUF(uf);
    }
}
