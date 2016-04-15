package br.gov.to.sefaz.arr.persistence.entity;

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
 * Entidade das Agências Bancárias.
 * 
 * @author roger.gouveia
 */
@Entity
@Table(name = "ta_banco_agencias")
public class Agencias implements SerializableEntity<Integer> {

    @Id
    @Column(name = "id_banco")
    @NotNull(message = "O campo Código do Banco é obrigatório e deve ser informado!")
    @Max(value = 9999, message = "O campo Código do Banco deve conter no máximo 4 digitos!")
    @Min(value = 1, message = "O campo Código do Banco deve ser maior do que 0!")
    private Integer idBanco;

    // TODO: criar objeto para chave primaria composta
    @Column(name = "id_agencia")
    @NotNull(message = "O campo Código da Agência é obrigatório e deve ser informado!")
    @Max(value = 999999, message = "O campo Código da Agência deve conter no máximo 6 digitos!")
    @Min(value = 1, message = "O campo Código da Agência deve ser maior do que 0!")
    private Integer idAgencia;

    @Column(name = "digito_agencia")
    @Size(max = 2, message = "O campo Dígito da Agência deve possuir no máximo 2 caracteres!")
    private String digitoAgencia;

    @Column(name = "nome_agencia")
    @NotEmpty(message = "O campo Nome da Agência é obrigatório e não foi informado!")
    @Size(max = 150, message = "O campo Nome da Agência deve possuir no máximo 150 caracteres!")
    private String nomeAgencia;

    @Column(name = "cnpj_agencia")
    @NotNull(message = "O campo CNPJ é obrigatório e não foi informado!")
    // @Max(value = 99999999999999, message = "O campo CNPJ deve possuir no
    // máximo 14 dígitos!")
    @Min(value = 0, message = "O campo CNPJ deve ser maior do que 0!")
    private Integer cnpjAgencia;

    @Column(name = "situacao")
    @NotNull(message = "O campo Situação é obrigatório e deve ser informado!")
    @Range(min = 1, max = 2, message = "O campo Situação deve ser Ativo ou Cancelado!")
    private Integer situacao;

    @Column(name = "numero_conta_corrente")
    // @Max(value = 9999999999, message = "O campo Número de Conta Corrente deve
    // possuir no máximo 10 dígitos!")
    @Min(value = 0, message = "O campo Número de Conta Corrente deve ser maior do que 0!")
    private Integer numeroContaCorrente;

    @Column(name = "digito_conta_corrente")
    @Size(max = 2, message = "O campo Nome do Banco deve possuir no máximo 2 caracteres!")
    private String digitoContaCorrente;

    @Column(name = "id_municipio")
    @Max(value = 9999999, message = "O campo Município deve possuir no máximo 7 dígitos!")
    @Min(value = 0, message = "O campo Município deve ser maior do que 0!")
    private Integer idMunicipio;

    @Column(name = "email")
    @Size(max = 150, message = "O campo Email deve possuir no máximo 150 caracteres!")
    private String email;

    public Agencias() {
        // Construtor para inicialização por reflexão.
    }

    public Agencias(Integer idBanco, Integer idAgencia, String digitoAgencia, String nomeAgencia, Integer cnpjAgencia,
            Integer situacao, Integer numeroContaCorrente, String digitoContaCorrente, Integer idMunicipio,
            String email) {
        this.idBanco = idBanco;
        this.idAgencia = idAgencia;
        this.digitoAgencia = digitoAgencia;
        this.nomeAgencia = nomeAgencia;
        this.cnpjAgencia = cnpjAgencia;
        this.situacao = situacao;
        this.numeroContaCorrente = numeroContaCorrente;
        this.digitoContaCorrente = digitoContaCorrente;
        this.idMunicipio = idMunicipio;
        this.email = email;
    }

    @Override
    public Integer getId() {
        return idAgencia;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(String digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public Integer getCnpjAgencia() {
        return cnpjAgencia;
    }

    public void setCnpjAgencia(Integer cnpjAgencia) {
        this.cnpjAgencia = cnpjAgencia;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Integer getNumeroContaCorrente() {
        return numeroContaCorrente;
    }

    public void setNumeroContaCorrente(Integer numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
    }

    public String getDigitoContaCorrente() {
        return digitoContaCorrente;
    }

    public void setDigitoContaCorrente(String digitoContaCorrente) {
        this.digitoContaCorrente = digitoContaCorrente;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Agencias that = (Agencias) obj;
        return idBanco == that.idBanco && idAgencia == that.idAgencia && situacao == that.situacao
                && Objects.equals(cnpjAgencia, that.cnpjAgencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, idAgencia, cnpjAgencia, situacao);
    }

    @Override
    public String toString() {
        return "Agencias{" + "idBanco=" + idBanco + ", nomeAgencia='" + nomeAgencia + "', idAgencia='" + idAgencia
                + "', cnpjAgencia='" + cnpjAgencia + "', situacao=" + situacao + '}';
    }
}
