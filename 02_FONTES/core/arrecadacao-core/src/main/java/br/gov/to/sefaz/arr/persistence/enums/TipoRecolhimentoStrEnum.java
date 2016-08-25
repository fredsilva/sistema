package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum de Tipos de Recolhimento de Arquivos STR por código e descrição.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 10:58:00
 */
public enum TipoRecolhimentoStrEnum implements EnumCodeLabel<String> {

    RECOLHIMENTO_NORMAL("N", "RECOLHIMENTO NORMAL"),
    RECOLHIMENTO_PAPEL("P", "RECOLHIMENTO_PAPEL"),
    LANCAMENTO_MANUAL_SEFAZ("M", "LANÇAMENTO MANUAL SEFAZ");

    private String code;
    private String label;

    TipoRecolhimentoStrEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getLabel() {
        return label;
    }

    /**
     * Encontra a {@link TipoRecolhimentoStrEnum} correspondente ao código passado como parâmetro.
     *
     * @param code código da situação do arquivo.
     * @return retorna {@link TipoRecolhimentoStrEnum} correspondente ao código passado como parâmetro.
     */
    public static TipoRecolhimentoStrEnum getValue(String code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoRecolhimentoStrEnum: codigo="
                        + code));
    }

}
