package br.gov.to.sefaz.arr.persistence.enums;

import br.gov.to.sefaz.persistence.enums.EnumCodeLabel;

import java.util.stream.Stream;

/**
 * Enum de Tipos de Receita de Arquivos STR por código e descrição.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 10:58:00
 */
public enum TipoReceitaStrEnum implements EnumCodeLabel<Integer> {

    ICMS(1, "ICMS"),
    IPVA(2, "IPVA"),
    SIMPLES_NACIONAL(3, "SIMPLES NACIONAL"),
    RECEITAS_NAO_TRIBUTARIAS(4, "RECEITAS NÃO TRIBUTÁRIAS"),
    GNRE(6, "GNRE"),
    DEMAIS_RECEITAS_TRIBUTARIAS(7, "DEMAIS RECEITAS TRIBUTÁRIAS");

    private Integer code;
    private String label;

    TipoReceitaStrEnum(Integer code, String label) {
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
     * Encontra a {@link TipoReceitaStrEnum} correspondente ao código passado como parâmetro.
     *
     * @param code código da situação do arquivo.
     * @return retorna {@link TipoReceitaStrEnum} correspondente ao código passado como parâmetro.
     */
    public static TipoReceitaStrEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoReceitaStrEnum: codigo="
                        + code));
    }

}
