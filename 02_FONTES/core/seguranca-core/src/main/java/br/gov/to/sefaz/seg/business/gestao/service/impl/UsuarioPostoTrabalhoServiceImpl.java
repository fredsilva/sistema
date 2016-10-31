package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPostoTrabalhoService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalhoPK;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPostoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementação do Serviço de {@link UsuarioPostoTrabalho}.
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/07/2016 10:28:00
 */
@Service
public class UsuarioPostoTrabalhoServiceImpl extends
        DefaultCrudService<UsuarioPostoTrabalho, UsuarioPostoTrabalhoPK> implements UsuarioPostoTrabalhoService {

    @Autowired
    public UsuarioPostoTrabalhoServiceImpl(UsuarioPostoTrabalhoRepository usuarioPostoTrabalhoRepository) {
        super(usuarioPostoTrabalhoRepository);
    }

    @Override
    protected UsuarioPostoTrabalhoRepository getRepository() {
        return (UsuarioPostoTrabalhoRepository) super.getRepository();
    }

    @Override
    public Collection<UsuarioPostoTrabalho> findAll() {
        return getRepository().find("upt", select -> select
                .innerJoinFetch("upt.postoTrabalho")
                .innerJoinFetch("upt.usuarioSistema")
                .orderBy("upt.nomeCompletoUsuario", Order.ASC));
    }

    @Override
    public UsuarioPostoTrabalho saveOrUpdate(String cpf, Integer identificacaoPostoTrabalho) {

        // Um UsuarioSistema só poderá ter um UsuarioPostoTrabalho, pois a cardinalidade desta referência é 1 para 1.
        UsuarioPostoTrabalho usuarioPostoTrabalho = getRepository().findByCpf(cpf);

        if (Objects.isNull(usuarioPostoTrabalho)) {
            usuarioPostoTrabalho = new UsuarioPostoTrabalho();
            usuarioPostoTrabalho.setCpfUsuario(cpf);
            usuarioPostoTrabalho.setIdentificacaoPostoTrabalho(identificacaoPostoTrabalho);
            return save(usuarioPostoTrabalho);
        } else {
            usuarioPostoTrabalho.setIdentificacaoPostoTrabalho(identificacaoPostoTrabalho);
            return update(usuarioPostoTrabalho);
        }

    }

    @Override
    public Optional<UsuarioPostoTrabalho> removeUsuarioPostoTrabalho(String cpf, Integer identificacaoPostoTrabalho) {
        return delete(new UsuarioPostoTrabalhoPK(cpf, identificacaoPostoTrabalho));
    }


}
