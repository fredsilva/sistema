package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.gestao.service.CorreioContribuinteService;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.repository.CorreioContribuinteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço de Correio Eletrônico.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 02/06/2016 11:53:00
 */
@Service
public class CorreioContribuinteServiceImpl extends DefaultCrudService<CorreioContribuinte, Long>
        implements CorreioContribuinteService {

    /**
     * Constante que define o número de caracteres a serem exibidos na pré-visualizção do conteúdo de um e-mail no
     * menu principal.
     */
    private static final int MESSAGE_PREVIEW_LENGTH = 50;

    /**
     * Constante que define o número de e-mails a serem mostrados no menu principal.
     */
    private static final int NUMBER_OF_LAST_SENT_EMAILS = 5;

    @Autowired
    public CorreioContribuinteServiceImpl(CorreioContribuinteRepository repository) {
        super(repository);
    }

    @Override
    public List<CorreioContribuinte> findLastSentEmailsForUser(UsuarioSistema usuarioSistema) {
        return getRepository().find(sb -> sb.where()
                .equal("destinatario.cpfUsuario", usuarioSistema.getCpfUsuario())
                .orderBy("dataEnvio", Order.DESC), getNumberOfEmailsToShow());
    }

    @Override
    public int getMessagePreviewLength() {
        return MESSAGE_PREVIEW_LENGTH;
    }

    private int getNumberOfEmailsToShow() {
        return NUMBER_OF_LAST_SENT_EMAILS;
    }
}
