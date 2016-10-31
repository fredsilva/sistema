package br.gov.to.sefaz.seg.business.consulta.service;

import br.gov.to.sefaz.seg.business.consulta.service.filter.HistoricoNavegacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoNavegacao;

import java.util.List;

/**
 * Contrato de acesso do serviço de Histórico de Navegação do Sistema.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 01/09/2016 17:42:06
 */
public interface HistoricoNavegacaoService {

    /**
     * Busca os logs de navegação no sistema (histórico) utilizando os filtro definidos por parâmetro.
     *
     * @param filter Objeto contendo os valores a serem utilizados como filtro.
     * @return Lista de {@link HistoricoNavegacao}.
     */
    List<HistoricoNavegacao> findNavigationHistory(HistoricoNavegacaoFilter filter);
}
