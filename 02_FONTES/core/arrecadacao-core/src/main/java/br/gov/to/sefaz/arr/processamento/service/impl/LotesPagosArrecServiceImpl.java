package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.enums.EstadoLoteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum;
import br.gov.to.sefaz.arr.persistence.repository.LotesPagosArrecRepository;
import br.gov.to.sefaz.arr.processamento.service.LotesPagosArrecService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 11:07:00
 */
@Service
public class LotesPagosArrecServiceImpl extends DefaultCrudService<LotesPagosArrec, Long>
        implements LotesPagosArrecService {

    private static final String ID_BANCO = "idBanco";
    private static final String ID_AGENCIA = "idAgencia";
    private static final String ID_CONVENIO = "idConvenio";
    private static final String DATA_PROCESSAMENTO = "dataProcessamento";
    private static final String TIPO = "tipo";
    private static final String ESTADO_LOTE = "estadoLote";

    @Autowired
    public LotesPagosArrecServiceImpl(LotesPagosArrecRepository repository) {
        super(repository);
    }

    @Override
    public LotesPagosArrec findBdar(Integer codigoBanco, Integer codigoAgencia, Long codigoConvenio,
            LocalDateTime dataArrecadacao) {
        return getRepository().findOne(select -> select
                .where()
                .equal(ID_BANCO, codigoBanco)
                .and()
                .equal(ID_AGENCIA, codigoAgencia)
                .and()
                .equal(ID_CONVENIO, codigoConvenio)
                .and()
                .equal(DATA_PROCESSAMENTO, dataArrecadacao)
                .and()
                .equal(TIPO, TipoLotePagosEnum.BDAR)
                .and()
                .equal(ESTADO_LOTE, EstadoLoteEnum.ABERTO));
    }


    @Override
    public LotesPagosArrec findTpar(Integer codigoBanco, Integer codigoAgencia, Long codigoConvenio,
            LocalDateTime dataArrecadacao) {
        return getRepository().findOne(select -> select
                .where()
                .equal(ID_BANCO, codigoBanco)
                .and()
                .equal(ID_AGENCIA, codigoAgencia)
                .and()
                .equal(ID_CONVENIO, codigoConvenio)
                .and()
                .equal(DATA_PROCESSAMENTO, dataArrecadacao)
                .and()
                .equal(TIPO, TipoLotePagosEnum.TPAR)
                .and()
                .equal(ESTADO_LOTE, EstadoLoteEnum.ABERTO));
    }

    @Override
    public List<LotesPagosArrec> findBdarConcilicao(Integer codigoBanco, Long codigoConvenio,
            LocalDateTime dataArrecadacao) {
        LocalDateTime dataFinal = LocalDateTime.of(dataArrecadacao.toLocalDate(), LocalTime.MAX);

        return getRepository().find(select -> select
                .where()
                .equal(ID_BANCO, codigoBanco)
                .and()
                .equal(ID_CONVENIO, codigoConvenio)
                .and().between("trunc(" + DATA_PROCESSAMENTO + ")", dataArrecadacao, dataFinal)
                .and()
                .equal(TIPO, TipoLotePagosEnum.BDAR));
    }

    @Override
    public List<LotesPagosArrec> findTparConcilicao(Integer codigoBanco, Long codigoConvenio,
            LocalDateTime dataArrecadacao) {
        LocalDateTime dataFinal = LocalDateTime.of(dataArrecadacao.toLocalDate(), LocalTime.MAX);

        return getRepository().find(select -> select
                .where().equal(ID_BANCO, codigoBanco)
                .and().equal(ID_CONVENIO, codigoConvenio)
                .and().between("trunc(" + DATA_PROCESSAMENTO + ")", dataArrecadacao, dataFinal)
                .and().equal(TIPO, TipoLotePagosEnum.TPAR));
    }

}
