package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.seg.persistence.converter.SituacaoUsuarioEnumConverter;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_USUARIO_PERFIL do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_USUARIO_PERFIL", schema = "SEFAZ_SEG")
public class UsuarioPerfil extends AbstractEntity<Long> {

    private static final long serialVersionUID = 8401087571441586038L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario_perfil")
    @SequenceGenerator(name = "sq_usuario_perfil", schema = "SEFAZ_SEG",
            sequenceName = "sq_usuario_perfil",
            allocationSize = 1)
    @Column(name = "IDENTIFICACAO_USUARIO_PERFIL")
    private Long identificacaoUsuarioPerfil;

    @NotNull
    @Convert(converter = SituacaoUsuarioEnumConverter.class)
    @Column(name = "SITUACAO_PERFIL")
    private SituacaoUsuarioEnum situacaoPerfil;

    @NotNull
    @Column(name = "CPF_USUARIO")
    private String cpfUsuario;

    @NotNull
    @Column(name = "IDENTIFICACAO_PERFIL")
    private Long identificacaoPerfil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDENTIFICACAO_PERFIL", referencedColumnName = "IDENTIFICACAO_PERFIL",
            insertable = false, updatable = false)
    private PerfilSistema perfisSistema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPF_USUARIO", referencedColumnName = "CPF_USUARIO",
            insertable = false, updatable = false)
    private UsuarioSistema usuarioSistema;

    public UsuarioPerfil() {
        // Construtor para inicialização por reflexão.
    }

    public UsuarioPerfil(String cpfUsuario, Long identificacaoPerfil) {
        this.cpfUsuario = cpfUsuario;
        this.identificacaoPerfil = identificacaoPerfil;
    }

    public UsuarioPerfil(Long identificacaoUsuarioPerfil, SituacaoUsuarioEnum situacaoPerfil, String cpfUsuario,
            Long identificacaoPerfil) {
        this.identificacaoUsuarioPerfil = identificacaoUsuarioPerfil;
        this.situacaoPerfil = situacaoPerfil;
        this.cpfUsuario = cpfUsuario;
        this.identificacaoPerfil = identificacaoPerfil;
    }

    @Override
    public Long getId() {
        return identificacaoUsuarioPerfil;
    }

    public Long getIdentificacaoUsuarioPerfil() {
        return identificacaoUsuarioPerfil;
    }

    public void setIdentificacaoUsuarioPerfil(Long identificacaoUsuarioPerfil) {
        this.identificacaoUsuarioPerfil = identificacaoUsuarioPerfil;
    }

    public SituacaoUsuarioEnum getSituacaoPerfil() {
        return situacaoPerfil;
    }

    public void setSituacaoPerfil(SituacaoUsuarioEnum situacaoPerfil) {
        this.situacaoPerfil = situacaoPerfil;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public Long getIdentificacaoPerfil() {
        return identificacaoPerfil;
    }

    public PerfilSistema getPerfisSistema() {
        return perfisSistema;
    }

    public void setPerfisSistema(PerfilSistema perfisSistema) {
        this.perfisSistema = perfisSistema;
    }

    public void setIdentificacaoPerfil(Long identificacaoPerfil) {
        this.identificacaoPerfil = identificacaoPerfil;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomePerfilSistema() {
        return getPerfisSistema().getNomePerfil();
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getNomeUsuarioSistema() {
        return Objects.isNull(usuarioSistema) ? "" : usuarioSistema.getNomeCompletoUsuario();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsuarioPerfil that = (UsuarioPerfil) o;
        return Objects.equals(identificacaoUsuarioPerfil, that.identificacaoUsuarioPerfil)
                && Objects.equals(situacaoPerfil, that.situacaoPerfil)
                && Objects.equals(cpfUsuario, that.cpfUsuario)
                && Objects.equals(identificacaoPerfil, that.identificacaoPerfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoUsuarioPerfil, situacaoPerfil, cpfUsuario, identificacaoPerfil);
    }

    @Override
    public String toString() {
        return "UsuarioPerfil{"
                + "identificacaoUsuarioPerfil=" + identificacaoUsuarioPerfil
                + ", situacaoPerfil=" + situacaoPerfil
                + ", cpfUsuario='" + cpfUsuario + '\''
                + ", identificacaoPerfil=" + identificacaoPerfil
                + '}';
    }
}
