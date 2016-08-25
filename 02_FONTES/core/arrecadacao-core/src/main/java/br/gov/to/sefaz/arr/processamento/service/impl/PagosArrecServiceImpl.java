package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.PagosArrec;
import br.gov.to.sefaz.arr.persistence.entity.PagosArrecPK;
import br.gov.to.sefaz.arr.persistence.repository.PagosArrecRepository;
import br.gov.to.sefaz.arr.processamento.service.PagosArrecService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/07/2016 10:38:00
 */
@Service
public class PagosArrecServiceImpl extends DefaultCrudService<PagosArrec, PagosArrecPK>
        implements PagosArrecService {

    @Autowired
    public PagosArrecServiceImpl(PagosArrecRepository repository) {
        super(repository);
    }

    @Override
    public int getLastOrdemLoteTpar(Long idBdarTpar) {
        PagosArrec pagosArrec = getRepository().findOne(hqlSelectBuilder -> hqlSelectBuilder
                .where()
                .equal("idBdarTpar", idBdarTpar)
                .orderBy("ordemLote", Order.DESC));
        return Objects.isNull(pagosArrec) ? 0 : pagosArrec.getOrdemLote();
    }

    @Override
    public boolean existsPagosArrecWith(String nsu, Integer codigoBanco, LocalDateTime dataArrecadacao,
            BigDecimal valorTotal) {
        return getRepository().exists(hqlSelectBuilder -> hqlSelectBuilder
                .where()
                .equal("nsuBarra", nsu)
                .and()
                .equal("lotesPagosArrec.idBanco", codigoBanco)
                .and()
                .equal("dataPagamento", dataArrecadacao)
                .and()
                .equal("valorTotal", valorTotal));
    }
}
