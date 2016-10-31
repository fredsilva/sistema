package br.gov.to.sefaz.persistence.satquery.parser.delete;

import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.delete.DeleteParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.delete.DeleteStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import br.gov.to.sefaz.persistence.satquery.parser.QualifySatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação custom de um {@link DeleteParser} para regras especificas do projeto SAT,
 * visando colunas de auditoria.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifySatQueryParser
public class SatDeleteParser extends DeleteParser {

    @Override
    @Autowired
    @QualifySatQueryParser
    public void setConditionsParser(QueryStructureParser<ConditionsStructure> conditionsParser) {
        super.setConditionsParser(conditionsParser);
    }

    @Override
    public ResultQuery parse(DeleteStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        return super.parse(structure, indentationLvl, paramId);
    }
}
