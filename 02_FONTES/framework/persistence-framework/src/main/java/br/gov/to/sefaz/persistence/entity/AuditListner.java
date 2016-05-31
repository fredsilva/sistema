package br.gov.to.sefaz.persistence.entity;

import org.springframework.security.core.context.SecurityContextHolder;

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

    @PrePersist
    public void prePersist(AbstractEntity<?> entity) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        entity.setUsuarioInsercao(userName);
        entity.setDataInsercao(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(AbstractEntity<?> entity) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        entity.setUsuarioAlteracao(userName);
        entity.setDataAlteracao(LocalDateTime.now());
    }

    @PreRemove
    public void preRemove(AbstractEntity<?> entity) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        entity.setRegistroExcluido(Boolean.TRUE);
        entity.setUsuarioExclusao(userName);
        entity.setDataExclusao(LocalDateTime.now());
    }

}
