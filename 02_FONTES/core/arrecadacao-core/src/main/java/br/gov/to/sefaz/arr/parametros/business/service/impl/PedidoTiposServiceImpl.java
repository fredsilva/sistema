package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoCamposAcoesService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoDocsExigidosService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoReceitaService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoTipoAcoesService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoTiposService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoAcoes;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipos;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoTiposRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.util.message.MessageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementação de um {@link PedidoTiposService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/05/2016 10:49:00
 */
@Service
@Transactional
public class PedidoTiposServiceImpl extends DefaultCrudService<PedidoTipos, Integer> implements PedidoTiposService {

    private final PedidoDocsExigidosService pedidoDocsExigidosService;
    private final PedidoCamposAcoesService pedidoCamposAcoesService;
    private final PedidoTipoAcoesService pedidoTipoAcoesService;
    private final PedidoReceitaService pedidoReceitaService;

    @Autowired
    public PedidoTiposServiceImpl(
            PedidoTiposRepository repository,
            PedidoDocsExigidosService pedidoDocsExigidosService, PedidoCamposAcoesService pedidoCamposAcoesService,
            PedidoTipoAcoesService pedidoTipoAcoesService, PedidoReceitaService pedidoReceitaService) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idTipoPedido")));
        this.pedidoDocsExigidosService = pedidoDocsExigidosService;
        this.pedidoCamposAcoesService = pedidoCamposAcoesService;
        this.pedidoTipoAcoesService = pedidoTipoAcoesService;
        this.pedidoReceitaService = pedidoReceitaService;
    }

    @Override
    protected PedidoTiposRepository getRepository() {
        return (PedidoTiposRepository) super.getRepository();
    }

    @Override
    public PedidoTipos save(@ValidationSuite(context = ValidationContext.SAVE) PedidoTipos pedidoTipos) {
        final PedidoTipos save = super.save(pedidoTipos);

        saveAllPedidoDocsExigidos(pedidoTipos);
        saveAllPedidoCamposAcoes(pedidoTipos);
        saveAllPedidoReceitas(pedidoTipos);

        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");
        return save;
    }

    @Override
    public PedidoTipos update(@ValidationSuite(context = ValidationContext.UPDATE) PedidoTipos pedidoTipos) {
        final PedidoTipos updatedPedidoTipos = super.update(pedidoTipos);

        saveAllPedidoDocsExigidos(pedidoTipos);
        saveAllPedidoCamposAcoes(pedidoTipos);
        saveAllPedidoReceitas(pedidoTipos);

        MessageUtil.addMesage(MessageUtil.ARR, "mensagem.sucesso.operacao");
        return updatedPedidoTipos;
    }

    private void saveAllPedidoReceitas(PedidoTipos pedidoTipos) {
        List<PedidoReceita> pedidoReceitas = pedidoTipos.getPedidoReceitas();
        Integer idTipoPedido = pedidoTipos.getIdTipoPedido();

        for (PedidoReceita pedidoReceita : pedidoReceitas) {
            pedidoReceita.setIdTipoPedido(idTipoPedido);
        }

        pedidoReceitaService.deleteAllPedidoReceitaByIdTipoPedido(idTipoPedido);
        pedidoReceitaService.save(pedidoReceitas);
    }

    private void saveAllPedidoDocsExigidos(PedidoTipos pedidoTipos) {
        List<PedidoDocsExigidos> pedidoDocsExigidos = pedidoTipos.getPedidoDocsExigidos();
        Integer idTipoPedido = pedidoTipos.getIdTipoPedido();

        for (PedidoDocsExigidos pedidoDocsExigido : pedidoDocsExigidos) {
            pedidoDocsExigido.setIdTipoPedido(idTipoPedido);
        }

        pedidoDocsExigidosService.deleteAllDocsExigidosByIdTipoPedido(idTipoPedido);
        pedidoDocsExigidosService.save(pedidoDocsExigidos);
    }

    @Override
    public Optional<PedidoTipos> delete(@ValidationSuite Integer id) {
        Optional<PedidoTipos> pedidoTipos;

        if (getRepository().existsLockReference(id)) {
            cancelarPedidoTipo(id);
            pedidoTipos = Optional.of(getRepository().findOne(id));
        } else {
            removerPedidoTipo(id);
            pedidoTipos = Optional.empty();
        }

        return pedidoTipos;
    }

    private void saveAllPedidoCamposAcoes(PedidoTipos pedidoTipos) {

        Integer idTipoPedido = pedidoTipos.getIdTipoPedido();

        pedidoCamposAcoesService.deleteAllTipoAcoesByIdTipoPedido(idTipoPedido);
        pedidoTipoAcoesService.deleteAllTipoAcoesByIdTipoPedido(idTipoPedido);

        List<PedidoCamposAcoes> pedidoCamposAcoes = pedidoTipos.getPedidoCamposAcoes();

        for (PedidoCamposAcoes pedidoCamposAcao : pedidoCamposAcoes) {
            // Cria PedidoTipoAcoes
            PedidoTipoAcoes pedidoTipoAcao = pedidoCamposAcao.getPedidoTipoAcoes();
            pedidoTipoAcao.setIdTipoPedido(idTipoPedido);
            pedidoTipoAcao.setSituacao(SituacaoEnum.ATIVO);
            pedidoTipoAcao.setTipoAcao(pedidoCamposAcao.getTipoAcao());
            pedidoTipoAcao = pedidoTipoAcoesService.save(pedidoTipoAcao);

            // Seta idAcoes em PedidoCamposAcoes
            pedidoCamposAcao.setIdAcoes(pedidoTipoAcao.getIdAcoes());
            pedidoCamposAcao.setPedidoTipoAcoes(pedidoTipoAcao);
        }
        pedidoCamposAcoesService.save(pedidoCamposAcoes);
    }

    private void cancelarPedidoTipo(Integer id) {
        getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
        pedidoDocsExigidosService.updateSituacaoByIdTipoPedido(id, SituacaoEnum.CANCELADO);
        pedidoReceitaService.updateSituacaoByIdTipoPedido(id, SituacaoEnum.CANCELADO);
        pedidoTipoAcoesService.updateSituacaoByIdTipoPedido(id, SituacaoEnum.CANCELADO);
    }

    private void removerPedidoTipo(Integer id) {
        pedidoDocsExigidosService.deleteAllDocsExigidosByIdTipoPedido(id);
        pedidoReceitaService.deleteAllPedidoReceitaByIdTipoPedido(id);
        pedidoCamposAcoesService.deleteAllTipoAcoesByIdTipoPedido(id);
        pedidoTipoAcoesService.deleteAllTipoAcoesByIdTipoPedido(id);
        getRepository().delete(id);
    }
}
