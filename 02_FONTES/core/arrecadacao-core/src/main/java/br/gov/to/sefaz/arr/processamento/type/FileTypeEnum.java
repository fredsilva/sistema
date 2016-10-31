package br.gov.to.sefaz.arr.processamento.type;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Define o tipo de arquivo a ser processado. Conforme as regras de negócio do sistema.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 23/06/2016 15:14:00
 */
public enum FileTypeEnum {

    ARRECADACAO(1, "A"),
    SIMPLES_NACIONAL(2, "1");

    private final int id;
    private final String name;

    FileTypeEnum(int id, String name) {
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
     * Verifica através do HEADER qual é o tipo do arquivo a ser processado.
     *
     * @param header do arquivo a ser processado
     * @return o tipo do arquivo definido através do HEADER do arquivo
     */
    public static FileTypeEnum valueBy(String header) {
        return Arrays.stream(FileTypeEnum.values())
                .filter(fileTypeEnum -> fileTypeEnum.isThisEnum(header))
                .findFirst()
                .orElse(null);
    }

    /**
     * Retorna o valor do {@link br.gov.to.sefaz.arr.processamento.type.FileTypeEnum} através do id fornecido.
     *
     * @param id será utilizado para retorar o valor do ENUM com a mesma identificação
     * @return um valor do ENUM.
     */
    public static FileTypeEnum getValue(int id) {
        return Stream.of(values()).filter(e -> e.id == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no TipoArquivoEnum: codigo=" + id));
    }

    private boolean isThisEnum(String header) {
        return header.startsWith(name);
    }
}
