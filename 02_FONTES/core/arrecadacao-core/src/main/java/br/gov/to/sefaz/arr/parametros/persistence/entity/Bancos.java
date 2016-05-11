package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.arr.parametros.persistence.converter.CnpjRaizConverter;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entidade referente a tabela TA_BANCOS do Banco de Dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 28/04/2016 17:48:00
 */
@Entity
@Table(name = "TA_BANCOS", schema = "SEFAZ_ARR")
public class Bancos extends AbstractEntity<Integer> {

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

    @Column(name = "SITUACAO", nullable = false)
    @NotNull(message = "#{arr_msg['parametros.bancos.situacao.obrigatorio']}")
    @Convert(converter = SituacaoEnumConverter.class)
    private SituacaoEnum situacao;

    @NotNull(message = "#{arr_msg['parametros.bancos.cnpjRaiz.obrigatorio']}")
    @Pattern(regexp = "[0-9]{2}.[0-9]{3}.[0-9]{3}", message = "#{arr_msg['parametros.bancos.cnpjRaiz.incorreto']}")
    @Size(max = 10, message = "#{arr_msg['parametros.bancos.cnpjRaiz.maximo']}")
    @Convert(converter = CnpjRaizConverter.class)
    @Column(name = "CNPJ_RAIZ", nullable = false)
    private String cnpjRaiz;

    public Bancos() {
        // Construtor para inicialização por reflexão.
    }

    public Bancos(Integer idBanco, String nomeBanco, String cnpjRaiz, SituacaoEnum situacao) {
        this.idBanco = idBanco;
        this.nomeBanco = nomeBanco;
        this.cnpjRaiz = cnpjRaiz;
        this.situacao = situacao;
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

    public String getCnpjRaiz() {
        return cnpjRaiz;
    }

    public void setCnpjRaiz(String cnpjRaiz) {
        this.cnpjRaiz = cnpjRaiz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Bancos bancos = (Bancos) obj;
        return Objects.equals(idBanco, bancos.idBanco) && Objects.equals(nomeBanco, bancos.nomeBanco)
                && situacao == bancos.situacao && Objects.equals(cnpjRaiz, bancos.cnpjRaiz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBanco, nomeBanco, situacao, cnpjRaiz);
    }

    @Override
    public String toString() {
        return "Bancos{" + "idBanco=" + idBanco + ", nomeBanco='" + nomeBanco + '\'' + ", situacao=" + situacao
                + ", cnpjRaiz='" + cnpjRaiz + '\'' + ", arquivoRecepcaoCollection=" + '}';
    }
}
