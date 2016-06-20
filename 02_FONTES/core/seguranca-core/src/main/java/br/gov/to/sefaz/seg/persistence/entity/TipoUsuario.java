package br.gov.to.sefaz.seg.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
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
public class TipoUsuario extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 6896180943920582395L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_USUARIO")
    @SequenceGenerator(name = "SQ_TIPO_USUARIO", schema = "SEFAZ_SEG",
            sequenceName = "SQ_TIPO_USUARIO",
            allocationSize = 1)
    @Column(name = "CODIGO_TIPO_USUARIO")
    private Integer codigoTipoUsuario;

    @NotEmpty(message = "#{seg_msg['seg.gestao.tipoUsuario.descricaoTipoUsuario.obrigatorio']}")
    @NotNull(message = "#{seg_msg['seg.gestao.tipoUsuario.descricaoTipoUsuario.obrigatorio']}")
    @Size(max = 20, message = "#{seg_msg['seg.gestao.tipoUsuario.descricaoTipoUsuario.tamanho']}")
    @Column(name = "DESCRICAO_TIPO_USUARIO")
    private String descricaoTipoUsuario;

    @Transient
    private Long quantidadeUsuarios;

    public TipoUsuario() {
        // Construtor para inicialização por reflexão.
    }

    public TipoUsuario(Integer codigoTipoUsuario, String descricaoTipoUsuario, Long quantidadeUsuarios) {
        this.codigoTipoUsuario = codigoTipoUsuario;
        this.descricaoTipoUsuario = descricaoTipoUsuario;
        this.quantidadeUsuarios = quantidadeUsuarios;
    }

    @Override
    public Integer getId() {
        return codigoTipoUsuario;
    }

    public Integer getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(Integer codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    public String getDescricaoTipoUsuario() {
        return descricaoTipoUsuario;
    }

    public void setDescricaoTipoUsuario(String descricaoTipoUsuario) {
        this.descricaoTipoUsuario = descricaoTipoUsuario;
    }

    public Long getQuantidadeUsuarios() {
        return quantidadeUsuarios;
    }

    public void setQuantidadeUsuarios(Long quantidadeUsuarios) {
        this.quantidadeUsuarios = quantidadeUsuarios;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoTipoUsuario, descricaoTipoUsuario, quantidadeUsuarios);
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
        return Objects.equals(this.codigoTipoUsuario, that.codigoTipoUsuario)
                && Objects.equals(this.descricaoTipoUsuario, that.descricaoTipoUsuario);
    }

    @Override
    public String toString() {
        return "TipoUsuario [codigoTipoUsuario=" + codigoTipoUsuario + ", descricaoTipoUsuario=" + descricaoTipoUsuario
                + ", quantidadeUsuarios=" + quantidadeUsuarios + "]";
    }

}
