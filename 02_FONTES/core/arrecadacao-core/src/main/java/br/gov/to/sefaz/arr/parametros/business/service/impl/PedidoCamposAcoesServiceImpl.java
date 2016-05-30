package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoCamposAcoesService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoCamposAcoesRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementação de um {@link PedidoCamposAcoesService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 18:33:00
 */
@Service
public class PedidoCamposAcoesServiceImpl extends DefaultCrudService<PedidoCamposAcoes, Integer>
        implements PedidoCamposAcoesService {

    @Autowired
    public PedidoCamposAcoesServiceImpl(
            PedidoCamposAcoesRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idCampoPedido")));
    }

    @Override
    protected PedidoCamposAcoesRepository getRepository() {
        return (PedidoCamposAcoesRepository) super.getRepository();
    }

    @Override
    public Collection<PedidoCamposAcoes> getPedidoCamposAcoesByIdTipoPedido(Integer idTipoPedido) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .equalsTo("pedidoTipoAcoes.idTipoPedido", idTipoPedido)
                .build(), getDefaultSort());
    }

    @Override
    public void deleteAllTipoAcoesByIdTipoPedido(Integer idTipoPedido) {
        getRepository().deleteAllCamposAcoesByIdTipoPedido(idTipoPedido);
    }

}
