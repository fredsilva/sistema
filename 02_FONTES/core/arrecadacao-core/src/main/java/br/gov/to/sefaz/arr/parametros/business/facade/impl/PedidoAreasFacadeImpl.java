package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.PedidoAreasFacade;
import br.gov.to.sefaz.arr.parametros.business.service.DelegaciaAgenciasService;
import br.gov.to.sefaz.arr.parametros.business.service.DelegaciasService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoAreasService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoAreasServidoresService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoTiposService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Delegacias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidoresPK;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Implementação de uma {@link PedidoAreasFacade}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/05/2016 10:58:00
 */
@Component
public class PedidoAreasFacadeImpl extends DefaultCrudFacade<PedidoAreas, Integer> implements PedidoAreasFacade {

    private final PedidoTiposService pedidoTiposService;
    private final DelegaciasService delegaciasService;
    private final DelegaciaAgenciasService delegaciaAgenciasService;
    private final PedidoAreasServidoresService servidoresService;
    private final UsuarioSistemaService usuarioSistemaService;

    @Autowired
    public PedidoAreasFacadeImpl(PedidoAreasService service, PedidoTiposService pedidoTiposService,
            DelegaciasService delegaciasService, DelegaciaAgenciasService delegaciaAgenciasService,
            PedidoAreasServidoresService servidoresService, UsuarioSistemaService usuarioSistemaService) {
        super(service);
        this.pedidoTiposService = pedidoTiposService;
        this.delegaciasService = delegaciasService;
        this.delegaciaAgenciasService = delegaciaAgenciasService;
        this.servidoresService = servidoresService;
        this.usuarioSistemaService = usuarioSistemaService;
    }

    @Override
    protected PedidoAreasService getService() {
        return (PedidoAreasService) super.getService();
    }

    @Override
    public Collection<PedidoTipos> findAllPedidoTipos() {
        return pedidoTiposService.findAll();
    }

    @Override
    public Collection<PedidoAreas> findAllByTipo(Integer idTipoPedido) {
        return getService().findAllByTipo(idTipoPedido);
    }

    @Override
    public Collection<Delegacias> findAllDelegacias() {
        return delegaciasService.findAll();
    }

    @Override
    public Collection<DelegaciaAgencias> findAgenciasByDelegacia(Integer idDelegacia) {
        return delegaciaAgenciasService.findAllByDelegacia(idDelegacia);
    }

    @Override
    public Collection<PedidoAreasServidores> findAllServidoresByPedido(Integer idPedidoArea) {
        return servidoresService.findAllByPedido(idPedidoArea);
    }

    @Override
    public Optional<PedidoAreasServidores> removeServidor(Integer idPedidoArea, Long idServidor) {
        return servidoresService.delete(new PedidoAreasServidoresPK(idPedidoArea, idServidor));
    }

    @Override
    public Collection<UsuarioSistema> searchServidor(Long idServidorSearchDto, String nomeServidorSearchDto) {
        return usuarioSistemaService.findAllByCpfAndName(idServidorSearchDto, nomeServidorSearchDto);
    }

    @Override
    public void validateDuplicatedServidor(List<PedidoAreasServidores> servidores) {
        servidoresService.validateDuplicatedServidor(servidores);
    }

    @Override
    public void validateServidorChefe(List<PedidoAreasServidores> servidores) {
        servidoresService.validateServidorChefe(servidores);
    }
}
