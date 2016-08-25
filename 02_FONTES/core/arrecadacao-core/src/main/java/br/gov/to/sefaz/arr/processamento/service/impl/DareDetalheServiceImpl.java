package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.DareDetalhe;
import br.gov.to.sefaz.arr.persistence.entity.DareDetalhePK;
import br.gov.to.sefaz.arr.persistence.repository.DareDetalheRepository;
import br.gov.to.sefaz.arr.processamento.service.DareDetalheService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 21/07/2016 19:19:00
 */
@Service
public class DareDetalheServiceImpl extends DefaultCrudService<DareDetalhe, DareDetalhePK>
        implements DareDetalheService {

    @Autowired
    public DareDetalheServiceImpl(DareDetalheRepository repository) {
        super(repository);
    }

    @Override
    public List<DareDetalhe> findAllByNossoNumero(Long idNossoNumeroDare) {
        return getRepository().find(hqlSelectBuilder -> hqlSelectBuilder
                .where()
                .equal("idNossoNumeroDare", idNossoNumeroDare));
    }

}
