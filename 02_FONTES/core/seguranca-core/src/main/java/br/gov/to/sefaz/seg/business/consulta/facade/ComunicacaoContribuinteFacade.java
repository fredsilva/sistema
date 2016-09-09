package br.gov.to.sefaz.seg.business.consulta.facade;

import br.gov.to.sefaz.seg.business.consulta.service.filter.ComunicacaoContribuinteFilter;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;

import java.util.List;

/**
 * Interface que define os métodos de fachada para a entidade
 * {@link br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 15:32:00
 */
public interface ComunicacaoContribuinteFacade {
    /**
     * Busca as comunicações realizadas com contribuintes de acordo com o filtro informado.
     *
     * @param filter Objeto contendo os valores a serem utilizados como filtro.
     * @return Lista de {@link br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte}.
     */
    List<ComunicacaoContribuinte> findByFilter(ComunicacaoContribuinteFilter filter);
}
