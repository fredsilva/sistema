package br.gov.to.sefaz.seg.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;

/**
 * PK da Classe {@link UsuarioPostoTrabalho}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 21/06/2016 14:37:00
 */
public class UsuarioPostoTrabalhoPK implements Serializable {

    private static final long serialVersionUID = -9201741041446483481L;

    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @Column(name = "IDENTIFICACAO_POSTO_TRABALHO")
    private Integer identificacaoPostoTrabalho;

    public UsuarioPostoTrabalhoPK() {
        // Construtor para inicialização por reflexão.
    }

    public UsuarioPostoTrabalhoPK(String cpfUsuario, Integer identificacaoPostoTrabalho) {
        this.cpfUsuario = cpfUsuario;
        this.identificacaoPostoTrabalho = identificacaoPostoTrabalho;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public Integer getIdentificacaoPostoTrabalho() {
        return identificacaoPostoTrabalho;
    }

    public void setIdentificacaoPostoTrabalho(Integer identificacaoPostoTrabalho) {
        this.identificacaoPostoTrabalho = identificacaoPostoTrabalho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioPostoTrabalhoPK that = (UsuarioPostoTrabalhoPK) o;
        return Objects.equals(cpfUsuario, that.cpfUsuario)
                && Objects.equals(identificacaoPostoTrabalho, that.identificacaoPostoTrabalho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfUsuario, identificacaoPostoTrabalho);
    }

    @Override
    public String toString() {
        return "UsuarioPostoTrabalhoPK{"
                + "cpfUsuario='" + cpfUsuario + '\''
                + ", identificacaoPostoTrabalho='" + identificacaoPostoTrabalho + '\''
                + '}';
    }
}
