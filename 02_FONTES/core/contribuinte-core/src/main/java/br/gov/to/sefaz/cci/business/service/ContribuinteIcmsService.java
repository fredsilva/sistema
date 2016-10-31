package br.gov.to.sefaz.cci.business.service;

import br.gov.to.sefaz.business.service.CrudService;
import br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms;

/**
 * Contrato de acesso do serviço de{@link br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 17/08/2016 17:29:00
 */
public interface ContribuinteIcmsService extends CrudService<ContribuinteIcms, String> {

    /**
     * Verifica se existe algum {@link br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms} com o CNPJ fornecido.
     *
     * @param cnpjContribuinte atributo que será utilizado pela consulta
     * @return true caso exista algum elemento com o CNPJ fornecido e false caso não encontre nenhum elemento.
     */
    boolean existsWithCnpj(String cnpjContribuinte);

    /**
     * Busca um {@link br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms} que tenha o CNPJ fornecido por
     * parametro e que seja Ativo e o primeiro cadastrado.
     *
     * @param cnpjContribuinte atributo que será utilizado pela consulta
     * @return null caso não encontre o elemento, ou o {@link br.gov.to.sefaz.cci.persistence.entity.ContribuinteIcms}
     */
    ContribuinteIcms findFirstContribuinteWithCnpj(String cnpjContribuinte);
}
