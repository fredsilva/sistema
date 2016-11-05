package br.gov.to.sefaz.persistence.query.parser.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Classe responsável por conter métodos que concatenam a consulta.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:59:00
 */
public class QueryAppender {

    private final StringBuilder query;
    private final int defaultPad;

    public QueryAppender() {
        this.defaultPad = 0;
        this.query = new StringBuilder();
    }

    public QueryAppender(int defaultPad) {
        this.defaultPad = defaultPad;
        this.query = new StringBuilder();
    }

    /**
     * Método responsável por concatenar uma consulta.
     * @param text informa o conteúdo da consulta a ser concatenada.
     * @return retornar a montagem da consulta atualizada.
     */
    public QueryAppender append(String text) {
        query.append(text);
        return this;
    }

    /**
     * Método responsável por concatenar uma consulta.
     * @param string informa o conteúdo de uma Coleção da consulta a ser concatenada.
     * @return retornar a montagem da consulta atualizada.
     */
    public QueryAppender append(Collection<String> string) {
        string.forEach(this::append);
        return this;
    }

    /**
     * Método responsável por concatenar uma consulta.
     * @param string informa o conteúdo da consulta a ser concatenada.
     * @return retornar a montagem da consulta atualizada.
     */
    public QueryAppender appendln(String string) {
        query.append(getPad(0)).append(string);
        return this;
    }

    /**
     * Método responsável por concatenar uma consulta.
     * @param leftPad conta preenchimento a esquerda.
     * @param string informa o conteúdo da consulta a ser concatenada.
     * @return retornar a montagem da consulta atualizada.
     */
    public QueryAppender appendln(int leftPad, String string) {
        query.append(getPad(leftPad)).append(string);
        return this;
    }

    /**
     * Método responsável por concatenar uma consulta.
     * @param strings informa o conteúdo da Coleção de consultas a serem concatenadas.
     * @return retornar a montagem da consulta atualizada.
     */
    public QueryAppender appendln(Collection<String> strings) {
        query.append(getPad(0)).append(strings.stream().collect(Collectors.joining(getPad(0))));
        return this;
    }

    /**
     * Método responsável por concatenar uma consulta.
     * @param leftPad conta preenchimento a esquerda.
     * @param strings informa o conteúdo da Coleção de consultas a serem concatenadas.
     * @return retornar a montagem da consulta atualizada.
     */
    public QueryAppender appendln(int leftPad, Collection<String> strings) {
        String pad = getPad(leftPad);
        query.append(pad).append(strings.stream().collect(Collectors.joining(pad)));
        return this;
    }

    /**
     * Método responsável por concatenar uma consulta.
     * @param leftPad conta preenchimento a esquerda.
     * @param strings informa o conteúdo da Coleção de consultas a serem concatenadas.
     * @param separator separa o contéudo da consulta.
     * @return retornar a montagem da consulta atualizada.
     */
    public QueryAppender appendln(int leftPad, Collection<String> strings, String separator) {
        String pad = getPad(leftPad);
        query.append(pad).append(strings.stream().collect(Collectors.joining(separator + pad)));
        return this;
    }

    public int getDefaultPad() {
        return defaultPad;
    }

    private String getPad(int pad) {
        return "\n " + StringUtils.repeat(' ', 2 * (defaultPad + pad));
    }

    @Override
    public String toString() {
        return query.toString();
    }
}
