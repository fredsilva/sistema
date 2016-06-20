package br.gov.to.sefaz.seg.business.authentication.domain;

import java.util.Objects;

/**
 * <p>Chave para a identificação de um grupo de roles. Um grupo de roles é identificado por um {@link RoleGroupType} e
 * um {@link Long}, identificador unico entre todos os grupos do tipo informado.</p>
 * <p>A {@link #description} não é utilizada para {@link #equals(Object)} ou {@link #hashCode()}, é apenas
 * para facilitar a identificação do grupo visualmente.</p>
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 09/06/2016 14:00:00
 */
public class RoleGroupKey implements Comparable<RoleGroupKey> {

    private final RoleGroupType type;
    private final Long id;
    private final String description;

    public RoleGroupKey(RoleGroupType type, Long id) {
        this.type = type;
        this.id = id;
        this.description = "";
    }

    public RoleGroupKey(RoleGroupType type, Long id, String description) {
        this.type = type;
        this.id = id;
        this.description = description;
    }

    public RoleGroupType getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    /**
     * {@inheritDoc}
     * <p>Compara apenas o {@link #type} e o {@link #id}.</p>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoleGroupKey that = (RoleGroupKey) o;
        return type == that.type
                && Objects.equals(id, that.id);
    }

    /**
     * {@inheritDoc}
     * <p>Utiliza apenas o {@link #type} e o {@link #id} para o calculo de hash.</p>
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, id);
    }

    @Override
    public String toString() {
        return "RoleGroupKey{"
                + "type=" + type
                + ", id=" + id
                + ", description='" + description + '\''
                + '}';
    }

    @Override
    public int compareTo(RoleGroupKey o) {
        int comparison = this.description.compareTo(o.description);
        if (comparison == 0) {
            comparison = this.id.compareTo(o.id);
        }

        return comparison;
    }
}
