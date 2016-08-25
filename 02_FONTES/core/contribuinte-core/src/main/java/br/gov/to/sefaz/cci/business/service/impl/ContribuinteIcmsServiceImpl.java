package br.gov.to.sefaz.cci.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.cci.business.service.ContribuinteIcmsService;
import br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms;
import br.gov.to.sefaz.cci.persistence.repository.ContribuinteIcmsRepository;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço para operações referentes a gerência de {@link br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 17/08/2016 17:31:00
 */
@Service
public class ContribuinteIcmsServiceImpl extends DefaultCrudService<ContribuinteIcms, String>
        implements ContribuinteIcmsService {

    @Autowired
    public ContribuinteIcmsServiceImpl(ContribuinteIcmsRepository repository) {
        super(repository);
    }

    @Override
    protected ContribuinteIcmsRepository getRepository() {
        return (ContribuinteIcmsRepository) super.getRepository();
    }

    @Override
    public boolean existsWithCnpj(String cnpjContribuinte) {
        return getRepository().exists(sb -> sb
                .where().equal("numCnpj", cnpjContribuinte));
    }

    @Override
    public ContribuinteIcms findFirstContribuinteWithCnpj(String cnpjContribuinte) {
        return getRepository().find(sb -> sb
                .where().equal("numCnpj", cnpjContribuinte)
                .orderBy("dataInicioAtividade", Order.ASC))
                .stream()
                .findFirst()
                .orElse(null);
    }
}
