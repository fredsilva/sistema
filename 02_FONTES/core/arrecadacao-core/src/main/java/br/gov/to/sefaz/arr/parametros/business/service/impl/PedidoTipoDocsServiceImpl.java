package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoTipoDocsService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoTipoDocs;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.arr.parametros.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoTipoDocsRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implementação de um {@link br.gov.to.sefaz.arr.parametros.business.service.PedidoTipoDocsService}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 16:03:00
 */
@Service
public class PedidoTipoDocsServiceImpl extends DefaultCrudService<PedidoTipoDocs, Integer>
        implements PedidoTipoDocsService {

    @Autowired
    public PedidoTipoDocsServiceImpl(
            PedidoTipoDocsRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idTipoDocs")));
    }

    @Override
    @SuppressWarnings("PMD")
    public List<TipoPedidoCampoEnum> getTipoPedidoCampoEnumValues(Integer idTipoPedido,
            TipoPedidoAcoesEnum tipoPedido) {

        List<TipoPedidoCampoEnum> list = new ArrayList<>();

        if (idTipoPedido == null || tipoPedido == null) {
            return Arrays.asList(TipoPedidoCampoEnum.values());
        }

        if (idTipoPedido == 1 && TipoPedidoAcoesEnum.CORRIGIR_PAGAMENTO.equals(tipoPedido)) {
            list.add(TipoPedidoCampoEnum.getValue(1));
            list.add(TipoPedidoCampoEnum.getValue(2));
            list.add(TipoPedidoCampoEnum.getValue(3));
            list.add(TipoPedidoCampoEnum.getValue(4));
            list.add(TipoPedidoCampoEnum.getValue(5));
            list.add(TipoPedidoCampoEnum.getValue(6));
        } else if (idTipoPedido == 2 && TipoPedidoAcoesEnum.CREDITAR_CONTA_TRIBUTARIA.equals(tipoPedido)) {
            list.add(TipoPedidoCampoEnum.getValue(7));
        } else if ((idTipoPedido == 3 || idTipoPedido == 4)
                && TipoPedidoAcoesEnum.CREDITAR_CONTA_CORRENTE.equals(tipoPedido)) {
            list.add(TipoPedidoCampoEnum.getValue(7));
        } else if (idTipoPedido == 5 && TipoPedidoAcoesEnum.ESTORNAR_PAGAMENTO.equals(tipoPedido)) {
            list.add(TipoPedidoCampoEnum.getValue(1));
            list.add(TipoPedidoCampoEnum.getValue(8));
            list.add(TipoPedidoCampoEnum.getValue(9));
            list.add(TipoPedidoCampoEnum.getValue(10));
        } else if (idTipoPedido == 6 && TipoPedidoAcoesEnum.PEDIDO_INDEFERIDO.equals(tipoPedido)) {
            list.add(TipoPedidoCampoEnum.getValue(7));
            list.add(TipoPedidoCampoEnum.getValue(11));
        } else {
            list.addAll(Arrays.asList(TipoPedidoCampoEnum.values()));
        }

        return list;
    }
}
