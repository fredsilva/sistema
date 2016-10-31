package br.gov.to.sefaz.arr.dare.service;

import br.gov.to.sefaz.arr.persistence.entity.DareDetalhe;
import br.gov.to.sefaz.arr.persistence.entity.DareDetalhePK;
import br.gov.to.sefaz.business.service.CrudService;

import java.util.List;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 15:06:00
 */
public interface DareDetalheService extends CrudService<DareDetalhe, DareDetalhePK> {

    /**
     * Busca todos os {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe} conforme o nosso numero.
     *
     * @param idNossoNumeroDare nosso numero do código de barras
     * @return uma lista com os {@link br.gov.to.sefaz.arr.persistence.entity.DareDetalhe}s encotrados.
     */
    List<DareDetalhe> findAllByNossoNumero(Long idNossoNumeroDare);
}
