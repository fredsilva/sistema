package br.gov.to.sefaz.persistence.entity;

import br.gov.to.sefaz.util.application.ApplicationUtil;

import java.time.LocalDateTime;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * Classe de auditoria de entidades. Seta os atributos/colunas de auditoria dos registros, antes de inserir, alterr ou
 * remover da base de dados.
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 06/05/2016 15:13:32
 */
public class AuditListner {

    /**
     * Antes de preencher a entidade preenche os dados referentes a {@link AbstractEntity#getUsuarioInsercao()} e
     * {@link AbstractEntity#getDataInsercao()}.
     *
     * @param entity entidade ao qual os dados serão aplicados antes da inserção.
     */
    @PrePersist
    public void prePersist(AbstractEntity<?> entity) {
        entity.setUsuarioInsercao(ApplicationUtil.getSafeNameAuthenticatedUser());
        entity.setDataInsercao(LocalDateTime.now());
    }

    /**
     * Antes de preencher a entidade preenche os dados referentes a {@link AbstractEntity#getUsuarioAlteracao()} e
     * {@link AbstractEntity#getDataAlteracao()}.
     *
     * @param entity entidade ao qual os dados serão aplicados antes da alteração.
     */
    @PreUpdate
    public void preUpdate(AbstractEntity<?> entity) {
        if (!AbstractEntity.SIM.equals(entity.getRegistroExcluido())) {
            entity.setUsuarioAlteracao(ApplicationUtil.getSafeNameAuthenticatedUser());
            entity.setDataAlteracao(LocalDateTime.now());
        }
    }

    /**
     * Antes de preencher a entidade preenche os dados referentes a {@link AbstractEntity#getUsuarioExclusao()} e
     * {@link AbstractEntity#getDataExclusao()}.
     *
     * @param entity entidade ao qual os dados serão aplicados antes da exclusão.
     */
    @PreRemove
    public void preRemove(AbstractEntity<?> entity) {
        entity.setRegistroExcluido(AbstractEntity.SIM);
        entity.setUsuarioExclusao(ApplicationUtil.getSafeNameAuthenticatedUser());
        entity.setDataExclusao(LocalDateTime.now());
    }

}
