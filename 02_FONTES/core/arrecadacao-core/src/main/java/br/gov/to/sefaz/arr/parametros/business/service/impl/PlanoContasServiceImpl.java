package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoContaEnum;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PlanoContasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço da entidade PlanoContas.
 *
 * @author <a href="mailto:roger.golveia@ntconsult.com.br">roger.golveia</a>
 * @since 19/04/2016 10:51:00
 */
@Service
public class PlanoContasServiceImpl extends DefaultCrudService<PlanoContas, Long> implements PlanoContasService {

    private final PlanoContasRepository repository;

    @Autowired
    public PlanoContasServiceImpl(PlanoContasRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<PlanoContas> find(String codigoPlano, String nomePlano, String codigoContabil,
            TipoContaEnum tipoConta) {
        return repository.find(codigoPlano, nomePlano, codigoContabil, tipoConta);
    }

}
