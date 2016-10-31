package br.gov.to.sefaz.arr.dare.service.filter;

import javax.validation.constraints.NotNull;

/**
 * Representa o Filtro para Debito de IPVA do DARE.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/09/2016 09:59:00
 */
public class DebitoIpvaFilter {

    @NotNull(message = "#{arr_msg['dare.DebitoIpvaFilter.anoInicial.vazio']}")
    private Integer anoInicial;

    @NotNull(message = "#{arr_msg['dare.DebitoIpvaFilter.anoFinal.vazio']}")
    private Integer anoFinal;

    @NotNull(message = "#{arr_msg['dare.DebitoParcialFilter.idContribuint.vazio']}")
    private Long identificacaoPessoa;

    public DebitoIpvaFilter(Integer anoInicial, Integer anoFinal, Long identificacaoPessoa) {
        this.anoInicial = anoInicial;
        this.anoFinal = anoFinal;
        this.identificacaoPessoa = identificacaoPessoa;
    }

    public DebitoIpvaFilter() {
        //Construtor para utilização no viewBean.
    }

    public Integer getAnoInicial() {
        return anoInicial;
    }

    public void setAnoInicial(Integer anoInicial) {
        this.anoInicial = anoInicial;
    }

    public Integer getAnoFinal() {
        return anoFinal;
    }

    public void setAnoFinal(Integer anoFinal) {
        this.anoFinal = anoFinal;
    }

    public Long getIdentificacaoPessoa() {
        return identificacaoPessoa;
    }

    public void setIdentificacaoPessoa(Long identificacaoPessoa) {
        this.identificacaoPessoa = identificacaoPessoa;
    }
}
