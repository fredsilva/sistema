package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.validator;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Valida se a lista de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} não está
 * vazia e não supera o valor limite de 999.999.999,99.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/10/2016 12:02:00
 */
@Component
public class DareEPagamentoValidator {

    /**
     * Valida se a lista de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}
     * não está vazia.
     *
     * @param dareEPagamentos lista de
     *                        {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}
     *                        a ser validada.
     */
    public boolean validateDareEPagamentoIsNotEmpty(Collection<DareEPagamento> dareEPagamentos) {

        if (Objects.isNull(dareEPagamentos) || dareEPagamentos.isEmpty()) {
            MessageUtil.addErrorMessage(MessageUtil.ARR, "arr.par.dare.DareEPAgamentos.dareEPagamentos.empty");
            return false;
        }

        return true;
    }

    /**
     * Valida se a soma dos valores da lista de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}
     * não é superior a 999.999.999,99.
     *
     * @param dareEPagamentos lista de
     *                        {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}
     *                        a ser validada.
     */
    public boolean validateDareEPagamentoMaxValue(Collection<DareEPagamento> dareEPagamentos) {
        List<BigDecimal> valoresTotais = dareEPagamentos
                .stream().map(DareEPagamento::getValorTotal).collect(Collectors.toList());

        BigDecimal valorTotal = valoresTotais.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);

        if (valorTotal.compareTo(new BigDecimal("999999999.99")) > 0) {
            MessageUtil.addErrorMessage(MessageUtil.ARR, "arr.par.dare.dareEPagamento.totalRecolher.limit");
            return false;
        }

        return true;
    }

}
