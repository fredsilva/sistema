package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.cat.persistence.entity.Municipio;
import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela TA_BANCO_AGENCIAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 04/05/2016 14:58:00
 */
@Entity
@Table(name = "TA_BANCO_AGENCIAS", schema = "SEFAZ_ARR")
@IdClass(BancoAgenciasPK.class)
public class BancoAgencias extends AbstractEntity<BancoAgenciasPK> {

    @Id
    @NotNull(message = "O campo Código do Banco é obrigatório e deve ser informado!")
    @Max(value = 9999, message = "O campo Código do Banco deve conter no máximo 4 digitos!")
    @Min(value = 1, message = "O campo Código do Banco deve ser maior do que 0!")
    @Column(name = "ID_BANCO", nullable = false)
    private Integer idBanco;

    @Id
    @NotNull(message = "O campo Código da Agência é obrigatório e deve ser informado!")
    @Max(value = 999999, message = "O campo Código da Agência deve conter no máximo 6 digitos!")
    @Min(value = 1, message = "O campo Código da Agência deve ser maior do que 0!")
    @Column(name = "ID_AGENCIA", nullable = false)
    private Integer idAgencia;

    @NotNull
    @Size(max = 2, message = "O campo Dígito da Agência deve possuir no máximo 2 caracteres!")
    @Column(name = "DIGITO_AGENCIA", nullable = false, length = 2)
    private String digitoAgencia;

    @NotEmpty(message = "O campo Nome da Agência é obrigatório e não foi informado!")
    @Size(max = 150, message = "O campo Nome da Agência deve possuir no máximo 150 caracteres!")
    @Column(name = "NOME_AGENCIA", nullable = false, length = 150)
    private String nomeAgencia;

    @NotNull(message = "O campo CNPJ é obrigatório e não foi informado!")
    @Column(name = "CNPJ_AGENCIA", nullable = false)
    private Long cnpjAgencia;

    @Column(name = "SITUACAO", nullable = false)
    @NotNull(message = "O campo Situação é obrigatório e deve ser informado!")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    @Column(name = "CENTRALIZADORA")
    private Integer centralizadora;

    @NotNull(message = "O campo Número da Conta Corrente é obrigatório e não foi informado!")
    @Column(name = "NUMERO_CONTA_CORRENTE", nullable = false)
    private Long numeroContaCorrente;

    @NotNull(message = "O campo Digito da Conta Corrente é obrigatório e não foi informado!")
    @Size(max = 2, message = "O campo Nome do Banco deve possuir no máximo 2 caracteres!")
    @Column(name = "DIGITO_CONTA_CORRENTE", nullable = false, length = 2)
    private String digitoContaCorrente;

    @NotNull(message = "O campo Município é obrigatório e não foi informado!")
    @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "CODIGO_MUNICIPIO", nullable = false)
    @ManyToOne(optional = false)
    private Municipio municipio;

    @NotNull(message = "O campo Email é obrigatório e não foi informado!")
    @Size(max = 150, message = "O campo Email deve possuir no máximo 150 caracteres!")
    @Column(name = "EMAIL", nullable = false, length = 150)
    private String email;

    @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_"
            + "BANCO", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bancos bancos;

    public BancoAgencias() {
        // Construtor para inicialização por reflexão.
    }

    public BancoAgencias(Integer idBanco, Integer idAgencia, String digitoAgencia, String nomeAgencia, Long cnpjAgencia,
            SituacaoEnum situacao, Integer centralizadora, Long numeroContaCorrente, String digitoContaCorrente,
            Municipio municipio, String email, Bancos bancos) {
        super();
        this.idBanco = idBanco;
        this.idAgencia = idAgencia;
        this.digitoAgencia = digitoAgencia;
        this.nomeAgencia = nomeAgencia;
        this.cnpjAgencia = cnpjAgencia;
        this.situacao = situacao;
        this.centralizadora = centralizadora;
        this.numeroContaCorrente = numeroContaCorrente;
        this.digitoContaCorrente = digitoContaCorrente;
        this.municipio = municipio;
        this.email = email;
        this.bancos = bancos;
    }

    @Override
    public BancoAgenciasPK getId() {
        return new BancoAgenciasPK(getIdBanco(), getIdAgencia());
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

    public Long getCnpjAgencia() {
        return cnpjAgencia;
    }

    public void setCnpjAgencia(Long cnpjAgencia) {
        this.cnpjAgencia = cnpjAgencia;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Integer getCentralizadora() {
        return centralizadora;
    }

    public void setCentralizadora(Integer centralizadora) {
        this.centralizadora = centralizadora;
    }

    public Long getNumeroContaCorrente() {
        return numeroContaCorrente;
    }

    public void setNumeroContaCorrente(Long numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
    }

    public String getDigitoContaCorrente() {
        return digitoContaCorrente;
    }

    public void setDigitoContaCorrente(String digitoContaCorrente) {
        this.digitoContaCorrente = digitoContaCorrente;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bancos getBancos() {
        return bancos;
    }

    public void setBancos(Bancos bancos) {
        this.bancos = bancos;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BancoAgencias that = (BancoAgencias) obj;
        return Objects.equals(idBanco, that.idBanco) && Objects.equals(idAgencia, that.idAgencia)
                && Objects.equals(digitoAgencia, that.digitoAgencia) && Objects.equals(nomeAgencia, that.nomeAgencia)
                && Objects.equals(cnpjAgencia, that.cnpjAgencia) && Objects.equals(situacao, that.situacao)
                && Objects.equals(centralizadora, that.centralizadora)
                && Objects.equals(numeroContaCorrente, that.numeroContaCorrente)
                && Objects.equals(digitoContaCorrente, that.digitoContaCorrente)
                && Objects.equals(municipio, that.municipio) && Objects.equals(email, that.email)
                && Objects.equals(bancos, that.bancos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, idAgencia, digitoAgencia, nomeAgencia, cnpjAgencia, situacao, centralizadora,
                numeroContaCorrente, digitoContaCorrente, municipio, email, bancos);
    }

    @Override
    public String toString() {
        return "BancoAgencias [idBanco=" + idBanco + ", idAgencia=" + idAgencia + ", digitoAgencia=" + digitoAgencia
                + ", nomeAgencia=" + nomeAgencia + ", cnpjAgencia=" + cnpjAgencia + ", centralizadora=" + centralizadora
                + ", numeroContaCorrente=" + numeroContaCorrente + ", digitoContaCorrente=" + digitoContaCorrente
                + ", email=" + email + "]";
    }
}
