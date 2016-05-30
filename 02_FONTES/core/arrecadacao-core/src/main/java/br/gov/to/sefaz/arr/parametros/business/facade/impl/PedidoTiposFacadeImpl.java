package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.PedidoTiposFacade;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoCamposAcoesService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoDocsExigidosService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoReceitaService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoTipoDocsService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoTiposService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasService;
import br.gov.to.sefaz.arr.parametros.business.service.ReceitasTaxasService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoDocs;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação da fachada da entidade {@link br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:50:00
 */
@Component
public class PedidoTiposFacadeImpl extends DefaultCrudFacade<PedidoTipos, Integer> implements PedidoTiposFacade {

    private final ReceitasService receitasService;
    private final ReceitasTaxasService receitasTaxasService;
    private final PedidoTipoDocsService pedidoTipoDocsService;
    private final PedidoDocsExigidosService pedidoDocsExigidosService;
    private final PedidoReceitaService pedidoReceitaService;
    private final PedidoCamposAcoesService pedidoCamposAcoesService;

    @Autowired
    public PedidoTiposFacadeImpl(
            PedidoTiposService service, ReceitasService receitasService,
            ReceitasTaxasService receitasTaxasService, PedidoTipoDocsService pedidoTipoDocsService,
            PedidoDocsExigidosService pedidoDocsExigidosService, PedidoReceitaService pedidoReceitaService,
            PedidoCamposAcoesService pedidoCamposAcoesService) {
        super(service);
        this.receitasService = receitasService;
        this.receitasTaxasService = receitasTaxasService;
        this.pedidoTipoDocsService = pedidoTipoDocsService;
        this.pedidoDocsExigidosService = pedidoDocsExigidosService;
        this.pedidoReceitaService = pedidoReceitaService;
        this.pedidoCamposAcoesService = pedidoCamposAcoesService;
    }

    @Override
    public List<Receitas> getAllReceitas() {
        return receitasService.findAllActiveReceitas().stream().collect(Collectors.toList());
    }

    @Override
    public List<ReceitasTaxas> getAllReceitasTaxasFromIdReceita(Integer idReceita) {
        return receitasTaxasService.getReceitasTaxasByIdReceita(idReceita).stream().collect(Collectors.toList());
    }

    @Override
    public List<PedidoTipoDocs> getAllPedidoTipoDoc() {
        return pedidoTipoDocsService.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Collection<PedidoDocsExigidos> getPedidoDocsExigidosByIdTipoPedido(Integer idTipoPedido) {
        return pedidoDocsExigidosService.getPedidoDocsExigidosByIdTipoPedido(idTipoPedido);
    }

    @Override
    public Collection<PedidoReceita> getPedidoReceitaByIdTipoPedido(Integer idTipoPedido) {
        return pedidoReceitaService.getPedidoReceitaByIdTipoPedido(idTipoPedido);
    }

    @Override
    public Collection<PedidoCamposAcoes> getPedidoCamposAcoesByIdTipoPedido(Integer idTipoPedido) {
        return pedidoCamposAcoesService.getPedidoCamposAcoesByIdTipoPedido(idTipoPedido);
    }

    @Override
    public List<TipoPedidoCampoEnum> getTipoPedidoCampoEnumValues(Integer idTipoPedido,
            TipoPedidoAcoesEnum tipoPedido) {
        return pedidoTipoDocsService.getTipoPedidoCampoEnumValues(idTipoPedido, tipoPedido);
    }
}
