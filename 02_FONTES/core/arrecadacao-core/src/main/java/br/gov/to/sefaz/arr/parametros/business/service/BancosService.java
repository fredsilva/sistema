package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;

/**
 * Contrato de acesso do serviço de {@link Bancos}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface BancosService extends CrudService<Bancos, Integer> {

    /**
     * Busca todos os {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos} com
     * {@link br.gov.to.sefaz.arr.parametros.persistence.entity.Bancos#situacao}
     * {@link br.gov.to.sefaz.persistence.enums.SituacaoEnum#ATIVO}.
     *
     * @return lista de bancos ativos
     */
    Collection<Bancos> findAllActiveBancos();
}
