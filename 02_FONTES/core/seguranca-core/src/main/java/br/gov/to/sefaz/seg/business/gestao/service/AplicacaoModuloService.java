package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.AplicacaoModulo;

import java.util.Collection;

/**
 * Contrato de serviço da entidade {@link AplicacaoModulo}.
 *
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 19/07/2016 15:16:00
 */
public interface AplicacaoModuloService extends CrudService<AplicacaoModulo, Long> {
    /**
     * Busca todos as aplicações através do @{link ModuloSistema#identificacaoModuloSistema}.
     *
     * @return lista de aplicações encontradas
     */
    Collection<AplicacaoModulo> findByModuloSistema(Long identificacaoModuloSistema);
}
