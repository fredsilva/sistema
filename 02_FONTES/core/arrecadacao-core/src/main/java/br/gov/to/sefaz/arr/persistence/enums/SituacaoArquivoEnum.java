package br.gov.to.sefaz.arr.persistence.enums;

import java.util.stream.Stream;

/**
 * Enum que representa os valores possíveis para a Situação do Arquivo.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 15:52:00
 */
public enum SituacaoArquivoEnum {

    PROCESSADO(1), NAO_PROCESSADO(2), PROCESSADO_COM_ERROS(3);

    private final Integer code;

    SituacaoArquivoEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    /**
     * Converte inteiro em {@link SituacaoArquivoEnum} baseado no seu code. Se for passado inteiro que não existe no
     * enum, é disparada {@link IllegalArgumentException}
     *
     * @param code valor do enum
     * @return {@link SituacaoArquivoEnum}
     */
    public static SituacaoArquivoEnum getValue(Integer code) {
        return Stream.of(values()).filter(e -> e.code.equals(code)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código não existe no SituacaoArquivoEnum: codigo="
                        + code));
    }

}
