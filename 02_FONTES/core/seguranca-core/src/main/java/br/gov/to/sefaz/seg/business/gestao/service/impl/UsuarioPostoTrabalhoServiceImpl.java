package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.gestao.service.UsuarioPostoTrabalhoService;
import br.gov.to.sefaz.seg.persistence.entity.PostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalho;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioPostoTrabalhoPK;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioPostoTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public Collection<UsuarioPostoTrabalho> findAll() {
        return getRepository().find(select -> select.orderBy("nomeCompletoUsuario", Order.ASC));
    }

    @Override
    public Optional<UsuarioPostoTrabalho> removeUsuarioPostoTrabalho(String cpf, PostoTrabalho postoTrabalho) {
        return delete(new UsuarioPostoTrabalhoPK(cpf, postoTrabalho.getIdentificacaoPostoTrabalho()));
    }
}
