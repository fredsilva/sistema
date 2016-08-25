package br.gov.to.sefaz.par.gestao.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_PAR.TA_PAIS do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 02/05/2016 14:51:00
 */
@Entity
@Table(name = "TA_PAIS", schema = "SEFAZ_PAR")
public class Pais extends AbstractEntity<String> {

    private static final long serialVersionUID = -2053750571634766343L;

    @Id
    @NotNull(message = "#{par_msg['pais.codigoPais.obrigatorio']}")
    @Size(min = 1, max = 3, message = "#{par_msg['pais.codigoPais.tamanho']}")
    @Column(name = "CODIGO_PAIS")
    private String codigoPais;

    @Size(max = 50, message = "#{par_msg['pais.nomePais.tamanho']}")
    @Column(name = "NOME_PAIS")
    private String nomePais;

    public Pais() {
        // Construtor para inicialização por reflexão.
    }

    public Pais(String codigoPais, String nomePais) {
        super();
        this.codigoPais = codigoPais;
        this.nomePais = nomePais;
    }

    @Override
    public String getId() {
        return codigoPais;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Pais that = (Pais) obj;
        return Objects.equals(codigoPais, that.codigoPais) && Objects.equals(nomePais, that.nomePais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPais, nomePais);
    }

    @Override
    public String toString() {
        return "Pais{" + "codigoPais='" + codigoPais + '\'' + ", nomePais='" + nomePais + '\'' + '}';
    }
}
