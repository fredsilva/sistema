package br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare;

import br.gov.to.sefaz.arr.parametros.managedbean.converter.DareListaPagamentoConverter;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente;
import br.gov.to.sefaz.arr.persistence.view.NotaAvulsa;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;
import br.gov.to.sefaz.presentation.component.EstadoMunicipioViewFactory;
import br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe de controle dos paineis referentes ao dados de pagamento da geração de um DARE-e.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 27/09/2016 15:38:00
 */
@SuppressWarnings("PMD.GodClass")
public class DareDadosPagamentoController {

    private final EstadoMunicipioViewFactory estadoMunicipioViewFactory;
    private final DareListaPagamentoConverter dareListaPagamentoConverter;
    private final DebitoPagamentoParcialViewBean debitoPagamentoParcial;
    private final DebitoSemPagamentoParcialViewBean debitoSemPagamentoParcial;
    private final DebitoIpvaViewBean debitoIpva;
    private final DebitoIcmsFreteViewBean debitoIcmsFrete;
    private final DebitoDiversoViewBean debitoDiverso;

    private EstadoMunicipioViewUtil estadoMunicipioOrigem;
    private EstadoMunicipioViewUtil estadoMunicipioDestino;
    private Long idContaCorrente;

    public DareDadosPagamentoController(EstadoMunicipioViewFactory estadoMunicipioViewFactory,
            DareListaPagamentoConverter dareListaPagamentoConverter) {
        this.estadoMunicipioViewFactory = estadoMunicipioViewFactory;
        this.dareListaPagamentoConverter = dareListaPagamentoConverter;
        this.debitoPagamentoParcial = new DebitoPagamentoParcialViewBean();
        this.debitoSemPagamentoParcial = new DebitoSemPagamentoParcialViewBean();
        this.debitoIpva = new DebitoIpvaViewBean();
        this.debitoIcmsFrete = new DebitoIcmsFreteViewBean();
        this.debitoDiverso = new DebitoDiversoViewBean();
    }

    public DebitoPagamentoParcialViewBean getDebitoPagamentoParcial() {
        return debitoPagamentoParcial;
    }

    public DebitoSemPagamentoParcialViewBean getDebitoSemPagamentoParcial() {
        return debitoSemPagamentoParcial;
    }

    public DebitoIpvaViewBean getDebitoIpva() {
        return debitoIpva;
    }

    public DebitoIcmsFreteViewBean getDebitoIcmsFrete() {
        return debitoIcmsFrete;
    }

    public DebitoDiversoViewBean getDebitoDiverso() {
        return debitoDiverso;
    }

    /**
     * Retorna o {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil} que gerencia as informações
     * de estado e município do painel Dados do Pagamento referente ao Débito ICMS Frete da janela de Gerar DARE-e.
     *
     * @return {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil} que contém os estados e
     *     municípios do painel Débito ICMS Frete
     */
    public EstadoMunicipioViewUtil getEstadoMunicipioOrigem() {
        if (Objects.isNull(estadoMunicipioOrigem)) {
            estadoMunicipioOrigem = estadoMunicipioViewFactory.createEstadoMunicipioViewUtil();
        }
        return estadoMunicipioOrigem;
    }

    /**
     * Retorna o {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil} que gerencia as informações
     * de estado e município do painel Dados do Pagamento referente ao Débito ICMS Frete da janela de Gerar DARE-e.
     *
     * @return {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil} que contém os estados e
     *     municípios do painel Débito ICMS Frete.
     */
    public EstadoMunicipioViewUtil getEstadoMunicipioDestino() {
        if (Objects.isNull(estadoMunicipioDestino)) {
            estadoMunicipioDestino = estadoMunicipioViewFactory.createEstadoMunicipioViewUtil();
        }
        return estadoMunicipioDestino;
    }

    public DareListaPagamentoConverter getDareListaPagamentoConverter() {
        return dareListaPagamentoConverter;
    }

    public Long getIdContaCorrente() {
        return idContaCorrente;
    }

