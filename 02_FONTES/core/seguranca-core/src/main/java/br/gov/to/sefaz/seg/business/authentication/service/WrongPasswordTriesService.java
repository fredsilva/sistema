package br.gov.to.sefaz.seg.business.authentication.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Serviço para armazenamento de tentativas de acessos de ususarios ao sistema.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/06/2016 15:31:00
 */
@Component
public class WrongPasswordTriesService {

    private static final int MAX_TRIES = 5;
    private final Map<String, AtomicInteger> triesMap = new ConcurrentHashMap<>();

    /**
     * Zera a quantidade de tentativas de login de um usuario.
     * @param cpf cpf do ususario
     */
    public void clearTries(String cpf) {
        triesMap.remove(cpf);
    }

    /**
     * Incrementa a o numero de tentativas de acesso de um ususario.
     *
     * @param cpf cpf do ususario
     * @throws PasswordMaxTriesException caso o numero de tentativas exceda {@value MAX_TRIES} tentativas.
     */
    public void incrementTries(String cpf) throws PasswordMaxTriesException {
        triesMap.putIfAbsent(cpf, new AtomicInteger());

        if (triesMap.get(cpf).incrementAndGet() > MAX_TRIES) {
            throw new PasswordMaxTriesException("You have tried more than system allows (" + MAX_TRIES + " times)!");
        }
    }
}
