package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.ConveniosArrecFacade;
import br.gov.to.sefaz.arr.parametros.business.service.BancoAgenciasService;
import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.filter.ConveniosArrecFilter;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosTarifas;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Implementação da fachada da entidade {@link ConveniosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 18:13:54
 */
@Component
public class ConveniosArrecFacadeImpl extends DefaultCrudFacade<ConveniosArrec, Long> implements ConveniosArrecFacade {

    private final ConveniosArrecService service;
    private final BancosService bancosService;
    private final BancoAgenciasService bancoAgenciasService;
    private final ConveniosTarifasService conveniosTarifasService;
    private final ReceitasService receitasService;
    private final ConveniosReceitasService conveniosReceitasService;

    @Autowired
    public ConveniosArrecFacadeImpl(ConveniosArrecService service, BancosService bancosService,
            BancoAgenciasService bancoAgenciasService, ConveniosTarifasService conveniosTarifasService,
            ReceitasService receitasService, ConveniosReceitasService conveniosReceitasService) {
        super(service);
        this.service = service;
        this.bancosService = bancosService;
        this.bancoAgenciasService = bancoAgenciasService;
        this.conveniosTarifasService = conveniosTarifasService;
        this.receitasService = receitasService;
        this.conveniosReceitasService = conveniosReceitasService;
    }

    @Override
    public Collection<Bancos> getAllActiveBancos() {
        return bancosService.findAllActiveBancos();
    }

    @Override
    public Collection<BancoAgencias> getAllActiveBancoAgenciasFromIdBanco(Integer idBanco) {
        return bancoAgenciasService.getAllActiveBancoAgenciasFromIdBanco(idBanco);
    }

    @Override
    public Collection<Receitas> getAllActiveReceitas() {
        return receitasService.findAllActiveReceitas();
    }

    @Override
    public Collection<ConveniosTarifas> getAllConveniosTarifasByIdConvenioArrec(Long idConvenio) {
        return conveniosTarifasService.getAllConveniosTarifasByIdConvenioArrec(idConvenio);
    }

    @Override
    public Collection<Receitas> getAllReceitasByIdConvenio(Long idConvenio) {
        return receitasService.getAllReceitasByIdConvenio(idConvenio);
    }

    @Override
    public void validateTarifa(ConveniosArrec conveniosArrec, ConveniosTarifas conveniosTarifas) {
        conveniosTarifasService.validateDuplicatedTarifa(conveniosArrec, conveniosTarifas);
        conveniosTarifasService.validateDataFimTarifa(conveniosTarifas);
    }

    @Override
    public void validateReceita(ConveniosArrec conveniosArrec, Receitas receita) {
        conveniosReceitasService.validateDuplicatedReceita(conveniosArrec, receita);
    }

    @Override
    public List<ConveniosArrec> find(ConveniosArrecFilter filter) {
        return service.find(filter);
    }

}