    public void setIdContaCorrente(Long idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    //####################################
    // Controlador de Estados e Municipios
    //####################################

    /**
     * Carrega o combo de seleção de municípios com as opções disponíveis para o estado selecionado
     * do painel Dados do Pagamento referente ao Débito ICMS Frete.
     */
    public void loadMunicipiosOrigem() {
        getEstadoMunicipioOrigem().loadMunicipios(getDebitoIcmsFrete().getUfOrigem());
    }

    /**
     * Carrega o combo de seleção de municípios com as opções disponíveis para o estado selecionado
     * do painel Dados do Pagamento referente ao Débito ICMS Frete.
     */
    public void loadMunicipiosDestino() {
        getEstadoMunicipioDestino().loadMunicipios(getDebitoIcmsFrete().getUfDestino());
    }

    //####################################
    // Controlador de Pagamentos
    //####################################

    /**
     * Converte uma lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} em uma lista de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     *
     * @param debitosContaCorrentes  debitos a serem convertidos
     * @param informacaoComplementar informação complementar para cada item inserido
     * @return lista de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     */
    public List<DareEPagamento> convertDebitosToDareEPagamento(List<DebitosContaCorrente> debitosContaCorrentes,
            String informacaoComplementar) {
        return dareListaPagamentoConverter
                .convertDebitosToDareEPagamento(debitosContaCorrentes, informacaoComplementar);
    }

    public DareEPagamento getPagamentoDebitoComPagamentoParcial() {
        return dareListaPagamentoConverter.convertPagamentoParcialToDareEPagamento(debitoPagamentoParcial);
    }

    /**
     * Converte uma lista de {@link br.gov.to.sefaz.arr.persistence.view.NotaAvulsa} em uma lista de
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     *
     * @param notaAvulsas            notas avulsas a serem convertidas
     * @param informacaoComplementar informação complementar para cada item inserido
     * @return lista de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     */
    public List<DareEPagamento> convertNotasAvulsasToDareEPagamento(List<NotaAvulsa> notaAvulsas,
            String informacaoComplementar) {
        return dareListaPagamentoConverter.convertNotaAvulsaToDareEPagamento(notaAvulsas, informacaoComplementar);
    }

    /**
     * Utiliza o {@link br.gov.to.sefaz.arr.parametros.managedbean.converter.DareListaPagamentoConverter} para
     * converter um
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoIcmsFreteViewBean} em
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento}.
     *
     * @return {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento} conforme os parâmetros
     *     de debito de ICMS frete.
     */
    public DareEPagamento getPagamentoDebitoFreteIcms() {
        debitoIcmsFrete.setNomeMunicipioOrigem(getNomeMunicipioOrigem());
        debitoIcmsFrete.setNomeMunicipioDestino(getNomeMunicipioDestino());

        return dareListaPagamentoConverter.convertDebitoIcmsFreteToDareEPagamento(debitoIcmsFrete);
    }

    private String getNomeMunicipioDestino() {
        return getEstadoMunicipioDestino()
                .getMunicipios().stream()
                .filter(municipio -> municipio.getCodigoIbge()
                        .equals(debitoIcmsFrete.getMunicipioDestino()))
                .findFirst()
                .orElse(new Municipio())
                .getNomeMunicipio();
    }

    private String getNomeMunicipioOrigem() {
        return getEstadoMunicipioOrigem()
                .getMunicipios().stream()
                .filter(municipio -> municipio.getCodigoIbge()
                        .equals(debitoIcmsFrete.getMunicipioOrigem()))
                .findFirst()
                .orElse(new Municipio())
                .getNomeMunicipio();
    }

    public DareEPagamento getPagamentoDebitoDiverso() {
        return dareListaPagamentoConverter.convertDebitoDiversoToDareEPagamento(debitoDiverso);
    }

    //####################################
    // Controlador de Lista de Débitos
    //####################################

    /**
     * Adiciona o débito selecionado na lista de Débitos de Pagamento de IPVA.
     * Painel Débitos de Pagamentos de IPVA.
     */
    public void addDebitoToIpva() {
        debitoIpva.getDareEPagamentos().stream()
                .filter(dcc -> dcc.getIdContaCorrente().equals(getIdContaCorrente()))
                .findFirst()
                .ifPresent(dareEPagamento -> dareEPagamento.setSelected(true));
        setIdContaCorrente(null);
    }

    /**
     * Adiciona o débito selecionado na lista de Débitos de Pagamento de IPVA.
     * Painel Débitos de Pagamentos de IPVA.
     */
    public void addDebitoToSemPagamentoParcial() {
        debitoSemPagamentoParcial.getDareEPagamentos().stream()
                .filter(dcc -> dcc.getIdContaCorrente().equals(getIdContaCorrente()))
                .findFirst()
                .ifPresent(dareEPagamento -> dareEPagamento.setSelected(true));
        setIdContaCorrente(null);
    }

    /**
     * Adiciona todos os débitos para a lista de selecionados de Débitos Localizados.
     * Painel Débitos de Pagamentos de IPVA.
     */
    public void addAllDebitosToPagamentoIpva() {
        debitoIpva.getDareEPagamentos().forEach(dareEPagamento -> dareEPagamento.setSelected(true));
    }

    /**
     * Adiciona todos os débitos para a lista de selecionados de Débitos Localizados.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais.
     */
    public void addAllDebitosToSemPagamentoParcial() {
        debitoSemPagamentoParcial.getDareEPagamentos().forEach(dareEPagamento -> dareEPagamento.setSelected(true));
    }

    /**
     * Remove um débito da lista de selecionados de Débitos Localizados.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais.
     */
    public void removeDebitoFromSemPagamentoParcialSelectedList() {
        debitoSemPagamentoParcial.getDareEPagamentos().stream()
                .filter(dareEPagamento -> dareEPagamento.getIdContaCorrente()
                        .equals(getIdContaCorrente()))
                .forEach(dareEPagamento -> dareEPagamento.setSelected(false));
        setIdContaCorrente(null);
    }

    /**
     * Remove um débito da lista de selecionados de Débitos Localizados.
     * Painel Débitos de Pagamentos de IPVA.
     */
    public void removeDebitoFromPagamentoIpvaSelectedList() {
        debitoIpva.getDareEPagamentos().stream()
                .filter(dareEPagamento -> dareEPagamento.getIdContaCorrente()
                        .equals(getIdContaCorrente()))
                .forEach(dareEPagamento -> dareEPagamento.setSelected(false));

        setIdContaCorrente(null);
    }

    /**
     * Remove todos os débitos da lista de selecionados de Débitos Localizados.
     * Painel Débitos de Pagamentos de IPVA.
     */
    public void removeAllDebitosFromPagamentoIpva() {
        debitoIpva.getDareEPagamentos().forEach(dareEPagamento -> dareEPagamento.setSelected(false));
    }

    /**
     * Remove todos os débitos da lista de selecionados de Débitos Localizados.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais.
     */
    public void removeAllDebitosFromSemPagamentoParcial() {
        debitoSemPagamentoParcial.getDareEPagamentos().forEach(dareEPagamento -> dareEPagamento.setSelected(false));
    }

    //####################################
    // Controlador de Debito Com Pagamentos Parciais
    //####################################

    /**
     * Completa os campos de valores referente à receita escolhida no combo de receitas.
     * Painel Débitos com Possibilidade de Pagamentos Parciais.
     */
    public void getValuesDebitoComPagamentoParcial() {
        debitoPagamentoParcial.getDebitosContaCorrente()
                .stream()
                .filter(debitosContaCorrente -> debitosContaCorrente.getReceita()
                        .equals(debitoPagamentoParcial.getIdReceita()))
                .findFirst()
                .ifPresent(this::setValuesReceitasComPagamentoParcial);
    }

    /**
     * Atualiza os valores referentes ao périodo de referência, valor do imposto, valor da multa, valor do juros,
     * valor de atlz monetária, redução de juros, redução de multa e calcula o valor total referente ao painel de
     * Debito com Pagamento Parcial.
     *
     * @param debitosContaCorrente {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} que possui os
     *                             valores que serão atualizados.
     */
    public void setValuesReceitasComPagamentoParcial(DebitosContaCorrente debitosContaCorrente) {
        debitoPagamentoParcial.setPeriodoReferencia(debitosContaCorrente.getPeriodoReferencia());
        debitoPagamentoParcial.setValorImposto(debitosContaCorrente.getValorImposto());
        debitoPagamentoParcial.setValorMulta(debitosContaCorrente.getValorMulta());
        debitoPagamentoParcial.setValorJuros(debitosContaCorrente.getValorJuros());
        debitoPagamentoParcial.setValorAtlzMonetaria(debitosContaCorrente.getValorCorrecaoMonet());
        debitoPagamentoParcial.setValorReducaoMulta(debitosContaCorrente.getValorReducaoMulta());
        debitoPagamentoParcial.setValorReducaoJuros(debitosContaCorrente.getValorReducaoJuros());
        calculateTotalDebitoParcial();
    }

    /**
     * Busca as receitas referentes aos {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} do painel
     * de Debito com Pagamento Parcial.
     *
     * @return identificação das receitas encontradas
     */
    public Set<Integer> getReceitasForDebitoComPagametoParcial() {
        Set<Integer> idReceitas = new HashSet<>();
        List<DebitosContaCorrente> debitosContaCorrente = debitoPagamentoParcial.getDebitosContaCorrente();

        if (!CollectionUtils.isEmpty(debitosContaCorrente)) {
            idReceitas.addAll(debitosContaCorrente.stream()
                    .map(DebitosContaCorrente::getReceita)
                    .collect(Collectors.toList()));
        }

        return idReceitas;
    }

    /**
     * Altera o valor total a pagar se foi selecionado a opção de pagar um valor diferente.
     * Painel Débitos com Possibilidade de Pagamentos Parciais.
     */
    public void updateValorTotalComPgtoParcialWithValorPagar() {
        if (debitoPagamentoParcial.getIsValorPagarDiferente()) {
            debitoPagamentoParcial.setTotalRecolher(debitoPagamentoParcial.getValorPagar());
        } else {
            debitoPagamentoParcial.setValorPagar(null);
            calculateTotalDebitoParcial();
        }
    }

    /**
     * Recalcula o valor total, caso seja desmarcada a opção de informar um valor diferente.
     * Painel Débitos com Possibilidade de Pagamentos Parciais.
     */
    private void calculateTotalDebitoParcial() {
        BigDecimal valor = debitoPagamentoParcial.getValorImposto()
                .add(debitoPagamentoParcial.getValorMulta())
                .add(debitoPagamentoParcial.getValorJuros())
                .add(debitoPagamentoParcial.getValorAtlzMonetaria())
                .subtract(debitoPagamentoParcial.getValorReducaoJuros())
                .subtract(debitoPagamentoParcial.getValorReducaoMulta());
        debitoPagamentoParcial.setTotalRecolher(valor);
    }

    //####################################
    // Controlador de Debito ICMS Frete
    //####################################

    /**
     * Calcula o valor total do Frete, de acordo com o valor BC.
     * Painel Débitos ICMS Frete.
     */
    public void calculateValorTotalFrete() {
        BigDecimal valorBc = debitoIcmsFrete.getValorBc();
        BigDecimal valorImposto = getValueForValorBcAndTaxa(valorBc, debitoIcmsFrete.getAliquota());
        BigDecimal valorMulta = getValueForValorBcAndTaxa(valorBc, debitoIcmsFrete.getMulta());
        debitoIcmsFrete.setValorImposto(valorImposto);
        debitoIcmsFrete.setValorMulta(valorMulta);

        debitoIcmsFrete.setValorTotalFrete(valorBc
                .add(debitoIcmsFrete.getValorImposto())
                .add(debitoIcmsFrete.getValorMulta()));
    }

    private BigDecimal getValueForValorBcAndTaxa(BigDecimal valorBc, EnumCodeLabel<Double> enumTaxa) {
        BigDecimal taxa = new BigDecimal(Objects.isNull(enumTaxa) ? NumberUtils.INTEGER_ONE : enumTaxa.getCode());
        return valorBc.multiply(taxa).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    //####################################
    // Controlador de Debito Diverso
    //####################################

    /**
     * Calcula o valor TSE ao selecionar um Sub Código.
     */
    public void calculateTseValues() {
        ReceitasTaxas receitaTaxa = debitoDiverso.getReceitasTaxas()
                .stream()
                .filter(receitasTaxas -> receitasTaxas.getIdSubcodigo().equals(debitoDiverso.getIdSubCodigo()))
                .findFirst().orElse(null);

        if (Objects.nonNull(receitaTaxa)) {
            updateTseValues();
            BigDecimal valorUnitarioTse = Objects.isNull(debitoDiverso.getValorUnitarioTse()) ? receitaTaxa
                    .getValorUnitario() : debitoDiverso.getValorUnitarioTse();
            debitoDiverso.setValorUnitarioTse(valorUnitarioTse);
            int quantidadeTse = Objects.isNull(debitoDiverso.getQuantidadeTse()) ? NumberUtils.INTEGER_ONE :
                    debitoDiverso.getQuantidadeTse();
            debitoDiverso.setQuantidadeTse(quantidadeTse);

            BigDecimal quantTse = new BigDecimal(debitoDiverso.getQuantidadeTse());
            if (Objects.isNull(receitaTaxa.getValorAcrescimo())) {
                debitoDiverso.setValorTse(debitoDiverso.getValorUnitarioTse().multiply(quantTse));
            } else {
                debitoDiverso.setValorTse(debitoDiverso.getValorUnitarioTse().multiply(
                        quantTse).add(receitaTaxa.getValorAcrescimo().multiply(quantTse)));
            }

            updateTotalRecolherDebitosDiversos();
        } else {
            clearDebitosDiversosViewBeanValues();
        }
    }

    /**
     * Atualiza o total a pagar.
     */
    public void updateTotalRecolherDebitosDiversos() {
        BigDecimal valorTotalRecolher;
        if (Objects.isNull(debitoDiverso.getIdSubCodigo())) {
            BigDecimal valorImposto = getDebitosDiversosValorImposto();
            BigDecimal valorMulta = getDebitosDiversosValorMulta();
            BigDecimal valorJuros = getDebitosDiversosValorJuros();

            valorTotalRecolher = valorImposto.add(valorMulta).add(valorJuros);
        } else {
            valorTotalRecolher = getDebitosDiversosValorTse();
        }

        debitoDiverso.setTotalRecolher(valorTotalRecolher);
    }

    private void updateTseValues() {
        if (Objects.isNull(debitoDiverso.getIdSubCodigo())) {
            debitoDiverso.setValorUnitarioTse(null);
            debitoDiverso.setQuantidadeTse(null);
        }
    }

    private BigDecimal getDebitosDiversosValorJuros() {
        return Objects.isNull(debitoDiverso.getValorJuros()) ? BigDecimal.ZERO :
                debitoDiverso.getValorJuros();
    }

    private BigDecimal getDebitosDiversosValorMulta() {
        return Objects.isNull(debitoDiverso.getValorMulta()) ? BigDecimal.ZERO :
                debitoDiverso.getValorMulta();
    }

    private BigDecimal getDebitosDiversosValorImposto() {
        return Objects.isNull(debitoDiverso.getValorImposto()) ? BigDecimal.ZERO :
                debitoDiverso.getValorImposto();
    }

    private BigDecimal getDebitosDiversosValorTse() {
        return Objects.isNull(debitoDiverso.getValorTse()) ? BigDecimal.ZERO :
                debitoDiverso.getValorTse();
    }

    private void clearDebitosDiversosViewBeanValues() {
        debitoDiverso.setQuantidadeTse(null);
        debitoDiverso.setValorUnitarioTse(null);
        debitoDiverso.setValorTse(null);
        debitoDiverso.setValorImposto(null);
        debitoDiverso.setValorMulta(null);
        debitoDiverso.setValorJuros(null);
        debitoDiverso.setTotalRecolher(null);
    }

}
