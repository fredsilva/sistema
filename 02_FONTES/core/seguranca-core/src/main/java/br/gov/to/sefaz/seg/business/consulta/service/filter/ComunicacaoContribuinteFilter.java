package br.gov.to.sefaz.seg.business.consulta.service.filter;

import br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum;

import java.time.LocalDate;

/**
 * Classe de filtro para a tela de consulta de comunicações com os contribuintes.
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 23/08/2016 15:30:00
 */
public class ComunicacaoContribuinteFilter {
    private String nomeDestinatario;
    private LocalDate dataComunicacao;
    private String cpf;
    private TipoComunicacaoEnum tipoComunicacao;

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public LocalDate getDataComunicacao() {
        return dataComunicacao;
    }

    public void setDataComunicacao(LocalDate dataComunicacao) {
        this.dataComunicacao = dataComunicacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TipoComunicacaoEnum getTipoComunicacao() {
        return tipoComunicacao;
    }

    public void setTipoComunicacao(TipoComunicacaoEnum tipoComunicacao) {
        this.tipoComunicacao = tipoComunicacao;
    }
}
