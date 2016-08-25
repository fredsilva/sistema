package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.persistence.converter.SituacaoEnumConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_TIPO_DOCS.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 24/05/2016 14:46:39
 */
@Entity
@Table(name = "TA_PEDIDO_TIPO_DOCS", schema = "SEFAZ_ARR")
public class PedidoTipoDocs extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 9218412255990004559L;

    @Id
    @NotNull
    @Column(name = "ID_TIPO_DOCS")
    private Integer idTipoDocs;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @Convert(converter = SituacaoEnumConverter.class)
    @Column(name = "SITUACAO")
    private SituacaoEnum situacao;

    @Override
    public Integer getId() {
        return getIdTipoDocs();
    }

    public Integer getIdTipoDocs() {
        return idTipoDocs;
    }

    public void setIdTipoDocs(Integer idTipoDocs) {
        this.idTipoDocs = idTipoDocs;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SituacaoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoEnum situacao) {
        this.situacao = situacao;
    }

    public String getCompositeName() {
        return getIdTipoDocs() + " - " + getDescricao();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoTipoDocs that = (PedidoTipoDocs) o;
        return Objects.equals(idTipoDocs, that.idTipoDocs)
                && Objects.equals(descricao, that.descricao)
                && Objects.equals(situacao, that.situacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTipoDocs, descricao, situacao);
    }

    @Override
    public String toString() {
        return "PedidoTipoDocs{"
                + "idTipoDocs=" + idTipoDocs
                + ", descricao='" + descricao + '\''
                + ", situacao=" + situacao
                + '}';
    }
}
