package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.ManutencaoCadastroFuncionalidadeFacade;
import br.gov.to.sefaz.seg.business.gestao.service.AplicacaoModuloService;
import br.gov.to.sefaz.seg.business.gestao.service.ModuloSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.OpcaoAplicacaoService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.OpcaoAplicacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.AplicacaoModulo;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Fachada da entidade.
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 19/07/2016 14:50:00
 */
@Component
public class ManutencaoCadastroFuncionalidadeFacadeImpl extends DefaultCrudFacade<OpcaoAplicacao, Long> implements
        ManutencaoCadastroFuncionalidadeFacade {

    private final ModuloSistemaService moduloSistemaService;
    private final AplicacaoModuloService aplicacaoModuloService;


    @Autowired
    public ManutencaoCadastroFuncionalidadeFacadeImpl(OpcaoAplicacaoService service,
            ModuloSistemaService moduloSistemaService, AplicacaoModuloService aplicacaoModuloService) {
        super(service);
        this.moduloSistemaService = moduloSistemaService;
        this.aplicacaoModuloService = aplicacaoModuloService;
    }


    @Override
    public Collection<ModuloSistema> findAllModuloSistema() {
        return this.moduloSistemaService.findAllSortedByAbreviacao();
    }

    @Override
    public Collection<AplicacaoModulo> findAllAplicacaoModulo() {
        return this.aplicacaoModuloService.findAll();
    }

    @Override
    public Collection<AplicacaoModulo> findAplicacoesPorModulo(Long identificacaoModuloSistema) {
        return aplicacaoModuloService.findByModuloSistema(identificacaoModuloSistema);
    }

    @Override
    protected OpcaoAplicacaoService getService() {
        return (OpcaoAplicacaoService) super.getService();
    }

    @Override
    public List<OpcaoAplicacao> find(OpcaoAplicacaoFilter filter) {
        return getService().findByFilter(filter);
    }

}
