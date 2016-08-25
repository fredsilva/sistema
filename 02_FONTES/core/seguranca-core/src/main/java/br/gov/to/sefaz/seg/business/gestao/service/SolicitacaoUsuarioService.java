package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.SolicitacaoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

/**
 * Contrato de acesso do serviço de Solicitação de Usuário.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 28/06/2016 10:52:00
 */
public interface SolicitacaoUsuarioService extends CrudService<SolicitacaoUsuario, Long> {

    /**
     * Salva a solicitação de um novo usuário com o Tipo de Usuário Contador.
     * @param usuarioSistema da tela de manutenção.
     */
    void save(UsuarioSistema usuarioSistema);
}
