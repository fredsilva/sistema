package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgencias;
import br.gov.to.sefaz.arr.parametros.persistence.entity.DelegaciaAgenciasPK;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Serviço para gerencia de dados de {@link DelegaciaAgencias}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 24/05/2016 18:41:00
 */
public interface DelegaciaAgenciasService extends CrudService<DelegaciaAgencias, DelegaciaAgenciasPK> {

    /**
     * Busca todas as delegacias agências para utilização em tela.
     * @param idDelegacia identificação da delegacia.
     * @return lista de delegacia agencias.
     */
    Collection<DelegaciaAgencias> findAllByDelegacia(Integer idDelegacia);
}
