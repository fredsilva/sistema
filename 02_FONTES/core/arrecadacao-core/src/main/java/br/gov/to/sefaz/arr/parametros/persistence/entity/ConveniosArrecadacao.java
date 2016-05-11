package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela TA_CONVENIOS_ARREC do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_CONVENIOS_ARREC", schema = "SEFAZ_ARR")
public class ConveniosArrecadacao extends AbstractEntity<Long> {

    @Id
    @NotNull
    @Column(name = "ID_CONVENIO", nullable = false)
    private Long idConvenio;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRICAO_CONVENIO", nullable = false, length = 100)
    private String descricaoConvenio;

    @NotNull
    @Column(name = "TIPO_BARRA", nullable = false)
    private Integer tipoBarra;

    @NotNull
    @Column(name = "SITUACAO", nullable = false)
    private Integer situacao;

    @NotNull
    @Column(name = "VERSAO_ARQUIVO", nullable = false)
    private Integer versaoArquivo;

    @NotNull
    @Column(name = "TIPO_CONVENIO", nullable = false)
    private Integer tipoConvenio;

    @JoinColumns({ @JoinColumn(name = "ID_BANCO", referencedColumnName = "ID_BANCO", nullable = false),
            @JoinColumn(name = "ID_AGENCIA", referencedColumnName = "ID_AGENCIA", nullable = false) })
    @ManyToOne(optional = false)
    private BancoAgencias bancoAgencias;

    public ConveniosArrecadacao() {
        // Construtor para inicialização por reflexão.
    }

    public ConveniosArrecadacao(Long idConvenio, String descricaoConvenio, Integer tipoBarra, Integer situacao,
            Integer versaoArquivo, Integer tipoConvenio, BancoAgencias bancoAgencias) {
        super();
        this.idConvenio = idConvenio;
        this.descricaoConvenio = descricaoConvenio;
        this.tipoBarra = tipoBarra;
        this.situacao = situacao;
        this.versaoArquivo = versaoArquivo;
        this.tipoConvenio = tipoConvenio;
        this.bancoAgencias = bancoAgencias;
    }

    @Override
    public Long getId() {
        return idConvenio;
    }

    public Long getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Long idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getDescricaoConvenio() {
        return descricaoConvenio;
    }

    public void setDescricaoConvenio(String descricaoConvenio) {
        this.descricaoConvenio = descricaoConvenio;
    }

    public Integer getTipoBarra() {
        return tipoBarra;
    }

    public void setTipoBarra(Integer tipoBarra) {
        this.tipoBarra = tipoBarra;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Integer getVersaoArquivo() {
        return versaoArquivo;
    }

    public void setVersaoArquivo(Integer versaoArquivo) {
        this.versaoArquivo = versaoArquivo;
    }

    public Integer getTipoConvenio() {
        return tipoConvenio;
    }

    public void setTipoConvenio(Integer tipoConvenio) {
        this.tipoConvenio = tipoConvenio;
    }

    public BancoAgencias getBancoAgencias() {
        return bancoAgencias;
    }

    public void setBancoAgencias(BancoAgencias bancoAgencias) {
        this.bancoAgencias = bancoAgencias;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ConveniosArrecadacao that = (ConveniosArrecadacao) obj;
        return tipoBarra == that.tipoBarra && situacao == that.situacao && versaoArquivo == that.versaoArquivo
                && tipoConvenio == that.tipoConvenio && Objects.equals(idConvenio, that.idConvenio)
                && Objects.equals(descricaoConvenio, that.descricaoConvenio)
                && Objects.equals(bancoAgencias, that.bancoAgencias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idConvenio, descricaoConvenio, tipoBarra, situacao, versaoArquivo, tipoConvenio,
                bancoAgencias);
    }

    @Override
    public String toString() {
        return "ConveniosArrecadacao{" + "idConvenio=" + idConvenio + ", descricaoConvenio='" + descricaoConvenio + '\''
                + ", tipoBarra=" + tipoBarra + ", situacao=" + situacao + ", versaoArquivo=" + versaoArquivo
                + ", tipoConvenio=" + tipoConvenio + ", bancoAgencias=" + bancoAgencias + '}';
    }
}
