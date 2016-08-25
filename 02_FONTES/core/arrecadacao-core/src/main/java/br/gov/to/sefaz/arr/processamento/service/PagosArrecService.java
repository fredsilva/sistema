package br.gov.to.sefaz.arr.processamento.service;

import br.gov.to.sefaz.arr.persistence.entity.PagosArrec;
import br.gov.to.sefaz.arr.persistence.entity.PagosArrecPK;
import br.gov.to.sefaz.business.service.CrudService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.PagosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/07/2016 10:37:00
 */
public interface PagosArrecService extends CrudService<PagosArrec, PagosArrecPK> {

    /**
     * Busca a última ordem de lote para o TPAR informado.
     *
     * @param idBdarTpar código do TPAR
     * @return o número da última ordem de lote na base de dados para o TPAR
     */
    int getLastOrdemLoteTpar(Long idBdarTpar);


    /**
     * Verifica se um detalhe do arquivo, através dos parâmetros fornecidos, já foi processado.
     *
     * @param nsu             Número Sequencial Único
     * @param codigoBanco     código do Banco
     * @param dataArrecadacao data da arrecadação
     * @param valorTotal      valor da arrecadação
     * @return true se encontrar um detalhe já processado na base de dados, false caso não encontrar
     */
    boolean existsPagosArrecWith(String nsu, Integer codigoBanco, LocalDateTime dataArrecadacao, BigDecimal valorTotal);
}
