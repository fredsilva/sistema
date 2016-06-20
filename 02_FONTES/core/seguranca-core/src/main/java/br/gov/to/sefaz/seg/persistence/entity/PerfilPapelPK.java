package br.gov.to.sefaz.seg.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Entidade referente a primary key da tabela SEFAZ_SEG.TA_PERFIL_PAPEL do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Embeddable
public class PerfilPapelPK implements Serializable {

    private static final long serialVersionUID = 128190482213892738L;

    @NotNull
    @Column(name = "IDENTIFICACAO_PERFIL")
    private Long identificacaoPerfil;

    @NotNull
    @Column(name = "IDENTIFICACAO_PAPEL")
    private Long identificacaoPapel;

    public PerfilPapelPK() {
        // Construtor para inicialização por reflexão.
    }

    public PerfilPapelPK(Long identificacaoPerfil, Long identificacaoPapel) {
        this.identificacaoPerfil = identificacaoPerfil;
        this.identificacaoPapel = identificacaoPapel;
    }

    public Long getIdentificacaoPerfil() {
        return identificacaoPerfil;
    }

    public void setIdentificacaoPerfil(Long identificacaoPerfil) {
        this.identificacaoPerfil = identificacaoPerfil;
    }

    public Long getIdentificacaoPapel() {
        return identificacaoPapel;
    }

    public void setIdentificacaoPapel(Long identificacaoPapel) {
        this.identificacaoPapel = identificacaoPapel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificacaoPerfil, identificacaoPapel);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PerfilPapelPK that = (PerfilPapelPK) obj;
        return Objects.equals(this.identificacaoPerfil, that.identificacaoPerfil)
                && Objects.equals(this.identificacaoPapel, that.identificacaoPapel);
    }

    @Override
    public String toString() {
        return "PerfilPapelPK [identificacaoPerfil=" + identificacaoPerfil + ", identificacaoPapel="
                + identificacaoPapel + "]";
    }

}
