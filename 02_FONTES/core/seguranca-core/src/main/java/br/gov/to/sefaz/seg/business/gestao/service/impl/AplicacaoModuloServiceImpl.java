package br.gov.to.sefaz.seg.business.gestao.service.impl;

import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.persistence.query.structure.select.orderby.Order;
import br.gov.to.sefaz.seg.business.gestao.service.AplicacaoModuloService;
import br.gov.to.sefaz.seg.persistence.entity.AplicacaoModulo;
import br.gov.to.sefaz.seg.persistence.repository.AplicacaoModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Repositório da classe {@link AplicacaoModulo}.
 * @author <a href="mailto:fabio.fucks@ntconsult.com.br">fabio.fucks</a>
 * @since 19/07/2016 16:28:00
 */
@Service
public class AplicacaoModuloServiceImpl extends DefaultCrudService<AplicacaoModulo, Long> implements
        AplicacaoModuloService {

    @Autowired
    public AplicacaoModuloServiceImpl(AplicacaoModuloRepository repository) {
        super(repository);
    }

    @Override
    public Collection<AplicacaoModulo> findByModuloSistema(Long identificacaoModuloSistema) {

        return getRepository().find("am", select -> select
                .where()
                .opt().equal("am.identificacaoModuloSistema", identificacaoModuloSistema)
                .orderBy("am.descricaoAplicacaoModulo", Order.ASC));
    }
}
