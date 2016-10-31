package br.gov.to.sefaz.arr.parametros.business.service;

import br.gov.to.sefaz.arr.persistence.entity.Bancos;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.Collection;
import java.util.List;

/**
 * Contrato de acesso do serviço de {@link Bancos}.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 22/04/2016 16:20:00
 */
public interface BancosService extends CrudService<Bancos, Integer> {

    /**
     * Busca todos os {@link br.gov.to.sefaz.arr.persistence.entity.Bancos} com
     * {@link br.gov.to.sefaz.arr.persistence.entity.Bancos#situacao}
     * {@link br.gov.to.sefaz.persistence.enums.SituacaoEnum#ATIVO}.
     *
     * @return lista de bancos ativos
     */
    Collection<Bancos> findAllActiveBancos();

    /**
     * Busca o banco {@link br.gov.to.sefaz.arr.persistence.entity.Bancos} de acordo com o
     * cnpj {@link br.gov.to.sefaz.arr.persistence.entity.Bancos#cnpjRaiz} passado por parâmetro.
     *
     * @param cnpjAgenteBancarioDebidato Cnpj raiz do Banco.
     * @return o banco correspondente ao cnpj raiz.
     */
    List<Bancos> findByCpjRaiz(Integer cnpjAgenteBancarioDebidato);
}
