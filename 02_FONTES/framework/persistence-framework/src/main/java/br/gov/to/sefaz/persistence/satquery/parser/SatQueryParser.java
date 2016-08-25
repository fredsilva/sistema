package br.gov.to.sefaz.persistence.satquery.parser;

import br.gov.to.sefaz.persistence.query.builder.QueryStructureBuilder;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.delete.DeleteStructure;
import br.gov.to.sefaz.persistence.query.structure.insert.InsertStructure;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.update.UpdateStructure;
import br.gov.to.sefaz.persistence.satquery.parser.delete.SatDeleteParser;
import br.gov.to.sefaz.persistence.satquery.parser.insert.SatInsertParser;
import br.gov.to.sefaz.persistence.satquery.parser.select.SatSelectParser;
import br.gov.to.sefaz.persistence.satquery.parser.update.SatUpdateParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 08/07/2016 18:25:00
 */
@Component
public class SatQueryParser {

    private final SatSelectParser selectParser;
    private final SatUpdateParser updateParser;
    private final SatDeleteParser deleteParser;
    private final SatInsertParser insertParser;

    @Autowired
    public SatQueryParser(SatSelectParser selectParser, SatUpdateParser updateParser, SatDeleteParser deleteParser,
            SatInsertParser insertParser) {
        this.selectParser = selectParser;
        this.updateParser = updateParser;
        this.deleteParser = deleteParser;
        this.insertParser = insertParser;
    }

    public ResultQuery parseSelect(
            QueryStructureBuilder<? extends QueryStructureBuilder<?, SelectStructure>, ?> selectBuilder) {
        return selectParser.parse(selectBuilder.getRoot().build());
    }

    public ResultQuery parseUpdate(
            QueryStructureBuilder<? extends QueryStructureBuilder<?, UpdateStructure>, ?> updateBuilder) {
        return updateParser.parse(updateBuilder.getRoot().build());
    }

    public ResultQuery parseDelete(
            QueryStructureBuilder<? extends QueryStructureBuilder<?, DeleteStructure>, ?> deleteBuilder) {
        return deleteParser.parse(deleteBuilder.getRoot().build());
    }

    public ResultQuery parseInsert(
            QueryStructureBuilder<? extends QueryStructureBuilder<?, InsertStructure>, ?> insertBuilder) {
        return insertParser.parse(insertBuilder.getRoot().build());
    }
}
