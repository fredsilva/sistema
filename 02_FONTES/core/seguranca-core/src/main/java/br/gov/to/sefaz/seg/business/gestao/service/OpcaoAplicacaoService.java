package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.business.gestao.service.filter.OpcaoAplicacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao}.
 *
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 19/07/2016 14:59:00
 */
public interface OpcaoAplicacaoService extends CrudService<OpcaoAplicacao, Long> {

    /**
     * Busca todos os OpcaoAplicacao.
     * @param filter passado em tela.
     * @return Lista de {@link OpcaoAplicacao}.
     */
    List<OpcaoAplicacao> findByFilter(OpcaoAplicacaoFilter filter);

    /**
     * Busca lista com todas as opções para ser mostrado em tela.
     * @return lista de {@link OpcaoAplicacao}
     */
    List<OpcaoAplicacao> findAllOpcoes();

    /**
     * Busca lista com todas as opções dos perfis do usuario logado.
     * @return lista de {@link OpcaoAplicacao}
     */
    List<OpcaoAplicacao> findAllFromPerfilUsuario();
}
