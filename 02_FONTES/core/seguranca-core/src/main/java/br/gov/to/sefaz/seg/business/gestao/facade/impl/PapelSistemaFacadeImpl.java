package br.gov.to.sefaz.seg.business.gestao.facade.impl;

import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.facade.PapelSistemaFacade;
import br.gov.to.sefaz.seg.business.gestao.service.OpcaoAplicacaoService;
import br.gov.to.sefaz.seg.business.gestao.service.PapelOpcaoService;
import br.gov.to.sefaz.seg.business.gestao.service.PapelSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.PerfilSistemaService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PapelSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcao;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/** Implementação da fachada {@link PapelSistemaFacade}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 22/07/2016 15:07:00
 */
@Component
public class PapelSistemaFacadeImpl extends DefaultCrudFacade<PapelSistema, Long> implements PapelSistemaFacade {

    private final OpcaoAplicacaoService opcaoAplicacaoService;
    private final PapelOpcaoService papelOpcaoService;
    private final PerfilSistemaService perfilSistemaService;

    @Autowired
    public PapelSistemaFacadeImpl(PapelSistemaService service, OpcaoAplicacaoService opcaoAplicacaoService,
            PapelOpcaoService papelOpcaoService, PerfilSistemaService perfilSistemaService) {
        super(service);
        this.opcaoAplicacaoService = opcaoAplicacaoService;
        this.papelOpcaoService = papelOpcaoService;
        this.perfilSistemaService = perfilSistemaService;
    }

    @Override
    protected PapelSistemaService getService() {
        return (PapelSistemaService) super.getService();
    }

    @Override
    public Collection<PapelSistema> findByFilter(PapelSistemaFilter filter) {
        return getService().findAllPerfilPapelOpcao(filter);
    }

    @Override
    public List<PapelSistema> findAllPapelSistema() {
        return getService().findAllPerfilPapelOpcao(new PapelSistemaFilter(StringUtils.EMPTY));
    }

    @Override
    public List<OpcaoAplicacao> findAllOpcaoAplicacao() {
        return opcaoAplicacaoService.findAllOpcoes();
    }

    @Override
    public PapelSistema saveOrUpdatePapelSistema(PapelSistema dto) {
        return getService().saveOrUpdatePapelSistema(dto);
    }

    @Override
    public PapelSistema findOneCounted(Long id) {
        return getService().findOneCounted(id);
    }

    @Override
    public Set<PapelOpcao> findAllPapelOpcaoById(Long idPapel) {
        return papelOpcaoService.findAllPapelOpcao(idPapel);
    }

    @Override
    public Collection<PerfilSistema> findAllPerfilByPapel(Long id) {
        return perfilSistemaService.findAllPerfilSistemaByPapel(id);
    }
}
