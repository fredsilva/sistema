package br.gov.to.sefaz.presentation.managedbean.composites.domain;

import br.gov.to.sefaz.presentation.managedbean.composites.DataTableMB;

import java.util.List;
import java.util.Objects;

/**
 * Pojo de definições de um evento de um item de uma data table.
 * @see DataTableMB
 *
 * @author <a href="mailto:thiago.luz@ntconsult.com.br">thiago.luz</a>
 * @since 30/06/2016 10:26:00
 */
public class DataTableActionVisible implements JsFunctionSignature {

    private final String elementClass;
    private final String visibility;
    private final String function;
    private final List<String> params;

    public DataTableActionVisible(String elementClass, String visibility,
            String function, List<String> params) {
        this.elementClass = elementClass;
        this.visibility = visibility;
        this.function = function;
        this.params = params;
    }

    public String getElementClass() {
        return elementClass;
    }

    public String getVisibility() {
        return visibility;
    }

    @Override
    public String getFunction() {
        return function;
    }

    @Override
    public List<String> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DataTableActionVisible that = (DataTableActionVisible) obj;
        return Objects.equals(elementClass, that.elementClass)
                && Objects.equals(visibility, that.visibility)
                && Objects.equals(function, that.function)
                && Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementClass, visibility, function, params);
    }

    @Override
    public String toString() {
        return "DataTableActionEvents{"
                + "elementClass='" + elementClass + '\''
                + ", bindType='" + visibility + '\''
                + ", function='" + function + '\''
                + ", params=" + params
                + '}';
    }
}
