package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.gestao.service.SmsContribuinteService;
import br.gov.to.sefaz.seg.persistence.entity.SmsContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;
import br.gov.to.sefaz.seg.persistence.repository.SmsContribuinteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementação do serviço de SMSs.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 23/08/2016 14:55:00
 */
@Service
public class SmsContribuinteServiceImpl extends DefaultCrudService<SmsContribuinte, Long> implements
        SmsContribuinteService {
    /**
     * Constante que define o número de alertas SMSs a serem mostrados no menu principal.
     */
    private static final int NUMBER_OF_LAST_SENT_SMSS = 5;

    @Autowired
    public SmsContribuinteServiceImpl(SmsContribuinteRepository repository) {
        super(repository);
    }

    @Override
    public List<SmsContribuinte> findLastSentSMSsForUser(UsuarioSistema usuarioSistema) {
        return getRepository().find(sb -> sb.where()
                .equal("destinatario.cpfUsuario", usuarioSistema.getCpfUsuario())
                .orderBy("dataEnvio", Order.DESC), getNumberOfSMSsToShow());
    }

    private int getNumberOfSMSsToShow() {
        return NUMBER_OF_LAST_SENT_SMSS;
    }
}

