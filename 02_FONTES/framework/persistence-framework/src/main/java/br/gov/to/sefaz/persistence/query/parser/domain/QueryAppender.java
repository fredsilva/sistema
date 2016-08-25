package br.gov.to.sefaz.persistence.query.parser.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.stream.Collectors;

/**
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

    public QueryAppender append(String text) {
        query.append(text);
        return this;
    }

    public QueryAppender append(Collection<String> string) {
        string.forEach(this::append);
        return this;
    }

    public QueryAppender appendln(String string) {
        query.append(getPad(0)).append(string);
        return this;
    }

    public QueryAppender appendln(int leftPad, String string) {
        query.append(getPad(leftPad)).append(string);
        return this;
    }

    public QueryAppender appendln(Collection<String> strings) {
        query.append(getPad(0)).append(strings.stream().collect(Collectors.joining(getPad(0))));
        return this;
    }

    public QueryAppender appendln(int leftPad, Collection<String> strings) {
        String pad = getPad(leftPad);
        query.append(pad).append(strings.stream().collect(Collectors.joining(pad)));
        return this;
    }

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
