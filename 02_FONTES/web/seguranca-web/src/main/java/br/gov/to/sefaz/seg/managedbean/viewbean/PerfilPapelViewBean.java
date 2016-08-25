package br.gov.to.sefaz.seg.managedbean.viewbean;

/**
 * View Bean da tela de Manutenção de Perfis.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/08/2016 17:53:00
 */

public class PerfilPapelViewBean {

    private String nomePapel;
    private String descricaoPapel;
    private Long identificacaoPapel;
    private Boolean isUsing;

    public PerfilPapelViewBean() {
        //Construtor para incialização por reflexão.
    }

    public PerfilPapelViewBean(String nomePapel, String descricaoPapel, Long identificacaoPapel, Boolean isUsing) {
        this.nomePapel = nomePapel;
        this.descricaoPapel = descricaoPapel;
        this.identificacaoPapel = identificacaoPapel;
        this.isUsing = isUsing;
    }

    public String getNomePapel() {
        return nomePapel;
    }

    public void setNomePapel(String nomePapel) {
        this.nomePapel = nomePapel;
    }

    public String getDescricaoPapel() {
        return descricaoPapel;
    }

    public void setDescricaoPapel(String descricaoPapel) {
        this.descricaoPapel = descricaoPapel;
    }

    public Long getIdentificacaoPapel() {
        return identificacaoPapel;
    }

    public void setIdentificacaoPapel(Long identificacaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean using) {
        isUsing = using;
    }
}
