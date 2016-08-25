package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade para join das classes {@link PostoTrabalho} e {@link UsuarioSistema}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 21/06/2016 14:19:00
 */
@Entity
@Table(name = "TA_USUARIO_POSTO_TRABALHO", schema = "SEFAZ_SEG")
@IdClass(value = UsuarioPostoTrabalhoPK.class)
public class UsuarioPostoTrabalho extends AbstractEntity<UsuarioPostoTrabalhoPK> {

    @Id
<<<<<<< Updated upstream
    @NotEmpty
=======
    @NotEmpty(message = "#{seg_msg['seg.usuarioPostoTrabalho.identificacaoPostoTrabalho.cpfVazio']}")
>>>>>>> Stashed changes
    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @Id
<<<<<<< Updated upstream
    @NotNull
=======
    @NotNull(message = "#{seg_msg['seg.usuarioPostoTrabalho.identificacaoPostoTrabalho.null']}")
>>>>>>> Stashed changes
    @Column(name = "IDENTIFICACAO_POSTO_TRABALHO")
    private Integer identificacaoPostoTrabalho;

    @JoinColumn(name = "CPF_USUARIO", referencedColumnName = "CPF_USUARIO",
            insertable = false, updatable = false)
    @OneToOne(optional = false)
    private UsuarioSistema usuarioSistema;

    @JoinColumn(name = "IDENTIFICACAO_POSTO_TRABALHO", referencedColumnName = "IDENTIFICACAO_POSTO_TRABALHO",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PostoTrabalho postoTrabalho;

<<<<<<< Updated upstream
=======
    public UsuarioPostoTrabalho() {
        this.postoTrabalho = new PostoTrabalho();
    }
>>>>>>> Stashed changes

    @Override
    public UsuarioPostoTrabalhoPK getId() {
        return new UsuarioPostoTrabalhoPK(cpfUsuario, identificacaoPostoTrabalho);
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

<<<<<<< Updated upstream
    public UsuarioPostoTrabalho() {
        // Construtor para inicialização por reflexão.
    }

=======
>>>>>>> Stashed changes
    public UsuarioPostoTrabalho(String cpfUsuario, Integer identificacaoPostoTrabalho) {
        this.cpfUsuario = cpfUsuario;
        this.identificacaoPostoTrabalho = identificacaoPostoTrabalho;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

<<<<<<< Updated upstream
    public PostoTrabalho getPostoTrabalho() {
=======
    /**
     * Busca o Posto de Trabalho do Usuário.
     * @return {@link PostoTrabalho}.
     */
    public PostoTrabalho getPostoTrabalho() {
        if (postoTrabalho == null) {
            postoTrabalho = new PostoTrabalho();
        }
>>>>>>> Stashed changes
        return postoTrabalho;
    }

    public void setPostoTrabalho(PostoTrabalho postoTrabalho) {
        this.postoTrabalho = postoTrabalho;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioPostoTrabalho that = (UsuarioPostoTrabalho) o;
        return Objects.equals(cpfUsuario, that.cpfUsuario)
                && Objects.equals(identificacaoPostoTrabalho, that.identificacaoPostoTrabalho);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfUsuario, identificacaoPostoTrabalho);
    }

    @Override
    public String toString() {
        return "UsuarioPostoTrabalho{"
                + "identificacaoPostoTrabalho=" + identificacaoPostoTrabalho
                + ", cpfUsuario='" + cpfUsuario + '\''
                + '}';
    }
}
