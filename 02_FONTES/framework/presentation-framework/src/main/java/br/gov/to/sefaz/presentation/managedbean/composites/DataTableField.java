package br.gov.to.sefaz.presentation.managedbean.composites;

/**
 * Pojo para definições de campos da tabela.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 10/05/2016 09:34:00
 */
public class DataTableField {

    private final String name;
    private final Boolean hide;
    private final DataTableFieldPrint printType;

    public DataTableField(String name, Boolean hide, DataTableFieldPrint printType) {
        this.name = name;
        this.hide = hide;
        this.printType = printType;
    }

    public String getName() {
        return name;
    }

    public Boolean getHide() {
        return hide;
    }

    public DataTableFieldPrint getPrintType() {
        return printType;
    }
}
