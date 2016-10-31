package br.gov.to.sefaz.arr.dare.service;

import br.gov.to.sefaz.arr.dare.service.domain.DareEmail;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import br.gov.to.sefaz.business.service.CrudService;

/**
 * Contrato de acesso do serviço de {@link br.gov.to.sefaz.arr.persistence.entity.Dare}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 13/07/2016 14:36:00
 */
public interface DareService extends CrudService<Dare, Long> {

    /**
     * Envia e-mail de acordo com dados informados.
     *
     * @param dareEmail {@link DareEmail}.
     */
    void sendDareEmail(DareEmail dareEmail);

    /**
     * Método para criação de um PDF do DARE-E.
     *
     * @param nossoNumero {@link Dare#getIdNossoNumeroDare()}
     * @return PDF referente ao Nosso Número da URL recebida por e-mail.
     */
    byte[] createDareEPdf(Long nossoNumero);

    /**
     * Retorna o caminho para a página com o DARE-e Gerado conforme o nosso número.
     *
     * @param nossoNumero número de identificação do DARE-e
     * @return caminho para a página com o DARE-e para ser impresso
     */
    String getDarePath(Long nossoNumero);
}
