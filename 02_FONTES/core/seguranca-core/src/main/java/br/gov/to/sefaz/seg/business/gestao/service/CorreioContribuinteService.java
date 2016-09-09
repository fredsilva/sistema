package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.CorreioContribuinte;
import br.gov.to.sefaz.seg.persistence.entity.UsuarioSistema;

import java.util.List;

/**
 * Contrato de acesso do serviço de Correio Eletrônico.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 02/06/2016 11:44:00
 */
public interface CorreioContribuinteService extends CrudService<CorreioContribuinte, Long> {
    /**
     * Busca os últimos emails enviados para o usuário informado. O número de emails retornados está definido em
     * regra de negócio.
     *
     * @param usuarioSistema {@link UsuarioSistema} o qual se deseja carregar os últimos emails
     * @return Lista contendo objetos do tipo {@link CorreioContribuinte}
     */
    List<CorreioContribuinte> findLastSentEmailsForUser(UsuarioSistema usuarioSistema);


    /**
     * Retorna o tamanho máximo de caracteres a serem mostrados na pré-visualização do e-mail enviado para o
     * contribuinte.
     *
     * @return Tamanho máximo de caracteres.
     */
    int getMessagePreviewLength();
}
