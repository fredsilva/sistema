package br.gov.to.sefaz.arr.processamento.service;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao;
import br.gov.to.sefaz.business.service.CrudService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoRecepcao}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 15:33:00
 */
public interface ArquivoRecepcaoService extends CrudService<ArquivoRecepcao, Long> {

    /**
     * Procura {@link ArquivoRecepcao} por data de arrecadação, código do banco e código do convênio.
     *
     * @param dataArrecadacao data de arrecadação.
     * @param idBanco         código do banco.
     * @param idConvenio      código do convênio.
     * @return lista de {@link ArquivoRecepcao} correspondentes aos parâmetros passados.
     */
    List<ArquivoRecepcao> findToConciliacao(LocalDateTime dataArrecadacao, Integer idBanco, Long idConvenio);

    /**
     * Verifica se existe um arquivo consolidado conforme o id do Banco, Data de Geração do Arquivo e id do Convênio.
     *
     * @param bancoId            identificação do Banco
     * @param dataGeracaoArquivo Data de Geração do Arquivo
     * @param convenioId         e identificação do Convênio
     * @return true caso encontre algum Arquivo consolidado e false caso não encontre
     */
    boolean existsArquivoConsolidadoWith(Integer bancoId, LocalDate dataGeracaoArquivo, Long convenioId);

    /**
     * Verifica se existe um arquivo através do número sequencial já foi processado.
     *
     * @param numeroSequencial número sequencial do HEADER do arquivo
     * @return true caso encontre algum arquivo processado com o numero sequencial fornecido, false caso não encontre
     */
    boolean existsNumeroSequencial(Long numeroSequencial);

    /**
     * Busca o último numero de sequência de um arquivo conforme o codigo do convenio, codigo do Banco e data de
     * geracao do arquivo.
     *
     * @param codigoConvenio     codigo do convenio do arquivo
     * @param codigoBanco        codigo do banco do arquivo
     * @param dataGeracaoArquivo data de geração do arquivo
     * @return o ultimo número sequencial presente na base de dados para um arquivo processado conforme os parâmetros
     *      fornecidos
     */
    Long getLastNumeroSequencialBy(Long codigoConvenio, Integer codigoBanco, LocalDate dataGeracaoArquivo);
}
