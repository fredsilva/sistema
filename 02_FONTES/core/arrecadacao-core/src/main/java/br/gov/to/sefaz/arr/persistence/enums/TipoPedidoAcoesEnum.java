package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Tipo de Ações de Pedido por um número de identificação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 09:32:00
 */
public enum TipoPedidoAcoesEnum implements EnumCodeLabel<Integer> {
    //@formatter:off
    CORRIGIR_PAGAMENTO(1, "Corrigir Pagamento"),
    CREDITAR_CONTA_TRIBUTARIA(2, "Creditar em Conta Tributária"),
    CREDITAR_CONTA_CORRENTE(3, "Creditar em Conta Corrente"),
    ESTORNAR_PAGAMENTO(4, "Estornar Pagamento"),
    PEDIDO_INDEFERIDO(5, "Pedido Indeferido");
    //@formatter:on

    private final Integer code;
    private final String label;

    TipoPedidoAcoesEnum(
            Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Converte inteiro em {@link TipoPedidoAcoesEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoPedidoAcoesEnum}
     */
    public static TipoPedidoAcoesEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoPedidoAcoesEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
