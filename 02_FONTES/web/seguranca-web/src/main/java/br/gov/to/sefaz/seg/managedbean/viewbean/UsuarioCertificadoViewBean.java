package br.gov.to.sefaz.seg.managedbean.viewbean;

import java.util.Objects;

/**
 * Bean para utilização nas telas que buscam os dados do certificado do usuário.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 20/07/2016 11:46:00
 */
public class UsuarioCertificadoViewBean {

    private String cpfUsuario;
    private String nomeUsuario;
    private String emailUsuario;

    public UsuarioCertificadoViewBean(String cpfUsuario, String nomeUsuario, String emailUsuario) {
        this.cpfUsuario = cpfUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioCertificadoViewBean that = (UsuarioCertificadoViewBean) o;
        return Objects.equals(cpfUsuario, that.cpfUsuario)
                && Objects.equals(nomeUsuario, that.nomeUsuario)
                && Objects.equals(emailUsuario, that.emailUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfUsuario, nomeUsuario, emailUsuario);
    }

    @Override
    public String toString() {
        return "UsuarioCertificadoViewBean{"
                + "cpfUsuario='" + cpfUsuario + '\''
                + ", nomeUsuario='" + nomeUsuario + '\''
                + ", emailUsuario='" + emailUsuario + '\''
                + '}';
    }
}
