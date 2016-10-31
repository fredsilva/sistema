package br.gov.to.sefaz.seg.business.consulta.facade;

import br.gov.to.sefaz.seg.business.consulta.service.filter.HistoricoNavegacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao;

import java.util.List;

/**
 * Interface que define os métodos de fachada para acesso ao serviço
 * {@link br.gov.to.sefaz.seg.business.consulta.service.HistoricoNavegacaoService}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 02/09/2016 10:26:00
 */
public interface ConsultaHistoricoNavegacaoFacade {

    /**
     * Busca os logs de navegação no sistema utilizando os filtro definidos por parâmetro.
     *
     * @param filter Objeto contendo os valores a serem utilizados como filtro.
     * @return Lista de {@link br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao}.
     */
    List<HistoricoNavegacao> findNavigationHistory(HistoricoNavegacaoFilter filter);
}
