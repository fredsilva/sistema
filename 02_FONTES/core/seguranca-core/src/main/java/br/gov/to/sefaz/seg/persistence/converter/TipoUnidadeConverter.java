package br.gov.to.sefaz.seg.persistence.converter;

import br.gov.to.sefaz.seg.business.gestao.service.UnidadeOrganizacionalService;
import br.gov.to.sefaz.seg.persistence.domain.TipoUnidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.seg.persistence.domain.TipoUnidade} com seu
 * respectivo caractere no banco de dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 06/06/2016 18:11:52
 */
@Component
public class TipoUnidadeConverter implements AttributeConverter<TipoUnidade, Character> {

    private static UnidadeOrganizacionalService service;

    @Autowired
    public void setService(UnidadeOrganizacionalService service) {
        this.service = service;
    }

    @Override
    public Character convertToDatabaseColumn(TipoUnidade tipoUnidade) {
        return tipoUnidade.getCode();
    }

    @Override
    public TipoUnidade convertToEntityAttribute(Character code) {
        if (Objects.isNull(code)) {
            return null;
        }
        List<TipoUnidade> list = service.findTiposUnidades();
        return list.stream().filter(tipoUsuario -> code.compareTo(tipoUsuario.getCode()) == 0).findAny().get();
    }
}
