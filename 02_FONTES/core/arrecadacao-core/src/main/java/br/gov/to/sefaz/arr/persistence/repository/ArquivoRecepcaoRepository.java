package br.gov.to.sefaz.arr.persistence.repository;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.persistence.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 18/07/2016 16:25:00
 */
@Repository
public class ArquivoRecepcaoRepository extends BaseRepository<ArquivoRecepcao, Long> {
    @Override
    public ArquivoRecepcao findOne(Long id) {
        return findOne("ar", select -> select
                .innerJoinFetch("ar.bancos", "ba")
                .innerJoinFetch("ar.conveniosArrecadacao", "ca")
                .innerJoinFetch("ca.bancoAgencias", "ba")
                .where().equal("ar.idArquivos", id));
    }
}
