package br.gov.to.sefaz.arr.dare.service;

import br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter;
import br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente;

import java.util.List;

/**
 * Interface que define os métodos de serviço para a view
 * {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:42:00
 */
public interface DebitosContaCorrenteService {

    /**
     * Busca uma lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} conforme o
     * {@link DebitoParcialFilter}.
     *
     * @param debitoParcialFilter icontém os filtros para identificação de
     *                            {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente}.
     * @return a lista com os debitos encontrados.
     */
    List<DebitosContaCorrente> findAllByDebitoParcialFilter(DebitoParcialFilter debitoParcialFilter);

    /**
     * Busca uma lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} conforme o
     * {@link br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter} e do
     * {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente#getTipoConta()} que deve ser
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum#IPVA}.
     *
     * @param debitoIpvaFilter contém os filtros de ano para IPVA
     * @return lista com os debitos encontrados
     */
    List<DebitosContaCorrente> findAllByIpvaFilter(DebitoIpvaFilter debitoIpvaFilter);

}
