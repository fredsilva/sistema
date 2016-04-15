package br.gov.to.sefaz.common.controller;

import br.gov.to.sefaz.common.controller.iface.CrudController;
import br.gov.to.sefaz.common.model.SerializableEntity;
import br.gov.to.sefaz.common.service.CrudService;
import br.gov.to.sefaz.util.LoggerUtil;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe genérica de acesso às operações de CRUD.
 *
 * @param <E> Veja em {@link CrudController}
 * @param <I> Veja em {@link CrudController}
 * @param <S> Veja em {@link AbstractController}
 *
 * @author cristiano.luis@ntconsult.com.br
 */
public abstract class AbstractCrudController
        <S extends CrudService<E, I>, E extends SerializableEntity<I>, I extends Serializable>
        extends AbstractController<S> implements CrudController<E, I> {

    private final LoggerUtil logger = LoggerUtil.getLogger(AbstractCrudController.class);
    protected E dto;
    protected Collection<E> list;

    public AbstractCrudController(S service) {
        super(service);
    }

    public E getDto() {
        return dto;
    }

    public void setDto(E dto) {
        this.dto = dto;
    }

    /**
     * {@inheritDoc}.
     */
    public Collection<E> getList() {
        return list;
    }

    /**
     * {@inheritDoc}.
     */
    public void loadList() {
        list = service.findAll();
    }

    /**
     * {@inheritDoc}.
     */
    public void save() {
        service.save(dto);
        clear();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Operação Realizada com Sucesso!"));
    }

    /**
     * {@inheritDoc}.
     */
    public void delete(I id) {
        service.delete(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro Excluído com Sucesso!"));
    }

    /**
     * {@inheritDoc}.
     */
    public void select(I id) {
        dto = list.stream().filter(e -> e.getId() == id).findFirst().get();
    }

    /**
     * {@inheritDoc}.
     */
    public void clear() {
        try {
            dto = (E) dto.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Error on clear Entity", e);
        }
    }

}
