package br.gov.to.sefaz.presentation.managedbean.composites.domain;

import br.gov.to.sefaz.presentation.managedbean.composites.DataTableMB;

import java.util.List;
import java.util.Objects;

/**
 * Pojo de definições de um evento de um item de uma data table.
 * @see DataTableMB
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 02/05/2016 13:41:00
 */
public class DataTableActionEvents implements JsFunctionSignature {

    private final String elementClass;
    private final String bindType;
    private final String function;
    private final List<String> params;
    private final String eventType;
    private final String complement;

    public DataTableActionEvents(String elementClass, String bindType,
            String function, List<String> params, String eventType, String complement) {
        this.elementClass = elementClass;
        this.bindType = bindType;
        this.function = function;
        this.params = params;
        this.eventType = eventType;
        this.complement = complement;
    }

    public String getElementClass() {
        return elementClass;
    }

    public String getBindType() {
        return bindType;
    }

    @Override
    public String getFunction() {
        return function;
    }

    @Override
    public List<String> getParams() {
        return params;
    }

    public String getEventType() {
        return eventType;
    }

    public String getComplement() {
        return complement;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DataTableActionEvents that = (DataTableActionEvents) obj;
        return Objects.equals(elementClass, that.elementClass)
                && Objects.equals(bindType, that.bindType)
                && Objects.equals(function, that.function)
                && Objects.equals(params, that.params)
                && Objects.equals(eventType, that.eventType)
                && Objects.equals(complement, that.complement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementClass, bindType, function, params, eventType, complement);
    }

    @Override
    public String toString() {
        return "DataTableActionEvents{"
                + "elementClass='" + elementClass + '\''
                + ", bindType='" + bindType + '\''
                + ", function='" + function + '\''
                + ", params=" + params
                + ", eventType='" + eventType + '\''
                + ", complement='" + complement + '\''
                + '}';
    }
}
