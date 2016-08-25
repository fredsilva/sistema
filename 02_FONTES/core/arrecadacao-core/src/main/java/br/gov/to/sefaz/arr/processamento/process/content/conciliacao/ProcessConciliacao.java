package br.gov.to.sefaz.arr.processamento.process.content.conciliacao;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.arr.persistence.entity.ResumoStr;
import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;
import br.gov.to.sefaz.arr.processamento.service.ArquivoRecepcaoService;
import br.gov.to.sefaz.arr.processamento.service.DetalhesStrService;
import br.gov.to.sefaz.arr.processamento.service.LotesPagosArrecService;
import br.gov.to.sefaz.arr.processamento.service.ResumoStrService;
import br.gov.to.sefaz.arr.processamento.validation.validator.conciliacao.ConciliacaoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Classe responsável por fazer a conciliação dos arquivos de arrecadação.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 12/07/2016 14:46:00
 */
@Component
public class ProcessConciliacao {

    private final DetalhesStrService detalhesStrService;
    private final ArquivoRecepcaoService arquivoRecepcaoService;
    private final LotesPagosArrecService lotesPagosArrecService;
    private final ConciliacaoValidator conciliacaoValidator;
    private final ResumoStrService resumoStrService;

    @Autowired
    public ProcessConciliacao(DetalhesStrService detalhesStrService,
            ArquivoRecepcaoService arquivoRecepcaoService, LotesPagosArrecService lotesPagosArrecService,
            ConciliacaoValidator conciliacaoValidator, ResumoStrService resumoStrService) {
        this.detalhesStrService = detalhesStrService;
        this.arquivoRecepcaoService = arquivoRecepcaoService;
        this.lotesPagosArrecService = lotesPagosArrecService;
        this.conciliacaoValidator = conciliacaoValidator;
        this.resumoStrService = resumoStrService;
    }

    /**
     * Processa a conciliação dos arquivos de arrecadação.
     *
     * @param dataArrecadacao data de arrecadação.
     * @param idBanco         código do banco.
     * @param convenio        código do convênio.
     */
    public void process(LocalDate dataArrecadacao, Integer idBanco, Long convenio) {
        validateParametrosEntrada(dataArrecadacao, idBanco, convenio);

        ResumoStr resumoStr = initResumoStr(dataArrecadacao, idBanco, convenio);

        setSumarizacaoRecepcao(resumoStr);
        setSumarizacaoArredacao(resumoStr);
        setSumarizacaoStr(resumoStr);
        resumoStr.setSituacao(this.conciliacaoValidator.getSituacao(resumoStr));
        registerConciliacao(resumoStr);
    }

    private void validateParametrosEntrada(LocalDate dataArrecadacao, Integer idBanco, Long idConvenio) {
        if (dataArrecadacao == null || idBanco == null || idConvenio == null) {
            throw new IllegalArgumentException("Faltam parâmetros para conciliação: dataArrecadacao: "
                    + dataArrecadacao + " idBanco: " + idBanco + " idConvenio: " + idConvenio);
        }
    }

    private ResumoStr initResumoStr(LocalDate dataArrecadacao, Integer idBanco, Long idConvenio) {
        ResumoStr resumoStr = new ResumoStr();

        resumoStr.setIdBanco(idBanco);
        resumoStr.setDataArrecadacao(dataArrecadacao.atStartOfDay());
        resumoStr.setIdConvenio(idConvenio);

        return resumoStr;
    }

    private void setSumarizacaoRecepcao(ResumoStr resumoStr) {
        List<ArquivoRecepcao> arqsRecepcao = this.arquivoRecepcaoService.findToConciliacao(
                resumoStr.getDataArrecadacao(), resumoStr.getIdBanco(), resumoStr.getIdConvenio());
        Predicate<ArquivoRecepcao> predCons = s -> s.getCaracteristicaArquivo() == TipoArquivoEnum.CONSOLIDADO;
        List<ArquivoRecepcao> consolidados = arqsRecepcao.stream().filter(predCons).collect(Collectors.toList());
        Predicate<ArquivoRecepcao> predParcial = s -> s.getCaracteristicaArquivo() == TipoArquivoEnum.PARCIAL;
        List<ArquivoRecepcao> parciais = arqsRecepcao.stream().filter(predParcial).collect(Collectors.toList());
        Long totalQtdDocsParcial = parciais.stream().mapToLong(ArquivoRecepcao::getQuantidadeDocs).sum();
        Long totalQtdDocs = consolidados.stream().mapToLong(ArquivoRecepcao::getQuantidadeDocs).sum();
        BigDecimal sumVlParcial = parciais.stream().map(ArquivoRecepcao::getValorTotal).reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        BigDecimal sumValor = consolidados.stream().map(ArquivoRecepcao::getValorTotal).reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        resumoStr.setDataConsolidado(this.conciliacaoValidator.getDataRecepcao(consolidados));
        resumoStr.setQuantidadeRecepcionada(totalQtdDocs);
        resumoStr.setQuantidadeRecepcionadParcial(totalQtdDocsParcial);
        resumoStr.setValorRecepcionado(sumValor);
        resumoStr.setValorRecepcionadoParcial(sumVlParcial);
    }

    private void setSumarizacaoArredacao(ResumoStr resumoStr) {
        List<LotesPagosArrec> tpars = this.lotesPagosArrecService.findTparConcilicao(resumoStr.getIdBanco(),
                resumoStr.getIdConvenio(), resumoStr.getDataArrecadacao());
        List<LotesPagosArrec> bdars = this.lotesPagosArrecService.findBdarConcilicao(resumoStr.getIdBanco(),
                resumoStr.getIdConvenio(), resumoStr.getDataArrecadacao());
        BigDecimal sumVlBdar = BigDecimal.ZERO;
        Integer qtdBdar = 0;
        BigDecimal sumVlTpar = tpars.stream().map(LotesPagosArrec::getValorRecepcionado).reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        Long qtdTpar = tpars.stream().mapToLong(LotesPagosArrec::getQuantidadeRecepcionado).sum();
        if (!bdars.isEmpty()) {
            sumVlBdar = bdars.get(0).getValorRecepcionado();
            qtdBdar = bdars.get(0).getQuantidadeRecepcionado();
        }

        resumoStr.setQuantidadeArrecadada(qtdBdar.longValue());
        resumoStr.setQuantidadeArrecadadoParcial(qtdTpar);
        resumoStr.setValorArrecadado(sumVlBdar);
        resumoStr.setValorArrecadadoParcial(sumVlTpar);
    }

    private void setSumarizacaoStr(ResumoStr resumoStr) {
        BigDecimal sumValorInformativo = this.detalhesStrService.sumValorInformativo(
                resumoStr.getDataArrecadacao().toLocalDate(), resumoStr.getIdBanco(), resumoStr.getIdConvenio());

        resumoStr.setValorLancamentoStr(sumValorInformativo);
    }

    private void registerConciliacao(ResumoStr resumoStr) {
        resumoStr.setDataProcessamento(LocalDateTime.now());
        resumoStrService.save(resumoStr);
    }

}
