package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoCamposAcoesService;
import br.gov.to.sefaz.arr.persistence.entity.PedidoCamposAcoes;
import br.gov.to.sefaz.arr.persistence.repository.PedidoCamposAcoesRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
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
        super(repository);
    }

    @Override
    protected PedidoCamposAcoesRepository getRepository() {
        return (PedidoCamposAcoesRepository) super.getRepository();
    }

    @Override
    public Collection<PedidoCamposAcoes> getPedidoCamposAcoesByIdTipoPedido(Integer idTipoPedido) {
        return getRepository().find("pca", sb -> sb.innerJoin("pca.pedidoTipoAcoes", "pta").where()
                .equal("pta.idTipoPedido", idTipoPedido)
                .orderById());
    }

    @Override
    public void deleteAllTipoAcoesByIdTipoPedido(Integer idTipoPedido) {
        getRepository().deleteAllCamposAcoesByIdTipoPedido(idTipoPedido);
    }

}
