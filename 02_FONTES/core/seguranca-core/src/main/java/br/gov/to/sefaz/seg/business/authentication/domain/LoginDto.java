package br.gov.to.sefaz.seg.business.authentication.domain;

import br.gov.to.sefaz.business.service.validation.custom.Cpf;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

import javax.validation.constraints.Size;

/**
 * Classe utilizada para fazer o login do sistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 09:24:00
 */
public class LoginDto {

    @NotEmpty(message = "#{seg_msg['login.cpf.obrigatorio']}")
    @Size(min = 11, max = 11, message = "#{seg_msg['login.cpf.invalido']}")
    @Cpf(message = "#{seg_msg['login.cpf.digitos.verificadores.invalidos']}")
    private String cpf;

    @NotEmpty(message = "#{seg_msg['login.senha.obrigatorio']}")
    @Size(max = 20, message = "#{seg_msg['login.senha.tamanho']}")
    private String passwd;

    public LoginDto() {
        // Construtor para inicialização por reflexão.
    }

    public LoginDto(
            String cpf, String passwd) {
        this.cpf = cpf;
        this.passwd = passwd;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, passwd);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LoginDto that = (LoginDto) obj;
        return Objects.equals(cpf, that.cpf)
                && Objects.equals(passwd, that.passwd);
    }

    @Override
    public String toString() {
        return "LoginDto [cpf=" + cpf + ", passwd=" + passwd + "]";
    }

}
