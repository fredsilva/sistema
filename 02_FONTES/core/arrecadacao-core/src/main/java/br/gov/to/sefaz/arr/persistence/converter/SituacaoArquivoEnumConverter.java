package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.persistence.enums.SituacaoArquivoEnum;

import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento da {@link br.gov.to.sefaz.arr.persistence.enums.SituacaoArquivoEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 15:54:00
 */
public class SituacaoArquivoEnumConverter implements AttributeConverter<SituacaoArquivoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SituacaoArquivoEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public SituacaoArquivoEnum convertToEntityAttribute(Integer code) {
        return SituacaoArquivoEnum.getValue(code);
    }
}
