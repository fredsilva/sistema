package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.gestao.service.TipoUsuarioService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.TipoUsuarioFilter;
import br.gov.to.sefaz.seg.persistence.entity.TipoUsuario;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.seg.persistence.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementação do serviço da entidade TipoUsuario.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/06/2016 09:22:00
 */
@Service
public class TipoUsuarioServiceImpl extends DefaultCrudService<TipoUsuario, Integer> implements
        TipoUsuarioService {

    @Autowired
    public TipoUsuarioServiceImpl(TipoUsuarioRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "codigoTipoUsuario")));
    }

    @Override
    protected TipoUsuarioRepository getRepository() {
        return (TipoUsuarioRepository) super.getRepository();
    }

    @Override
    public List<TipoUsuario> findAllByDescricao(TipoUsuarioFilter filter) {
        String descricaoTipoUsuario = Objects.isNull(filter) ? "%" : "%" + filter.getDescricaoTipoUsuario() + "%";
        return getRepository()
                .allProfilesCounted(SituacaoUsuarioEnum.ATIVO, descricaoTipoUsuario);
    }

    @Override
    public Optional<TipoUsuario> delete(
            @ValidationSuite(context = ValidationContext.DELETE, clazz = TipoUsuario.class) Integer id) {
        TipoUsuario tipoUsuario = findOne(id);
        validateDelete(tipoUsuario);
        return super.delete(id);
    }

    /**
     * Classe para deleção de TipoUsuario.
     * @param tipoUsuario tipo usuário para remover pelo ID.
     */
    @SuppressWarnings("PMD")
    private void validateDelete(@ValidationSuite(context = ValidationContext.DELETE) TipoUsuario
            tipoUsuario){
        //Método que serve pra validar TipoUsuario tipoUsuario.
    }

}
