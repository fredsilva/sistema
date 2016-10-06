package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.business.service.validation.custom.Cnpj;
import br.gov.to.sefaz.par.gestao.persistence.entity.Municipio;
import br.gov.to.sefaz.persistence.converter.OneOrNullBooleanConverter;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_BANCO_AGENCIAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 04/05/2016 14:58:00
 */
@Entity
@Table(name = "TA_BANCO_AGENCIAS", schema = "SEFAZ_ARR")
@IdClass(BancoAgenciasPK.class)
public class BancoAgencias extends AbstractEntity<BancoAgenciasPK> {

    private static final long serialVersionUID = 8497288537240673716L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.bancoAgencias.idBanco.obrigatorio']}")
    @Max(value = 9999, message = "#{arr_msg['parametros.bancoAgencias.idBanco.maximo']}")
    @Min(value = 1, message = "#{arr_msg['parametros.bancoAgencias.idBanco.minimo']}")
    @Column(name = "ID_BANCO", nullable = false)
    private Integer idBanco;

    @Id
    @NotNull(message = "#{arr_msg['parametros.bancoAgencias.idAgencia.obrigatorio']}")
    @Max(value = 999999, message = "#{arr_msg['parametros.bancoAgencias.idAgencia.maximo']}")
    @Min(value = 1, message = "#{arr_msg['parametros.bancoAgencias.idAgencia.minimo']}")
    @Column(name = "ID_AGENCIA", nullable = false)
    private Integer idAgencia;

    @NotEmpty(message = "#{arr_msg['parametros.bancoAgencias.digitoAgencia.obrigatorio']}")
    @Size(max = 2, message = "#{arr_msg['parametros.bancoAgencias.digitoAgencia.tamanho']}")
    @Column(name = "DIGITO_AGENCIA", nullable = false, length = 2)
    private String digitoAgencia;

    @NotEmpty(message = "#{arr_msg['parametros.bancoAgencias.nomeAgencia.obrigatorio']}")
    @Size(max = 150, message = "#{arr_msg['parametros.bancoAgencias.nomeAgencia.tamanho']}")
    @Column(name = "NOME_AGENCIA", nullable = false, length = 150)
    private String nomeAgencia;

    @NotNull(message = "#{arr_msg['parametros.bancoAgencias.cnpjAgencia.obrigatorio']}")
    @Cnpj(message = "#{arr_msg['parametros.bancoAgencias.cnpjAgencia.incorreto']}")
    @Column(name = "CNPJ_AGENCIA", nullable = false)
    private Long cnpjAgencia;

