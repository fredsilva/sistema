package br.gov.to.sefaz.presentation.managedbean.impl;

import br.gov.to.sefaz.business.facade.CrudFacade;
import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.presentation.managedbean.AutowiredMB;
import br.gov.to.sefaz.presentation.managedbean.CrudMB;
import br.gov.to.sefaz.util.message.MessageUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import javax.faces.bean.ManagedBean;

/**
 * Implementação default de um {@link CrudMB}.
 *
 * @param <E> Tipo da entidade gerenciada pelo serviço.
 * @param <I> Tipo do ID da entidade.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 29/04/2016 18:52:00
 */
@ManagedBean
public class DefaultCrudMB<E extends AbstractEntity<I>, I extends Serializable>
        extends AutowiredMB implements CrudMB<E, I> {

    protected Collection<E> resultList;
    private E dto;
    private CrudFacade<E, I> facade;
    private final Supplier<E> dtoProvider;

    public DefaultCrudMB(Supplier<E> dtoProvider) {
        this.dtoProvider = dtoProvider;
        clearDto();
    }

    protected void setFacade(CrudFacade<E, I> facade) {
        this.facade = facade;
    }

    protected CrudFacade<E, I> getFacade() {
        return this.facade;
    }

    @Override
    public void setDto(E dto) {
        this.dto = dto;
    }

    @Override
    public E getDto() {
        return dto;
    }

    public void setResultList(Collection<E> resultList) {
        this.resultList = resultList;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public Collection<E> getResultList() {
        if (resultList == null) {
            resultList = getFacade().findAll();
        }

        return resultList;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void save() {
        E save = getFacade().save(getDto());
        showSaveMessage();
        getResultList().add(save);
        clearDto();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void update() {
        E update = getFacade().update(getDto());
        getResultList().removeIf(e -> e.getId().equals(getDto().getId()));
        showUpdateMessage();
        getResultList().add(update);
        clearDto();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void delete() {
        Optional<E> delete = getFacade().delete(getDto().getId());
        getResultList().removeIf(e -> e.getId().equals(getDto().getId()));

        if (delete.isPresent()) {
            showLogicalDeleteMessage();
            getResultList().add(delete.get());
        } else {
            showPhysicalDeleteMessage();
        }

        clearDto();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void clearDto() {
        setDto(dtoProvider.get());
    }

    /**
     * Métodos para responsavel pela exibição de mensagens ao fim do {@link #save()}.
     */
    protected void showSaveMessage() {
        MessageUtil.addMesage("mensagem.sucesso.operacao");
    }

    /**
     * Métodos para responsavel pela exibição de mensagens ao fim do {@link #update()}.
     */

    protected void showUpdateMessage() {
        // Caso precise de mensagens no update este metodo deve ser sobreescrito.
        MessageUtil.addMesage("mensagem.sucesso.operacao");
    }

    /**
     * Métodos para responsavel pela exibição de mensagens ao fim do {@link #delete()}.
     */
    protected void showLogicalDeleteMessage() {
        MessageUtil.addMesage("mensagem.delecao.logica");
    }

    /**
     * Métodos para responsavel pela exibição de mensagens ao fim do {@link #delete()}.
     */
    protected void showPhysicalDeleteMessage() {
        MessageUtil.addMesage("mensagem.delecao.fisica");
    }
}
