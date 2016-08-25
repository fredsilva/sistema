package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoErrosStr;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoErrosStrPK;
import br.gov.to.sefaz.arr.persistence.repository.ArquivoErrosStrRepository;
import br.gov.to.sefaz.arr.processamento.service.ArquivoErrosStrService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço de Erros de Arquivos STR {@link ArquivoErrosStrService}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 06/07/2016 18:50:00
 */
@Service
public class ArquivoErrosStrServiceImpl extends DefaultCrudService<ArquivoErrosStr, ArquivoErrosStrPK>
        implements ArquivoErrosStrService {

    @Autowired
    public ArquivoErrosStrServiceImpl(ArquivoErrosStrRepository repository) {
        super(repository);
    }

}
