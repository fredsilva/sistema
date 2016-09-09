package br.gov.to.sefaz.seg.business.consulta.facade;

import br.gov.to.sefaz.seg.business.consulta.service.filter.ConsultaComunicacaoSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.List;

/**
 * Interface que define os métodos de fachada para consultas a entidade {@link ComunicacaoContribuinte}.
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 29/08/2016 13:35:00
 */
public interface ConsultaComunicacaoSistemaFacade {

    /**
     * Obtém o usuário do sistema logado.
     *
     * @return o usuário logado.
     */
    UsuarioSistema getUsuarioSistema();

    /**
     * Busca todas comunicações realizadas pelo SAT para o {@link UsuarioSistema} logado de acordo com o filtro
     * informado.
     *
     * @param usuarioSistema {@link UsuarioSistema} logado no sistema.
     * @param filter         Objeto contendo os valores a serem utilizados como filtro.
     * @return Lista de {@link ComunicacaoContribuinte}.
     */
    List<ComunicacaoContribuinte> findSystemCommunicationsForUser(UsuarioSistema usuarioSistema,
            ConsultaComunicacaoSistemaFilter filter);
}
