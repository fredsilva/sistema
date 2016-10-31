package br.gov.to.sefaz.arr.processamento.domain.header;

import java.util.stream.Stream;

/**
 * ENUM para os tipos de arquivos do HEADER do processamento de arquivos.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 27/06/2016 11:09:00
 */
public enum TipoArquivoEnum {

    PARCIAL(1, "01"),
    CONSOLIDADO(2, "04");

    private final int id;
    private final String name;

    TipoArquivoEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Retorna o valor do {@link br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum} através do name
     * fornecido.
     *
     * @param id será utilizado para retorar o valor do ENUM com a mesma identificação
     * @return um valor do ENUM.
     */
    public static TipoArquivoEnum getById(String id) {
        return Stream.of(values()).filter(e -> e.name.equals(id)).findFirst().orElse(null);
    }

    /**
     * Retorna o valor do {@link br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum} através do id
     * fornecido.
     *
     * @param id será utilizado para retorar o valor do ENUM com a mesma identificação
     * @return um valor do ENUM.
     */
    public static TipoArquivoEnum getValue(int id) {
        return Stream.of(values()).filter(e -> e.id == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoArquivoEnum: codigo=" + id));
    }

}
