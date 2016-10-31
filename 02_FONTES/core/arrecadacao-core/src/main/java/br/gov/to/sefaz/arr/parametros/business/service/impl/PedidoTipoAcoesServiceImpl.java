package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoTipoAcoesService;
import br.gov.to.sefaz.arr.persistence.entity.PedidoTipoAcoes;
import br.gov.to.sefaz.arr.persistence.repository.PedidoTipoAcoesRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação de um {@link br.gov.to.sefaz.arr.parametros.business.service.PedidoTipoAcoesService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 16:02:00
 */
@Service
public class PedidoTipoAcoesServiceImpl extends DefaultCrudService<PedidoTipoAcoes, Integer>
        implements PedidoTipoAcoesService {

    @Autowired
    public PedidoTipoAcoesServiceImpl(
            PedidoTipoAcoesRepository repository) {
        super(repository);
    }

    @Override
    protected PedidoTipoAcoesRepository getRepository() {
        return (PedidoTipoAcoesRepository) super.getRepository();
    }

    @Override
    public void deleteAllTipoAcoesByIdTipoPedido(Integer idTipoPedido) {
        getRepository().deleteAllTipoAcoesByIdTipoPedido(idTipoPedido);
    }

    @Override
    public void updateSituacaoByIdTipoPedido(Integer idTipoPedido, SituacaoEnum situacao) {
        getRepository().updateSituacaoByIdTipoPedido(idTipoPedido, situacao);
    }

}
