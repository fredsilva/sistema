package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.AutowiredMB;
import br.gov.to.sefaz.seg.business.consulta.facade.ConsultaComunicacaoSistemaFacade;
import br.gov.to.sefaz.seg.business.consulta.service.filter.ConsultaComunicacaoSistemaFilter;
import br.gov.to.sefaz.seg.managedbean.viewbean.ComunicacaoSistemaViewBean;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum;
import br.gov.to.sefaz.seg.persistence.enums.TipoPeriodoConsultaComunicacaoEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * ManagedBean da tela de Consulta de Comunicações do Sistema.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">Volceri D Avila</a>
 * @since 29/08/2016 09:08:10
 */
@ManagedBean(name = "consultaComunicacaoSistemaMB")
@ViewScoped
public class ConsultaComunicacaoSistemaMB extends AutowiredMB {


    private static final String PARAM_TIPO_COMUNICACAO = "tipoComunicacao";
    private static final int DEFAULT_LAST_30_DAYS = 30;
    private final ConsultaComunicacaoSistemaFilter filter;

    private ConsultaComunicacaoSistemaFacade facade;

    private List<ComunicacaoSistemaViewBean> resultList;

    public ConsultaComunicacaoSistemaMB() {
        this.filter = new ConsultaComunicacaoSistemaFilter();
        this.resultList = new ArrayList<>();
    }

    /**
     * Inicializa o managed bean auto populando as comunicações realizadas pelo SAT com o usuário logado no sistema.
     * Na inicialização é verificado se o tipo de comunicação foi informado por parâmetro via <code>HTTP GET</code>.
     * Caso o tipo de comunicação tenha sido informado e o mesmo seja um código de <code>enum</code> válido definido em
     * {@link TipoComunicacaoEnum}, será então pesquisadas as comunicações realizadas pelo SAT utilizando parâmetros
     * default definidos na regra de negócio.
     */
    @PostConstruct
    public void prePopulateCommunications() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        String codeTipoComunicacao = context.getRequestParameterMap().get(PARAM_TIPO_COMUNICACAO);

        if (StringUtils.isNotEmpty(codeTipoComunicacao)) {
            try {
                setDefaultFilterValuesForTipoComunicacao(TipoComunicacaoEnum.getValue(codeTipoComunicacao));

                search();
            } catch (IllegalArgumentException e) {
                MessageUtil.addMessage(MessageUtil.SEG,
                        "seg.consulta.consultaComunicacaoSistema.pesquisa.tipoMensagem.invalido");
            }
        }
    }

    /**
     * Pesquisa as comunicações realizas pelo SAT para o {@link UsuarioSistema} logado através do filtros informados
     * em tela.
     */
    public void search() {
        UsuarioSistema loggedUser = getFacade().getUsuarioSistema();
        List<ComunicacaoContribuinte> resultList = getFacade().findSystemCommunicationsForUser(loggedUser, getFilter());
        List<ComunicacaoSistemaViewBean> viewBeanResults = convertToViewBeanList(resultList);

        setResultList(viewBeanResults);
    }


    /**
     * Busca todos os tipos possíveis de comunicação para serem utilizados como filtro.
     *
     * @return Lista de {@link br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum}.
     */
    public TipoComunicacaoEnum[] getAllTiposComunicacao() {
        return TipoComunicacaoEnum.values();
    }


    /**
     * Obtem a lista de comunicações realizadas pelo SAT com o {@link UsuarioSistema}.
     *
     * @return Lista de {@link ComunicacaoSistemaViewBean} com as comunicações realizadas para o usuário logado.
     */
    public List<ComunicacaoSistemaViewBean> getResultList() {
        return resultList;
    }

    private void setResultList(List<ComunicacaoSistemaViewBean> resultList) {

        this.resultList = resultList;
    }

    /**
     * Obtém a instância do filtro utilizado na tela de consulta.
     *
     * @return o filtro utilizado na tela de consulta.
     */
    public ConsultaComunicacaoSistemaFilter getFilter() {
        return filter;
    }

    private ConsultaComunicacaoSistemaFacade getFacade() {
        return facade;
    }

    @Autowired
    public void setFacade(ConsultaComunicacaoSistemaFacade facade) {
        this.facade = facade;
    }

    public TipoPeriodoConsultaComunicacaoEnum[] getAllTiposPeriodosFiltro() {
        return TipoPeriodoConsultaComunicacaoEnum.values();
    }

    private List<ComunicacaoSistemaViewBean> convertToViewBeanList(List<ComunicacaoContribuinte> resultList) {
        return resultList.stream()
                .map(ComunicacaoSistemaViewBean::new)
                .collect(Collectors.toList());
    }


    private void setDefaultFilterValuesForTipoComunicacao(TipoComunicacaoEnum tipoComunicacao) {
        filter.setDataInicial(LocalDate.now().minusDays(DEFAULT_LAST_30_DAYS));
        filter.setDataFinal(LocalDate.now());
        filter.setTipoComunicacao(tipoComunicacao);
        filter.setTipoPeriodo(TipoPeriodoConsultaComunicacaoEnum.ULTIMO_30);
    }
}
