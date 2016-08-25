package br.gov.to.sefaz.arr.processamento.validation.validator.str;

import br.gov.to.sefaz.arr.persistence.entity.ArquivosStr;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.DetalheStr;
import br.gov.to.sefaz.arr.processamento.domain.str.STR0020;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoRejeicaoEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivosStrService;
import br.gov.to.sefaz.exception.file.ProcessFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Validações de exceções do Arquivo STR.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 08/07/2016 10:11:00
 */
@Component
public class ArquivoStrValidator {

    private static final int COD_SEFAZ = 27;

    private final ArquivosStrService arquivosStrService;

    @Autowired
    ArquivoStrValidator(ArquivosStrService arquivosStrService) {
        this.arquivosStrService = arquivosStrService;
    }

    /**
     * Validação de convênio não localizado.
     *
     * @param conveniosArrec parâmetro de convênio {@link ConveniosArrec} pesquisado.
     */
    public void validateCodConvenioNaoLocalizado(ConveniosArrec conveniosArrec) {
        if (conveniosArrec == null) {
            throw new ProcessFileException(TipoRejeicaoEnum.COD_CONV_BANC_N_LOCALIZADO.getCode());
        }
    }

    /**
     * Validação de valor de lançamento divergente do somatório de valores informativos.
     *
     * @param valorTotalLancamento valor do lancamento referente a um arquivo STR.
     * @param valoresInformativos  valor do somatório dos valores informativos dos detalhes.
     */
    public void validateValorLancamentoDivergente(BigDecimal valorTotalLancamento,
            List<DetalheStr> valoresInformativos) {
        BigDecimal vlrInfTotal = valoresInformativos.stream()
                .map(DetalheStr::getValorInformativo)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (valorTotalLancamento.compareTo(vlrInfTotal) != 0) {
            throw new ProcessFileException(TipoRejeicaoEnum.VL_LANC_DIVERG_VL_INF_ACUMULADO.getCode());
        }
    }

    /**
     * Validação do Código Sefaz, deve ser 027.
     *
     * @param arquivoStr0020Domain objeto do domínio do arquivo STR.
     */
    public void validateCodSefazInvalido(STR0020 arquivoStr0020Domain) {
        Integer codSefaz = Integer.valueOf(arquivoStr0020Domain.getCodSefaz());

        if (codSefaz != COD_SEFAZ) {
            throw new ProcessFileException(TipoRejeicaoEnum.COD_SEFAZ_DIF_027.getCode());
        }
    }

    /**
     * Validação de arquivo STR já lido pelo sistema.
     *
     * @param arquivosStrEntity Entidade do arquivo STR.
     */
    public void validateArquivoJaLido(ArquivosStr arquivosStrEntity) {
        boolean exist = !this.arquivosStrService.findByNumeroControle(arquivosStrEntity.getNumeroControleStr())
                .isEmpty();

        if (exist) {
            throw new ProcessFileException(TipoRejeicaoEnum.NR_CONTROLE_STR_JA_PROCESSADO.getCode());
        }
    }


}
