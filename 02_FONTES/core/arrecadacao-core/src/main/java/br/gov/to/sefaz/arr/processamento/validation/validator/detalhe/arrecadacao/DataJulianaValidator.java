package br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoRejeicaoEnum;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator;

import java.time.LocalDate;

/**
 * Validador para data juliana do código de barras de uma linha do arquivo de arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 17:13:00
 */
@SuppressWarnings("PMD")
public class DataJulianaValidator implements DetalheValidator {

    private final String dataVencimento;
    private final ArquivoRecepcao arquivoRecepcao;

    public DataJulianaValidator(String dataVencimento, ArquivoRecepcao arquivoRecepcao) {
        this.dataVencimento = dataVencimento;
        this.arquivoRecepcao = arquivoRecepcao;
    }

    @Override
    public boolean isValid() {
        String day = dataVencimento.substring(2, 5);
        String year = dataVencimento.substring(0, 2);
        try {
            int anoAtual = LocalDate.now().getYear();
            int milenioAtual = (anoAtual / 1000) * 1000;
            int centenaAtual = ((anoAtual / 100) - ((anoAtual / 1000) * 10)) * 10;

            Integer dezenaDataVencimento = Integer.valueOf(year);

            if (milenioAtual + centenaAtual + dezenaDataVencimento < anoAtual) {
                if (centenaAtual == 900) {
                    centenaAtual = 0;
                    milenioAtual += 1000;
                } else {
                    centenaAtual += 100;
                }
            }

            LocalDate julianDate = LocalDate.ofYearDay(milenioAtual + centenaAtual + dezenaDataVencimento,
                    Integer.valueOf(day));

            return arquivoRecepcao.getDataArquivo().isBefore(julianDate);
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public int getCodigoErro() {
        return TipoRejeicaoEnum.DT_ARREC_INF_RECP_INVALIDA.getCode();
    }
}
