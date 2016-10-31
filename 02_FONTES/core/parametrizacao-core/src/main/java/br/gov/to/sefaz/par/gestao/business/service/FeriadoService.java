package br.gov.to.sefaz.par.gestao.business.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.par.gestao.persistence.entity.Feriado;
import br.gov.to.sefaz.par.gestao.persistence.entity.FeriadoPK;

import java.time.LocalDate;

/**
 * Serviços para manipulação de {@link br.gov.to.sefaz.par.gestao.persistence.entity.Feriado}.
 *
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 21/09/2016 16:33:00
 */
public interface FeriadoService extends CrudService<Feriado, FeriadoPK> {

    /**
     * Retorna a próxima data útil do município a partir da data passada, caso a mesma não seja.
     *
     * @param data data a ser verificada.
     * @param codIbgeMunicipio código ibge do município.
     * @return data útil.
     */
    LocalDate getDataUtil(LocalDate data, Integer codIbgeMunicipio);

}
