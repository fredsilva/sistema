package br.gov.to.sefaz.seg.business.gestao.service.filter;

/**
 * Filtro para a tela de Opção de aplicação.
 *
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 19/07/2016 17:16:00
 */
public class OpcaoAplicacaoFilter {

    private Long identificacaoModulo;
    private Long identificacaoAplicacao;


    public Long getIdentificacaoModulo() {
        return identificacaoModulo;
    }

    public void setIdentificacaoModulo(Long identificacaoModulo) {
        this.identificacaoModulo = identificacaoModulo;
    }

    public Long getIdentificacaoAplicacao() {
        return identificacaoAplicacao;
    }

    public void setIdentificacaoAplicacao(Long identificacaoAplicacao) {
        this.identificacaoAplicacao = identificacaoAplicacao;
    }
}
