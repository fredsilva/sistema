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

    /**
     * Método responsável por inserir os campos dos parâmetros informados
     * <code>name</code> e <code>value</code>.
     *
     * @param name informa o nome do parâmetro.
     * @param value informa o valor do parâmetro.
     *
     * @return retornar o objeto o qual foi inserido.
     */
    public ParamsBuilder put(String name, Object value) {
        parameters.put(name, value);
        return this;
    }

    /**
     * Método responsável por inserir os campos dos parâmetros informados
     * <code>params</code>.
     *
     * @param params informa um conjunto de parâmetro.
     *
     * @return retornar um Map de params o qual foram inseridos.
     */
    public ParamsBuilder put(Map<String, Object> params) {
        parameters.putAll(params);
        return this;
    }

    /**
     * Método responsável por listar os parâmetros informados no Map.
     *
     * @return retornar um Map de params.
     */
    public Map<String, Object> toMap() {
        return parameters;
    }

    /**
     * Método responsável por criar um novo parâmetro
     * <code>name</code> e  <code>value</code>.
     *
     * @param name informa o nome do parâmetro.
     * @param value informa o valor do parâmetro.
     *
     * @return retorna o objeto da entidade.
     */
    public static ParamsBuilder create(String name, Object value) {
        return new ParamsBuilder(name, value);
    }

    /**
     * Método responsável por retornar um parâmetro vazio.
     *
     * @return retorna o objeto vazio.
     */
    public static ParamsBuilder empty() {
        return new ParamsBuilder();
    }
}
