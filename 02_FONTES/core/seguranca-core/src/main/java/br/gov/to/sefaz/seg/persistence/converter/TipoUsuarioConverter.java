package br.gov.to.sefaz.seg.persistence.converter;

import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.persistence.domain.TipoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeConverter;

/**
 * Conversor para realizar o mapeamento do {@link br.gov.to.sefaz.seg.persistence.domain.TipoUsuario} com seu
 * respectivo caractere no banco de dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 06/06/2016 18:11:52
 */
@Component
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, Integer> {

    private static TipoUsuarioService service;

    @Autowired
    public void setService(TipoUsuarioService service) {
        this.service = service;
    }

    @Override
    public Integer convertToDatabaseColumn(TipoUsuario tipoUsuario) {
        return tipoUsuario.getCode();
    }

    @Override
    public TipoUsuario convertToEntityAttribute(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        List<TipoUsuario> list = service.findAll();
        return list.stream().filter(tipoUsuario -> code.compareTo(tipoUsuario.getCode()) == 0).findAny().get();
    }
}
