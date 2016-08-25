package br.gov.to.sefaz.arr.processamento.process.content.util;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.service.PagosArrecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Utilitário de auxilio as operações referentes ao conteúdo do arquivo. Como
 * {@link br.gov.to.sefaz.arr.processamento.domain.FileContent} e sua representação de entidade
 * {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/07/2016 16:46:00
 */
@Component
public class FileContentUtil {

    private final PagosArrecService pagosArrecService;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    @Autowired
    public FileContentUtil(PagosArrecService pagosArrecService, ArquivoRecepcaoService arquivoRecepcaoService) {
        this.pagosArrecService = pagosArrecService;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    /**
     * Verifica se uma linha do arquivo já foi processada, através do seu NSU, banco, data de arrecadação e valor.
     *
     * @param arquivoDetalhePagos entidade que contém as informações da linha do arquivo que está sendo processado
     * @return true se a linha já foi processada anteriormente, false caso não tenha sido processada ainda
     */
    public boolean isLineAlreadyProcessed(ArquivoDetalhePagos arquivoDetalhePagos) {
        String numeroNsu = arquivoDetalhePagos.getNumeroNsu();
        LocalDateTime dataArrecadacao = arquivoDetalhePagos.getDataPagamento();
        BigDecimal valorPrincipal = arquivoDetalhePagos.getValorPagamento();
        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(arquivoDetalhePagos.getIdArquivos());
        Integer codigoBanco = arquivoRecepcao.getIdBanco();

        return pagosArrecService.existsPagosArrecWith(numeroNsu, codigoBanco, dataArrecadacao, valorPrincipal);
    }
}
