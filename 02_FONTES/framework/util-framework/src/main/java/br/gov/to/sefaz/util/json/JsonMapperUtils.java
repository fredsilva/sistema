package br.gov.to.sefaz.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utilitário para manipulação de JSON.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 06/06/2016 15:45:00
 */
public class JsonMapperUtils {

    /**
     * Cria uma instancia já configurada de um {@link ObjectMapper}.
     * @return instancia de um mapeador de objtos
     */
    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.findAndRegisterModules();

        return mapper;
    }

    /**
     * Transforma um {@link Object} e m JSON atrávés de reflection.
     *
     * @param object objeto que será transformado para JSON
     * @return representação do objeto em JSON
     */
    public static String objectToJson(Object object) {
        try {
            return createObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JasonMappingException("Erro ao transformar " + object + " em JSON", e);
        }
    }
}
