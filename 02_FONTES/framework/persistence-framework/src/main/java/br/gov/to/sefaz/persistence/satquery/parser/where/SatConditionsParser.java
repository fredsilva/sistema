package br.gov.to.sefaz.persistence.satquery.parser.where;

import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.where.ConditionsParser;
import br.gov.to.sefaz.persistence.query.structure.select.SelectStructure;
import br.gov.to.sefaz.persistence.satquery.parser.QualifySatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementação custom de um {@link ConditionsParser} para regras especificas do projeto SAT,
 * visando colunas de auditoria.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 16:35:00
 */
@Component
@QualifySatQueryParser
public class SatConditionsParser extends ConditionsParser {

    @Override
    @Autowired
    @QualifySatQueryParser
    public void setSelectParser(QueryStructureParser<SelectStructure> selectParser) {
        super.setSelectParser(selectParser);
    }
}
