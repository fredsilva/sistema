package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Tipo de campo de Pedido por um número de identificação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 25/05/2016 09:46:00
 */
public enum TipoPedidoCampoEnum implements EnumCodeLabel<Integer> {

    //@formatter:off
    NRO_DOCUMENTO(1, "Nro. Documento"),
    PERIODO_REFERENCIA(2, "Periodo de Referência"),
    NRO_PARCELA(3, "Nro. Parcela"),
    INSCRICAO_ESTADUAL(4, "Inscrição Estadual"),
    CPF_CNPJ(5, "CPF/CNPJ"),
    RENAVAM(6, "Renavam"),
    VALOR_TOTAL(7, "Valor Total"),
    NUMERO_AUTENTICACAO(8, "Número Autenticação"),
    VALOR_AUTENTICADO(9, "Valor Autenticado"),
    NSU(10, "NSU"),
    NUMERO_PEDIDO_INDEFERIDO(11, "Número Pedido Indeferido");
    //@formatter:off

    private final Integer code;
    private final String label;

    TipoPedidoCampoEnum(
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
     * Converte inteiro em {@link TipoPedidoCampoEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoPedidoCampoEnum}
     */
    public static TipoPedidoCampoEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoPedidoCampoEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }

}
