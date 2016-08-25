package br.gov.to.sefaz.persistence.query.builder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Builder para criação de mapa de parametros.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 24/06/2016 13:32:00
 */
public class ParamsBuilder {

    private final Map<String, Object> parameters;

    public ParamsBuilder() {
        this.parameters = new LinkedHashMap<>();
    }

    public ParamsBuilder(String name, Object value) {
        this();
        this.parameters.put(name, value);
    }

    public ParamsBuilder put(String name, Object value) {
        parameters.put(name, value);
        return this;
    }

    public ParamsBuilder put(Map<String, Object> params) {
        parameters.putAll(params);
        return this;
    }

    public Map<String, Object> toMap() {
        return parameters;
    }

    public static ParamsBuilder create(String name, Object value) {
        return new ParamsBuilder(name, value);
    }

    public static ParamsBuilder empty() {
        return new ParamsBuilder();
    }
}
