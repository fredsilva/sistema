package br.gov.to.sefaz.arr.persistence.converter;


import br.gov.to.sefaz.arr.processamento.domain.str.TipoValorInformativoEnum;

import javax.persistence.AttributeConverter;

/**
 * * Conversor para realizar o mapeamento do
 * {@link br.gov.to.sefaz.arr.processamento.domain.str.TipoValorInformativoEnum} com seu respectivo número no
 * banco de dados.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 19/05/2016 11:53:00
 */
public class TipoValorInformativoEnumConverter implements AttributeConverter<TipoValorInformativoEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoValorInformativoEnum tipoValorInformativoEnum) {
        return tipoValorInformativoEnum.getCode();
    }

    @Override
    public TipoValorInformativoEnum convertToEntityAttribute(Integer id) {
        return TipoValorInformativoEnum.getValue(id);
    }
}
