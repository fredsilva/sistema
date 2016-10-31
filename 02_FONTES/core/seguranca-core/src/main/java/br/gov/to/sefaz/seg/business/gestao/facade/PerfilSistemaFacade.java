package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PerfilSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapel;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de PerfilSistema.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 29/07/2016 15:08:00
 */
public interface PerfilSistemaFacade extends CrudFacade<PerfilSistema, Long> {

    /**
     * Filtro da tela de PerfilSistema.
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    Collection<PerfilSistema> findByFilter(PerfilSistemaFilter filter);

    /**
     * Busca lista de {@link PerfilSistema}.
     * @return lista de {@link PerfilSistema}.
     */
    Collection<PerfilSistema> findAllPerfilSistema(PerfilSistemaFilter filter);

    /*
     * Busca lista de {@link PapelOpcao}.
     * @return lista de {@link PapelOpcao}.

    List<OpcaoAplicacao> findAllPapelOpcao();
    */
    /**
     * Salva um {@link PerfilSistema}.
     * @param dto perfil enviado pela tela.
     * @return PapelSistema salvo.
     */
    PerfilSistema saveOrUpdatePerfilSistema(PerfilSistema dto);

    /**
     * Busca um {@link PerfilSistema} pelo ID.
     * @param id do Perfil.
     * @return {@link PerfilSistema}.
     */
    PerfilSistema findOneComplete(Long id);

    /*
     * Busca as Opções do {@link PapelSistema} pelo {@link PapelSistema#identificacaoPapel}.
     * @param idPapel identificação do Papel.
     * @return Lista de PapelOpcao.

    Set<PapelOpcao> findAllPapelOpcaoById(Long idPapel);
    */
    /**
     * Busca todos os {@link PapelSistema} pelo {@link PerfilSistema#identificacaoPerfil}.
     * @param id {@link PerfilSistema#identificacaoPerfil}.
     * @return Lista de Perfis.
     */
    Collection<PapelSistema> findAllPapelByPerfil(Long id);

    /**
     * Busca todos os {@link PerfilPapel} pelo {@link PerfilSistema#identificacaoPerfil}.
     * @param id {@link PerfilSistema#identificacaoPerfil}.
     * @return Lista de Perfis.
     */
    Collection<PerfilPapel> findAllPerfilPapelByPerfil(Long id);

    /**
     * Busca todos os Papéis do Sistema.
     * @return lista de {@link PapelSistema}.
     */
    Collection<PapelSistema> findAllPapelSistema();

    /**
     * Busca todos os {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil} pelo ID do perfil.
     * @param identificacaoPerfil identificação do {@link PerfilSistema}.
     * @return Lista de UsuarioPerfil.
     */
    Collection<UsuarioPerfil> findAllUsuariosByPerfil(Long identificacaoPerfil);
}

