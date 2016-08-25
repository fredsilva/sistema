package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.ResumoStr;
import br.gov.to.sefaz.arr.persistence.entity.ResumoStrPK;
import br.gov.to.sefaz.arr.persistence.repository.ResumoStrRepository;
import br.gov.to.sefaz.arr.processamento.service.ResumoStrService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço {@link br.gov.to.sefaz.arr.processamento.service.ResumoStrService} da entidade
 * {@link br.gov.to.sefaz.arr.persistence.entity.ResumoStr}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 13/07/2016 17:34:00
 */
@Service
public class ResumoStrServiceImpl extends DefaultCrudService<ResumoStr, ResumoStrPK> implements ResumoStrService {

    @Autowired
    public ResumoStrServiceImpl(ResumoStrRepository repository) {
        super(repository);
    }

}
