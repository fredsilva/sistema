package br.gov.to.sefaz.seg.business.authentication.domain;

import br.gov.to.sefaz.business.service.validation.custom.Cpf;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Classe utilizada para resetar a senha do usuário.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 09:24:00
 */
public class ResetPasswordDto {

    @NotEmpty(message = "#{seg_msg['reset.password.cpf.obrigatorio']}")
    @Size(min = 11, max = 11, message = "#{seg_msg['reset.password.cpf.invalido']}")
    @Cpf(message = "#{seg_msg['login.cpf.digitos.verificadores.invalidos']}")
    private String cpf;

    @NotEmpty(message = "#{seg_msg['reset.password.email.obrigatorio']}")
    @Size(max = 50, message = "#{seg_msg['reset.password.email.tamanho']}")
    @Pattern(regexp = "([A-Za-z0-9\\._-]+@[A-Za-z0-9\\._-]+\\.[A-Za-z]{2,4}+)|",
            message = "#{seg_msg['reset.password.email.incorreto']}")
    private String email;

    public ResetPasswordDto() {
        // Construtor para inicialização por reflexão.
    }

    public ResetPasswordDto(
            String cpf, String email) {
        this.cpf = cpf;
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ResetPasswordDto that = (ResetPasswordDto) obj;
        return Objects.equals(cpf, that.cpf)
                && Objects.equals(email, that.email);
    }

    @Override
    public String toString() {
        return "LoginDto [cpf=" + cpf + ", email=" + email + "]";
    }

}
