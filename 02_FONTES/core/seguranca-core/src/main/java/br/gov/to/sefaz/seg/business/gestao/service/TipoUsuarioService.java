package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.seg.persistence.domain.TipoUsuario}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 15/06/2016 13:43:00
 */
public interface TipoUsuarioService {

    /**
     * Busca todos os tipos de usuários pela descrição.
     * @param filter descrição do usuário preenchido em tela.
     * @return lista de tipos de usuários.
     */
    List<TipoUsuario> findByFilter(TipoUsuarioFilter filter);

    /**
     * Busca todos os tipos de usuários.
     * @return lista de tipos de usuários.
     */
    List<TipoUsuario> findAll();

    /**
     * Busca todos os tipos de usuários com o somatório de usuários para cada tipo.
     * @return lista de tipos de usuários.
     */
    List<TipoUsuario> findAllCountUsers();

}
