package br.gov.to.sefaz.arr.parametros.persistence.enums;

import br.gov.to.sefaz.business.managedbean.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum para Forma de Pagamento por um número de identificação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 05/05/2016 17:30:32
 */
public enum FormaPagamentoEnum implements EnumCodeLabel<Integer> {

    CAIXA(1, "Guiche de Caixa"), ARRECADACAO(2, "Arrecadação Eletrônica"), INTERNET(3, "Internet"),
    OUTROS(4, "Outros Meios"), CORRESPONDENTE(5, "Correspondente Bancário");

    private final Integer code;
    private final String label;

    private FormaPagamentoEnum(Integer code, String label) {
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
     * Converte inteiro em {@link FormaPagamentoEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link FormaPagamentoEnum}
     */
    public static FormaPagamentoEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no FormaPagamentoEnum: codigo="
                        + code));
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
