package br.gov.to.sefaz.arr.processamento.creator;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.processamento.domain.FileContent;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe que auxilia a criação de {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos} para
 * o processamento do conteúdo do detalhe do arquivo de arrecadação Simples Nacional.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 11:51:00
 */
@SuppressWarnings("PMD")
@Component
public class ArquivoDetalhePagosSNCreator {

    private static final String CODIGO_BARRA = StringUtils.EMPTY;
    private static final FormaPagamentoEnum FORMA_PAGAMENTO = FormaPagamentoEnum.INTERNET;

    private final ConveniosTarifasService conveniosTarifasService;

    @Autowired
    public ArquivoDetalhePagosSNCreator(ConveniosTarifasService conveniosTarifasService) {
        this.conveniosTarifasService = conveniosTarifasService;
    }

    /**
     * Cria um {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos} conforme o
     * {@link br.gov.to.sefaz.arr.processamento.domain.FileContent} fornecido e
     * {@link br.gov.to.sefaz.arr.processamento.domain.detalhe.simplesnacional.FileDetalheSN}.
     *
     * @param fileContent   contém as informações de número da linha e identificação do arquivo
     * @param fileDetalheSN contém informações relevantes para a linha que está sendo lida do arquivo de Simples
     *                      Nacional
     * @return um novo {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos}.
     */
    public ArquivoDetalhePagos create(FileContent fileContent, FileDetalheSN fileDetalheSN) {
        Long codigoConvenio = fileContent.getFileHeader().getCodigoConvenio();
        LocalDate dataArrecadacao = fileDetalheSN.getDataArrecadacao();
        BigDecimal valorTarifaConvenio = conveniosTarifasService.getValorTarifaBy(codigoConvenio, FORMA_PAGAMENTO,
                dataArrecadacao);

        Long idArquivo = fileContent.getIdArquivo();
        int currentLine = fileContent.getCurrentLine();
        String numeroSequencial = fileDetalheSN.getNumeroSequencial();
        BigDecimal valorAutenticacao = fileDetalheSN.getValorAutenticacao();
        BigDecimal valorBarra = BigDecimal.ZERO;
        BigDecimal valorPagamento = Objects.isNull(fileDetalheSN.getValorPrincipal()) ? BigDecimal.ZERO :
                fileDetalheSN.getValorPrincipal();
        LocalDateTime arrecadacao = Objects.isNull(dataArrecadacao) ? null : dataArrecadacao.atStartOfDay();

        return new ArquivoDetalhePagos(idArquivo, currentLine, numeroSequencial, arrecadacao,
                valorBarra, CODIGO_BARRA, valorPagamento, valorTarifaConvenio, FORMA_PAGAMENTO, valorAutenticacao);
    }
}
