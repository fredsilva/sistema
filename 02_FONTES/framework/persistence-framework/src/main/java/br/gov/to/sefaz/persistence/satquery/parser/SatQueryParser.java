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
 * Classe que representa a estrutura do componente Parse para o comando Select.
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

    /**
     * Método responsável que realiza o parse no comando do Select.
     *
     * @param selectBuilder informa o valor do select.
     *
     * @return retornar o valor do parse da Query.
     */
    public ResultQuery parseSelect(
            QueryStructureBuilder<? extends QueryStructureBuilder<?, SelectStructure>, ?> selectBuilder) {
        return selectParser.parse(selectBuilder.getRoot().build());
    }

    /**
     * Método responsável que realiza o parse no comando Update.
     *
     * @param updateBuilder informa o valor do Update.
     */
    public ResultQuery parseUpdate(
            QueryStructureBuilder<? extends QueryStructureBuilder<?, UpdateStructure>, ?> updateBuilder) {
        return updateParser.parse(updateBuilder.getRoot().build());
    }

    /**
     * Método responsável que realiza o parse no comando de Delete.
     *
     * @param deleteBuilder informa o valor do Delete.
     *
     * @return retornar o valor do parse da Query.
     */
    public ResultQuery parseDelete(
            QueryStructureBuilder<? extends QueryStructureBuilder<?, DeleteStructure>, ?> deleteBuilder) {
        return deleteParser.parse(deleteBuilder.getRoot().build());
    }

    /**
     * Método responsável que realiza o parse no comando de Insert.
     *
     * @param insertBuilder informa o valor do Insert.
     *
     * @return retornar o valor do parse da Query.
     */
    public ResultQuery parseInsert(
            QueryStructureBuilder<? extends QueryStructureBuilder<?, InsertStructure>, ?> insertBuilder) {
        return insertParser.parse(insertBuilder.getRoot().build());
    }
}
