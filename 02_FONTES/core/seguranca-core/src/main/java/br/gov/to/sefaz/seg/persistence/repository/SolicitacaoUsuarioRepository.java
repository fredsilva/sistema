package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.SolicitacaoUsuario;

import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à base dados da entidade {@link SolicitacaoUsuario}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public interface SolicitacaoUsuarioRepository extends BaseRepository<SolicitacaoUsuario, Long> {

}
