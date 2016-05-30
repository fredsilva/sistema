package br.gov.to.sefaz.arr.parametros.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade referente a primary key da tabela TA_BANCO_AGENCIAS do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 12/05/2016 08:40:39
 */
public class BancoAgenciasPK implements Serializable {

    private static final long serialVersionUID = -6015967555080876974L;

    private Integer idBanco;
    private Integer idAgencia;

    public BancoAgenciasPK() {
        // Construtor para inicialização por reflexão.
    }

    public BancoAgenciasPK(Integer idBanco, Integer idAgencia) {
        this.idBanco = idBanco;
        this.idAgencia = idAgencia;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BancoAgenciasPK that = (BancoAgenciasPK) object;
        return Objects.equals(this.idAgencia, that.idAgencia) && Objects.equals(this.idBanco, idBanco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAgencia, idBanco);
    }

    @Override
    public String toString() {
        return "BancoAgenciasPK [idBanco=" + idBanco + ", idAgencia=" + idAgencia + "]";
    }

}
