package br.gov.to.sefaz.seg.business.gestao.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.seg.persistence.entity.ModuloSistema;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link ModuloSistema}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 13/06/2016 20:18:00
 */
public interface ModuloSistemaService extends CrudService<ModuloSistema, Long> {
    /**
     * Busca todos os módulos do sistema ordenados por abreviação.
     *
     * @return todos os registros do banco ordenados por abreviação
     */
    List<ModuloSistema> findAllSortedByAbreviacao();
}
