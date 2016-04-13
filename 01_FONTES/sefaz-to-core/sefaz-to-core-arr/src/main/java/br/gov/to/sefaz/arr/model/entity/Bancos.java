package br.gov.to.sefaz.arr.model.entity;

import br.gov.to.sefaz.common.model.SerializableEntity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author roger.gouveia
 */
@Entity
@Table(name = "ta_bancos")
public class Bancos implements SerializableEntity<Integer> {

    @Id
    @Column(name = "id_banco")
    @NotNull(message = "O campo Código é obrigatório e deve ser informado!")
    @Max(value = 9999, message = "O campo Código deve conter no máximo 4 digitos!")
    @Min(value = 1, message = "O campo Código deve ser maior do que 0!")
    private Integer idBanco;

    @Column(name = "nome_banco")
    @NotEmpty(message = "O campo Nome do Banco é obrigatório e não foi informado!")
    @Size(max = 150, message = "O campo Nome do Banco deve possuir no máximo 150 caracteres!")
    private String nomeBanco;

    @Column(name = "situacao")
    @NotNull(message = "O campo Situação é obrigatório e deve ser informado!")
    @Range(min = 1, max = 2, message = "O campo Situação deve ser Ativo ou Cancelado!")
    private Integer situacao;

    @Column(name = "cnpj_raiz")
    @NotNull(message = "O campo Raíz do CNPJ é obrigatório e não foi informado!")
    @Max(value = 99999999, message = "O campo Raíz do CNPJ deve possuir no máximo 8 dígitos!")
    @Min(value = 0, message = "O campo Raíz do CNPJ deve ser maior do que 0!")
    private Integer cnpjRaiz;

    public Bancos() {
        // Construtor para inicialização por reflexão.
    }

    public Bancos(int idBanco, String nomeBanco, int situacao, int cnpjRaiz) {
        this.idBanco = idBanco;
        this.nomeBanco = nomeBanco;
        this.situacao = situacao;
        this.cnpjRaiz = cnpjRaiz;
    }

    @Override
    public Integer getId() {
        return idBanco;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public Integer getCnpjRaiz() {
        return cnpjRaiz;
    }

    public void setCnpjRaiz(Integer cnpjRaiz) {
        this.cnpjRaiz = cnpjRaiz;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bancos that = (Bancos) obj;
        return idBanco == that.idBanco && situacao == that.situacao && Objects.equals(cnpjRaiz, that.cnpjRaiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, cnpjRaiz, situacao);
    }

    @Override
    public String toString() {
        return "BancosAgenciasEntity{" + "idBanco=" + idBanco + ", nomeBanco='" + nomeBanco + "' cnpjRaiz='" + cnpjRaiz
                + "', situacao=" + situacao + '}';
    }
}
