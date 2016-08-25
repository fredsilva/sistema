package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PapelSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcao;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Contrato de acesso do serviço de PapelSistema.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 22/07/2016 15:07:00
 */
public interface PapelSistemaFacade extends CrudFacade<PapelSistema, Long> {

    /**
     * Filtro da tela de PapelSistema.
     * @param filter Objeto para ser usado no filtro.
     * @return a lista que foi encontrada através do filtro.
     */
    Collection<PapelSistema> findByFilter(PapelSistemaFilter filter);

    /**
     * Busca lista de {@link PapelSistema}.
     * @return lista de {@link PapelSistema}.
     */
    Collection<PapelSistema> findAllPapelSistema();

    /**
     * Busca lista de {@link PapelOpcao}.
     * @return lista de {@link PapelOpcao}.
     */
    List<OpcaoAplicacao> findAllOpcaoAplicacao();

    /**
     * Salva um {@link PapelSistema}.
     * @param dto papel enviado pela tela.
     * @return PapelSistema salvo.
     */
    PapelSistema saveOrUpdatePapelSistema(PapelSistema dto);

    /**
     * Busca um {@link PapelSistema} pelo ID.
     * @param id do Papel.
     * @return {@link PapelSistema}.
     */
    PapelSistema findOneCounted(Long id);

    /**
     * Busca as Opções do {@link PapelSistema} pelo {@link PapelSistema#identificacaoPapel}.
     * @param idPapel identificação do Papel.
     * @return Lista de PapelOpcao.
     */
    Set<PapelOpcao> findAllPapelOpcaoById(Long idPapel);

    /**
     * Busca todos os {@link PerfilSistema} pelo {@link PapelSistema#identificacaoPapel}.
     * @param id {@link PapelSistema#identificacaoPapel}.
     * @return Lista de Perfis.
     */
    Collection<PerfilSistema> findAllPerfilByPapel(Long id);
}

