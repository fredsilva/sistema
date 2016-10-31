package br.gov.to.sefaz.arr.parametros.managedbean.mapper;

import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que auxilia na decisão para identificar os Dados de Pagamento da janela Gerar DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 29/08/2016 17:38:00
 */
@Component
public class DarePanelDadoPagamentoHelper {

    /**
     * Verifica se o {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e a
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados, conforme as regras de negócio,
     * representam um débito com possibilidade de pagamento parcial.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito permita pagamento parcial.
     */
    public boolean isDebitoComPagamentoParcial(TipoImpostoEnum tipoImposto, OrigemDebitoEnum origemDebito) {
        List<TipoImpostoEnum> tipoImpostos = getTipoImpostoPagamentoParcial();
        List<OrigemDebitoEnum> origemDebitos = getOrigemDebitoPagamentoParcial();

        boolean impostoComPagamentoParcial = !Objects.isNull(tipoImposto)
                && tipoImpostos.contains(tipoImposto);
        boolean origemDebitoParcial = !Objects.isNull(origemDebito)
                && origemDebitos.contains(origemDebito);

        return impostoComPagamentoParcial && origemDebitoParcial;
    }

    /**
     * Verifica se a{@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados,
     * conforme as regras de negócio, representa um débito sem possibilidade de pagamento parcial.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito não permita pagamento parcial.
     */
    public boolean isDebitoSemPagamentoParcial(OrigemDebitoEnum origemDebito) {
        List<OrigemDebitoEnum> origemDebitos = new ArrayList<>();
        origemDebitos.add(OrigemDebitoEnum.PARCELAMENTO);
        origemDebitos.add(OrigemDebitoEnum.PARCELAMENTO_DIVIDA_ATIVA);
        origemDebitos.add(OrigemDebitoEnum.COBRANCA_TRANSITO);
        origemDebitos.add(OrigemDebitoEnum.NOTA_AVULSA);
        origemDebitos.add(OrigemDebitoEnum.DPCA);

        return !Objects.isNull(origemDebito)
                && origemDebitos.contains(origemDebito);
    }

    /**
     * Verifica se o {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e a
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados, conforme as regras de negócio,
     * representam um pagamento de IPVA.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito represente um pagamento de IPVA.
     */
    public boolean isDebitoIpva(TipoImpostoEnum tipoImposto, OrigemDebitoEnum origemDebito) {
        boolean impostoComPagamentoParcial = !Objects.isNull(tipoImposto)
                && tipoImposto == TipoImpostoEnum.IPVA;
        boolean origemDebitoParcial = !Objects.isNull(origemDebito)
                && origemDebito == OrigemDebitoEnum.IPVA;

        return impostoComPagamentoParcial && origemDebitoParcial;
    }

    /**
     * Verifica se o {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e a
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados, conforme as regras de negócio,
     * representam um pagamento de ICMS com frete.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito represente um pagamento de ICMS com frete.
     */
    public boolean isDebitoIcms(TipoImpostoEnum tipoImposto, OrigemDebitoEnum origemDebito) {
        boolean impostoComPagamentoParcial = !Objects.isNull(tipoImposto)
                && tipoImposto == TipoImpostoEnum.ICMS;
        boolean origemDebitoParcial = !Objects.isNull(origemDebito)
                && origemDebito == OrigemDebitoEnum.ICMS_FRETE;

        return impostoComPagamentoParcial && origemDebitoParcial;
    }

    /**
     * Verifica se o {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e a
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados, conforme as regras de negócio,
     * representam um débito diverso.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito represente um débito diverso.
     */
    public boolean isDebitoDiverso(TipoImpostoEnum tipoImposto, OrigemDebitoEnum origemDebito) {
        List<TipoImpostoEnum> tipoImpostos = new ArrayList<>();
        tipoImpostos.add(TipoImpostoEnum.TAXAS_NAO_TRIBUTARIAS);
        tipoImpostos.add(TipoImpostoEnum.TAXAS_TRIBUTARIAS);

        List<OrigemDebitoEnum> origemDebitos = new ArrayList<>();
        origemDebitos.add(OrigemDebitoEnum.MULTAS);
        origemDebitos.add(OrigemDebitoEnum.TAXAS);
        origemDebitos.add(OrigemDebitoEnum.OUTRAS_RECEITAS);

        boolean impostoComPagamentoParcial = !Objects.isNull(tipoImposto)
                && tipoImpostos.contains(tipoImposto);
        boolean origemDebitoParcial = !Objects.isNull(origemDebito)
                && origemDebitos.contains(origemDebito);

        return impostoComPagamentoParcial && origemDebitoParcial;
    }

    private List<OrigemDebitoEnum> getOrigemDebitoPagamentoParcial() {
        List<OrigemDebitoEnum> origemDebitos = new ArrayList<>();
        origemDebitos.add(OrigemDebitoEnum.DECLARADO_ICMS);
        origemDebitos.add(OrigemDebitoEnum.DECLARADO_ICMS_ST);
        origemDebitos.add(OrigemDebitoEnum.DECLARADO_ICMS_COMPL);
        origemDebitos.add(OrigemDebitoEnum.AUTO_INFRACAO_NLD);
        origemDebitos.add(OrigemDebitoEnum.DIVIDA_ATIVA);
        origemDebitos.add(OrigemDebitoEnum.NF_OPERACOES_EXPONTANEAS_ST);
        origemDebitos.add(OrigemDebitoEnum.ITCD);

        return origemDebitos;
    }

    private List<TipoImpostoEnum> getTipoImpostoPagamentoParcial() {
        List<TipoImpostoEnum> tipoImpostos = new ArrayList<>();
        tipoImpostos.add(TipoImpostoEnum.ICMS);
        tipoImpostos.add(TipoImpostoEnum.IPVA);
        tipoImpostos.add(TipoImpostoEnum.ITCD);

        return tipoImpostos;
    }
}
