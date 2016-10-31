package br.gov.to.sefaz.arr.processamento.converter;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;

import java.text.ParseException;

/**
 * Contrato para conversao de dominio em entidade.
 *
 * @param <T> Tipo do dominio a ser convertido.
 * @param <K> Tipo do entity {@link AbstractEntity} para o qual será convertido.
 * @author <a href="mailto:breno.hoffmeister@ntconsult.com.br">breno.hoffmeister</a>
 * @since 07/07/2016 09:42:00
 */
public interface DomainToEntityConverter<T, K extends AbstractEntity> {
    /**
     * Converte domínio em entidade.
     *
     * @param domain Tipo do domínio a ser convertido.
     * @return Tipo do entity {@link AbstractEntity} para o qual será convertido.
     * @throws ParseException erro de formatação na conversão.
     */
    K convert(T domain) throws ParseException;

}
