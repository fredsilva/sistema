package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.BancoAgenciasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.BancoAgenciasService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.BancoAgenciasPK;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.cat.business.service.EstadoService;
import br.gov.to.sefaz.cat.business.service.MunicipioService;
import br.gov.to.sefaz.cat.persistence.entity.Estado;
import br.gov.to.sefaz.cat.persistence.entity.Municipio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Implementação da fachada de Agências Bancárias.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/05/2016 11:59:00
 */
@Component
public class BancoAgenciasFacadeImpl extends DefaultCrudFacade<BancoAgencias, BancoAgenciasPK>
        implements BancoAgenciasFacade {

    private final EstadoService estadoService;

    private final MunicipioService municipioService;

    @Autowired
    public BancoAgenciasFacadeImpl(
            BancoAgenciasService service, EstadoService estadoService,
            MunicipioService municipioService) {
        super(service);
        this.estadoService = estadoService;
        this.municipioService = municipioService;
    }

    @Override
    protected BancoAgenciasService getService() {
        return (BancoAgenciasService) super.getService();
    }

    @Override
    public Collection<BancoAgencias> findByIdBanco(Integer idBanco) {
        return getService().findByIdBanco(idBanco);
    }

    @Override
    public Collection<Estado> findAllEstados() {
        return estadoService.findAll();
    }

    @Override
    public Collection<Municipio> findMunicipiosByUF(String uf) {
        return municipioService.findByUF(uf);
    }

    @Override
    public void validateSave(BancoAgencias agencia, Collection<BancoAgencias> list) {
        getService().validateSave(agencia);
        getService().validateSave(list);
    }

    @Override
    public void validateUpdate(BancoAgencias agencia, Collection<BancoAgencias> list) {
        getService().validateUpdate(agencia);
        getService().validateUpdate(list);
    }

}
