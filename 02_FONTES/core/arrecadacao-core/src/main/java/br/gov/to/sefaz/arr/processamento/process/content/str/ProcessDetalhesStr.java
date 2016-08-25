package br.gov.to.sefaz.arr.processamento.process.content.str;

import br.gov.to.sefaz.arr.parametros.business.service.ConveniosArrecService;
import br.gov.to.sefaz.arr.persistence.entity.ArquivosStr;
import br.gov.to.sefaz.arr.persistence.entity.ConveniosArrec;
import br.gov.to.sefaz.arr.persistence.entity.DetalheStr;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoReceitaStrEnum;
import br.gov.to.sefaz.arr.processamento.converter.str.TipoReceitaToTipoConvenioEnum;
import br.gov.to.sefaz.arr.processamento.converter.str.ValorInformativoToConvenioEnum;
import br.gov.to.sefaz.arr.processamento.domain.str.GrupoValorInformativo;
import br.gov.to.sefaz.arr.processamento.domain.str.STR0020;
import br.gov.to.sefaz.arr.processamento.domain.str.TipoValorInformativoEnum;
import br.gov.to.sefaz.arr.processamento.service.DetalhesStrService;
import br.gov.to.sefaz.arr.processamento.validation.validator.str.ArquivoStrValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static br.gov.to.sefaz.util.xml.ConverterUtil.convertBigDecimal;

/**
 * Processa os detalhes do arquivo STR.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 08/07/2016 11:34:00
 */
@Component
public class ProcessDetalhesStr {

    private static final int NR_DIGITOS_DECIMAL = 2;

    private final ArquivoStrValidator arquivoStrValidator;
    private final ConveniosArrecService conveniosArrecService;
    private final DetalhesStrService detalhesStrService;

    @Autowired
    ProcessDetalhesStr(ArquivoStrValidator arquivoStrValidator, ConveniosArrecService conveniosArrecService,
            DetalhesStrService detalhesStrService) {
        this.arquivoStrValidator = arquivoStrValidator;
        this.conveniosArrecService = conveniosArrecService;
        this.detalhesStrService = detalhesStrService;
    }

    /**
     * Processa os detalhes do arquivo STR.
     *
     * @param arquivosStrEntity    entidade de arquivo STR.
     * @param arquivoStr0020Domain dominio do arquivo STR.
     * @throws ParseException lança exceção de erro de formatação de dados na converção dos mesmos.
     */
    public List<DetalheStr> processDetalhes(ArquivosStr arquivosStrEntity, STR0020 arquivoStr0020Domain)
            throws ParseException {
        Integer cnpjRaizDeb = Integer.valueOf(arquivoStr0020Domain.getIspbifDebtd());
        TipoReceitaStrEnum tipoReceitaEnum = getTipoReceitaStr(arquivoStr0020Domain);
        List<GrupoValorInformativo> vlsInformativo = arquivoStr0020Domain.getGrupoValorInformativo();
        List<DetalheStr> detalheStr;

        if (TipoReceitaStrEnum.DEMAIS_RECEITAS_TRIBUTARIAS.equals(tipoReceitaEnum)) {
            detalheStr = processDetalhesDemaisReceitasTributarias(arquivosStrEntity, cnpjRaizDeb, vlsInformativo);
        } else {
            TipoConvenioEnum tipoConvenioEnum = TipoReceitaToTipoConvenioEnum.forTipoReceita(tipoReceitaEnum);
            List<ConveniosArrec> conveniosArrecs = conveniosArrecService.findByBancoCnpjAndTipoConvenio(cnpjRaizDeb,
                    tipoConvenioEnum);
            ConveniosArrec conveniosArrec = getConveniosArrec(conveniosArrecs);

            this.arquivoStrValidator.validateCodConvenioNaoLocalizado(conveniosArrec);
            detalheStr = registerDetalhesStr(vlsInformativo, arquivosStrEntity, conveniosArrec);
        }

        this.arquivoStrValidator.validateValorLancamentoDivergente(arquivosStrEntity.getValorTotalLancamento(),
                detalheStr);

        return detalheStr;

    }

