package br.gov.to.sefaz.arr.processamento.process.content.arrecadacao;

import br.gov.to.sefaz.arr.dare.service.DareService;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos;
import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.arr.persistence.entity.PagosArrec;
import br.gov.to.sefaz.arr.persistence.enums.TipoCodigoBarraEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum;
import br.gov.to.sefaz.arr.processamento.creator.PagosArrecCreator;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.CodigoBarra;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.FileDetalheArrec;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.builder.CodigoBarraCreator;
import br.gov.to.sefaz.arr.processamento.domain.detalhe.arrecadacao.builder.CodigoBarrasExtractor;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.service.PagosArrecService;
import br.gov.to.sefaz.arr.processamento.validation.DetalheValidationSuite;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.DetalheValidator;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao.builder.CodigoBarraArrecDareValidatorBuilder;
import br.gov.to.sefaz.arr.processamento.validation.validator.detalhe.arrecadacao.builder.CodigoBarraArrecGnreValidatorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Processa o pagamento de uma arrecadação, obtida através de um arquivo em sua linha de detalhe.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/07/2016 10:44:00
 */
@Component
public class PagosArrecManager {

    private final PagosArrecService pagosArrecService;
    private final CodigoBarraCreator codigoBarraCreator;
    private final DareService dareService;
    private final DetalheValidationSuite detalheValidationSuite;
    private final CodigoBarrasExtractor codigoBarrasExtractor;
    private final PagosArrecCreator arrecCreator;
    private final ArquivoRecepcaoService arquivoRecepcaoService;

    @Autowired
    public PagosArrecManager(PagosArrecService pagosArrecService, CodigoBarraCreator codigoBarraCreator,
            DareService dareService, DetalheValidationSuite detalheValidationSuite,
            CodigoBarrasExtractor codigoBarrasExtractor, PagosArrecCreator arrecCreator,
            ArquivoRecepcaoService arquivoRecepcaoService) {
        this.pagosArrecService = pagosArrecService;
        this.codigoBarraCreator = codigoBarraCreator;
        this.dareService = dareService;
        this.detalheValidationSuite = detalheValidationSuite;
        this.codigoBarrasExtractor = codigoBarrasExtractor;
        this.arrecCreator = arrecCreator;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
    }

    /**
     * Cria e salva na base de dados uma coleção de
     * {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec} conforme os parâmetros fornecidos.
     * o processamento está vinculado ao {@link br.gov.to.sefaz.arr.persistence.enums.TipoConvenioEnum}.
     *
     * @param idBdarTpar          identificação do TPAR a ser vinculado com o pagamento
     * @param arquivoDetalhePagos contém informações para a criação do
     *                            {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec}
     * @param fileDetalheArrec    contém informações da linha corrente do arquivo processado
     * @return uma coleção com os {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec} criados
     *     através dos parâmetros fornecidos.
     */
    public Collection<PagosArrec> createAndSavePagosArrec(Long idBdarTpar, ArquivoDetalhePagos arquivoDetalhePagos,
            FileDetalheArrec fileDetalheArrec) {
        int ordemLote = pagosArrecService.getLastOrdemLoteTpar(idBdarTpar);
        ordemLote++;

        String codigoBarras = fileDetalheArrec.getCodigoBarras();
        TipoConvenioEnum tipoConvenio = codigoBarrasExtractor.getTipoConvenio(codigoBarras);

        if (tipoConvenio == TipoConvenioEnum.GNRE) {
            return createAndSavePagosArrecGnre(idBdarTpar, arquivoDetalhePagos, fileDetalheArrec, ordemLote,
                    codigoBarras);
        } else {
            return createAndSavePagosArrecDare(idBdarTpar, arquivoDetalhePagos, fileDetalheArrec, ordemLote,
                    codigoBarras);
        }
    }

    private Collection<PagosArrec> createAndSavePagosArrecDare(Long idBdarTpar, ArquivoDetalhePagos detalhePagos,
            FileDetalheArrec fileDetalheArrec, int ordemLote, String codigoBarras) {
        CodigoBarra codigoBarra = codigoBarraCreator.createDare(codigoBarras);
        TipoCodigoBarraEnum tipoDare = TipoCodigoBarraEnum.DARE;
        Long idArquivos = detalhePagos.getIdArquivos();
        ArquivoRecepcao arquivoRecepcao = arquivoRecepcaoService.findOne(idArquivos);

        List<DetalheValidator> detalheValidators =
                new CodigoBarraArrecDareValidatorBuilder(arquivoRecepcao, dareService)
                        .withCodigoBarra(codigoBarra)
                        .withAllValidators()
                        .build();

        detalheValidationSuite.validate(detalheValidators);

        return createAndSave(idBdarTpar, detalhePagos, fileDetalheArrec, ordemLote, codigoBarra, tipoDare);
    }

    private Collection<PagosArrec> createAndSavePagosArrecGnre(Long idBdarTpar, ArquivoDetalhePagos arquivoDetalhePagos,
            FileDetalheArrec fileDetalheArrec, int ordemLote, String codigoBarras) {
        CodigoBarra codigoBarra = codigoBarraCreator.createGnre(codigoBarras);
        TipoCodigoBarraEnum tipoDare = TipoCodigoBarraEnum.GNRE;
        ArquivoRecepcao arquivoRecepcao = arquivoDetalhePagos.getArquivoRecepcao();

        List<DetalheValidator> detalheValidators = new CodigoBarraArrecGnreValidatorBuilder(arquivoRecepcao,
                dareService)
                .withCodigoBarra(codigoBarra)
                .withAllValidators()
                .build();

        detalheValidationSuite.validate(detalheValidators);

        return createAndSave(idBdarTpar, arquivoDetalhePagos, fileDetalheArrec, ordemLote, codigoBarra, tipoDare);
    }

    private Collection<PagosArrec> createAndSave(Long idBdarTpar, ArquivoDetalhePagos detalheArquivo,
            FileDetalheArrec fileDetalheArrec, int ordemLote, CodigoBarra codigoBarra, TipoCodigoBarraEnum tipoDare) {
        Dare dare = dareService.findOne(Long.valueOf(codigoBarra.getNossoNumero()));

        Collection<PagosArrec> pagosArrecList = arrecCreator.createPagosArrecCollection(idBdarTpar, detalheArquivo,
                fileDetalheArrec, ordemLote, tipoDare, dare);

        dare.setDataPago(LocalDateTime.now());
        dareService.update(dare);

        return pagosArrecService.save(pagosArrecList);
    }

}
