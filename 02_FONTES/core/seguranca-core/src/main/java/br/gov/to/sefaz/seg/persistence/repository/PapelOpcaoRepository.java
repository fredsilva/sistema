package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcao;
import br.gov.to.sefaz.seg.persistence.entity.PapelOpcaoPK;

import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso à base dados da entidade {@link PapelOpcao}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 17:41:56
 */
@Repository
public interface PapelOpcaoRepository extends BaseRepository<PapelOpcao, PapelOpcaoPK> {

}
