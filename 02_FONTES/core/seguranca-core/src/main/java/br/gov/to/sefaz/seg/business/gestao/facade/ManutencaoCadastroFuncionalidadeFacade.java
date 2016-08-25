package br.gov.to.sefaz.seg.business.gestao.facade;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.seg.business.gestao.service.filter.OpcaoAplicacaoFilter;
import br.gov.to.sefaz.seg.persistence.entity.AplicacaoModulo;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;
import br.gov.to.sefaz.seg.persistence.entity.OpcaoAplicacao;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de Bancos.
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 18/07/2016 11:33:00
 */
public interface ManutencaoCadastroFuncionalidadeFacade extends CrudFacade<OpcaoAplicacao, Long> {

    /**
     * Busca a OpcaoAplicacao.
     * @param filter filtro passado em tela.
     * @return Lista de OpcaoAplicacao.
     */
    List<OpcaoAplicacao> find(OpcaoAplicacaoFilter filter);

    /**
     * Busca todos os {@link ModuloSistema}.
     * @return Lista de ModuloSistema.
     */
    Collection<ModuloSistema> findAllModuloSistema();

    /**
     * Busca todas as {@link AplicacaoModulo}.
     * @return lista de AplicacaoModulo.
     */
    Collection<AplicacaoModulo> findAllAplicacaoModulo();

    /**
     * Busca todos as aplicações através do @{link ModuloSistema#identificacaoModuloSistema}.
     *
     * @return lista de aplicações encontradas
     */
    Collection<AplicacaoModulo> findAplicacoesPorModulo(Long identificacaoModuloSistema);
}

