package br.gov.to.sefaz.arr.dare.service.filter;

import javax.validation.constraints.NotNull;

/**
 * Representa o Filtro para DARE-e relacionadas a Pagamentos Parcial.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 11/10/2016 10:03:00
 */
public class DebitoParcialFilter {

    @NotNull(message = "#{arr_msg['arr.par.dareEletronicoConsolidado.dadoPagamento.documento.empty']}")
    private Long idDocumento;

    @NotNull(message = "#{arr_msg['dare.DebitoParcialFilter.idContribuint.vazio']}")
    private Long idContribuinte;

    public DebitoParcialFilter(Long idDocumento, Long idContribuinte) {
        this.idDocumento = idDocumento;
        this.idContribuinte = idContribuinte;
    }

    public DebitoParcialFilter() {
        //Construtor para utilização no viewBean.
    }

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Long getIdContribuinte() {
        return idContribuinte;
    }

    public void setIdContribuinte(Long idContribuinte) {
        this.idContribuinte = idContribuinte;
    }
}
