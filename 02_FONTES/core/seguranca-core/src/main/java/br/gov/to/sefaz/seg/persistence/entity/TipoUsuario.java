package br.gov.to.sefaz.seg.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_SEG.TA_TIPO_USUARIO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 13/05/2016 10:30:37
 */
@Entity
@Table(name = "TA_TIPO_USUARIO", schema = "SEFAZ_SEG")
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 256894585328674312L;

    @Id
    @NotNull
    @Column(name = "CODIGO_TIPO_USUARIO")
    private Short codigoTipoUsuario;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "DESCRICAO_TIPO_USUARIO")
    private String descricaoTipoUsuario;

    public TipoUsuario() {
        //Construtor default utilizado para instanciação via reflexão
    }

    public TipoUsuario(Short codigoTipoUsuario, String descricaoTipoUsuario) {
        super();
        this.codigoTipoUsuario = codigoTipoUsuario;
        this.descricaoTipoUsuario = descricaoTipoUsuario;
    }

    public Short getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(Short codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    public String getDescricaoTipoUsuario() {
        return descricaoTipoUsuario;
    }

    public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {
        this.descricaoTipoUsuario = descricaoTipoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoTipoUsuario, descricaoTipoUsuario);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TipoUsuario that = (TipoUsuario) obj;
        return codigoTipoUsuario == that.codigoTipoUsuario
                && descricaoTipoUsuario == that.descricaoTipoUsuario;
    }

    @Override
    public String toString() {
        return "TipoUsuario [codigoTipoUsuario=" + codigoTipoUsuario + ", descricaoTipoUsuario=" + descricaoTipoUsuario
                + "]";
    }

}