    private List<DetalheStr> processDetalhesDemaisReceitasTributarias(ArquivosStr arquivosStrEntity,
            Integer cnpjRaiz, List<GrupoValorInformativo> valoresInformativo) throws ParseException {
        for (GrupoValorInformativo grupoValorInformativo : valoresInformativo) {
            TipoConvenioEnum tipoConvenioEnum = getTipoConvenio(grupoValorInformativo);
            List<ConveniosArrec> conveniosArrecs = this.conveniosArrecService.findByBancoCnpjAndTipoConvenio(cnpjRaiz,
                    tipoConvenioEnum);
            ConveniosArrec conveniosArrec = getConveniosArrec(conveniosArrecs);

            grupoValorInformativo.setConveniosArrec(conveniosArrec);
            this.arquivoStrValidator.validateCodConvenioNaoLocalizado(conveniosArrec);
        }

        return registerDetalheStrs(arquivosStrEntity, valoresInformativo);
    }

    private List<DetalheStr> registerDetalheStrs(ArquivosStr arquivosStrEntity,
            List<GrupoValorInformativo> valoresInformativo) throws ParseException {
        List<DetalheStr> detalheStr = new ArrayList<>();

        for (GrupoValorInformativo grupoValorInformativo : valoresInformativo) {
            detalheStr.add(registerValorInformativo(arquivosStrEntity, grupoValorInformativo));
        }

        return detalheStr;
    }

    private ConveniosArrec getConveniosArrec(List<ConveniosArrec> conveniosArrecs) {
        if (!conveniosArrecs.isEmpty()) {
            return conveniosArrecs.get(0);
        }
        return null;
    }

    private TipoReceitaStrEnum getTipoReceitaStr(STR0020 arquivoStr0020Domain) {
        Integer tipoReceitaCode = Integer.valueOf(arquivoStr0020Domain.getTpReceita());
        return TipoReceitaStrEnum.getValue(tipoReceitaCode);
    }

    private TipoConvenioEnum getTipoConvenio(GrupoValorInformativo grupoValorInformativo) {
        Integer valorInformativo = Integer.valueOf(grupoValorInformativo.getTpVlrInf());
        TipoValorInformativoEnum tipoValorEnum = TipoValorInformativoEnum.getValue(valorInformativo);
        return ValorInformativoToConvenioEnum.forValorInformativo(tipoValorEnum);
    }

    private List<DetalheStr> registerDetalhesStr(List<GrupoValorInformativo> valoresInformativo,
            ArquivosStr arquivosStrEntity, ConveniosArrec conveniosArrec) throws ParseException {
        List<DetalheStr> detalhesStr = new ArrayList<>();
        for (GrupoValorInformativo valorInformativo : valoresInformativo) {
            valorInformativo.setConveniosArrec(conveniosArrec);
            detalhesStr.add(registerValorInformativo(arquivosStrEntity, valorInformativo));
        }
        return detalhesStr;
    }

    private DetalheStr registerValorInformativo(ArquivosStr arquivosStrEntity, GrupoValorInformativo valorInformativo)
            throws ParseException {
        BigDecimal vlrInf = convertBigDecimal(valorInformativo.getVlrInf(), NR_DIGITOS_DECIMAL);
        Integer tipoValor = Integer.valueOf(valorInformativo.getTpVlrInf());
        DetalheStr detalheStr = new DetalheStr();

        detalheStr.setArquivosStr(arquivosStrEntity);
        detalheStr.setIdConvenio(valorInformativo.getConveniosArrec().getIdConvenio());
        detalheStr.setValorInformativo(vlrInf);
        detalheStr.setTipoValor(TipoValorInformativoEnum.getValue(tipoValor));
        return this.detalhesStrService.save(detalheStr);
    }

}
