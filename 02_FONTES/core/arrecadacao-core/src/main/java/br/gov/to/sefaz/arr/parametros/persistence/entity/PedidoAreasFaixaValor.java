package br.gov.to.sefaz.arr.parametros.persistence.entity;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Entidade que representa os dados da tabela SEFAZ_ARR.TA_PEDIDO_AREAS_FAIXA_VALOR.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 20/05/2016 15:18:05
 */
@Entity
@Table(name = "TA_PEDIDO_AREAS_FAIXA_VALOR", schema = "SEFAZ_ARR")
public class PedidoAreasFaixaValor extends AbstractEntity<Integer> {

    private static final long serialVersionUID = 2968092728659633890L;

    @Id
    @NotNull(message = "#{arr_msg['parametros.PedidoAreasFaixaValor.idPedidoArea.obrigatorio']}")
    @Column(name = "ID_PEDIDO_AREA")
    private Integer idPedidoArea;

    @Digits(integer = 14, fraction = 2, message = "#{arr_msg['parametros.PedidoAreasFaixaValor.valorInicial.digitos']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['parametros.PedidoAreasFaixaValor.valorInicial.minimo']}")
    @Column(name = "VALOR_INICIAL")
    private BigDecimal valorInicial;

    @Digits(integer = 14, fraction = 2, message = "#{arr_msg['parametros.PedidoAreasFaixaValor.valorFinal.digitos']}")
    @DecimalMin(value = "0.01", message = "#{arr_msg['parametros.PedidoAreasFaixaValor.valorFinal.minimo']}")
    @Column(name = "VALOR_FINAL")
    private BigDecimal valorFinal;

    public PedidoAreasFaixaValor() {
        // Construtor para inicialização por reflexão.
    }

    public PedidoAreasFaixaValor(
            Integer idPedidoArea, BigDecimal valorInicial, BigDecimal valorFinal) {
        this.idPedidoArea = idPedidoArea;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
    }

    @Override
    public Integer getId() {
        return getIdPedidoArea();
    }

    public Integer getIdPedidoArea() {
        return idPedidoArea;
    }

    public void setIdPedidoArea(Integer idPedidoArea) {
        this.idPedidoArea = idPedidoArea;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PedidoAreasFaixaValor that = (PedidoAreasFaixaValor) o;
        return Objects.equals(idPedidoArea, that.idPedidoArea)
                && Objects.equals(valorInicial, that.valorInicial)
                && Objects.equals(valorFinal, that.valorFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedidoArea, valorInicial, valorFinal);
    }
}
