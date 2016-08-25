package br.gov.to.sefaz.seg.managedbean.viewbean;

/**
 * View Bean da tela de Manutenção de Papéis.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/08/2016 09:49:00
 */

public class OpcaoUsedViewBean {

    private String abreviacaoModulo;
    private String descricaoAplicacao;
    private String descripcaoOpcao;
    private Long identificacaoOpcaoAplicacao;
    private boolean isUsed;

    public OpcaoUsedViewBean() {
        //Construtor para incialização por reflexão.
    }

    public OpcaoUsedViewBean(String abreviacaoModulo, String descricaoAplicacao, String descripcaoOpcao,
            Long identificacaoOpcaoAplicacao, boolean isUsed) {
        this.abreviacaoModulo = abreviacaoModulo;
        this.descricaoAplicacao = descricaoAplicacao;
        this.descripcaoOpcao = descripcaoOpcao;
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
        this.isUsed = isUsed;
    }

    public String getAbreviacaoModulo() {
        return abreviacaoModulo;
    }

    public void setAbreviacaoModulo(String abreviacaoModulo) {
        this.abreviacaoModulo = abreviacaoModulo;
    }

    public String getDescricaoAplicacao() {
        return descricaoAplicacao;
    }

    public void setDescricaoAplicacao(String descricaoAplicacao) {
        this.descricaoAplicacao = descricaoAplicacao;
    }

    public String getDescripcaoOpcao() {
        return descripcaoOpcao;
    }

    public void setDescripcaoOpcao(String descripcaoOpcao) {
        this.descripcaoOpcao = descripcaoOpcao;
    }

    public Long getIdentificacaoOpcaoAplicacao() {
        return identificacaoOpcaoAplicacao;
    }

    public void setIdentificacaoOpcaoAplicacao(Long identificacaoOpcaoAplicacao) {
        this.identificacaoOpcaoAplicacao = identificacaoOpcaoAplicacao;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        this.isUsed = used;
    }
}
