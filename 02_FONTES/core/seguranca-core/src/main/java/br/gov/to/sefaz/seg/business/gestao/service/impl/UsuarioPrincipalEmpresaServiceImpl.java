package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPrincipalEmpresaService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPrincipalEmpresa;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPrincipalEmpresaRepository;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioSistemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serviço de contrato para busca de {@link UsuarioPrincipalEmpresa}.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 26/08/2016 16:10:00
 */
@Service
public class UsuarioPrincipalEmpresaServiceImpl extends DefaultCrudService<UsuarioPrincipalEmpresa, Long>
        implements UsuarioPrincipalEmpresaService {

    private final UsuarioSistemaRepository usuarioSistemaRepository;

    @Autowired
    public UsuarioPrincipalEmpresaServiceImpl(UsuarioPrincipalEmpresaRepository repository,
            UsuarioSistemaRepository usuarioSistemaRepository) {
        super(repository);
        this.usuarioSistemaRepository = usuarioSistemaRepository;
    }

    @Override
    protected UsuarioPrincipalEmpresaRepository getRepository() {
        return (UsuarioPrincipalEmpresaRepository) super.getRepository();
    }

    @Override
    public UsuarioPrincipalEmpresa save(@ValidationSuite(context = ValidationContext.SAVE)
            UsuarioPrincipalEmpresa entity) {
        UsuarioPrincipalEmpresa save = super.save(entity);

        save.setNomeEmpresa(getRepository().findCompanyName(save.getCnpjEmpresa().substring(0, 8)));
        UsuarioSistema usuarioSistema = usuarioSistemaRepository.findOne(save.getCpfUsuario());
        save.setNomeUsuario(usuarioSistema.getNomeCompletoUsuario());

        return save;
    }
}
