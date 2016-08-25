package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.processamento.type.FileTypeEnum;

import java.util.Objects;
import javax.persistence.AttributeConverter;

/**
 * * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.processamento.type.FileTypeEnum} com seu
 * respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 11:53:00
 */
@SuppressWarnings("PMD")
public class FileTypeEnumConverter implements AttributeConverter<FileTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(FileTypeEnum fileTypeEnum) {
        return Objects.isNull(fileTypeEnum) ? null : fileTypeEnum.getId();
    }

    @Override
    public FileTypeEnum convertToEntityAttribute(Integer id) {
        return id != null ? FileTypeEnum.getValue(id) : null;
    }
}
