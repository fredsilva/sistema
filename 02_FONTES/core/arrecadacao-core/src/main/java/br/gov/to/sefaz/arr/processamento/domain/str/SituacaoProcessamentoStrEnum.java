package br.gov.to.sefaz.arr.processamento.domain.str;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum de Situações de Processamento de Arquivos STR por código e descrição.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 10:58:00
 */
public enum SituacaoProcessamentoStrEnum implements EnumCodeLabel<Integer> {

    PROCESSADO(1, "PROCESSADO"),
    NAO_PROCESSADO(2, "NÃO PROCESSADO"),
    LANCAMENTO_MANUAL(3, "LANÇAMENTO MANUAL"),
    EXCLUSAO_LANCAMENTO_MANUAL(4, "EXCLUSAO LANÇAMENTO MANUAL");

    private Integer code;
    private String label;

    SituacaoProcessamentoStrEnum(Integer code, String label) {
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
     * Encontra a {@link SituacaoProcessamentoStrEnum} correspondente ao código passado como parâmetro.
     *
     * @param code código da situação do arquivo.
     * @return retorna {@link SituacaoProcessamentoStrEnum} correspondente ao código passado como parâmetro.
     */
    public static SituacaoProcessamentoStrEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoProcessamentoStrEnum: "
                        + "codigo=" + code));
    }

}
