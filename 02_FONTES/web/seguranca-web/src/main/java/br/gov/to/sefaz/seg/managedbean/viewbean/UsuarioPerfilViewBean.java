package br.gov.to.sefaz.seg.managedbean.viewbean;

/**
 * View Bean da tela de Atribuição de Perfis.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 03/08/2016 17:53:00
 */

public class UsuarioPerfilViewBean {

    private Long identificacaoPerfil;
    private String descricaoPerfil;
    private Boolean isUsing;

    public UsuarioPerfilViewBean() {
        //Construtor para incialização por reflexão.
    }

    public UsuarioPerfilViewBean(Long identificacaoPerfil, String descricaoPerfil, Boolean isUsing) {
        this.identificacaoPerfil = identificacaoPerfil;
        this.descricaoPerfil = descricaoPerfil;
        this.isUsing = isUsing;
    }

    public Long getIdentificacaoPerfil() {
        return identificacaoPerfil;
    }

    public void setIdentificacaoPerfil(Long identificacaoPerfil) {
        this.identificacaoPerfil = identificacaoPerfil;
    }

    public String getDescricaoPerfil() {
        return descricaoPerfil;
    }

    public void setDescricaoPerfil(String descricaoPerfil) {
        this.descricaoPerfil = descricaoPerfil;
    }

    public Boolean getUsing() {
        return isUsing;
    }

    public void setUsing(Boolean using) {
        isUsing = using;
    }
}
