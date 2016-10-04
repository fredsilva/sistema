package br.gov.to.sefaz.arr.persistence.entity;

import br.gov.to.sefaz.arr.persistence.converter.TipoPedidoCampoEnumConverter;
import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoAcoesEnum;
import br.gov.to.sefaz.arr.persistence.enums.TipoPedidoCampoEnum;
import br.gov.to.sefaz.persistence.converter.OneOrTwoBooleanConverter;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_CAMPOS_ACOES.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 13:22:30
 */
@Entity
@Table(name = "TA_PEDIDO_CAMPOS_ACOES", schema = "SEFAZ_ARR")
public class PedidoCamposAcoes extends AbstractEntity<Integer> {

    private static final long serialVersionUID = -764912572211501937L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO_CAMPOS_ACOES")
    @SequenceGenerator(name = "SQ_PEDIDO_CAMPOS_ACOES", schema = "SEFAZ_ARR", sequenceName = "SQ_PEDIDO_CAMPOS_ACOES",
            allocationSize = 1)
    @Column(name = "ID_CAMPO_PEDIDO")
    private Integer idCampoPedido;

    @NotNull(message = "#{arr_msg['parametros.pedidoCamposAcoes.tipoCampo.obrigatorio']}")
    @Convert(converter = TipoPedidoCampoEnumConverter.class)
    @Column(name = "TIPO_CAMPO")
    private TipoPedidoCampoEnum tipoCampo;

    @NotNull(message = "#{arr_msg['parametros.pedidoCamposAcoes.campoObrigatorio.obrigatorio']}")
    @Convert(converter = OneOrTwoBooleanConverter.class)
    @Column(name = "CAMPO_OBRIGATORIO")
    private Boolean campoObrigatorio;

    @NotNull(message = "#{arr_msg['parametros.pedidoCamposAcoes.idAcoes.obrigatorio']}")
    @Column(name = "ID_ACOES")
    private Integer idAcoes;

    @NotNull(message = "#{arr_msg['parametros.pedidoReceita.idTipoPedido.obrigatorio']}")
    @JoinColumn(name = "ID_ACOES", referencedColumnName = "ID_ACOES", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PedidoTipoAcoes pedidoTipoAcoes;

    @Override
    public Integer getId() {
        return getIdCampoPedido();
    }

    public Integer getIdCampoPedido() {
        return idCampoPedido;
    }

    public void setIdCampoPedido(Integer idCampoPedido) {
        this.idCampoPedido = idCampoPedido;
    }

    public TipoPedidoCampoEnum getTipoCampo() {
        return tipoCampo;
    }

    public void setTipoCampo(TipoPedidoCampoEnum tipoCampo) {
        this.tipoCampo = tipoCampo;
    }

    public Boolean getCampoObrigatorio() {
        return campoObrigatorio;
    }

    public void setCampoObrigatorio(Boolean campoObrigatorio) {
        this.campoObrigatorio = campoObrigatorio;
    }

    public PedidoTipoAcoes getPedidoTipoAcoes() {
        return pedidoTipoAcoes;
    }

    public void setPedidoTipoAcoes(PedidoTipoAcoes pedidoTipoAcoes) {
        this.pedidoTipoAcoes = pedidoTipoAcoes;
    }

    public TipoPedidoAcoesEnum getTipoAcao() {
        return pedidoTipoAcoes.getTipoAcao();
    }

    public SituacaoEnum getSituacao() {
        return pedidoTipoAcoes.getSituacao();
    }

    public Integer getIdAcoes() {
        return idAcoes;
    }

    public void setIdAcoes(Integer idAcoes) {
        this.idAcoes = idAcoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoCamposAcoes that = (PedidoCamposAcoes) o;
        return Objects.equals(idCampoPedido, that.idCampoPedido)
                && tipoCampo == that.tipoCampo
                && Objects.equals(campoObrigatorio, that.campoObrigatorio)
                && Objects.equals(idAcoes, that.idAcoes)
                && Objects.equals(pedidoTipoAcoes, that.pedidoTipoAcoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampoPedido, tipoCampo, campoObrigatorio, idAcoes, pedidoTipoAcoes);
    }

    @Override
    public String toString() {
        return "PedidoCamposAcoes{"
                + "idCampoPedido=" + idCampoPedido
                + ", tipoCampo=" + tipoCampo
                + ", campoObrigatorio=" + campoObrigatorio
                + ", idAcoes=" + idAcoes
                + ", pedidoTipoAcoes=" + pedidoTipoAcoes
                + '}';
    }
}
