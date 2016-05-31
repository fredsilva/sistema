package br.gov.to.sefaz.cat.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.cat.business.service.AtividadeEconomicaService;
import br.gov.to.sefaz.cat.persistence.entity.AtividadeEconomica;
import br.gov.to.sefaz.cat.persistence.repository.AtividadeEconomicaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Serviços para manipulação de {@link AtividadeEconomica} (CNAE's).
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/05/2016 11:33:00
 */
@Service
public class AtividadeEconomicaServiceImpl extends DefaultCrudService<AtividadeEconomica, String>
        implements AtividadeEconomicaService {

    @Autowired
    public AtividadeEconomicaServiceImpl(
            AtividadeEconomicaRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "codigoCnae")));
    }

    @Override
    protected AtividadeEconomicaRepository getRepository() {
        return (AtividadeEconomicaRepository) super.getRepository();
    }

    @Override
    public Collection<AtividadeEconomica> findAllCnaesByGrupo(Integer idGrupoCnae) {
        return getRepository().findAllCnaesByGrupo(idGrupoCnae);
    }
}
