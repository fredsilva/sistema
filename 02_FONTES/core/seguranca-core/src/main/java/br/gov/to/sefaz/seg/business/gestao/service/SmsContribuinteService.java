package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.SmsContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.List;

/**
 * Contrato de acesso do serviço de SMS.
 *
 * @author <a href="mailto:volceri.davila@ntconsult.com.br">volceri.davila</a>
 * @since 23/08/2016 14:52:13
 */
public interface SmsContribuinteService extends CrudService<SmsContribuinte, Long> {
    /**
     * Busca os últimos SMSs enviados para o usuário informado. O número de elementos retornados está definido em
     * regra de negócio.
     *
     * @param usuarioSistema {@link UsuarioSistema} o qual se deseja carregar os últimos SMSs
     * @return Lista contendo objetos do tipo {@link SmsContribuinte}
     */
    List<SmsContribuinte> findLastSentSMSsForUser(UsuarioSistema usuarioSistema);
}
