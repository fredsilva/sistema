package br.gov.to.sefaz.arr.parametros.business.facade.impl;

import br.gov.to.sefaz.arr.parametros.business.facade.TipoRejeicaoArquivosFacade;
import br.gov.to.sefaz.arr.parametros.business.service.TipoRejeicaoArquivosService;
import br.gov.to.sefaz.arr.persistence.entity.TipoRejeicaoArquivos;
import br.gov.to.sefaz.business.facade.impl.DefaultCrudFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Serviço para operações referentes a gerência de tipos de rejeição de arquivos.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Component
public class TipoRejeicaoArquivosFacadeImpl extends DefaultCrudFacade<TipoRejeicaoArquivos, Integer>
        implements TipoRejeicaoArquivosFacade {

    @Autowired
    public TipoRejeicaoArquivosFacadeImpl(TipoRejeicaoArquivosService service) {
        super(service);
    }

}
