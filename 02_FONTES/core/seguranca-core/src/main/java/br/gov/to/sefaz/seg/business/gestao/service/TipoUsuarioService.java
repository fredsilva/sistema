package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link TipoUsuario}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/06/2016 13:43:00
 */
public interface TipoUsuarioService extends CrudService<TipoUsuario, Integer> {

    /**
     * busca todos os tipos de usuários pela descrição.
     * @param filter descrição do usuário preenchido em tela.
     * @return lista de usuários.
     */
    List<TipoUsuario> findAllByDescricao(TipoUsuarioFilter filter);
}
