package br.gov.to.sefaz.seg.persistence.repository;

import br.gov.to.sefaz.persistence.repository.BaseRepository;
import br.gov.to.sefaz.seg.persistence.entity.ListagemCpfProcuracao;
import org.springframework.stereotype.Repository;

/**
 * Repositório de acesso a entidade {@link ListagemCpfProcuracao}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 01/09/2016 14:43:00
 */
@Repository
public class ListagemCpfProcuracaoRepository extends BaseRepository<ListagemCpfProcuracao, String> {
}
