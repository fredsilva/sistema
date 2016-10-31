package br.gov.to.sefaz.cci.persistence.repository;

import br.gov.to.sefaz.cci.persistence.entity.PessoaJuridica;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link org.springframework.stereotype.Repository} responsavel por operações de persistencia de
 * {@link br.gov.to.sefaz.cci.persistence.entity.PessoaJuridica}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 30/08/2016 14:27:00
 *
 */
@Repository
public class PessoaJuridicaRepository extends BaseRepository<PessoaJuridica, String> {
}
