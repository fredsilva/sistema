package br.gov.to.sefaz.arr.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade referente a primary key da tabela SEFAZ_ARR.TA_RESUMO_STR do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 21/06/2016 18:25:42
 */
public class ResumoStrPK implements Serializable {

    private static final long serialVersionUID = -8127244422911739433L;

    private LocalDateTime dataArrecadacao;
    private Integer idBanco;
    private Long idConvenio;

    public ResumoStrPK() {
        // Construtor para inicialização por reflexão.
    }

    public ResumoStrPK(LocalDateTime dataArrecadacao, Integer idBanco, Long idConvenio) {
        this.dataArrecadacao = dataArrecadacao;
        this.idBanco = idBanco;
        this.idConvenio = idConvenio;
    }

    public LocalDateTime getDataArrecadacao() {
        return dataArrecadacao;
    }

    public void setDataArrecadacao(LocalDateTime dataArrecadacao) {
        this.dataArrecadacao = dataArrecadacao;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResumoStrPK that = (ResumoStrPK) o;
        return Objects.equals(dataArrecadacao, that.dataArrecadacao)
                && Objects.equals(idBanco, that.idBanco)
                && Objects.equals(idConvenio, that.idConvenio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataArrecadacao, idBanco, idConvenio);
    }

    @Override
    public String toString() {
        return "ResumoStrPK{"
                + "dataArrecadacao=" + dataArrecadacao
                + ", idBanco=" + idBanco
                + ", idConvenio=" + idConvenio
                + '}';
    }
}
