package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;
import br.gov.to.sefaz.util.formatter.FormatterUtil;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Enum que representa os valores possíveis para os Tipos de Pessoa para Arrecadação.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 17:54:00
 */
public enum TipoPessoaEnum implements EnumCodeLabel<Integer> {

    INSCRICAO(1, "INSCRIÇÃO ESTADUAL", FormatterUtil::formatInscricaoEstadual),
    CNPJ(2, "CNPJ", id -> FormatterUtil.formatCnpj(id.toString())),
    CPF(3, "CPF", id -> FormatterUtil.formatCpf(id.toString())),
    RENAVAM(4, "RENAVAM", Object::toString);

    private final Integer code;
    private final String label;
    private final Function<Long, String> formatter;

    TipoPessoaEnum(Integer code, String label, Function<Long, String> formatter) {
        this.code = code;
        this.label = label;
        this.formatter = formatter;
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
     * formata o id de um contribuinte de acordo com o tipo de pessoa.
     *
     * @param idPessoa id do contribuinte
     * @return idPessoa formatado
     */
    public String formatIdPessoa(Long idPessoa) {
        return formatter.apply(idPessoa);
    }

    /**
     * Converte inteiro em {@link TipoPessoaEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link TipoPessoaEnum}
     */
    public static TipoPessoaEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoPessoaEnum: codigo="
                        + code));
    }
}
