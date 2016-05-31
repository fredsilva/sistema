package br.gov.to.sefaz.cat.business.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.cat.persistence.entity.AtividadeEconomica;

import java.util.Collection;

/**
 * Serviços para manipulação de {@link AtividadeEconomica} (CNAE's).
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/05/2016 11:30:00
 */
public interface AtividadeEconomicaService extends CrudService<AtividadeEconomica, String> {

    Collection<AtividadeEconomica> findAllCnaesByGrupo(Integer idGrupoCnae);
}
