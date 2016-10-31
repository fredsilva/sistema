package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.ArquivosStr;
import br.gov.to.sefaz.arr.persistence.repository.ArquivosStrRepository;
import br.gov.to.sefaz.arr.processamento.domain.str.SituacaoProcessamentoStrEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivosStrService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Implementação do serviço de Arquivos STR {@link ArquivosStrService}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 06/07/2016 18:50:00
 */
@Service
public class ArquivosStrServiceImpl extends DefaultCrudService<ArquivosStr, Long>
        implements ArquivosStrService {

    @Autowired
    public ArquivosStrServiceImpl(ArquivosStrRepository repository) {
        super(repository);
    }


    @Override
    public List<ArquivosStr> findByNumeroControle(String numeroControleStr) {
        return getRepository().find(hqlSelectBuilder -> hqlSelectBuilder
                .where()
                .equal("numeroControleStr", numeroControleStr)
                .and().equal("situacao", SituacaoProcessamentoStrEnum.PROCESSADO));
    }

    @Override
    public long countToConciliacao(LocalDate dataArrecadacao, Integer idBanco, Long idConvenio) {
        return getRepository().count(hqlSelectBuilder -> hqlSelectBuilder
                .where()
                .equal("dataArrecadacao", dataArrecadacao)
                .and().equal("idBancoDebitado", idBanco)
                .and().equal("idConvenio", idConvenio));
    }
}
