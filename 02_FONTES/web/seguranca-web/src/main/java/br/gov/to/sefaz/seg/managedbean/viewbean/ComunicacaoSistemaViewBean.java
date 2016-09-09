package br.gov.to.sefaz.seg.managedbean.viewbean;

import br.gov.to.sefaz.seg.persistence.entity.ComunicacaoContribuinte;
import br.gov.to.sefaz.seg.persistence.enums.TipoComunicacaoEnum;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import java.time.LocalDateTime;

/**
 * View bean que encapsula a entidade ${@link ComunicacaoContribuinte}.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 29/08/2016 11:14:00
 */
public class ComunicacaoSistemaViewBean {

    private static final int PREVIEW_MAX_LENGTH = 150;

    private final LocalDateTime dataHoraComunicacao;

    private final String assunto;

    private final String conteudoAbreviado;

    private final String conteudo;

    private final TipoComunicacaoEnum tipoComunicacao;

    public ComunicacaoSistemaViewBean(ComunicacaoContribuinte comunicacaoContribuinte) {

        this.tipoComunicacao = comunicacaoContribuinte.getTipoComunicacao();
        this.assunto = comunicacaoContribuinte.getAssunto();
        this.dataHoraComunicacao = comunicacaoContribuinte.getDataHoraComunicacao();
        this.conteudo = StringUtils.trimToEmpty(comunicacaoContribuinte.getConteudo());
        this.conteudoAbreviado = StringUtils.abbreviate(Jsoup.parse(this.conteudo).text(), PREVIEW_MAX_LENGTH);
    }

    public LocalDateTime getDataHoraComunicacao() {
        return dataHoraComunicacao;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getConteudoAbreviado() {
        return conteudoAbreviado;
    }

    public TipoComunicacaoEnum getTipoComunicacao() {
        return tipoComunicacao;
    }

    public String getConteudo() {
        return conteudo;
    }
}
