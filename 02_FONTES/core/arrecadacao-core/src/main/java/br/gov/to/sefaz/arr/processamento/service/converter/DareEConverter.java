package br.gov.to.sefaz.arr.processamento.service.converter;

import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.processamento.service.domain.DareE;
import br.gov.to.sefaz.arr.processamento.service.domain.DareEDetalhe;
import br.gov.to.sefaz.util.barcode.BarCodeRender;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Conversor para {@link DareE}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 26/08/2016 17:44:00
 */
@Component
public class DareEConverter {

    /**
     * Converte um {@link Dare} para um {@link DareE}.
     *
     * @param dare de entrada
     * @return saida
     */
    public DareE convert(Dare dare) {
        byte[] barcodeImg = BarCodeRender.createInterleaved2Of5(dare.getBarraDare());

        return new DareE(
                barcodeImg,
                dare.getBarraDare(),
                dare.getIdNossoNumeroDare(),
                dare.getDataEmissao(),
                dare.getDataVencimento(),
                dare.getQuantidadeDebitos(),
                dare.getValorTotalDare(),
                dare.getNomeInstituicao(),
                dare.getUfEmissao(),
                dare.getNomeMunicipio(),
                dare.getTipoPessoa(),
                dare.getIdPessoa(),
                dare.getNomeRazaoSocial(),
                dare.getDareDetalheCollection().stream()
                        .map(d -> new DareEDetalhe(
                                d.getIdSeqDareDetalhe(),
                                d.getNumeroDocumento(),
                                d.getPeriodoReferencia(),
                                d.getDataVencimentoDare(),
                                d.getValorImposto(),
                                d.getValorCorrecaoMonetaria(),
                                d.getValorMulta(),
                                d.getValorJuros(),
                                d.getValorReducaoJuros().add(d.getValorReducaoMulta()),
                                d.getValorTaxa(),
                                d.getValorTotal(),
                                d.getDescricaoReceita(),
                                d.getDescricaoSubCodigo(),
                                d.getObservacao(),
                                d.getInformadoContribuinte()
                        )).collect(Collectors.toList()));
    }

}
