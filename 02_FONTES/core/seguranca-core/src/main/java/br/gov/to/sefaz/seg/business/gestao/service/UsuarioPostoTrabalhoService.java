package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalhoPK;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Contrato de acesso do serviço de Usuários do Sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:46
 */
@Service
public interface UsuarioPostoTrabalhoService extends CrudService<UsuarioPostoTrabalho, UsuarioPostoTrabalhoPK> {

    /**
     * Remove referência do {@link br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema} com o {@link PostoTrabalho}.
     * @param cpf do usuário.
     * @param postoTrabalho {@link PostoTrabalho}.
     * @return {@link Optional} de UsuarioPostoTrabalho.
     */
    Optional<UsuarioPostoTrabalho> removeUsuarioPostoTrabalho(String cpf, PostoTrabalho postoTrabalho);
}
