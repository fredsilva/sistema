package br.gov.to.sefaz.persistence.domain;

/**
 * Calsse que representa um código, valor para consultar parâmetros gerais.
 *
 * @param <C></C> tipo do atributo code
 * @param <D></D> tipo do atributo value
 *
 * @author <a href="mailto:cristiano.luis@ntconsult.com.br">cristiano.luis</a>
 * @since 15/07/2016 14:08:00
 */
public class CodeData<C,D> {

    private C code;

    private D value;

    public CodeData(C code, D value) {
        this.code = code;
        this.value = value;
    }

    public C getCode() {
        return code;
    }

    public void setCode(C code) {
        this.code = code;
    }

    public D getValue() {
        return value;
    }

    public void setValue(D value) {
        this.value = value;
    }
}
