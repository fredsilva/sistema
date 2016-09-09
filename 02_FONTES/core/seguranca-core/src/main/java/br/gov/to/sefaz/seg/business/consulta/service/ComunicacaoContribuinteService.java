package br.gov.to.sefaz.seg.business.consulta.service;

import br.gov.to.sefaz.seg.business.consulta.service.filter.ComunicacaoContribuinteFilter;
import br.gov.to.sefaz.seg.business.consulta.service.filter.ConsultaComunicacaoSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.List;

/**
 * Interface que define os métodos de negócio para a entidade {@link ComunicacaoContribuinte}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 22/08/2016 10:30:37
 */
public interface ComunicacaoContribuinteService {

    /**
     * Busca as comunicações realizadas com contribuintes de acordo com o filtro informado.
     *
     * @param filter Objeto contendo os valores a serem utilizados como filtro.
     * @return Lista de {@link ComunicacaoContribuinte}.
     */
    List<ComunicacaoContribuinte> findByFilter(ComunicacaoContribuinteFilter filter);

    /**
     * Busca as comunicações realizadas pelo SAT com o usuário informado, filtrando por tipo de comunicação e
     * período em que a comunicação ocorreu.
     *
     * @param filter         Objeto contendo os valores a serem utilizados como filtro.
     * @param usuarioSistema {@link UsuarioSistema} o qual se deseja carregar as comunicações
     * @return Lista de {@link ComunicacaoContribuinte} com os resultados encontrados.
     */
    List<ComunicacaoContribuinte> findSystemCommunicationsForUser(ConsultaComunicacaoSistemaFilter filter,
            UsuarioSistema usuarioSistema);
}
