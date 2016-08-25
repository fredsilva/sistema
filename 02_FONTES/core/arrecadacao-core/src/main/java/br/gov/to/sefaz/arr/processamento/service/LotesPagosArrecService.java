package br.gov.to.sefaz.arr.processamento.service;

import br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec;
import br.gov.to.sefaz.business.service.CrudService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 07/07/2016 11:02:00
 */
public interface LotesPagosArrecService extends CrudService<LotesPagosArrec, Long> {

    /**
     * Busca um {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec}, que seja BDAR, conforme os
     * parâmetros passados.
     *
     * @param codigoBanco     código do Banco
     * @param codigoAgencia   código da Agência Bancária
     * @param codigoConvenio  Código do Convênio de Arrecadação com a agência e banco
     * @param dataArrecadacao Data da arrecadação.
     * @return o {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} caso encontre um conforme
     *     os parâmetros fornecidos, se não, retorna null
     */
    LotesPagosArrec findBdar(Integer codigoBanco, Integer codigoAgencia, Long codigoConvenio,
            LocalDateTime dataArrecadacao);

    /**
     * Busca um {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec}, que seja TPAR, conforme os
     * parâmetros passados.
     *
     * @param codigoBanco     código do Banco
     * @param codigoAgencia   código da Agência Bancária
     * @param codigoConvenio  Código do Convênio de Arrecadação com a agência e banco
     * @param dataArrecadacao Data da arrecadação.
     * @return o {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} caso encontre um conforme
     *     os parâmetros fornecidos, se não, retorna null
     */
    LotesPagosArrec findTpar(Integer codigoBanco, Integer codigoAgencia, Long codigoConvenio, LocalDateTime
            dataArrecadacao);

    /**
     * Busca {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} do tipo Bdar
     * {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec#tipo}
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum#BDAR} de acordo ao banco, convênio e
     * a data de arrecadação.
     *
     * @param codigoBanco     identificador do banco.
     * @param codigoConvenio  identificador do convênio.
     * @param dataArrecadacao data de arrecadação.
     * @return uma lista {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} do tipo Bdar
     * {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec#tipo}
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum#BDAR} de acordo ao banco, convenio e
     *     a data de arrecadação.
     */
    List<LotesPagosArrec> findBdarConcilicao(Integer codigoBanco, Long codigoConvenio, LocalDateTime dataArrecadacao);

    /**
     * Busca {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} do tipo Tpar
     * {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec#tipo}
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum#TPAR} de acordo ao banco, convenio e
     * a data de arrecadação.
     *
     * @param codigoBanco     identificador do banco.
     * @param codigoConvenio  identificador do convênio.
     * @param dataArrecadacao data de arrecadação.
     * @return uma lista {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec} do tipo Tpar
     * {@link br.gov.to.sefaz.arr.persistence.entity.LotesPagosArrec#tipo}
     * {@link br.gov.to.sefaz.arr.persistence.enums.TipoLotePagosEnum#TPAR} de acordo ao banco, convênio e
     *     a data de arrecadação.
     */
    List<LotesPagosArrec> findTparConcilicao(Integer codigoBanco, Long codigoConvenio, LocalDateTime dataArrecadacao);

}
