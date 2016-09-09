package br.gov.to.sefaz.seg.managedbean;

import br.gov.to.sefaz.presentation.managedbean.AutowiredMB;
import br.gov.to.sefaz.seg.business.consulta.facade.ConsultaHistoricoNavegacaoFacade;
import br.gov.to.sefaz.seg.business.consulta.service.filter.HistoricoNavegacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao;
import br.gov.to.sefaz.seg.persistence.entity.LogNavegacao;
import br.gov.to.sefaz.seg.persistence.enums.TipoOperacaoEnum;
import br.gov.to.sefaz.util.message.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean da tela de consulta de histórico de navegação no sistema.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">Volceri D Avila</a>
 * @since 22/08/2016 09:08:10
 */
@ManagedBean(name = "consultaHistoricoNavegacaoMB")
@ViewScoped
public class ConsultaHistoricoNavegacaoMB extends AutowiredMB {

    private final HistoricoNavegacaoFilter filter;

    private ConsultaHistoricoNavegacaoFacade facade;

    private List<HistoricoNavegacao> resultList;

    public ConsultaHistoricoNavegacaoMB() {
        this.filter = new HistoricoNavegacaoFilter();
        this.resultList = new ArrayList<>();
    }

    /**
     * Pesquisa os logs de acesso de usuários ao sistema através dos filtros informados em tela.
     */
    public void search() {

        final HistoricoNavegacaoFilter filter = getFilter();
        List<HistoricoNavegacao> resultList = getFacade().findNavigationHistory(filter);

        if (resultList.isEmpty()) {
            MessageUtil.addMessage(MessageUtil.SEG, "geral.pesquisa.vazia");
        }

        setResultList(resultList);
    }

    /**
     * Busca todos os valores possíveis de Tipos de operações de um {@link LogNavegacao}.
     *
     * @return Lista de {@link TipoOperacaoEnum}.
     */
    public TipoOperacaoEnum[] getAllTiposOperacao() {
        return TipoOperacaoEnum.values();
    }


    /**
     * Obtem a lista de logs de acesso de usuários ao sistema.
     *
     * @return Lista de {@link HistoricoNavegacao} representando um acesso realizado ao sistema.
     */
    public List<HistoricoNavegacao> getResultList() {
        return resultList;
    }

    private void setResultList(List<HistoricoNavegacao> resultList) {

        this.resultList = resultList;
    }

    /**
     * Obtém a instância do filtro utilizado na tela de consulta.
     *
     * @return o filtro utilizado na tela de consulta.
     */
    public HistoricoNavegacaoFilter getFilter() {
        return filter;
    }

    private ConsultaHistoricoNavegacaoFacade getFacade() {
        return facade;
    }

    /**
     * Seta a fachada de serviços a ser utilizada por este <i>MB</i> para acesso aos serviços de regra de
     * negócio. Este método é chamado pelo <i>Spring</i> durante a tarefas de injeção de dependência do mesmo.
     *
     * @param facade Instância de {@link ConsultaHistoricoNavegacaoFacade} injetado pelo <i>Spring</i>
     */
    @Autowired
    public void setFacade(ConsultaHistoricoNavegacaoFacade facade) {
        this.facade = facade;
    }

}
