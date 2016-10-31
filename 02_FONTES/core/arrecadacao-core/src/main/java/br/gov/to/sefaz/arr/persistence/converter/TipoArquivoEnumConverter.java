package br.gov.to.sefaz.arr.persistence.converter;

import br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum;

import java.util.Objects;
import javax.persistence.AttributeConverter;

/**
 * * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.arr.processamento.domain.header.TipoArquivoEnum}
 * com seu respectivo número no banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 11:53:00
 */
@SuppressWarnings("PMD")
public class TipoArquivoEnumConverter implements AttributeConverter<TipoArquivoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoArquivoEnum tipoArquivoEnum) {
        return Objects.isNull(tipoArquivoEnum) ? null : tipoArquivoEnum.getId();
    }

    @Override
    public TipoArquivoEnum convertToEntityAttribute(Integer id) {
        return TipoArquivoEnum.getValue(id);
    }
}
