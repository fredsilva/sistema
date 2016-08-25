package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPerfilService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPerfil;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoUsuarioEnum;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Serviço de contrato para busca de UsuarioPerfil.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 04/07/2016 15:11:00
 */
@Service
public class UsuarioPerfilServiceImpl extends DefaultCrudService<UsuarioPerfil, Long>
        implements UsuarioPerfilService {

    @Autowired
    public UsuarioPerfilServiceImpl(UsuarioPerfilRepository repository) {
        super(repository);
    }

    @Override
    protected UsuarioPerfilRepository getRepository() {
        return (UsuarioPerfilRepository) super.getRepository();
    }

    @Override
    public void buildProfileString(Collection<UsuarioSistema> allUsuarioSistema) {
        List<String> ids = allUsuarioSistema.stream().map(UsuarioSistema::getCpfUsuario).collect(Collectors.toList());

        if (!ids.isEmpty()) {
            Set<UsuarioPerfil> allByUsuarioSistema = getRepository().findAllByUsuariosSistema(ids);

            for (UsuarioSistema us : allUsuarioSistema) {
                us.setUsuarioPerfis(allByUsuarioSistema.stream()
                        .filter(up -> up.getCpfUsuario().equals(us.getCpfUsuario()))
                        .map(up -> up.getPerfisSistema().getNomePerfil())
                        .collect(Collectors.joining(", ")));
            }
        }
    }

    @Override
    public Collection<UsuarioPerfil> update(@ValidationSuite(context = ValidationContext.UPDATE)
            Collection<UsuarioPerfil> list) {
        return super.update(list);
    }

    @Override
    public Collection<UsuarioPerfil> getAllUsuarioPerfilByCpf(String cpfUsuario) {
        return getRepository().findAllByUsuarioSistema(cpfUsuario);
    }

    @Override
    public void updateUsuarioPerfil(@ValidationSuite(context = ValidationContext.UPDATE)
            Collection<UsuarioPerfil> usuarioPerfilList) {
        update(usuarioPerfilList);
    }

    @Override
    public void deleteAllWithPerfilId(Long id) {
        getRepository().delete("up", delete -> delete.where().equal("up.identificacaoPerfil", id));
    }

    @Override
    public Collection<UsuarioPerfil> findAllUsuariosPerfilByPerfilId(Long identificacaoPerfil) {
        return getRepository().findAllUsuariosPerfilByPerfilId(identificacaoPerfil);
    }

    @Override
    public Set<UsuarioPerfil> findAllPerfilById(String cpfUsuario) {
        return getRepository().findAllPerfilByUsuario(cpfUsuario);
    }

    @Override
    public void deleteUsuarioPerfilByUsuario(String cpfUsuario) {
        getRepository().delete("up", delete -> delete.where().equal("up.cpfUsuario",cpfUsuario));
    }

    @Override
    @Transactional
    public void updateAtribuirUsuarioPerfil(Set<UsuarioPerfil> usuarioPerfil, String cpfUsuario) {
        deleteUsuarioPerfilByUsuario(cpfUsuario);
        usuarioPerfil.forEach(up -> up.setSituacaoPerfil(SituacaoUsuarioEnum.ATIVO));
        save(usuarioPerfil);
    }
}
