package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.ReceitasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.PlanoContasService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasRepasseService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasTaxasService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.ReceitasFilter;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PlanoContas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasRepasse;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação da fachada da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 17:28:00
 */
@Component
public class ReceitasFacadeImpl extends DefaultCrudFacade<Receitas, Integer> implements ReceitasFacade {

    private final ReceitasService service;
    private final PlanoContasService planoContasService;
    private final ReceitasTaxasService receitasTaxasService;
    private final ReceitasRepasseService receitasRepasseService;

    @Autowired
    public ReceitasFacadeImpl(ReceitasService service, PlanoContasService planoContasService,
            ReceitasTaxasService receitasTaxasService, ReceitasRepasseService receitasRepasseService) {
        super(service);
        this.service = service;
        this.planoContasService = planoContasService;
        this.receitasTaxasService = receitasTaxasService;
        this.receitasRepasseService = receitasRepasseService;
    }

    @Override
    public List<Receitas> find(ReceitasFilter filter) {
        return service.find(filter);
    }

    @Override
    public List<PlanoContas> getAllPlanoContas() {
        return planoContasService.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Collection<ReceitasTaxas> getReceitasTaxasByIdReceita(Integer idReceita) {
        return receitasTaxasService.getReceitasTaxasByIdReceita(idReceita);
    }

    @Override
    public Collection<ReceitasRepasse> getReceitasRepasseByIdReceita(Integer idReceita) {
        return receitasRepasseService.getReceitasRepasseByIdReceita(idReceita);
    }
}
