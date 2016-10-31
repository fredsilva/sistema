package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PerfilSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;

import java.util.Collection;
import java.util.List;

/**
 * Serviço da entidade {@link PerfilSistema}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 25/07/2016 17:01:00
 */
public interface PerfilSistemaService extends CrudService<PerfilSistema, Long> {
    /**
     * Busca uma lista de {@link PerfilSistema} de acordo com o
     * {@link br.gov.to.sefaz.seg.persistence.entity.PapelSistema#identificacaoPapel}.
     * @param idPapel {@link br.gov.to.sefaz.seg.persistence.entity.PapelSistema#identificacaoPapel}
     * @return lista de {@link PerfilSistema}.
     */
    List<PerfilSistema> findAllPerfilSistemaByPapel(Long idPapel);

    /**
     * Busca todos os {@link PerfilSistema} pelo filtro passado em tela.
     * @return lista de {@link PerfilSistema}.
     */
    Collection<PerfilSistema> findAllPerfilSistema(PerfilSistemaFilter filter);

    /**
     * Busca um {@link PerfilSistema} com suas chaves estrangeiras.
     * @param id identificação do Perfil.
     * @return Perfil com os dados completos.
     */
    PerfilSistema findOneComplete(Long id);

    /**
     * Salva ou atualiza o {@link PerfilSistema}.
     * @param dto Perfil a ser salvo.
     * @return Perfil Salvo.
     */
    PerfilSistema saveOrUpdatePerfilSistema(PerfilSistema dto);
}
