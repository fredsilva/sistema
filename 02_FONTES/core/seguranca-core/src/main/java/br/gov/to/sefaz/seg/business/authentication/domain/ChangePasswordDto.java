package br.gov.to.sefaz.seg.business.authentication.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import javax.validation.constraints.Size;

/**
 * Classe utilizada para resetar a senha do usuário.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 07/06/2016 09:24:00
 */
public class ChangePasswordDto {

    @NotEmpty(message = "#{seg_msg['change.password.senhaAtual.obrigatorio']}")
    @Size(max = 20, message = "#{seg_msg['change.password.senhaAtual.tamanho']}")
    private String senhaAtual;

    @NotEmpty(message = "#{seg_msg['change.password.novaSenha.obrigatorio']}")
    @Size(max = 20, message = "#{seg_msg['change.password.novaSenha.tamanho']}")
    private String novaSenha;

    @NotEmpty(message = "#{seg_msg['change.password.confirmarNovaSenha.obrigatorio']}")
    @Size(max = 20, message = "#{seg_msg['change.password.confirmarNovaSenha.tamanho']}")
    private String confirmarNovaSenha;

    public ChangePasswordDto() {
        // Construtor para inicialização por reflexão.
    }

    public ChangePasswordDto(String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
        this.confirmarNovaSenha = confirmarNovaSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmarNovaSenha() {
        return confirmarNovaSenha;
    }

    public void setConfirmarNovaSenha(String confirmarNovaSenha) {
        this.confirmarNovaSenha = confirmarNovaSenha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChangePasswordDto)) {
            return false;
        }
        ChangePasswordDto that = (ChangePasswordDto) o;
        return Objects.equals(getSenhaAtual(), that.getSenhaAtual())
                && Objects.equals(getNovaSenha(), that.getNovaSenha())
                && Objects.equals(getConfirmarNovaSenha(), that.getConfirmarNovaSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSenhaAtual(), getNovaSenha(), getConfirmarNovaSenha());
    }

    @Override
    public String toString() {
        return "ChangePasswordDto{"
                + "senhaAtual='" + senhaAtual + '\''
                + ", novaSenha='" + novaSenha + '\''
                + ", confirmarNovaSenha='" + confirmarNovaSenha + '\''
                + '}';
    }
}
