package br.gov.to.sefaz.seg.business.consulta.service.filter;

import br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum;
import br.gov.to.sefaz.seg.persistence.enums.TipoPeriodoConsultaComunicacaoEnum;

import java.time.LocalDate;

/**
 * Classe de filtro para a tela de consulta de comunicações do sistema.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 29/08/2016 10:30:00
 */
public class ConsultaComunicacaoSistemaFilter {
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private TipoPeriodoConsultaComunicacaoEnum tipoPeriodo;
    private TipoComunicacaoEnum tipoComunicacao;

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public TipoPeriodoConsultaComunicacaoEnum getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(TipoPeriodoConsultaComunicacaoEnum tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public TipoComunicacaoEnum getTipoComunicacao() {
        return tipoComunicacao;
    }

    public void setTipoComunicacao(TipoComunicacaoEnum tipoComunicacao) {
        this.tipoComunicacao = tipoComunicacao;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
}
