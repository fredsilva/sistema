package br.gov.to.sefaz.arr.dare.service;

import br.gov.to.sefaz.arr.dare.service.filter.DareContribuinteFilter;
import br.gov.to.sefaz.arr.persistence.view.Contribuintes;

/**
 * Interface que define os métodos de serviço para a view
 * {@link br.gov.to.sefaz.arr.persistence.view.Contribuintes}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 01/09/2016 15:36:00
 */
public interface ContribuitesService {

    /**
     * Busca um {@link br.gov.to.sefaz.arr.persistence.view.Contribuintes} conforme o
     * {@link br.gov.to.sefaz.arr.dare.service.filter.DareContribuinteFilter} fornecido.
     *
     * @param contribuinteFilter filtro que contém as informações necessárias para buscar um
     *                           {@link br.gov.to.sefaz.arr.persistence.view.Contribuintes}
     * @return {@link br.gov.to.sefaz.arr.persistence.view.Contribuintes} conforme filtro.
     */
    Contribuintes findByFilter(DareContribuinteFilter contribuinteFilter);
}
