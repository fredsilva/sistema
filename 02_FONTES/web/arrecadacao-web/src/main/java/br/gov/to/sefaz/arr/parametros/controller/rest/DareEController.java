package br.gov.to.sefaz.arr.parametros.controller.rest;

import br.gov.to.sefaz.arr.dare.facade.DareFacade;
import br.gov.to.sefaz.arr.persistence.entity.Dare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Web Service para criação de um PDF do DARE-E.
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 13/09/2016 15:53:00
 */
@RestController
public class DareEController {

    @Autowired
    DareFacade dareFacade;

    /**
     * Web Service para criação de Report do DARE-E.
     *
     * @param nossoNumero {@link Dare#getIdNossoNumeroDare()}
     * @return PDF referente ao Nosso Número da URL recebida por e-mail.
     */
    @RequestMapping(path = "/public/imprime-dare/{nossoNumero}")
    public ResponseEntity<byte[]> nossoNumero(@PathVariable Long nossoNumero) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(dareFacade.createDareEPdf(nossoNumero));
    }
}
