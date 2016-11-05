package br.gov.to.sefaz.persistence.query.parser.domain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classe que representa o Param Id Gererator.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 05/07/2016 14:48:00
 */
public class ParamIdGenerator {

    private final AtomicInteger id;

    public ParamIdGenerator() {
        this.id = new AtomicInteger();
    }

    /**
     * método que gera o Id da classe.
     *
     * @return retorna o valor da geração do ID.
     */
    public String generate() {
        return "param_" + id.incrementAndGet();
    }
}
