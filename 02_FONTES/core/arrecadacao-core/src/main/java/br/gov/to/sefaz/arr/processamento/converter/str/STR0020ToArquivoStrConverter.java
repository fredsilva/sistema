package br.gov.to.sefaz.arr.processamento.converter.str;

import br.gov.to.sefaz.arr.parametros.business.service.BancoAgenciasService;
import br.gov.to.sefaz.arr.parametros.business.service.BancosService;
import br.gov.to.sefaz.arr.persistence.entity.ArquivosStr;
import br.gov.to.sefaz.arr.persistence.entity.BancoAgencias;
import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.arr.persistence.enums.TipoReceitaStrEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoRecolhimentoStrEnum;
import br.gov.to.sefaz.arr.processamento.converter.DomainToEntityConverter;
import br.gov.to.sefaz.arr.processamento.domain.str.STR0020;
import br.gov.to.sefaz.arr.processamento.domain.str.SituacaoProcessamentoStrEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import static br.gov.to.sefaz.util.xml.ConverterUtil.convertBigDecimal;
import static br.gov.to.sefaz.util.xml.ConverterUtil.convertLocalDate;

/**
 * Implementação para conversão do domínio {@link STR0020} de um Arquivo STR0020 para a entidade {@link ArquivosStr}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 09:41:00
 */
@Component
public class STR0020ToArquivoStrConverter implements DomainToEntityConverter<STR0020, ArquivosStr> {

    private static final String DATA_HORA_PATTERN = "ddMMyyyyHHmmss";
    private static final String DATA_PATTERN = "ddMMyyyy";
    private static final int NR_DIGITOS_DECIMAL = 2;
    private final BancoAgenciasService bancoAgenciasService;
    private final BancosService bancosService;

    @Autowired
    STR0020ToArquivoStrConverter(BancoAgenciasService bancoAgenciasService, BancosService bancosService) {
        this.bancoAgenciasService = bancoAgenciasService;
        this.bancosService = bancosService;
    }

    @Override
    public ArquivosStr convert(STR0020 domain) throws ParseException {
        ArquivosStr arquivosStr = new ArquivosStr();

        arquivosStr.setDataRecepcao(LocalDate.now());
        arquivosStr.setNumeroControleStr(domain.getNumCtrlIF());
        arquivosStr.setDataArrecadacao(convertLocalDate(domain.getDtArrec(), DATA_PATTERN));

        Integer cnpjAgenteBancarioDebidato = Integer.valueOf(domain.getIspbifDebtd());
        List<Bancos> bancoDebitado = this.bancosService.findByCpjRaiz(cnpjAgenteBancarioDebidato);
        if (!bancoDebitado.isEmpty()) {
            arquivosStr.setIdBancoDebitado(bancoDebitado.get(0).getIdBanco());
        }
        Integer cnpjAgenteBancarioCreditado = Integer.valueOf(domain.getIspbifCredtd());
        Integer agenciaCreditada = Integer.valueOf(domain.getAgCredtd());

        List<BancoAgencias> bancoAgenciasCreditado = this.bancoAgenciasService.findByCtaBanco(
                cnpjAgenteBancarioCreditado, agenciaCreditada);
        if (!bancoAgenciasCreditado.isEmpty()) {
            arquivosStr.setIdBancoCreditado(bancoAgenciasCreditado.get(0).getIdBanco());
            arquivosStr.setIdAgenciaCreditada(bancoAgenciasCreditado.get(0).getIdAgencia());
        }
        arquivosStr.setContaCreditada(domain.getCtCredtd());
        arquivosStr.setTipoReceita(TipoReceitaStrEnum.getValue(Integer.valueOf(domain.getTpReceita())));

        arquivosStr.setTipoRecolhimento(TipoRecolhimentoStrEnum.getValue(domain.getTpRecolht()));
        arquivosStr.setValorTotalLancamento(convertBigDecimal(domain.getVlrLanc(), NR_DIGITOS_DECIMAL));
        if (domain.getDtAgendt() != null) {
            String date = domain.getDtAgendt() + domain.getHrAgendt();
            arquivosStr.setDataBacen(convertLocalDate(date, DATA_HORA_PATTERN));
        }
        if (domain.getDtMovto() != null) {
            arquivosStr.setDataMovimento(convertLocalDate(domain.getDtMovto(), DATA_PATTERN));
        }
        arquivosStr.setHistoricoMovimento(domain.getHist());
        arquivosStr.setSituacao(SituacaoProcessamentoStrEnum.NAO_PROCESSADO);

        return arquivosStr;
    }

}
