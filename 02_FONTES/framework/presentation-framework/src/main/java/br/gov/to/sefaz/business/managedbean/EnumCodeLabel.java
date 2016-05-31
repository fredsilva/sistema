package br.gov.to.sefaz.business.managedbean;

/**
 * Interface padrão para os valores de Enum que possui um código relacionado a uma label.
 *
 * @param <T> tipo do código
 * @author <a href="mailto:gabriel.santos@ntconsult.com.br">gabriel.santos</a>
 * @since 09/05/2016 15:23:40
 */
public interface EnumCodeLabel<T> {

    T getCode();

    String getLabel();
}
