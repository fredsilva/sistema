package br.gov.to.sefaz.persistence.query.builder.sql.select.join;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.select.SelectBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereBuilder;
import br.gov.to.sefaz.persistence.query.builder.sql.where.WhereHandler;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinStructure;
import br.gov.to.sefaz.persistence.query.structure.select.join.JoinType;

import java.util.Optional;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 30/06/2016 10:47:00
 */
@SuppressWarnings("PMD.ShortMethodName")
public class JoinBuilder implements QueryStructureBuilder<SelectBuilder, JoinStructure> {

    private final SelectBuilder parent;
    private final Alias<String> table;
    private final JoinType type;
    private Optional<WhereBuilder<SelectBuilder>> onBuilder;

    public JoinBuilder(SelectBuilder parent, Alias<String> table, JoinType type) {
        this.parent = parent;
        this.table = table;
        this.type = type;
        this.onBuilder = Optional.empty();
    }

    public SelectBuilder on(WhereHandler onHandler) {
        onBuilder = Optional.of(new WhereBuilder<>(parent));
        onHandler.handle(onBuilder.get());
        return getRoot();
    }

    public SelectBuilder on(String leftColumn, String rightColumn) {
        String fromLeftColumn = withFrom(parent.getFrom(), leftColumn);
        String fromRightColumn = withFrom(getFrom(), rightColumn);

        on(wb -> wb.equalColumns(fromLeftColumn, fromRightColumn));

        return getRoot();
    }

    public SelectBuilder on(String column) {
        on(column, column);
        return getRoot();
    }

    private String getFrom() {
        if (table.hasAlias()) {
            return table.getAlias();
        }

        return table.getValue();
    }

    private String withFrom(String from, String column) {
        if (column.contains(".")) {
            return column;
        }

        return from.replaceAll("^.+?\\.", "") + "." + column;
    }

    @Override
    public SelectBuilder getRoot() {
        return parent.getRoot();
    }

    @Override
    public JoinStructure build() {
        if (onBuilder.isPresent()) {
            return new JoinStructure(table, type, onBuilder.get().build());
        }

        return new JoinStructure(table, type);
    }
}