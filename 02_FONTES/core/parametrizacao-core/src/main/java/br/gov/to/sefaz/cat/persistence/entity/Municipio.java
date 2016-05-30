package br.gov.to.sefaz.cat.persistence.entity;

import br.gov.to.sefaz.persistence.converter.YesOrNoBooleanConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_PAR.TA_MUNICIPIO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 02/05/2016 14:39:37
 */
@Entity
@Table(name = "TA_MUNICIPIO", schema = "SEFAZ_PAR")
public class Municipio extends AbstractEntity<Integer> {

    private static final long serialVersionUID = -8892878405392876020L;

    @Id
    @NotNull(message = "#{par_msg['municipios.codigoIbge.obrigatorio']}")
    @Max(value = 9999999, message = "#{par_msg['municipios.codigoIbge.maximo']}")
    @Min(value = 1, message = "#{par_msg['municipios.codigoIbge.minimo']}")
    @Column(name = "CODIGO_IBGE", nullable = false)
    private Integer codigoIbge;

    @Max(value = 9999999, message = "#{par_msg['municipios.codigoMunicipio.maximo']}")
    @Min(value = 1, message = "#{par_msg['municipios.codigoMunicipio.minimo']}")
    @Column(name = "CODIGO_MUNICIPIO")
    private Integer codigoMunicipio;

    @Size(max = 50, message = "#{par_msg['municipios.nomeMunicipio.tamanho']}")
    @Column(name = "NOME_MUNICIPIO", length = 50)
    private String nomeMunicipio;

    @Max(value = 9999, message = "#{par_msg['municipios.codigoMunicipioTom.maximo']}")
    @Min(value = 1, message = "#{par_msg['municipios.codigoMunicipioTom.minimo']}")
    @Column(name = "CODIGO_MUNICIPIO_TOM")
    private Integer codigoMunicipioTom;

    @Max(value = 999999999, message = "#{par_msg['municipios.codigoMunicipioSerpro.maximo']}")
    @Min(value = 1, message = "#{par_msg['municipios.codigoMunicipioSerpro.minimo']}")
    @Column(name = "CODIGO_MUNICIPIO_SERPRO")
    private Integer codigoMunicipioSerpro;

    @Column(name = "E_CAPITAL")
    @Convert(converter = YesOrNoBooleanConverter.class)
    private Boolean capital;

    @NotNull(message = "#{par_msg['municipios.unidadeFederacao.obrigatorio']}")
    @Column(name = "UNIDADE_FEDERACAO")
    private String unidadeFederacao;

    @JoinColumn(name = "UNIDADE_FEDERACAO", referencedColumnName = "UNIDADE_FEDERACAO", insertable = false,
            updatable = false)
    @ManyToOne
    private Estado estado;

    public Municipio() {
        // Construtor para inicialização por reflexão.
    }

    public Municipio(Integer codigoIbge, Integer codigoMunicipio, String nomeMunicipio, Integer codigoMunicipioTom,
            Integer codigoMunicipioSerpro, Boolean capital, String unidadeFederacao) {
        super();
        this.codigoIbge = codigoIbge;
        this.codigoMunicipio = codigoMunicipio;
        this.nomeMunicipio = nomeMunicipio;
        this.codigoMunicipioTom = codigoMunicipioTom;
        this.codigoMunicipioSerpro = codigoMunicipioSerpro;
        this.capital = capital;
        this.unidadeFederacao = unidadeFederacao;
    }

    @Override
    public Integer getId() {
        return codigoIbge;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public Integer getCodigoMunicipioTom() {
        return codigoMunicipioTom;
    }

    public void setCodigoMunicipioTom(Integer codigoMunicipioTom) {
        this.codigoMunicipioTom = codigoMunicipioTom;
    }

    public Integer getCodigoMunicipioSerpro() {
        return codigoMunicipioSerpro;
    }

    public void setCodigoMunicipioSerpro(Integer codigoMunicipioSerpro) {
        this.codigoMunicipioSerpro = codigoMunicipioSerpro;
    }

    public Boolean getCapital() {
        return capital;
    }

    public void setCapital(Boolean capital) {
        this.capital = capital;
    }

    public String getUnidadeFederacao() {
        return unidadeFederacao;
    }

    public void setUnidadeFederacao(String unidadeFederacao) {
        this.unidadeFederacao = unidadeFederacao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoIbge, codigoMunicipio, nomeMunicipio, codigoMunicipioTom, codigoMunicipioSerpro,
                capital, unidadeFederacao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Municipio municipio = (Municipio) o;
        return Objects.equals(codigoIbge, municipio.codigoIbge)
                && Objects.equals(codigoMunicipio, municipio.codigoMunicipio)
                && Objects.equals(nomeMunicipio, municipio.nomeMunicipio)
                && Objects.equals(codigoMunicipioTom, municipio.codigoMunicipioTom)
                && Objects.equals(codigoMunicipioSerpro, municipio.codigoMunicipioSerpro)
                && Objects.equals(capital, municipio.capital)
                && Objects.equals(unidadeFederacao, municipio.unidadeFederacao);
    }

    @Override
    public String toString() {
        return "Municipio{" + "codigoIbge=" + codigoIbge + ", codigoMunicipio=" + codigoMunicipio + ", nomeMunicipio='"
                + nomeMunicipio + '\'' + ", codigoMunicipioTom=" + codigoMunicipioTom + ", codigoMunicipioSerpro="
                + codigoMunicipioSerpro + ", capital=" + capital + ", unidadeFederacao=" + unidadeFederacao + '}';
    }
}
