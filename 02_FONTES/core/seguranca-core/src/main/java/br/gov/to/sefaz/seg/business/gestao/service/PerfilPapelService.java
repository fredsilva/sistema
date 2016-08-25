package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapel;
import br.gov.to.sefaz.seg.persistence.entity.PerfilPapelPK;

import java.util.Collection;

/**
 * Serviço da entidade {@link PerfilPapel}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 25/07/2016 17:01:00
 */
public interface PerfilPapelService extends CrudService<PerfilPapel, PerfilPapelPK> {

    /**
     * Remove todos os PerfilPapel com o Id do Perfil.
     * @param id identificação do Perfil.
     */
    void deleteAllWithPerfilId(Long id);

    /**
     * Encontra todos os {@link PerfilPapel} pelo id do {@link br.gov.to.sefaz.seg.persistence.entity.PerfilSistema}.
     * @param id identificação do Perfil.
     * @return lista de PerfilPapel.
     */
    Collection<PerfilPapel> findAllPerfilPapelByPerfil(Long id);

}
