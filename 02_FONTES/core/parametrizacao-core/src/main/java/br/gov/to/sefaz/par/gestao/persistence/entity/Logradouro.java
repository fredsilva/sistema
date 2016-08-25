package br.gov.to.sefaz.par.gestao.persistence.entity;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_PAR.TA_LOGRADOURO do Banco de Dados.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 14/07/2016 17:39:00
 */
@Entity
@Table(name = "TA_LOGRADOURO", schema = "SEFAZ_PAR")
public class Logradouro implements Serializable {

    private static final long serialVersionUID = 6972919425485318834L;

    @Id
    @NotEmpty(message = "#{par_msg['logradouro.codigoLogradouro.obrigatorio']}")
    @Size(min = 1, max = 3, message = "#{par_msg['logradouro.codigoLogradouro.tamanho']}")
    @Column(name = "CODIGO_LOGRADOURO")
    private String codigoLogradouro;

    @NotEmpty(message = "#{par_msg['logradouro.descricaoLogradouro.obrigatorio']}")
    @Size(max = 50, message = "#{par_msg['logradouro.descricaoLogradouro.tamanho']}")
    @Column(name = "DESCRICAO_LOGRADOURO")
    private String descricaoLogradouro;

    public Logradouro() {
        // Construtor para inicialização por reflexão.
    }

    public Logradouro(String codigoLogradouro, String descricaoLogradouro) {
        this.codigoLogradouro = codigoLogradouro;
        this.descricaoLogradouro = descricaoLogradouro;
    }

    public String getCodigoLogradouro() {
        return codigoLogradouro;
    }

    public void setCodigoLogradouro(String codigoLogradouro) {
        this.codigoLogradouro = codigoLogradouro;
    }

    public String getDescricaoLogradouro() {
        return descricaoLogradouro;
    }

    public void setDescricaoLogradouro(String descricaoLogradouro) {
        this.descricaoLogradouro = descricaoLogradouro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Logradouro that = (Logradouro) o;
        return Objects.equals(codigoLogradouro, that.codigoLogradouro)
                && Objects.equals(descricaoLogradouro, that.descricaoLogradouro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoLogradouro, descricaoLogradouro);
    }

    @Override
    public String toString() {
        return "Logradouro{"
                + "codigoLogradouro='" + codigoLogradouro + '\''
                + ", descricaoLogradouro='" + descricaoLogradouro + '\''
                + '}';
    }
}
