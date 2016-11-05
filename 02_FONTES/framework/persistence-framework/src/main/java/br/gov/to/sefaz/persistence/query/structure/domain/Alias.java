package br.gov.to.sefaz.persistence.query.structure.domain;

import java.util.Objects;
import java.util.function.Function;

/**
 * Classe responsável por conter os métodos para Alias do QueryBuilder.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 09:32:00
 * @param <T> entidade base.
 */
public class Alias<T> {

    private final T value;
    private final String aliasName;

    public Alias(T value) {
        this.value = value;
        this.aliasName = "";
    }

    public Alias(T value, String alias) {
        this.value = value;
        this.aliasName = alias;
    }

    public T getValue() {
        return value;
    }

    public String getAlias() {
        return aliasName;
    }

    /**
     * Método que contém Alias para a cláusula da consulta.
     *
     * @return retorna a sentença do Alias.
     */
    public boolean hasAlias() {
        return !aliasName.isEmpty();
    }

    /**
     * Método que verifica se contém Alias para a cláusula da consulta.
     *
     * @return retorna o valor do Alias caso exista.
     */
    public String getIfAlias(Function<String, String> handler) {
        return hasAlias() ? handler.apply(aliasName) : "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alias alias1 = (Alias) o;
        return Objects.equals(value, alias1.value)
                && Objects.equals(aliasName, alias1.aliasName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, aliasName);
    }
}
