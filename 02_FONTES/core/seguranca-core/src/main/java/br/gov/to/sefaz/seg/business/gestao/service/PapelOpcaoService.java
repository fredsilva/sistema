package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcao;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcaoPK;

import java.util.Set;

/** Serviço da entidade {@link PapelOpcao}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 25/07/2016 17:01:00
 */
public interface PapelOpcaoService extends CrudService<PapelOpcao, PapelOpcaoPK> {

    /**
     * Busca lista de todos os PapelOpcao pelo ID do Papel.
     * @param id do Papel.
     * @return Lista de PapelOpcao.
     */
    Set<PapelOpcao> findAllPapelOpcao(Long id);

    /**
     * Remove todos os {@link PapelOpcao} pelo
     * {@link br.gov.to.sefaz.seg.persistence.entity.PapelSistema#identificacaoPapel}.
     * @param id identificacaoPapel
     */
    void removeAllPapelOpcaoByPapelId(Long id);
}
