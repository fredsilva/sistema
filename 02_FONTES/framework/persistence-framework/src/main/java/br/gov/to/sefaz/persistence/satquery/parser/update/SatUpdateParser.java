package br.gov.to.sefaz.persistence.satquery.parser.update;

import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.parser.update.UpdateParser;
import br.gov.to.sefaz.persistence.query.structure.update.UpdateStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import br.gov.to.sefaz.persistence.satquery.parser.QualifySatQueryParser;
import br.gov.to.sefaz.persistence.satquery.parser.handler.RegistroExcluidoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Implementação custom de um {@link UpdateParser} para regras especificas do projeto SAT,
 * visando colunas de auditoria.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifySatQueryParser
public class SatUpdateParser extends UpdateParser {

    @Override
    @Autowired
    @QualifySatQueryParser
    public void setConditionsParser(QueryStructureParser<ConditionsStructure> conditionsParser) {
        super.setConditionsParser(conditionsParser);
    }

    @Override
    public ResultQuery parse(UpdateStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        RegistroExcluidoHandler
                .createConditions(structure.getWhere(), structure.getFrom(), structure.getQueryLanguage())
                .ifPresent(structure::setWhere);
        return super.parse(structure, indentationLvl, paramId);
    }
}
