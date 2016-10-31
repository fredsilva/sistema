package br.gov.to.sefaz.arr.parametros.managedbean.converter;

import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.persistence.entity.DareDetalhe;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Conversor objeto {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean} para
 * {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 23/09/2016 14:38:00
 */
@Component
public class DareEViewBeanConverter {

    /**
     * Converte objeto {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean} para
     * {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
     *
     * @param dareEViewBean objeto {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean}.
     * @return objeto {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
     */
    public Dare convertDareEViewBeanToEntity(DareEViewBean dareEViewBean) {
        return new Dare(null, dareEViewBean.getUnidadeOrganizacional(), dareEViewBean.getUnidadeFederacao(),
                dareEViewBean.getMunicipio(), null, dareEViewBean.getTipoPessoa(),
                dareEViewBean.getIdentificacaoPessoa(), dareEViewBean.getNomePessoa(), LocalDateTime.now(), null,
                dareEViewBean.getListaPagamentos().size(), dareEViewBean.getValorTotal(), null,
                null, convertDebitosToDareDetalhe(dareEViewBean));
    }

    private List<DareDetalhe> convertDebitosToDareDetalhe(DareEViewBean dareEViewBean) {
        List<DareDetalhe> dareDetalhes = new ArrayList<>();
        int idSeqDareDetalhe = NumberUtils.INTEGER_ZERO;

        for (DareEPagamento pagamento : dareEViewBean.getListaPagamentos()) {
            if (isNotaAvulsaComTaxa(pagamento.getValorTse())) {
                dareDetalhes.add(createDareDetalheWithValorTse(dareEViewBean, ++idSeqDareDetalhe, pagamento));
                dareDetalhes.add(createDareDetalheWithValorImposto(dareEViewBean, ++idSeqDareDetalhe, pagamento));
            } else {
                dareDetalhes.add(new DareDetalhe(null, ++idSeqDareDetalhe, pagamento.getReceita(),
                        pagamento.getIdSubcodigo(), pagamento.getInformacaoComplementar(), pagamento.getPeriodoAno(),
                        pagamento.getDocumento(), null, pagamento.getValorImposto(),
                        pagamento.getValorCorrecaoMonetaria(),
                        pagamento.getValorMulta(), pagamento.getValorReducaoMulta(), pagamento.getValorJuros(),
                        pagamento.getValorReducaoJuros(), BigDecimal.ZERO, pagamento.getValorTotal(),
                        pagamento.getInformadoContribuinte(), dareEViewBean.getIdentificacaoPessoa(),
                        dareEViewBean.getTipoPessoa().getCode(), pagamento.getIdContaCorrente(),
                        dareEViewBean.getMunicipio(), pagamento.getDataVencimento()));
            }
        }

        return dareDetalhes;
    }

    private DareDetalhe createDareDetalheWithValorImposto(DareEViewBean dareEViewBean, int idSeqDareDetalhe,
            DareEPagamento pagamento) {
        return new DareDetalhe(null, idSeqDareDetalhe, pagamento.getReceita(),
                null, pagamento.getInformacaoComplementar(), pagamento.getPeriodoAno(),
                pagamento.getDocumento(), null, pagamento.getValorImposto(),
                pagamento.getValorCorrecaoMonetaria(),
                pagamento.getValorMulta(), pagamento.getValorReducaoMulta(), pagamento.getValorJuros(),
                pagamento.getValorReducaoJuros(), BigDecimal.ZERO, pagamento.getValorImposto(),
                pagamento.getInformadoContribuinte(), dareEViewBean.getIdentificacaoPessoa(),
                dareEViewBean.getTipoPessoa().getCode(), pagamento.getIdContaCorrente(),
                dareEViewBean.getMunicipio(), pagamento.getDataVencimento());
    }

    private DareDetalhe createDareDetalheWithValorTse(DareEViewBean dareEViewBean, int idSeqDareDetalhe,
            DareEPagamento pagamento) {
        return new DareDetalhe(null, idSeqDareDetalhe, pagamento.getIdReceitaTaxa(),
                pagamento.getIdSubcodigo(), pagamento.getInformacaoComplementar(), pagamento.getPeriodoAno(),
                pagamento.getDocumento(), null, pagamento.getValorTse(), pagamento.getValorCorrecaoMonetaria(),
                pagamento.getValorMulta(), pagamento.getValorReducaoMulta(), pagamento.getValorJuros(),
                pagamento.getValorReducaoJuros(), BigDecimal.ZERO, pagamento.getValorTse(),
                pagamento.getInformadoContribuinte(), dareEViewBean.getIdentificacaoPessoa(),
                dareEViewBean.getTipoPessoa().getCode(), pagamento.getIdContaCorrente(),
                dareEViewBean.getMunicipio(), pagamento.getDataVencimento());
    }

    private boolean isNotaAvulsaComTaxa(BigDecimal valorTse) {
        return Objects.nonNull(valorTse) && !Objects.equals(valorTse, BigDecimal.ZERO);
    }


}
