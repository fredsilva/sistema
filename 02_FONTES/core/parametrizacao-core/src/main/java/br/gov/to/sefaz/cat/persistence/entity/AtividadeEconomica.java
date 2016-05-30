package br.gov.to.sefaz.cat.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade da tabela SEFAZ_PAR.TA_ATIVIDADE_ECONOMICA.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 19/05/2016 15:07:00
 */
@Entity
@Table(name = "TA_ATIVIDADE_ECONOMICA", schema = "SEFAZ_PAR")
public class AtividadeEconomica extends AbstractEntity<String> {

    private static final long serialVersionUID = -280178682163903873L;

    @Id
    @NotNull
    @Column(name = "CODIGO_CNAE")
    private String codigoCnae;

    @NotNull
    @Column(name = "DESCRICAO_CNAE")
    private String descricaoCnae;

    @NotNull
    @Column(name = "CODIGO_SUPERIOR_CNAE")
    private String codigoSuperiorCnae;

    @NotNull
    @Column(name = "ID_GRUPO_CNAE")
    private Integer idGrupoCnae;

    @NotNull
    @Column(name = "PORCENTAGEM_CNAE")
    private Integer porcentagemCnae;

    @NotNull
    @Column(name = "SITUACAO_CNAE")
    private Character situacaoCnae;

    public AtividadeEconomica() {
        // Construtor vazio para instanciação via reflection
    }

    public AtividadeEconomica(
            String codigoCnae, String descricaoCnae, String codigoSuperiorCnae, Integer idGrupoCnae,
            Integer porcentagemCnae, Character situacaoCnae) {
        this.codigoCnae = codigoCnae;
        this.descricaoCnae = descricaoCnae;
        this.codigoSuperiorCnae = codigoSuperiorCnae;
        this.idGrupoCnae = idGrupoCnae;
        this.porcentagemCnae = porcentagemCnae;
        this.situacaoCnae = situacaoCnae;
    }

    @Override
    public String getId() {
        return codigoCnae;
    }

    public String getCodigoCnae() {
        return codigoCnae;
    }

    public void setCodigoCnae(String codigoCnae) {
        this.codigoCnae = codigoCnae;
    }

    public String getDescricaoCnae() {
        return descricaoCnae;
    }

    public void setDescricaoCnae(String descricaoCnae) {
        this.descricaoCnae = descricaoCnae;
    }

    public String getCodigoSuperiorCnae() {
        return codigoSuperiorCnae;
    }

    public void setCodigoSuperiorCnae(String codigoSuperiorCnae) {
        this.codigoSuperiorCnae = codigoSuperiorCnae;
    }

    public Integer getIdGrupoCnae() {
        return idGrupoCnae;
    }

    public void setIdGrupoCnae(Integer idGrupoCnae) {
        this.idGrupoCnae = idGrupoCnae;
    }

    public Integer getPorcentagemCnae() {
        return porcentagemCnae;
    }

    public void setPorcentagemCnae(Integer porcentagemCnae) {
        this.porcentagemCnae = porcentagemCnae;
    }

    public Character getSituacaoCnae() {
        return situacaoCnae;
    }

    public void setSituacaoCnae(Character situacaoCnae) {
        this.situacaoCnae = situacaoCnae;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AtividadeEconomica that = (AtividadeEconomica) o;
        return Objects.equals(codigoCnae, that.codigoCnae)
                && Objects.equals(descricaoCnae, that.descricaoCnae)
                && Objects.equals(codigoSuperiorCnae, that.codigoSuperiorCnae)
                && Objects.equals(idGrupoCnae, that.idGrupoCnae)
                && Objects.equals(porcentagemCnae, that.porcentagemCnae)
                && Objects.equals(situacaoCnae, that.situacaoCnae);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoCnae, descricaoCnae, codigoSuperiorCnae, idGrupoCnae, porcentagemCnae, situacaoCnae);
    }

    @Override
    public String toString() {
        return "AtividadeEconomica{"
                + "codigoCnae='" + codigoCnae + '\''
                + ", descricaoCnae='" + descricaoCnae + '\''
                + ", codigoSuperiorCnae='" + codigoSuperiorCnae + '\''
                + ", idGrupoCnae=" + idGrupoCnae
                + ", porcentagemCnae=" + porcentagemCnae
                + ", situacaoCnae=" + situacaoCnae
                + '}';
    }
}
