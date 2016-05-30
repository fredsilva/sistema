package br.gov.to.sefaz.seg.business.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;
import br.gov.to.sefaz.seg.business.service.UsuarioSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.repository.UsuarioSistemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementação do serviço da entidade UsuarioSistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:38
 */
@Service
public class UsuarioSistemaServiceImpl extends DefaultCrudService<UsuarioSistema, String>
        implements UsuarioSistemaService {

    @Autowired
    public UsuarioSistemaServiceImpl(UsuarioSistemaRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "nomeCompletoUsuario")));
    }

    @Override
    public Collection<UsuarioSistema> findAllByCpfAndName(Long cpf, String nome) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .like("cpfUsuario", cpf)
                .like("nomeCompletoUsuario", nome)
                .like("codigoTipoUsuario", 4)
                .like("situacaoUsuario", 'A')
                .build());
    }
}