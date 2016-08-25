package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.ProcuracaoOpcao;

/**
 * Serviço para manipulação da entidade {@link ProcuracaoOpcao}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/08/2016 10:33:00
 */
public interface ProcuracaoOpcaoService extends CrudService<ProcuracaoOpcao, Long> {

    /**
     * Remove todos os registros de uma procuração.
     * @param identificacaoProcurUsuario identificação da procuração
     */
    void deleteByProcuracao(Long identificacaoProcurUsuario);
}