    @Column(name = "SITUACAO", nullable = false)
    @NotNull(message = "#{arr_msg['parametros.bancoAgencias.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    @Column(name = "CENTRALIZADORA")
    @Convert(converter = OneOrNullBooleanConverter.class)
    private Boolean centralizadora;

    @NotNull(message = "#{arr_msg['parametros.bancoAgencias.numeroContaCorrente.obrigatorio']}")
    @Min(value = 1, message = "#{arr_msg['parametros.bancoAgencias.numeroContaCorrente.minimo']}")
    @Max(value = 99999999, message = "#{arr_msg['parametros.bancoAgencias.numeroContaCorrente.maximo']}")
    @Column(name = "NUMERO_CONTA_CORRENTE", nullable = false)
    private Long numeroContaCorrente;

    @NotNull(message = "#{arr_msg['parametros.bancoAgencias.digitoContaCorrente.obrigatorio']}")
    @Size(max = 2, message = "#{arr_msg['parametros.bancoAgencias.digitoContaCorrente.tamanho']}")
    @Column(name = "DIGITO_CONTA_CORRENTE", nullable = false, length = 2)
    private String digitoContaCorrente;

    @NotNull(message = "#arr_msg['parametros.bancoAgencias.idMunicipio.obrigatorio']")
    @Column(name = "ID_MUNICIPIO")
    private Integer idMunicipio;

    @NotEmpty(message = "#{arr_msg['parametros.bancoAgencias.email.obrigatorio']}")
    @Size(max = 150, message = "#{arr_msg['parametros.bancoAgencias.email.tamanho']}")
    @Pattern(regexp = "([A-Za-z0-9\\._-]+@[A-Za-z0-9\\._-]+\\.[A-Za-z]{2,4}+)|",
            message = "#{arr_msg['parametros.bancoAgencias.email.incorreto']}")
    @Column(name = "EMAIL", nullable = false, length = 150)
    private String email;

    @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO",
            nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Bancos bancos;

    @JoinColumn(name = "ID_MUNICIPIO", referencedColumnName = "CODIGO_IBGE",
            insertable = false, updatable = false)
    @ManyToOne()
    private Municipio municipio;

    public BancoAgencias() {
        bancos = new Bancos();
        municipio = new Municipio();
    }

    public BancoAgencias(
            BancoAgenciasPK agenciasPK) {
        this.idBanco = agenciasPK.getIdBanco();
        this.idAgencia = agenciasPK.getIdAgencia();
    }

    public BancoAgencias(
            Integer idBanco, Integer idAgencia, String digitoAgencia, String nomeAgencia,
            Long cnpjAgencia, SituacaoEnum situacao, Boolean centralizadora, Long numeroContaCorrente,
            String digitoContaCorrente, Integer idMunicipio, String email, Bancos bancos) {
        this.idBanco = idBanco;
        this.idAgencia = idAgencia;
        this.digitoAgencia = digitoAgencia;
        this.nomeAgencia = nomeAgencia;
        this.cnpjAgencia = cnpjAgencia;
        this.situacao = situacao;
        this.centralizadora = centralizadora;
        this.numeroContaCorrente = numeroContaCorrente;
        this.digitoContaCorrente = digitoContaCorrente;
        this.idMunicipio = idMunicipio;
        this.email = email;
        this.bancos = bancos;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    /**
     * Atribui valor ao campo {@link #idBanco} e {@link #bancos}.
     *
     * @param idBanco id do banco
     */
    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
        this.bancos.setIdBanco(idBanco);
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    @Override
    public BancoAgenciasPK getId() {
        return new BancoAgenciasPK(getIdBanco(), getIdAgencia());
    }

    public String getDigitoAgencia() {
        return digitoAgencia;
    }

    public void setDigitoAgencia(String digitoAgencia) {
        this.digitoAgencia = digitoAgencia;
    }

    public String getNumeroAgencia() {
        return this.idAgencia + "-" + this.digitoAgencia;
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

    public Boolean getCentralizadora() {
        return centralizadora;
    }

    public void setCentralizadora(Boolean centralizadora) {
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

    public String getNumeroCC() {
        return numeroContaCorrente + "-" + this.digitoContaCorrente;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    /**
     * Atribui valor ao campo {@link #idMunicipio} e {@link #municipio}.
     *
     * @param idMunicipio id do municipio
     */
    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
        this.municipio.setCodigoIbge(idMunicipio);
    }

    public String getUnidadeFederacao() {
        return municipio != null ? municipio.getUnidadeFederacao() : "";
    }

    /**
     * Atribui valor ao campo {@link Municipio#setUnidadeFederacao(String)}.
     *
     * @param unidadeFederacao id do municipio
     */
    public void setUnidadeFederacao(String unidadeFederacao) {
        this.municipio.setUnidadeFederacao(unidadeFederacao);
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public String getNomeMunicipio() {
        return municipio == null ? "" : municipio.getNomeMunicipio();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BancoAgencias that = (BancoAgencias) o;
        return Objects.equals(idBanco, that.idBanco)
                && Objects.equals(idAgencia, that.idAgencia)
                && Objects.equals(digitoAgencia, that.digitoAgencia)
                && Objects.equals(nomeAgencia, that.nomeAgencia)
                && Objects.equals(cnpjAgencia, that.cnpjAgencia)
                && situacao == that.situacao
                && centralizadora == that.centralizadora
                && Objects.equals(numeroContaCorrente, that.numeroContaCorrente)
                && Objects.equals(digitoContaCorrente, that.digitoContaCorrente)
                && Objects.equals(idMunicipio, that.idMunicipio)
                && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, idAgencia, digitoAgencia, nomeAgencia, cnpjAgencia, situacao, centralizadora,
                numeroContaCorrente, digitoContaCorrente, idMunicipio, email);
    }

    @Override
    public String toString() {
        return "BancoAgencias{"
                + "idBanco=" + idBanco
                + ", idAgencia=" + idAgencia
                + ", digitoAgencia='" + digitoAgencia + '\''
                + ", nomeAgencia='" + nomeAgencia + '\''
                + ", cnpjAgencia=" + cnpjAgencia
                + ", situacao=" + situacao
                + ", centralizadora=" + centralizadora
                + ", numeroContaCorrente=" + numeroContaCorrente
                + ", digitoContaCorrente='" + digitoContaCorrente + '\''
                + ", idMunicipio=" + idMunicipio
                + ", email='" + email + '\''
                + '}';
    }
}
