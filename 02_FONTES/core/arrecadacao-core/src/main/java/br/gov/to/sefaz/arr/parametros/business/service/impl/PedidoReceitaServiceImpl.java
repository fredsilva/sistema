package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoReceitaService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceita;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoReceitaPK;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoReceitaRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementação de um {@link br.gov.to.sefaz.arr.parametros.business.service.PedidoReceitaService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 20:31:00
 */
@Service
public class PedidoReceitaServiceImpl extends DefaultCrudService<PedidoReceita, PedidoReceitaPK>
        implements PedidoReceitaService {

    @Autowired
    public PedidoReceitaServiceImpl(
            PedidoReceitaRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idTipoPedido")));
    }

    @Override
    protected PedidoReceitaRepository getRepository() {
        return (PedidoReceitaRepository) super.getRepository();
    }

    @Override
    public void deleteAllPedidoReceitaByIdTipoPedido(Integer idTipoPedido) {
        getRepository().deleteAllPedidoReceitaByIdTipoPedido(idTipoPedido);
    }

    @Override
    public Collection<PedidoReceita> getPedidoReceitaByIdTipoPedido(Integer idTipoPedido) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .equalsTo("idTipoPedido", idTipoPedido)
                .build(), getDefaultSort());
    }

    @Override
    public int updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao) {
        return getRepository().updateSituacaoByIdTipoPedido(idTipoPedido, situacao);
    }
}
