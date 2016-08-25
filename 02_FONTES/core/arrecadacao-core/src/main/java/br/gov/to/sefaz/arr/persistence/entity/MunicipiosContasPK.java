package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_CONVENIOS_TARIFAS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 11:15:04
 */
public class MunicipiosContasPK implements Serializable {

    private static final long serialVersionUID = 663963460030238191L;

    @Id
    @Column(name = "TIPO_CONTA")
    private Integer tipoConta;
    @Id
    @Column(name = "ID_MUNICIPIO")
    private Integer idMunicipio;

    public MunicipiosContasPK() {
        // Construtor para inicialização via reflection
    }

    public MunicipiosContasPK(Integer tipoConta, Integer idMunicipio) {
        this.tipoConta = tipoConta;
        this.idMunicipio = idMunicipio;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MunicipiosContasPK that = (MunicipiosContasPK) o;
        return Objects.equals(tipoConta, that.tipoConta)
                && Objects.equals(idMunicipio, that.idMunicipio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoConta, idMunicipio);
    }

    @Override
    public String toString() {
        return "MunicipiosContasPK{"
                + "tipoConta=" + tipoConta
                + ", idMunicipio=" + idMunicipio
                + '}';
    }
}
