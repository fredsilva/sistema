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
        executeAfterSave(save);
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
        executeAfterUpdate(update);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void delete() {
        I id = getDto().getId();
        Optional<E> delete = getFacade().delete(id);
        getResultList().removeIf(e -> e.getId().equals(id));

        if (delete.isPresent()) {
            showLogicalDeleteMessage();
            getResultList().add(delete.get());
        } else {
            showPhysicalDeleteMessage();
        }
        clearDto();
        executeAfterDelete(id);
    }

    /**
     * Método responsável pela execução de açoes pós {@link #save()}.
     */
    protected void executeAfterSave(E dto) {
        // Método para ser sobreescrito quando for necessário executar algo após o {@link #save()}.
    }

    /**
     * Método responsável pela execução de açoes pós {@link #update()}.
     */
    protected void executeAfterUpdate(E dto) {
        // Método para ser sobreescrito quando for necessário executar algo após o {@link #update()}.
    }

    /**
     * Método responsável pela execução de açoes pós {@link #delete()}.
     */
    protected void executeAfterDelete(I id) {
        // Método para ser sobreescrito quando for necessário executar algo após o {@link #delete()}.
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void clearDto() {
        setDto(dtoProvider.get());
    }

    /**
     * Método responsavel pela exibição de mensagens ao fim do {@link #save()}.
     */
    protected void showSaveMessage() {
        MessageUtil.addMessage("mensagem.sucesso.operacao");
    }

    /**
     * Método responsavel pela exibição de mensagens ao fim do {@link #update()}.
     */
    protected void showUpdateMessage() {
        // Caso precise de mensagens no update este metodo deve ser sobreescrito.
        MessageUtil.addMessage("mensagem.sucesso.operacao");
    }

    /**
     * Método responsavel pela exibição de mensagens ao fim do {@link #delete()}.
     */
    protected void showLogicalDeleteMessage() {
        MessageUtil.addMessage("mensagem.delecao.logica");
    }

    /**
     * Métodos para responsavel pela exibição de mensagens ao fim do {@link #delete()}.
     */
    protected void showPhysicalDeleteMessage() {
        MessageUtil.addMessage("mensagem.delecao.fisica");
    }
}
