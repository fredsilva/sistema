package br.gov.to.sefaz.arr.parametros.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe para mapeamento de pk composta de {@link BancoAgencias}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public class BancoAgenciasPK implements Serializable {

    private static final long serialVersionUID = -1483815542829305337L;

    private Integer idBanco;

    private Integer idAgencia;

    public BancoAgenciasPK() {
        // Construtor para inicialização por reflexão.
    }

    public BancoAgenciasPK(Integer idBanco, Integer idAgencia) {
        super();
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BancoAgenciasPK that = (BancoAgenciasPK) obj;
        return Objects.equals(idBanco, that.idBanco) && Objects.equals(idAgencia, that.idAgencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, idAgencia);
    }

    @Override
    public String toString() {
        return "BancoAgenciasPK{" + "idBanco=" + idBanco + ", idAgencia=" + idAgencia + '}';
    }
}
