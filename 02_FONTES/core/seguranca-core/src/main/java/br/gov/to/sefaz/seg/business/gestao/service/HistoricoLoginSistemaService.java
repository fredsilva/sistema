package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoLoginSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

/**
 * Contrato de acesso do serviço de Histórico de Logins do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:55
 */
public interface HistoricoLoginSistemaService extends CrudService<HistoricoLoginSistema, Long> {

    /**
     * Salva o histórico de login do {@link UsuarioSistema}.
     *
     * @param usuarioSistema Usuário que está logando
     * @return Registro Histórico de Login
     */
    HistoricoLoginSistema saveHistoricoLoginSistema(UsuarioSistema usuarioSistema);

}
