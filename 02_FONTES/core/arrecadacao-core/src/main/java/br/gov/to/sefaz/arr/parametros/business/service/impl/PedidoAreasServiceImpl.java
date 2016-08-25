package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoAreasFaixaValorService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoAreasService;
import br.gov.to.sefaz.arr.parametros.business.service.PedidoAreasServidoresService;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreas;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasFaixaValor;
import br.gov.to.sefaz.arr.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.persistence.repository.PedidoAreasRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationContext;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Implementação de um {@link PedidoAreasService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/05/2016 10:44:00
 */
@Component
public class PedidoAreasServiceImpl extends DefaultCrudService<PedidoAreas, Integer>
        implements PedidoAreasService {

    private final PedidoAreasFaixaValorService faixaValorService;
    private final PedidoAreasServidoresService servidoresService;

    @Autowired
    public PedidoAreasServiceImpl(PedidoAreasRepository repository, PedidoAreasFaixaValorService faixaValorService,
            PedidoAreasServidoresService servidoresService) {
        super(repository);
        this.faixaValorService = faixaValorService;
        this.servidoresService = servidoresService;
    }

    @Override
    protected PedidoAreasRepository getRepository() {
        return (PedidoAreasRepository) super.getRepository();
    }

    @Override
    public Collection<PedidoAreas> findAllByTipo(Integer idTipoPedido) {
        return getRepository().find(sb -> sb.where().equal("idTipoPedido", idTipoPedido));
    }

    @Override
    public PedidoAreas save(@ValidationSuite(context = ValidationContext.SAVE) PedidoAreas entity) {
        return saveAndUpdate(entity);
    }

    @Override
    public PedidoAreas update(@ValidationSuite(context = ValidationContext.UPDATE) PedidoAreas entity) {
        return saveAndUpdate(entity);
    }

    private PedidoAreas saveAndUpdate(PedidoAreas entity) {
        PedidoAreasFaixaValor faixaValor = entity.getFaixaValor();
        Collection<PedidoAreasServidores> pedidoAreasServidores = entity.getPedidoAreasServidores();

        entity.setFaixaValor(null);
        entity.setPedidoAreasServidores(null);

        PedidoAreas saved = getRepository().save(entity);

        faixaValor.setIdPedidoArea(saved.getIdPedidoArea());
        pedidoAreasServidores.forEach(srv -> srv.setIdPedidoArea(saved.getIdPedidoArea()));

        faixaValorService.save(faixaValor);
        servidoresService.save(pedidoAreasServidores);

        saved.setFaixaValor(faixaValor);
        saved.setPedidoAreasServidores(pedidoAreasServidores);

        return saved;
    }

    @Transactional
    @Override
    public Optional<PedidoAreas> delete(Integer id) {
        Optional<PedidoAreas> entity;

        if (getRepository().existsLockReference(id)) {
            getRepository().updateSituacao(id, SituacaoEnum.CANCELADO);
            entity = Optional.of(getRepository().findOne(id));

        } else {
            faixaValorService.delete(id);
            servidoresService.deleteByIdPedidoArea(id);
            getRepository().delete(id);
            entity = Optional.empty();

        }

        return entity;
    }
}
