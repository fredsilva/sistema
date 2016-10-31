package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.repository.ArquivoRecepcaoRepository;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 15:34:00
 */
@SuppressWarnings("PMD")
@Service
public class ArquivoRecepcaoServiceImpl extends DefaultCrudService<ArquivoRecepcao, Long>
        implements ArquivoRecepcaoService {

    @Autowired
    public ArquivoRecepcaoServiceImpl(ArquivoRecepcaoRepository repository) {
        super(repository);
    }

    @Override
    public List<ArquivoRecepcao> findToConciliacao(LocalDateTime dataArrecadacao, Integer idBanco, Long idConvenio) {
        return getRepository().find(hqlSelectBuilder -> hqlSelectBuilder
                .where().equal("trunc(dataProcessamento)", dataArrecadacao)
                .and().equal("idBanco", idBanco)
                .and().equal("idConvenio", idConvenio));
    }

    @Override
    public boolean existsArquivoConsolidadoWith(Integer idBanco, LocalDate dataGeracaoArquivo, Long idConvenio) {
        return getRepository().exists(hqlSelectBuilder -> hqlSelectBuilder
                .where().equal("dataArquivo", dataGeracaoArquivo)
                .and().equal("idBanco", idBanco)
                .and().equal("idConvenio", idConvenio)
                .and().equal("caracteristicaArquivo", TipoArquivoEnum.CONSOLIDADO));
    }

    @Override
    public boolean existsNumeroSequencial(Long numeroSequencial) {
        return getRepository().exists(select -> select
                .where()
                .equal("sequencialNsa", numeroSequencial));
    }

    @Override
    public Long getLastNumeroSequencialBy(Long codigoConvenio, Integer codigoBanco, LocalDate dataGeracaoArquivo) {
        ArquivoRecepcao arquivoRecepcao = getRepository().findOne(select -> select
                .where()
                .equal("idConvenio", codigoConvenio)
                .and()
                .equal("idBanco", codigoBanco)
                .and()
                .equal("dataArquivo", dataGeracaoArquivo)
                .orderBy("sequencialNsa", Order.DESC));

        return Objects.isNull(arquivoRecepcao) ? null :
                arquivoRecepcao.getSequencialNsa();
    }
}
