package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.AgenciasService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.arr.parametros.persistence.repository.AgenciasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço de Agencias Bancárias.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
@Service
public class AgenciasServiceImpl extends DefaultCrudService<BancoAgencias, BancoAgenciasPK>
        implements AgenciasService {

    @Autowired
    public AgenciasServiceImpl(AgenciasRepository repository) {
        super(repository);
    }

}
