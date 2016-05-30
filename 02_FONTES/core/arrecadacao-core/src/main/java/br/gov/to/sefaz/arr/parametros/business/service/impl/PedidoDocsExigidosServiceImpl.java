package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoDocsExigidosService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidos;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoDocsExigidosPK;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoDocsExigidosRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idTipoPedido")));
    }

    @Override
    protected PedidoDocsExigidosRepository getRepository() {
        return (PedidoDocsExigidosRepository) super.getRepository();
    }

    @Override
    public Collection<PedidoDocsExigidos> getPedidoDocsExigidosByIdTipoPedido(Integer idTipoPedido) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .equalsTo("idTipoPedido", idTipoPedido)
                .build(), getDefaultSort());
    }

    @Override
    public void deleteAllDocsExigidosByIdTipoPedido(Integer idTipoPedido) {
        getRepository().deleteAllDocsExigidosByIdTipoPedido(idTipoPedido);
    }

    @Override
    public int updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao) {
        return getRepository().updateSituacaoByIdTipoPedido(idTipoPedido, situacao);
    }

}
