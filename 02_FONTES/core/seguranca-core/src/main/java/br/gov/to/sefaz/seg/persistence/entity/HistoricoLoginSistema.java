package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_HISTORICO_LOGIN_SISTEMA do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_HISTORICO_LOGIN_SISTEMA", schema = "SEFAZ_SEG")
public class HistoricoLoginSistema extends AbstractEntity<Long> {

    private static final long serialVersionUID = -5779312053718180998L;

    @Id
    @Column(name = "IDENTIFICACAO_LOGIN_SISTEMA")
    @SequenceGenerator(name = "SQ_HISTORICO_LOGIN_SISTEMA", schema = "SEFAZ_SEG",
            sequenceName = "SQ_HISTORICO_LOGIN_SISTEMA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_HISTORICO_LOGIN_SISTEMA")
    private Long identificacaoLoginSistema;

    @NotNull
    @Column(name = "DATA_HORA_LOGIN")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataHoraLogin;

    @JoinColumn(name = "CPF_USUARIO", referencedColumnName = "CPF_USUARIO")
    @ManyToOne(optional = false)
    private UsuarioSistema usuarioSistema;

    public HistoricoLoginSistema() {
        // Construtor para inicialização por reflexão.
    }

    public HistoricoLoginSistema(Long identificacaoLoginSistema, LocalDateTime dataHoraLogin,
            UsuarioSistema usuarioSistema) {
        this.identificacaoLoginSistema = identificacaoLoginSistema;
        this.dataHoraLogin = dataHoraLogin;
        this.usuarioSistema = usuarioSistema;
    }

    @Override
    public Long getId() {
        return identificacaoLoginSistema;
    }

    public Long getIdentificacaoLoginSistema() {
        return identificacaoLoginSistema;
    }

    public void setIdentificacaoLoginSistema(Long identificacaoLoginSistema) {
        this.identificacaoLoginSistema = identificacaoLoginSistema;
    }

    public LocalDateTime getDataHoraLogin() {
        return dataHoraLogin;
    }

    public void setDataHoraLogin(LocalDateTime dataHoraLogin) {
        this.dataHoraLogin = dataHoraLogin;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoLoginSistema, dataHoraLogin, usuarioSistema);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HistoricoLoginSistema that = (HistoricoLoginSistema) obj;
        return Objects.equals(this.identificacaoLoginSistema, that.identificacaoLoginSistema)
                && Objects.equals(this.dataHoraLogin, that.dataHoraLogin)
                && Objects.equals(this.usuarioSistema, that.usuarioSistema);
    }

    @Override
    public String toString() {
        return "HistoricoLoginSistema [identificacaoLoginSistema=" + identificacaoLoginSistema + ", dataHoraLogin="
                + dataHoraLogin + ", usuarioSistema=" + usuarioSistema + "]";
    }

}
