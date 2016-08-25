package br.gov.to.sefaz.persistence.satquery.parser.insert;

import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.insert.InsertParser;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.satquery.parser.QualifySatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifySatQueryParser
public class SatInsertParser extends InsertParser {

    @Override
    @Autowired
    @QualifySatQueryParser
    public void setSelectParser(QueryStructureParser<SelectStructure> selectParser) {
        super.setSelectParser(selectParser);
    }
}
