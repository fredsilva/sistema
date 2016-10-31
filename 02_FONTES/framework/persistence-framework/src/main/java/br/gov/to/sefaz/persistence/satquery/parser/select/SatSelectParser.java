package br.gov.to.sefaz.persistence.satquery.parser.select;

import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.parser.select.SelectParser;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import br.gov.to.sefaz.persistence.satquery.parser.QualifySatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação custom de um {@link SelectParser} para regras especificas do projeto SAT,
 * visando colunas de auditoria.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifySatQueryParser
public class SatSelectParser extends SelectParser {

    @Override
    @Autowired
    @QualifySatQueryParser
    public void setConditionsParser(QueryStructureParser<ConditionsStructure> conditionsParser) {
        super.setConditionsParser(conditionsParser);
    }

    @Override
    public ResultQuery parse(SelectStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        return super.parse(structure, indentationLvl, paramId);
    }
}
