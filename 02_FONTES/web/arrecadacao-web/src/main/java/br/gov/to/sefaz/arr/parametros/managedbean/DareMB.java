package br.gov.to.sefaz.arr.parametros.managedbean;

import br.gov.to.sefaz.arr.dare.enums.TipoUnidadeEnum;
import br.gov.to.sefaz.arr.dare.facade.DareFacade;
import br.gov.to.sefaz.arr.dare.service.domain.DareEmail;
import br.gov.to.sefaz.arr.dare.service.filter.DareContribuinteFilter;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoIpvaFilter;
import br.gov.to.sefaz.arr.dare.service.filter.DebitoParcialFilter;
import br.gov.to.sefaz.arr.parametros.managedbean.converter.DareEViewBeanConverter;
import br.gov.to.sefaz.arr.parametros.managedbean.converter.DareListaPagamentoConverter;
import br.gov.to.sefaz.arr.parametros.managedbean.helper.DareEmailHelper;
import br.gov.to.sefaz.arr.parametros.managedbean.mapper.DareComboMapper;
import br.gov.to.sefaz.arr.parametros.managedbean.mapper.DarePanelDadoPagamentoHelper;
import br.gov.to.sefaz.arr.parametros.managedbean.validator.DareViewBeanValidator;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareDadosPagamentoController;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEPagamento;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoDiversoViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoIpvaViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean;
import br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoSemPagamentoParcialViewBean;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.persistence.entity.Receitas;
import br.gov.to.sefaz.arr.persistence.entity.ReceitasTaxas;
import br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;
import br.gov.to.sefaz.arr.persistence.view.Contribuintes;
import br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente;
import br.gov.to.sefaz.arr.persistence.view.NotaAvulsa;
import br.gov.to.sefaz.presentation.component.EstadoMunicipioViewFactory;
import br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil;
import br.gov.to.sefaz.presentation.managedbean.impl.DefaultCrudMB;
import br.gov.to.sefaz.seg.persistence.entity.UnidadeOrganizacional;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * ManagedBean da entidade {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 22/08/2016 13:58:00
 */
@ManagedBean(name = "dareMB")
@ViewScoped
@SuppressWarnings("PMD.GodClass")
public class DareMB extends DefaultCrudMB<Dare, Long> {
    private DareEViewBean dareViewBean;
    private EstadoMunicipioViewUtil estadoMunicipioOperacao;
    private DareEmailHelper dareEmailHelper;
    private DareDadosPagamentoController dadosPagamentoController;

    @Autowired
    private DareListaPagamentoConverter dareListaPagamentoConverter;
    @Autowired
    private EstadoMunicipioViewFactory estadoMunicipioViewFactory;
    @Autowired
    private DareComboMapper dareComboMapper;
    @Autowired
    private DarePanelDadoPagamentoHelper panelDadoPagamentoHelper;
    @Autowired
    private DareEViewBeanConverter dareEViewBeanConverter;

    @Autowired
    private DareViewBeanValidator dareViewBeanValidator;

    public DareMB() {
        super(Dare::new);
    }

    /**
     * Instancia os Views Beans.
     */
    @PostConstruct
    public void init() {
        clearDtos();
    }

    @Override
    protected DareFacade getFacade() {
        return (DareFacade) super.getFacade();
    }

    @Autowired
    protected void setFacade(DareFacade facade) {
        super.setFacade(facade);
    }

    @Override
    public void save() {
        dareViewBeanValidator.gerarDareValidator(dareViewBean);
        Dare dare = dareEViewBeanConverter.convertDareEViewBeanToEntity(dareViewBean);
        setDto(dare);
        super.save();
    }

    @Override
    protected void executeAfterSave(Dare dare) {
        setDto(dare);
        dareViewBean.setNossoNumero(dare.getIdNossoNumeroDare());
        dareViewBean.setDataGeracao(dare.getDataEmissao());
    }

    /**
     * Limpa os Dtos e ViewBeans.
     */
    public void clearDtos() {
        clearDto();
        dareViewBean = new DareEViewBean();
        dareEmailHelper = new DareEmailHelper();
        clearViewBeans();
    }

    /**
     * Limpa os ViewBeans.
     */
    public void clearViewBeans() {
        dadosPagamentoController = new DareDadosPagamentoController(estadoMunicipioViewFactory,
                dareListaPagamentoConverter);
    }

    //####################################
    // Controlador de Estados e Municipios
    //####################################

    /**
     * Carrega o combo de seleção de municípios com as opções disponíveis para o estado selecionado
     * do painel Dados da Operação.
     */
    public void loadMunicipiosOperacao() {
        getEstadoMunicipioOperacao().loadMunicipios(dareViewBean.getUnidadeFederacao());
    }

    /**
     * Retorna o {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil} que gerencia as informações
     * de estado e município do painel Dados da Operação da janela de Gerar DARE-e.
     *
     * @return {@link br.gov.to.sefaz.presentation.component.EstadoMunicipioViewUtil} que contém os estados e
     *     municípios do painel Dados da Operação.
     */
    public EstadoMunicipioViewUtil getEstadoMunicipioOperacao() {
        if (Objects.isNull(estadoMunicipioOperacao)) {
            estadoMunicipioOperacao = estadoMunicipioViewFactory.createEstadoMunicipioViewUtil();
        }
        return estadoMunicipioOperacao;
    }

    //####################################
    // Gets ViewBean
    //####################################

    public DareEViewBean getDareViewBean() {
        return dareViewBean;
    }

    public DareDadosPagamentoController getDadosPagamentoController() {
        return dadosPagamentoController;
    }

    //####################################
    // Informações de Dados de Operação
    //####################################

    public List<UnidadeOrganizacional> getInstituicoes() {
        return getFacade().getAllInstituicoesWithTipoUnidadeSefazAndOrgaosExternos();
    }

    //####################################
    // Informações de Dados de Contribuinte
    //####################################

    public List<TipoContribuinteEnum> getTipoContribuintes() {
        return dareComboMapper.getTipoContribuinteMap().get(getTipoUnidade());
    }

    public List<TipoPessoaEnum> getTiposPessoa() {
        return dareComboMapper.getTipoContribuinteIdentificacaoMap().get(dareViewBean.getTipoContribuinte());
    }

    public boolean isTipoPessoaCpf() {
        return TipoPessoaEnum.CPF.equals(dareViewBean.getTipoPessoa());
    }

    public boolean isTipoPessoaCnpj() {
        return TipoPessoaEnum.CNPJ.equals(dareViewBean.getTipoPessoa());
    }

    public boolean isTipoPessoaRenavamOrInscricaoEstadual() {
        return Objects.isNull(dareViewBean.getTipoPessoa())
                || TipoPessoaEnum.RENAVAM.equals(dareViewBean.getTipoPessoa())
                || TipoPessoaEnum.INSCRICAO.equals(dareViewBean.getTipoPessoa());
    }

    public boolean isTipoContribuinteNaoContribuinte() {
        return dareViewBean.getTipoContribuinte() == TipoContribuinteEnum.NAO_CONTRIBUINTE;
    }

    /**
     * Realiza a busca de Contribuinte conforme o {@link br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum},
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum} e o código de identificação do contribuinte.
     */
    public void searchContribuinte() {
        if (isTipoContribuinteNaoContribuinte()) {
            dareViewBean.setIdentificacaoPessoa(dareViewBean.getIdContribuinteFilter());
        } else {
            Contribuintes contribuintes = getFacade().searchContribuinteBy(getDareContribuinteFilter());

            if (Objects.isNull(contribuintes)) {
                MessageUtil.addMessage(MessageUtil.ARR, "dare.busca.contribuinte.invalido");
            } else {
                dareViewBean.setIdentificacaoPessoa(contribuintes.getIdPessoa());
                dareViewBean.setNomePessoa(contribuintes.getRazaoSocialNome());
            }
        }
    }

    private DareContribuinteFilter getDareContribuinteFilter() {
        TipoContribuinteEnum tipoContribuinte = dareViewBean.getTipoContribuinte();
        TipoPessoaEnum tipoPessoa = dareViewBean.getTipoPessoa();
        Long idContribuinteFilter = dareViewBean.getIdContribuinteFilter();

        return new DareContribuinteFilter(tipoContribuinte, tipoPessoa,
                idContribuinteFilter);
    }

    /**
     * Limpa os campos de contribuinte.
     */
    public void clearContribuintesFields() {
        dareViewBean.setIdContribuinteFilter(null);
        dareViewBean.setIdentificacaoPessoa(null);
        dareViewBean.setNomePessoa(null);
    }

    //####################################
    // Informações de Dados de Identificação de Pagamento
    //####################################

    public List<TipoImpostoEnum> getTipoImpostos() {
        return dareComboMapper.getTipoImpostoMap().get(getTipoUnidade());
    }

    public List<OrigemDebitoEnum> getOrigemDebitos() {
        return dareComboMapper.getOrigemDebitoMap().get(dareViewBean.getTipoImposto());
    }

    /**
     * Altera o valor de {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean#origemDebito}
     * para null.
     */
    public void resetOrigemDebito() {
        dareViewBean.setOrigemDebito(null);
    }

    /**
     * Conforme regras de algumas combinações do painel de Dados de Pagamento, será necessário realizar algumas
     * alterações nos atributos.
     */
    public void loadDadosPagamento() {
        clearViewBeans();
        if (isDebitoComPagamentoParcial()) {
            processValorAPagarDebitoPagamentoParcial();
        } else if (isDebitoDiverso()) {
            dadosPagamentoController.getDebitoDiverso().setReceitas(
                    getFacade().searchReceitasWithOrigemDebitoId(dareViewBean.getOrigemDebito().getCode()));
        }

    }

    private void processValorAPagarDebitoPagamentoParcial() {
        boolean isNfOperacoes = dareViewBean.getOrigemDebito() == OrigemDebitoEnum.NF_OPERACOES_EXPONTANEAS_ST;
        DebitoPagamentoParcialViewBean debitoPagamentoParcial =
                dadosPagamentoController.getDebitoPagamentoParcial();

        if (Objects.isNull(debitoPagamentoParcial.getIsValorPagarDiferente()) && isNfOperacoes) {
            debitoPagamentoParcial.setIsValorPagarDiferente(true);
        } else if (Objects.isNull(debitoPagamentoParcial.getIsValorPagarDiferente())) {
            debitoPagamentoParcial.setIsValorPagarDiferente(false);
        }
    }


    //####################################
    // Informações de Dados do Pagamento
    //####################################

    private TipoUnidadeEnum getTipoUnidade() {
        Character unidadeOrganizacional = getFacade().getTipoUnidadeByUnidadeOrganizacional(dareViewBean
                .getUnidadeOrganizacional());
        return Objects.nonNull(unidadeOrganizacional) ? TipoUnidadeEnum.getValue(unidadeOrganizacional) : null;
    }

    /**
     * Verifica se o {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e a
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados, conforme as regras de negócio,
     * representam um débito com possibilidade de pagamento parcial.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito permita pagamento parcial.
     */
    public boolean isDebitoComPagamentoParcial() {
        return panelDadoPagamentoHelper.isDebitoComPagamentoParcial(dareViewBean.getTipoImposto(),
                dareViewBean.getOrigemDebito());
    }

    /**
     * Verifica a{@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionada,
     * conforme as regras de negócio, representa um débito sem possibilidade de pagamento parcial.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito não permita pagamento parcial.
     */
    public boolean isDebitoSemPagamentoParcial() {
        return panelDadoPagamentoHelper.isDebitoSemPagamentoParcial(dareViewBean.getOrigemDebito());
    }

    /**
     * Verifica se o {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e a
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados, conforme as regras de negócio,
     * representam um pagamento de IPVA.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito represente um pagamento de IPVA.
     */
    public boolean isDebitoIpva() {
        return panelDadoPagamentoHelper.isDebitoIpva(dareViewBean.getTipoImposto(), dareViewBean.getOrigemDebito());
    }

    /**
     * Verifica se o {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e a
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados, conforme as regras de negócio,
     * representam um pagamento de ICMS com frete.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito represente um pagamento de ICMS com frete.
     */
    public boolean isDebitoIcms() {
        return panelDadoPagamentoHelper.isDebitoIcms(dareViewBean.getTipoImposto(), dareViewBean.getOrigemDebito());
    }

    /**
     * Verifica se o {@link br.gov.to.sefaz.arr.persistence.enums.TipoImpostoEnum} e a
     * {@link br.gov.to.sefaz.arr.persistence.enums.OrigemDebitoEnum} selecionados, conforme as regras de negócio,
     * representam um débito diverso.
     *
     * @return true caso o tipo de imposto em conjunto com a origem do débito represente um débito diverso.
     */
    public boolean isDebitoDiverso() {
        return panelDadoPagamentoHelper.isDebitoDiverso(dareViewBean.getTipoImposto(), dareViewBean.getOrigemDebito());
    }

    /**
     * Método que adiciona pagamentos conforme o painel de Dados do Pagamento para a lista de pagamentos principal do
     * DARE-e.
     */
    public void addPagamentoToDarePagamentoList() {
        if (isTipoContribuinteNaoContribuinte()) {
            dareViewBean.setIdentificacaoPessoa(dareViewBean.getIdContribuinteFilter());
        }

        dareViewBeanValidator.dareEViewBeanValidator(dareViewBean);

        if (isDebitoIpva() && dareViewBeanValidator.dareEPagamentosValidator(dadosPagamentoController
                .getDebitoIpva().getDareEPagamentosSelected())) {
            dareViewBean.addAllListaPagamentos(dadosPagamentoController.getDebitoIpva().getDareEPagamentosSelected());
            clearAddedPagamento();
        } else if (isDebitoComPagamentoParcial()) {
            dareViewBeanValidator.debitoPagamentoParcialValidator(dadosPagamentoController.getDebitoPagamentoParcial());
            dareViewBean.addDareEPagamento(dadosPagamentoController.getPagamentoDebitoComPagamentoParcial());
            clearAddedPagamento();
        } else if (isDebitoSemPagamentoParcial() && dareViewBeanValidator
                .dareEPagamentosValidator(dadosPagamentoController.getDebitoSemPagamentoParcial()
                        .getDareEPagamentosSelected())) {
            dareViewBean.addAllListaPagamentos(dadosPagamentoController.getDebitoSemPagamentoParcial()
                    .getDareEPagamentosSelected());
            clearAddedPagamento();
        } else if (isDebitoIcms()) {
            dareViewBeanValidator.debitoIcmsFreteValidator(dadosPagamentoController.getDebitoIcmsFrete());
            dareViewBean.addDareEPagamento(dadosPagamentoController.getPagamentoDebitoFreteIcms());
            clearAddedPagamento();
        } else if (isDebitoDiverso()) {
            dareViewBeanValidator.debitoDiversosValidator(dadosPagamentoController.getDebitoDiverso());
            dareViewBean.addDareEPagamento(dadosPagamentoController.getPagamentoDebitoDiverso());
            clearAddedPagamento();
        }

    }

    private void clearAddedPagamento() {
        dareViewBean.setTipoImposto(null);
        dareViewBean.setOrigemDebito(null);
        clearViewBeans();
    }

    /**
     * Remove da {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean#listaPagamentos} um
     * pagamento conforme o {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean#idPagamento}.
     */
    public void removePagamento() {
        dareViewBean.getListaPagamentos().stream()
                .filter(dare -> dare.getId().equals(dareViewBean.getIdPagamento()))
                .findAny()
                .ifPresent(dareEPagamento -> dareViewBean.getListaPagamentos().remove(dareEPagamento));
    }

    //####################################
    // Controle de Lista de Débitos Localizados
    //####################################

    /**
     * Retorna a lista de Débitos Localizados.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais e Débitos de Pagamentos de IPVA.
     *
     * @return lista de Débitos Localizados conforme busca do usuário.
     */
    public List<DareEPagamento> getTableDebitoCC() {
        if (isDebitoIpva()) {
            return dadosPagamentoController.getDebitoIpva().getDareEPagamentos();
        } else if (isDebitoSemPagamentoParcial()) {
            return dadosPagamentoController.getDebitoSemPagamentoParcial().getDareEPagamentos();
        }
        return new ArrayList<>();
    }

    /**
     * Adiciona o débito selecionado na lista de Débitos.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais e Débitos de Pagamentos de IPVA.
     */
    public void addDebitoToSelectedList() {
        if (isDebitoIpva()) {
            dadosPagamentoController.addDebitoToIpva();
        } else if (isDebitoSemPagamentoParcial()) {
            dadosPagamentoController.addDebitoToSemPagamentoParcial();
        }
    }

    /**
     * Remove um débito da lista de selecionados de Débitos Localizados.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais e Débitos de Pagamentos de IPVA.
     */
    public void removeDebitosFromSelectedList() {
        if (isDebitoIpva()) {
            dadosPagamentoController.removeDebitoFromPagamentoIpvaSelectedList();
        } else if (isDebitoSemPagamentoParcial()) {
            dadosPagamentoController.removeDebitoFromSemPagamentoParcialSelectedList();
        }
    }

    /**
     * Adiciona todos os débitos para a lista de selecionados da tabela.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais e Débitos de Pagamentos de IPVA.
     */
    public void addAllDebitosToSelectedList() {
        if (isDebitoIpva()) {
            dadosPagamentoController.addAllDebitosToPagamentoIpva();
        } else if (isDebitoSemPagamentoParcial()) {
            dadosPagamentoController.addAllDebitosToSemPagamentoParcial();
        }
    }

    /**
     * Remove todos os débitos da lista de selecionados da tabela.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais e Débitos de Pagamentos de IPVA.
     */
    public void removeAllDebitosFromSelectedList() {
        if (isDebitoIpva()) {
            dadosPagamentoController.removeAllDebitosFromPagamentoIpva();
        } else if (isDebitoSemPagamentoParcial()) {
            dadosPagamentoController.removeAllDebitosFromSemPagamentoParcial();
        }
    }

    /**
     * Retorna o tamanho da lista de débitos localizados selecionados conforme o painel selecionado.
     * Painel Débitos sem Possibilidade de Pagamentos Parciais e Débitos de Pagamentos de IPVA.
     *
     * @return tamanho da lista.
     */
    public Integer getDebitosSelectedListSize() {
        if (isDebitoIpva()) {
            return dadosPagamentoController.getDebitoIpva().getDareEPagamentosSelected().size();
        } else if (isDebitoSemPagamentoParcial()) {
            return dadosPagamentoController.getDebitoSemPagamentoParcial().getDareEPagamentosSelected().size();
        }
        return NumberUtils.INTEGER_ZERO;
    }

    //####################################
    // Controle de Buscas
    //####################################

    /**
     * Busca a lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} conforme o id do documento do
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoSemPagamentoParcialViewBean#idDocumento}.
     */
    public void searchDocumentoSemPagamentoParcial() {
        DebitoSemPagamentoParcialViewBean debitoSemPagamentoParcial = dadosPagamentoController
                .getDebitoSemPagamentoParcial();
        List<DareEPagamento> dareEPagamentos;
        if (isTipoContribuinteNaoContribuinte()) {
            dareViewBean.setIdentificacaoPessoa(dareViewBean.getIdContribuinteFilter());
        }

        DebitoParcialFilter debitoParcialFilter = new DebitoParcialFilter(debitoSemPagamentoParcial.getIdDocumento(),
                dareViewBean.getIdentificacaoPessoa());

        if (OrigemDebitoEnum.NOTA_AVULSA.equals(dareViewBean.getOrigemDebito())) {
            List<NotaAvulsa> notaAvulsas = getFacade().searchNotasAvulsasByDebitoParcialFilter(debitoParcialFilter);

            if (notaAvulsas.isEmpty()) {
                MessageUtil.addMessage(MessageUtil.ARR, "dare.debitoSemPagamentoParcialViewBean.documento.invalido");
            }

            dareEPagamentos = dadosPagamentoController.convertNotasAvulsasToDareEPagamento(notaAvulsas,
                    debitoSemPagamentoParcial.getInformacaoComplementar());
        } else {
            List<DebitosContaCorrente> debitosContaCorrente =
                    verifyEmptyDebitos("dare.debitoSemPagamentoParcialViewBean.documento.invalido",
                            getFacade().searchDebitosWithFilter(debitoParcialFilter));

            dareEPagamentos = dadosPagamentoController.convertDebitosToDareEPagamento(debitosContaCorrente,
                    debitoSemPagamentoParcial.getInformacaoComplementar());

        }
        debitoSemPagamentoParcial.setDareEPagamentos(dareEPagamentos);
    }

    /**
     * Busca a lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} conforme o ano inicial e
     * ano final {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoIpvaViewBean}.
     */
    public void searchDebitoIpvaByAnoReferencia() {
        DebitoIpvaViewBean debitoIpva = dadosPagamentoController.getDebitoIpva();
        DebitoIpvaFilter debitoIpvaFilter = debitoIpva.getDebitoIpvaFilter();

        if (isTipoContribuinteNaoContribuinte()) {
            dareViewBean.setIdentificacaoPessoa(dareViewBean.getIdContribuinteFilter());
        }

        debitoIpvaFilter.setIdentificacaoPessoa(dareViewBean.getIdentificacaoPessoa());

        List<DebitosContaCorrente> debitosContaCorrente = verifyEmptyDebitos("dare.busca.registro.vazio",
                getFacade().searchDebitoIpvaByAnoReferencia(debitoIpvaFilter));

        List<DareEPagamento> dareEPagamentos = dadosPagamentoController
                .convertDebitosToDareEPagamento(debitosContaCorrente, debitoIpva.getInformacaoComplementar());
        debitoIpva.setDareEPagamentos(dareEPagamentos);
    }

    /**
     * Busca a lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} conforme o id do documento
     * do {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean#idDocumento}.
     */
    public void searchDocumentoComPagamentoParcial() {
        dadosPagamentoController.setValuesReceitasComPagamentoParcial(new DebitosContaCorrente());
        DebitoPagamentoParcialViewBean debitoPagamentoParcial = dadosPagamentoController.getDebitoPagamentoParcial();

        if (isTipoContribuinteNaoContribuinte()) {
            dareViewBean.setIdentificacaoPessoa(dareViewBean.getIdContribuinteFilter());
        }

        DebitoParcialFilter debitoParcialFilter = new DebitoParcialFilter(debitoPagamentoParcial.getIdDocumento(),
                dareViewBean.getIdentificacaoPessoa());

        List<DebitosContaCorrente> debitosContaCorrente =
                verifyEmptyDebitos("dare.debitoSemPagamentoParcialViewBean.documento.invalido",
                        getFacade().searchDebitosWithFilter(debitoParcialFilter));

        debitoPagamentoParcial.setDebitosContaCorrente(debitosContaCorrente);
    }

    private List<DebitosContaCorrente> verifyEmptyDebitos(String message,
            List<DebitosContaCorrente> debitosContaCorrentes) {
        if (CollectionUtils.isEmpty(debitosContaCorrentes)) {
            MessageUtil.addMessage(MessageUtil.ARR, message);
        }
        return debitosContaCorrentes;
    }

    //####################################
    // Controle de Debito com Pagamento Parcial
    //####################################

    /**
     * Busca as {@link br.gov.to.sefaz.arr.persistence.entity.Receitas} conforme
     * lista de {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente} do
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DebitoPagamentoParcialViewBean}.
     *
     * @return lista de receita para cada {@link br.gov.to.sefaz.arr.persistence.view.DebitosContaCorrente}.
     */
    public Collection<Receitas> getReceitasComPagamentoParcial() {
        Set<Integer> idReceitas = dadosPagamentoController.getReceitasForDebitoComPagametoParcial();

        return idReceitas.isEmpty() ? new HashSet<>() : getFacade().searchReceitasWithId(idReceitas);
    }

    //####################################
    // Controle de Debitos Diversos
    //####################################

    /**
     * Carrega os {@link ReceitasTaxas} de acordo com o {@link Receitas#idReceita} selecionado.
     */
    public void loadSubCodigos() {
        DebitoDiversoViewBean debitoDiverso = dadosPagamentoController.getDebitoDiverso();
        Collection<ReceitasTaxas> receitasTaxas = getFacade().searchReceitasTaxasWithReceitaId(debitoDiverso
                .getIdReceita());
        debitoDiverso.setReceitasTaxas(receitasTaxas);
    }

    //####################################
    // Controle de Impressão
    //####################################

    /**
     * Abre a página para impressão do DARE-e, conforme
     * {@link br.gov.to.sefaz.arr.parametros.managedbean.viewbean.dare.DareEViewBean#nossoNumero}.
     *
     * @throws IOException caso não seja possui abrir a página.
     */
    public void abrirPaginaImpressao() throws IOException {
        String printPath = getFacade().getDarePath(dareViewBean.getNossoNumero());
        FacesContext.getCurrentInstance().getExternalContext().redirect(printPath);
    }

    //####################################
    // Informações de Email
    //####################################

    public DareEmailHelper getDareEmailHelper() {
        return dareEmailHelper;
    }

    /**
     * Envia e-mail de acordo com dados informados. E atribui o Nosso Número no {@link DareEmail#nossoNumero} para
     * utilização no link que gerará o PDF para impressão de DARE-e.
     */
    public void sendDareEmail() {
        DareEmail dareEmail = dareEmailHelper.getDareEmail();
        dareEmail.setNossoNumero(getDto().getId());
        getFacade().sendEmail(dareEmail);
        dareEmailHelper = new DareEmailHelper();
        MessageUtil.addMessage(MessageUtil.ARR, "arr.par.dareEletronicoConsolidado.email.success");
    }
}
