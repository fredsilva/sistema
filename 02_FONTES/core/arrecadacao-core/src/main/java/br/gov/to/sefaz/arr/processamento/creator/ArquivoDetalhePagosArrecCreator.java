package br.gov.to.sefaz.arr.processamento.creator;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosTarifasService;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.enums.FormaPagamentoEnum;
import br.gov.to.sefaz.arr.processamento.domain.FileContent;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.builder.CodigoBarrasExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe que auxilia a criação de {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos} para
 * o processamento do conteúdo do detalhe do arquivo de arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 11:51:00
 */
@SuppressWarnings("PMD")
@Component
public class ArquivoDetalhePagosArrecCreator {

    private final ConveniosTarifasService conveniosTarifasService;
    private final CodigoBarrasExtractor codigoBarrasExtractor;

    @Autowired
    public ArquivoDetalhePagosArrecCreator(ConveniosTarifasService conveniosTarifasService,
            CodigoBarrasExtractor codigoBarrasExtractor) {
        this.conveniosTarifasService = conveniosTarifasService;
        this.codigoBarrasExtractor = codigoBarrasExtractor;
    }

    /**
     * Cria um {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos} conforme os parâmetros
     * fornecidos.
     *
     * @param fileContent      Contém as informações de número da linha e identificação do arquivo
     * @param fileDetalheArrec Contém as informações da linha do detalhe do arquivo
     * @return um novo {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos}.
     */
    public ArquivoDetalhePagos create(FileContent fileContent, FileDetalheArrec fileDetalheArrec) {
        Long codigoConvenio = fileContent.getFileHeader().getCodigoConvenio();
        LocalDate dataPagamento = fileDetalheArrec.getDataPagamento();
        LocalDateTime dataArrecadacao = Objects.isNull(dataPagamento) ? null
                : dataPagamento.atStartOfDay();

        FormaPagamentoEnum formaPagamento;

        try {
            Integer pagamento = Objects.isNull(fileDetalheArrec.getFormaPagamento()) ? null
                    : Integer.valueOf(fileDetalheArrec.getFormaPagamento());
            formaPagamento = FormaPagamentoEnum.getValue(pagamento);
        } catch (RuntimeException e) {
            formaPagamento = null;
        }

        BigDecimal valorTarifaConvenio = conveniosTarifasService.getValorTarifaBy(codigoConvenio, formaPagamento,
                dataPagamento);

        String codigoBarra = fileDetalheArrec.getCodigoBarras();

        Long idArquivo = fileContent.getIdArquivo();
        int currentLine = fileContent.getCurrentLine();
        String numeroSequencial = fileDetalheArrec.getNumeroSequencial();
        BigDecimal valorBarra;

        try {
            valorBarra = codigoBarrasExtractor.getValorTotal(codigoBarra);
        } catch (RuntimeException e) {
            valorBarra = BigDecimal.ZERO;
        }

        BigDecimal valorAutenticado = Objects.isNull(fileDetalheArrec.getValorAutenticado()) ? BigDecimal.ZERO
                : fileDetalheArrec.getValorAutenticado();

        return new ArquivoDetalhePagos(idArquivo, currentLine, numeroSequencial, dataArrecadacao,
                valorBarra, codigoBarra, valorAutenticado, valorTarifaConvenio, formaPagamento, valorAutenticado);
    }
}
