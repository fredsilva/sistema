package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.DetalheStr;
import br.gov.to.sefaz.arr.persistence.repository.DetalhesStrRepository;
import br.gov.to.sefaz.arr.processamento.service.DetalhesStrService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Implementação do serviço {@link DetalhesStrService}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 08/07/2016 09:47:00
 */
@Service
public class DetalhesStrServiceImpl extends DefaultCrudService<DetalheStr, Long> implements DetalhesStrService {

    @Autowired
    public DetalhesStrServiceImpl(DetalhesStrRepository repository) {
        super(repository);
    }

    @Override
    public BigDecimal sumValorInformativo(LocalDate dataArrecadacao, Integer idBanco, Long idConvenio) {
        return getRepository().findOneColumn("ds", "sum(ds.valorInformativo)", select -> select
                .innerJoin("ds.arquivosStr", "ar")
                .where().equal("ds.idConvenio", idConvenio)
                .and().equal("trunc(ar.dataArrecadacao)", dataArrecadacao));
    }
}
