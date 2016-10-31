package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.PapelSistemaFilter;
import br.gov.to.sefaz.seg.persistence.entity.PapelSistema;
import br.gov.to.sefaz.seg.persistence.entity.PerfilSistema;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de Papeis do Sistema.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 22/07/2016 16:55:00
 */
public interface PapelSistemaService extends CrudService<PapelSistema, Long> {

    /**
     * Lista que retorna todos os {@link PapelSistema} de acordo com o filtro passado.
     * @param filter da tela.
     * @return Lista de Papel Sistema.
     */
    List<PapelSistema> findAllPerfilPapelOpcao(PapelSistemaFilter filter);

    /**
     * Salva ou atualiza um {@link PapelSistema}.
     * @param dto {@link PapelSistema}.
     * @return {@link PapelSistema} atualizado.
     */
    PapelSistema saveOrUpdatePapelSistema(PapelSistema dto);

    /**
     * Busca um único {@link PapelSistema} de acordo com a documentação (com contadores).
     * @param id {@link PapelSistema#identificacaoPapel}.
     * @return {@link PapelSistema}
     */
    PapelSistema findOneCounted(Long id);

    /**
     * Lista que retorna todos os {@link PapelSistema}.
     * @return Lista de Papel Sistema.
     */
    Collection<PapelSistema> findAllPapeisPerfil();

    /**
     * Busca todos os {@link PapelSistema} pelo Id do {@link PerfilSistema}.
     * @param id identificação do {@link PerfilSistema}.
     * @return Lista de {@link PapelSistema}.
     */
    Collection<PapelSistema> findAllPapeisByPerfilId(Long id);
}
