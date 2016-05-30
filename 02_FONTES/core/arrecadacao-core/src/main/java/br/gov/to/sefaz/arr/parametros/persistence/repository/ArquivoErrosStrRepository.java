package br.gov.to.sefaz.arr.parametros.persistence.repository;

import br.gov.to.sefaz.arr.parametros.persistence.entity.ArquivoErrosStr;
import br.gov.to.sefaz.arr.parametros.persistence.entity.ArquivoErrosStrPK;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link ArquivoErrosStr}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 16:48:40
 */
@Repository
public interface ArquivoErrosStrRepository extends BaseRepository<ArquivoErrosStr, ArquivoErrosStrPK> {

}
