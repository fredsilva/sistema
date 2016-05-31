package br.gov.to.sefaz.seg.business.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.Collection;

/**
 * Contratato de acesso do serviço de Usuários do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:46
 */
public interface UsuarioSistemaService extends CrudService<UsuarioSistema, String> {

    Collection<UsuarioSistema> findAllByCpfAndName(Long cpf, String nome);
}
