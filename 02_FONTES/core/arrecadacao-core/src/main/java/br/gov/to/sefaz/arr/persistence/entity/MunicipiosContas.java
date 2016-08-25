package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_CONVENIOS_TARIFAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 11:15:04
 */
@Entity
@Table(name = "TA_MUNICIPIOS_CONTAS", schema = "SEFAZ_ARR")
@IdClass(MunicipiosContasPK.class)
public class MunicipiosContas extends AbstractEntity<MunicipiosContasPK> {

    private static final long serialVersionUID = 663963460030238191L;

    @Id
    @Column(name = "TIPO_CONTA")
    private Integer tipoConta;
    @Id
    @Column(name = "ID_MUNICIPIO")
    private Integer idMunicipio;
    @Column(name = "ID_BANCO")
    private Integer idBanco;
    @Column(name = "ID_AGENCIA")
    private Integer idAgencia;
    @Column(name = "CONTA_CORRENTE")
    private String contaCorrente;

    public MunicipiosContas() {
        // Construtor para inicialização via reflection
    }

    public MunicipiosContas(Integer tipoConta, Integer idMunicipio, Integer idBanco,
            Integer idAgencia, String contaCorrente) {
        this.tipoConta = tipoConta;
        this.idMunicipio = idMunicipio;
        this.idBanco = idBanco;
        this.idAgencia = idAgencia;
        this.contaCorrente = contaCorrente;
    }

    @Override
    public MunicipiosContasPK getId() {
        return new MunicipiosContasPK(tipoConta, idMunicipio);
    }

    public Integer getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(Integer tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
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

    public String getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(String contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MunicipiosContas that = (MunicipiosContas) o;
        return Objects.equals(tipoConta, that.tipoConta)
                && Objects.equals(idMunicipio, that.idMunicipio)
                && Objects.equals(idBanco, that.idBanco)
                && Objects.equals(idAgencia, that.idAgencia)
                && Objects.equals(contaCorrente, that.contaCorrente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoConta, idMunicipio, idBanco, idAgencia, contaCorrente);
    }

    @Override
    public String toString() {
        return "MunicipiosContas{"
                + "tipoConta=" + tipoConta
                + ", idMunicipio=" + idMunicipio
                + ", idBanco=" + idBanco
                + ", idAgencia=" + idAgencia
                + ", contaCorrente='" + contaCorrente + '\''
                + '}';
    }
}
