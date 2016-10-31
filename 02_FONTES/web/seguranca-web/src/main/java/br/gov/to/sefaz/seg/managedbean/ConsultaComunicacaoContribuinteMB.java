package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.AutowiredMB;
import br.gov.to.sefaz.seg.business.consulta.facade.ComunicacaoContribuinteFacade;
import br.gov.to.sefaz.seg.business.consulta.service.filter.ComunicacaoContribuinteFilter;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da tela de Consulta de Comunicações com os Contribuintes.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">Volceri D Avila</a>
 * @since 22/08/2016 09:08:10
 */
@ManagedBean(name = "consultaComunicacaoContribuinteMB")
@ViewScoped
public class ConsultaComunicacaoContribuinteMB extends AutowiredMB {

    private final ComunicacaoContribuinteFilter filter;

    private ComunicacaoContribuinteFacade facade;

    private List<ComunicacaoContribuinte> resultList;

    public ConsultaComunicacaoContribuinteMB() {
        this.filter = new ComunicacaoContribuinteFilter();
        this.resultList = new ArrayList<>();
    }


    /**
     * Pesquisa as comunicações realizadas com os contribuintes através do filtros informados em tela.
     */
    public void search() {
        List<ComunicacaoContribuinte> resultList = getFacade().findByFilter(getFilter());

        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    /**
     * Busca todos os tipos possíveis de comunicação com o contribuinte.
     *
     * @return Lista de {@link br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum}.
     */
    public TipoComunicacaoEnum[] getAllTiposComunicacao() {
        return TipoComunicacaoEnum.values();
    }


    /**
     * Obtem a lista de comunicações realizadas com os contribuintes.
     *
     * @return Lista de {@link br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte} com as comunicações
     *         realizadas com os contribuintes.
     */
    public List<ComunicacaoContribuinte> getResultList() {
        return resultList;
    }

    private void setResultList(List<ComunicacaoContribuinte> resultList) {

        this.resultList = resultList;
    }

    /**
     * Obtém a instância do filtro utilizado na tela de consulta.
     *
     * @return o filtro utilizado na tela de consulta.
     */
    public ComunicacaoContribuinteFilter getFilter() {
        return filter;
    }

    @Autowired
    public void setFacade(ComunicacaoContribuinteFacade facade) {
        this.facade = facade;
    }

    public ComunicacaoContribuinteFacade getFacade() {
        return facade;
    }
}
