package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;

import java.util.List;

/**
 * Contrato de acesso do serviço de Bancos.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/06/2016 11:33:00
 */
public interface TipoUsuarioFacade {

    /**
     * Filtro da tela de TipoUsuarios.
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    List<TipoUsuario> find(TipoUsuarioFilter filter);

    /**
     * Filtro da tela de TipoUsuarios.
     * @return a lista que foi encontrada através do filtro.
     */
    List<TipoUsuario> findAllTipoUsuario();
}

