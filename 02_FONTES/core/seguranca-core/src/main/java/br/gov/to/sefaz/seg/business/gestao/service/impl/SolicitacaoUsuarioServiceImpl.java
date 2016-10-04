package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.seg.business.gestao.service.SolicitacaoUsuarioService;
import br.gov.to.sefaz.seg.persistence.entity.SolicitacaoUsuario;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.enums.SituacaoSolicitacaoEnum;
import br.gov.to.sefaz.seg.persistence.repository.SolicitacaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço da entidade Solicitação Usuário.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 09/06/2016 09:22:00
 */
@Service
public class SolicitacaoUsuarioServiceImpl extends DefaultCrudService<SolicitacaoUsuario, Long> implements
        SolicitacaoUsuarioService {

    @Autowired
    public SolicitacaoUsuarioServiceImpl(SolicitacaoUsuarioRepository repository) {
        super(repository);
    }

    @Override
    public void save(UsuarioSistema usuarioSistema) {
        SolicitacaoUsuario solicitacaoUsuario = usuarioSistema.getSolicitacaoUsuario();
        usuarioSistema.setSolicitacaoUsuario(null);
        solicitacaoUsuario.setSituacaoSolicitacao(SituacaoSolicitacaoEnum.PENDENTE);
        solicitacaoUsuario.setCpfUsuario(usuarioSistema.getCpfUsuario());
        validateSolicitacaoUsuario(solicitacaoUsuario);
        solicitacaoUsuario = super.save(solicitacaoUsuario);
        usuarioSistema.setSolicitacaoUsuario(solicitacaoUsuario);
    }

    @SuppressWarnings("PMD.UnusedFormalParameter")
    private void validateSolicitacaoUsuario(@ValidationSuite(context = ValidationContext.SAVE) SolicitacaoUsuario
            solicitacaoUsuario) {
        // Método que valida SolicitacaoUsuario através da anotação de parâmetro.
    }
}
