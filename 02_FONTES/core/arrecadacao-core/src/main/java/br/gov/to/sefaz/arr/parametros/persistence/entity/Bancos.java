package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela SEFAZ_ARR.TA_BANCO do Banco de Dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 12/05/2016 08:46:51
 */
@Entity
@Table(name = "TA_BANCOS", schema = "SEFAZ_ARR")
public class Bancos extends AbstractEntity<Integer> {

    private static final long serialVersionUID = -2312276170192298971L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.bancos.idBanco.obrigatorio']}")
    @Max(value = 9999, message = "#{arr_msg['parametros.bancos.idBanco.maximo']}")
    @Min(value = 1, message = "#{arr_msg['parametros.bancos.idBanco.minimo']}")
    @Column(name = "ID_BANCO", nullable = false)
    private Integer idBanco;

    @NotEmpty(message = "#{arr_msg['parametros.bancos.nomeBanco.obrigatorio']}")
    @Size(max = 150, message = "#{arr_msg['parametros.bancos.nomeBanco.tamanho']}")
    @Column(name = "NOME_BANCO", nullable = false, length = 150)
    private String nomeBanco;

    @NotNull(message = "#{arr_msg['parametros.bancos.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO", nullable = false)
    private SituacaoEnum situacao;

    @NotNull(message = "#{arr_msg['parametros.bancos.cnpjRaiz.obrigatorio']}")
    @Max(value = 9999999999L, message = "#{arr_msg['parametros.bancos.cnpjRaiz.maximo']}")
    @Column(name = "CNPJ_RAIZ", nullable = false)
    private Integer cnpjRaiz;

    public Bancos() {
        // Construtor para inicialização por reflexão.
    }

    public Bancos(
            Integer idBanco, String nomeBanco, SituacaoEnum situacao, Integer cnpjRaiz) {
        this.idBanco = idBanco;
        this.nomeBanco = nomeBanco;
        this.situacao = situacao;
        this.cnpjRaiz = cnpjRaiz;
    }

    @Override
    public Integer getId() {
        return idBanco;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public Integer getCnpjRaiz() {
        return cnpjRaiz;
    }

    public void setCnpjRaiz(Integer cnpjRaiz) {
        this.cnpjRaiz = cnpjRaiz;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Bancos that = (Bancos) object;
        return Objects.equals(this.idBanco, that.idBanco) && Objects.equals(this.nomeBanco, that.nomeBanco)
                && Objects.equals(this.situacao, that.situacao) && Objects.equals(this.cnpjRaiz, that.cnpjRaiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, nomeBanco, situacao, cnpjRaiz);
    }

    @Override
    public String toString() {
        return "Bancos{"
                + "idBanco=" + idBanco
                + ", nomeBanco='" + nomeBanco + '\''
                + ", situacao=" + situacao
                + ", cnpjRaiz='" + cnpjRaiz + '\''
                + '}';
    }
}
