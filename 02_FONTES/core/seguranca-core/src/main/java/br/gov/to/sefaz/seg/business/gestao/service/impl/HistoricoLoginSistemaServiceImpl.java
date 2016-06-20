package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.seg.business.gestao.service.HistoricoLoginSistemaService;
import br.gov.to.sefaz.seg.persistence.entity.HistoricoLoginSistema;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.repository.HistoricoLoginSistemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementação do serviço da entidade HistoricoLoginSistema.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 14/05/2016 13:33:24
 */
@Service
public class HistoricoLoginSistemaServiceImpl extends DefaultCrudService<HistoricoLoginSistema, Long>
        implements HistoricoLoginSistemaService {

    @Autowired
    public HistoricoLoginSistemaServiceImpl(
            HistoricoLoginSistemaRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "nomeCompletoUsuario")));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public HistoricoLoginSistema saveHistoricoLoginSistema(UsuarioSistema usuarioSistema) {
        HistoricoLoginSistema historicoLogin = new HistoricoLoginSistema();
        historicoLogin.setDataHoraLogin(LocalDateTime.now());
        historicoLogin.setUsuarioSistema(usuarioSistema);
        return save(historicoLogin);
    }

}