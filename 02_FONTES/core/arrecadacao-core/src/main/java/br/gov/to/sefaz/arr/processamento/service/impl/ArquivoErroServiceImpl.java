package br.gov.to.sefaz.arr.processamento.service.impl;

import br.gov.to.sefaz.arr.persistence.entity.ArquivoErro;
import br.gov.to.sefaz.arr.persistence.repository.ArquivoErroRepository;
import br.gov.to.sefaz.arr.processamento.service.ArquivoErroService;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação do serviço da entidade {@link br.gov.to.sefaz.arr.persistence.entity.ArquivoErro}.
 *
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 08/07/2016 14:43:00
 */
@Service
public class ArquivoErroServiceImpl extends DefaultCrudService<ArquivoErro, Long>
        implements ArquivoErroService {

    @Autowired
    public ArquivoErroServiceImpl(ArquivoErroRepository repository) {
        super(repository);
    }

    @Override
    public boolean existsWith(Integer codigoRejeicao, Long idDetalheArquivo) {
        return getRepository().exists(select -> select
                .where()
                .equal("idCodigoRejeicao", codigoRejeicao)
                .and()
                .equal("arquivoDetalhePagos.idDetalheArquivo", idDetalheArquivo));
    }
}
