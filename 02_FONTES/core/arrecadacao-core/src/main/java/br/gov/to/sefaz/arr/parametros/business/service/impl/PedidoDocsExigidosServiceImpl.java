package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoDocsExigidosService;
import br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.persistence.entity.PedidoDocsExigidosPK;
import br.gov.to.sefaz.arr.persistence.repository.PedidoDocsExigidosRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementação de um {@link br.gov.to.sefaz.arr.parametros.business.service.PedidoDocsExigidosService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 15:59:00
 */
@Service
public class PedidoDocsExigidosServiceImpl extends DefaultCrudService<PedidoDocsExigidos, PedidoDocsExigidosPK>
        implements PedidoDocsExigidosService {

    @Autowired
    public PedidoDocsExigidosServiceImpl(
            PedidoDocsExigidosRepository repository) {
        super(repository);
    }

    @Override
    protected PedidoDocsExigidosRepository getRepository() {
        return (PedidoDocsExigidosRepository) super.getRepository();
    }

    @Override
    public Collection<PedidoDocsExigidos> getPedidoDocsExigidosByIdTipoPedido(Integer idTipoPedido) {
        return getRepository().find(sb -> sb.where().equal("idTipoPedido", idTipoPedido).orderById());
    }

    @Override
    public void deleteAllDocsExigidosByIdTipoPedido(Integer idTipoPedido) {
        getRepository().deleteAllDocsExigidosByIdTipoPedido(idTipoPedido);
    }

    @Override
    public void updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao) {
        getRepository().updateSituacaoByIdTipoPedido(idTipoPedido, situacao);
    }

}
