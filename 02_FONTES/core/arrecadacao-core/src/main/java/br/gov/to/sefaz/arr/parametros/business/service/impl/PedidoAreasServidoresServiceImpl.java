package br.gov.to.sefaz.arr.parametros.business.service.impl;

import br.gov.to.sefaz.arr.parametros.business.service.PedidoAreasServidoresService;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidores;
import br.gov.to.sefaz.arr.parametros.persistence.entity.PedidoAreasServidoresPK;
import br.gov.to.sefaz.arr.parametros.persistence.repository.PedidoAreasServidoresRepository;
import br.gov.to.sefaz.business.service.impl.DefaultCrudService;
import br.gov.to.sefaz.business.service.validation.ValidationSuite;
import br.gov.to.sefaz.persistence.enums.SituacaoEnum;
import br.gov.to.sefaz.persistence.predicate.AndPredicateBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * implementação de um {@link PedidoAreasServidoresService}.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 21/05/2016 10:38:00
 */
@Component
public class PedidoAreasServidoresServiceImpl
        extends DefaultCrudService<PedidoAreasServidores, PedidoAreasServidoresPK>
        implements PedidoAreasServidoresService {

    public static final String DUPLICATED_SERVIDOR_CONTEXT = "duplicatedServidor";
    public static final String SERVIDOR_CHEFE_CONTEXT = "ServidorChefe";

    @Autowired
    public PedidoAreasServidoresServiceImpl(
            PedidoAreasServidoresRepository repository) {
        super(repository, new Sort(new Sort.Order(Sort.Direction.ASC, "idServidor"),
                new Sort.Order(Sort.Direction.ASC, "idPedidoArea")));
    }

    @Override
    protected PedidoAreasServidoresRepository getRepository() {
        return (PedidoAreasServidoresRepository) super.getRepository();
    }

    @Override
    public Collection<PedidoAreasServidores> findAllByPedido(Integer idPedidoArea) {
        return getRepository().findAll((root, query, cb) -> new AndPredicateBuilder(root, cb)
                .equalsTo("idPedidoArea", idPedidoArea)
                .build());
    }

    @Override
    public void validateDuplicatedServidor(
            @ValidationSuite(context = DUPLICATED_SERVIDOR_CONTEXT) List<PedidoAreasServidores> servidores) {
        // Método para validação de servidores chefe em uma lista de PedidoAreasServidores;
    }

    @Override
    public void validateServidorChefe(
            @ValidationSuite(context = SERVIDOR_CHEFE_CONTEXT) List<PedidoAreasServidores> servidores) {
        // Método para validação de servidores chefe em uma lista de PedidoAreasServidores;
    }

    @Override
    public void deleteByIdPedidoArea(Integer idPedidoArea) {
        findAllByPedido(idPedidoArea).stream()
                .map(PedidoAreasServidores::getId)
                .forEach(this::delete);
    }

    @Override
    public Collection<PedidoAreasServidores> save(Collection<PedidoAreasServidores> list) {
        return super.save(list);
    }

    @Transactional
    @Override
    public Optional<PedidoAreasServidores> delete(PedidoAreasServidoresPK id) {
        Optional<PedidoAreasServidores> entity;

        if (Objects.isNull(id.getIdPedidoArea()) || Objects.isNull(id.getIdServidor())) {
            entity = Optional.empty();
        } else if (getRepository().existsLockReference(id.getIdPedidoArea(), id.getIdServidor())) {
            getRepository().updateSituacao(id.getIdPedidoArea(), id.getIdServidor(), SituacaoEnum.CANCELADO);
            entity = Optional.of(getRepository().findOne(id));
        } else {
            getRepository().delete(id.getIdPedidoArea(), id.getIdServidor());
            entity = Optional.empty();
        }

        return entity;
    }
}
