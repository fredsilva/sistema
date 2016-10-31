package br.gov.to.sefaz.arr.dare.service.filter;

import br.gov.to.sefaz.arr.persistence.enums.TipoContribuinteEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPessoaEnum;

import javax.validation.constraints.NotNull;

/**
 * Representa o filtro de contribuinte de DARE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/09/2016 10:57:00
 */
public class DareContribuinteFilter {

    @NotNull(message = "#{arr_msg['dare.dareContribuinteFilter.tipoContribuinte.vazio']}")
    private TipoContribuinteEnum tipoContribuinte;
    @NotNull(message = "#{arr_msg['dare.dareContribuinteFilter.tipoIdentificacao.vazio']}")
    private TipoPessoaEnum tipoPessoa;
    @NotNull(message = "#{arr_msg['dare.dareContribuinteFilter.id.vazio']}")
    private Long idContribuinte;

    public DareContribuinteFilter(TipoContribuinteEnum tipoContribuinte, TipoPessoaEnum tipoPessoa,
            Long idContribuinte) {
        this.tipoContribuinte = tipoContribuinte;
        this.tipoPessoa = tipoPessoa;
        this.idContribuinte = idContribuinte;
    }

    public TipoContribuinteEnum getTipoContribuinte() {
        return tipoContribuinte;
    }

    public void setTipoContribuinte(TipoContribuinteEnum tipoContribuinte) {
        this.tipoContribuinte = tipoContribuinte;
    }

    public TipoPessoaEnum getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoaEnum tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Long getIdContribuinte() {
        return idContribuinte;
    }

    public void setIdContribuinte(Long idContribuinte) {
        this.idContribuinte = idContribuinte;
    }
}
