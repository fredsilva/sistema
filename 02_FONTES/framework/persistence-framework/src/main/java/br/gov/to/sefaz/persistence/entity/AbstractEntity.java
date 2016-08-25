package br.gov.to.sefaz.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * Classe abstrata default para as entidades. Possui as colunas de auditoria que estarão presentes em todas as
 * entidades/tabelas no banco de dados.
 *
 * @param <I> tipo do ID da entidade, pode ser simples ({@link String}, {@link Integer}, {@link Long}...) ou composto,
 *            sendo uma classe que possui mais de um atributo e juntos identificam a entidade.
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 05/05/2016 15:12:59
 */
@MappedSuperclass
@EntityListeners(AuditListner.class)
public abstract class AbstractEntity<I extends Serializable> implements Serializable {

    private static final long serialVersionUID = 4678335275783952713L;
    public static final String SIM = "S";
    public static final String NAO = "N";

    @Column(name = "USUARIO_INSERCAO", updatable = false)
    protected String usuarioInsercao;

    @Column(name = "DATA_INSERCAO", updatable = false)
    protected LocalDateTime dataInsercao;

    @Column(name = "USUARIO_ALTERACAO")
    protected String usuarioAlteracao;

    @Column(name = "DATA_ALTERACAO")
    protected LocalDateTime dataAlteracao;

    // Não utilizar enums ou outros objetos que necessitem de converter pois vai dar problema em native queries
    @Column(name = "REGISTRO_EXCLUIDO", nullable = false)
    protected String registroExcluido = "N";

    @Column(name = "USUARIO_EXCLUSAO")
    protected String usuarioExclusao;

    @Column(name = "DATA_EXCLUSAO")
    protected LocalDateTime dataExclusao;

    /**
     * Retorna o ID da entidade, que pode ser simples ou composto.
     *
     * @return ID da entidade
     */
    public abstract I getId();

    public String getUsuarioInsercao() {
        return usuarioInsercao;
    }

    public void setUsuarioInsercao(String usuarioInsercao) {
        this.usuarioInsercao = usuarioInsercao;
    }

    public LocalDateTime getDataInsercao() {
        return dataInsercao;
    }

    public void setDataInsercao(LocalDateTime dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    public String getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(String usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getRegistroExcluido() {
        return registroExcluido;
    }

    public void setRegistroExcluido(String registroExcluido) {
        this.registroExcluido = registroExcluido;
    }

    public String getUsuarioExclusao() {
        return usuarioExclusao;
    }

    public void setUsuarioExclusao(String usuarioExclusao) {
        this.usuarioExclusao = usuarioExclusao;
    }

    public LocalDateTime getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(LocalDateTime dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

}
