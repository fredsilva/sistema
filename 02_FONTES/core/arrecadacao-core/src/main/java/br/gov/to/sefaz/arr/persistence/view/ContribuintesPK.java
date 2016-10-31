package br.gov.to.sefaz.arr.persistence.view;

import java.io.Serializable;

/**
 * Classe para mapeamento de PK composta de {@link Contribuintes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:00:00
 */
public class ContribuintesPK implements Serializable {

    private static final long serialVersionUID = -3395436752911599855L;

    private Integer tipoContribuinte;
    private Integer tipoPessoa;
    private Long idPessoa;

    public ContribuintesPK() {
        // Construtor para inicialização por reflexão.
    }

    public ContribuintesPK(Integer tipoContribuinte, Integer tipoPessoa, Long idPessoa) {
        this.tipoContribuinte = tipoContribuinte;
        this.tipoPessoa = tipoPessoa;
        this.idPessoa = idPessoa;
    }

    public Integer getTipoContribuinte() {
        return tipoContribuinte;
    }

    public void setTipoContribuinte(Integer tipoContribuinte) {
        this.tipoContribuinte = tipoContribuinte;
    }

    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }
}
