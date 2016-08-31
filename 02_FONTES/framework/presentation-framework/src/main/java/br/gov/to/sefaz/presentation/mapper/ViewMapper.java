package br.gov.to.sefaz.presentation.mapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe utilitária para facilitar a criação de um mapa com chave e diversos valores. Utilizar em
 * combos da camada de apresentação para faciliar o mapeamento entre os valores possíveis dada a seleção de um combo
 * pai.
 *
 * @param <K> representa a chave do mapa
 * @param <V> representa a lista de valores dado uma chave para o mapa
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 26/08/2016 10:20:00
 */
public class ViewMapper<K, V> {

    private final Map<K, List<V>> map;

    public ViewMapper() {
        map = new HashMap<>();
    }

    /**
     * Adiciona um elemento no mapa.
     *
     * @param key    Chave {@link K} do mapa
     * @param values valores {@link V} possíveis para a chave do mapa
     */
    public void put(K key, V... values) {
        map.put(key, Arrays.asList(values));
    }

    /**
     * Retorna a lista com os valores {@link V} dado uma chave {@link K}.
     *
     * @param key chave utilizada para retornar sua lista de valores
     * @return retorna a lista de {@link V} conforme a chave {@link K}
     */
    public List<V> get(K key) {
        return map.get(key);
    }

    public Map<K, List<V>> getMap() {
        return map;
    }
}
