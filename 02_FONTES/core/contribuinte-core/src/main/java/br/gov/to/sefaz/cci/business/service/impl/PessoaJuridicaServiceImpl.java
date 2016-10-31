package br.gov.to.sefaz.cci.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.cci.business.service.PessoaJuridicaService;
import br.gov.to.sefaz.cci.persistence.entity.PessoaJuridica;
import br.gov.to.sefaz.cci.persistence.repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.cci.persistence.entity.PessoaJuridica}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 30/08/2016 14:35:00
 */
@Service
public class PessoaJuridicaServiceImpl extends DefaultCrudService<PessoaJuridica, String> implements
        PessoaJuridicaService {

    @Autowired
    public PessoaJuridicaServiceImpl(PessoaJuridicaRepository repository) {
        super(repository);
    }
}
