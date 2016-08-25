package br.gov.to.sefaz.arr.processamento.service;

import br.gov.to.sefaz.arr.persistence.entity.ArquivosStr;
import br.gov.to.sefaz.business.service.CrudService;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrato de acesso do serviço de Arquivos STR.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 06/07/2016 18:58:00
 */
public interface ArquivosStrService extends CrudService<ArquivosStr, Long> {

    /**
     * Contabiliza o número de arquivos STR.
     *
     * @param dataArrecadacao data da arrecadação.
     * @param idBanco identificador do banco.
     * @param idConvenio identificador do convênio.
     * @return número total de arquivos STR.
     */
    long countToConciliacao(LocalDate dataArrecadacao, Integer idBanco, Long idConvenio);

    /**
     * Procura por todos {@link ArquivosStr} com o Número de Controle passado por parâmetro.
     *
     * @param numeroControleStr numero de controle do arquivo STR.
     * @return todos os {@link ArquivosStr} encontradaos com o filtro.
     */
    List<ArquivosStr> findByNumeroControle(String numeroControleStr);
}
