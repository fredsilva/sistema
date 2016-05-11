package br.gov.to.sefaz.arr.parametros.persistence.converter;

import br.gov.to.sefaz.exception.SystemException;

import java.text.DecimalFormat;
import java.text.ParseException;
import javax.persistence.AttributeConverter;
import javax.swing.text.MaskFormatter;

/**
 * Conversor para inserir ou remover a máscara de cnpj raiz.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public class CnpjRaizConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String cnpjRaiz) {
        return Integer.valueOf(cnpjRaiz.replaceAll("[\\. ]", ""));
    }

    @Override
    public String convertToEntityAttribute(Integer cnpjRaiz) {
        DecimalFormat df = new DecimalFormat("00000000");
        String retorno = df.format(cnpjRaiz);
        try {
            MaskFormatter mask = new MaskFormatter("##.###.###");
            mask.setValueContainsLiteralCharacters(false);
            retorno = mask.valueToString(retorno);
        } catch (ParseException e) {
            throw new SystemException(
                    "Erro ao fazer parse de cnpjRaiz cadastrado na base de dados. cnpjRaiz: " + cnpjRaiz, e);
        }
        return retorno;
    }
}
