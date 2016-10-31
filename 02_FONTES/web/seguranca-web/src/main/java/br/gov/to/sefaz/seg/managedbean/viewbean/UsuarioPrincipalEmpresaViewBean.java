package br.gov.to.sefaz.seg.managedbean.viewbean;

/**
 * ViewBean da tela de atuar como usuário principal.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 31/08/2016 13:34:00
 */
public class UsuarioPrincipalEmpresaViewBean {

    private String nomeUsuario;
    private String nomeEmpresa;
    private String message;

    public UsuarioPrincipalEmpresaViewBean(String nomeUsuario, String nomeEmpresa, String message) {
        this.nomeUsuario = nomeUsuario;
        this.nomeEmpresa = nomeEmpresa;
        this.message = message;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
