package br.gov.to.sefaz.cci.persistence.repository;

import br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link org.springframework.stereotype.Repository} responsavel por operações de persistencia de
 * {@link br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 17/08/2016 17:32:00
 */
@Repository
public class ContribuinteIcmsRepository extends BaseRepository<ContribuinteIcms, String> {
}
