package br.gov.to.sefaz.arr.processamento.service;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoErro;
import br.gov.to.sefaz.business.service.CrudService;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoErro}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/07/2016 14:43:00
 */
public interface ArquivoErroService extends CrudService<ArquivoErro, Long> {

    /**
     * Verifica se existe um {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoErro} registra para o
     * código de rejeição e código do detalhe do arquivo.
     *
     * @param codigoRejeicao   código de rejeição para identificação do erro
     * @param idDetalheArquivo código do detalhe do arquivo, referente a
     *                         {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoDetalhePagos}
     * @return true caso encotrar algum registro na base de dados, false caso não encontre nenhum registro.
     */
    boolean existsWith(Integer codigoRejeicao, Long idDetalheArquivo);
}
