package br.gov.to.sefaz.persistence.satquery.parser.delete;

import br.gov.to.sefaz.persistence.entity.AbstractEntity;
import br.gov.to.sefaz.persistence.query.parser.QueryStructureParser;
import br.gov.to.sefaz.persistence.query.parser.delete.DeleteParser;
import br.gov.to.sefaz.persistence.query.parser.domain.ParamIdGenerator;
import br.gov.to.sefaz.persistence.query.parser.domain.ResultQuery;
import br.gov.to.sefaz.persistence.query.structure.delete.DeleteStructure;
import br.gov.to.sefaz.persistence.query.structure.domain.Alias;
import br.gov.to.sefaz.persistence.query.structure.domain.Value;
import br.gov.to.sefaz.persistence.query.structure.update.UpdateStructure;
import br.gov.to.sefaz.persistence.query.structure.where.ConditionsStructure;
import br.gov.to.sefaz.persistence.satquery.parser.QualifySatQueryParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static br.gov.to.sefaz.persistence.satquery.parser.handler.RegistroExcluidoHandler.getDataExclusaoColumn;
import static br.gov.to.sefaz.persistence.satquery.parser.handler.RegistroExcluidoHandler.getRegistroExcluidoColumn;
import static br.gov.to.sefaz.persistence.satquery.parser.handler.RegistroExcluidoHandler.getUsuarioExclusaoColumn;

/**
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 04/07/2016 11:29:00
 */
@Component
@QualifySatQueryParser
public class SatDeleteParser extends DeleteParser {

    private QueryStructureParser<UpdateStructure> updateParser;

    @Override
    @Autowired
    @QualifySatQueryParser
    public void setConditionsParser(QueryStructureParser<ConditionsStructure> conditionsParser) {
        super.setConditionsParser(conditionsParser);
    }

    @Autowired
    @QualifySatQueryParser
    public void setUpdateParser(QueryStructureParser<UpdateStructure> updateParser) {
        this.updateParser = updateParser;
    }

    @Override
    public ResultQuery parse(DeleteStructure structure, int indentationLvl, ParamIdGenerator paramId) {
        String queryLanguage = structure.getQueryLanguage();

        Map<String, Value> sets = new HashMap<>();
        String registroExcluidoColumn = prependFrom(getRegistroExcluidoColumn(queryLanguage), structure.getFrom());
        String dataExclusaoColumn = prependFrom(getDataExclusaoColumn(queryLanguage), structure.getFrom());
        String usuarioExclusaoColumn = prependFrom(getUsuarioExclusaoColumn(queryLanguage), structure.getFrom());

        String user = SecurityContextHolder.getContext().getAuthentication().getName();

        sets.put(registroExcluidoColumn, Value.ofParam(AbstractEntity.SIM));
        sets.put(dataExclusaoColumn, Value.ofParam(LocalDateTime.now()));
        sets.put(usuarioExclusaoColumn, Value.ofParam(user));

        UpdateStructure updateStructure = new UpdateStructure(queryLanguage, structure.getFrom(), sets);
        structure.getWhere().ifPresent(updateStructure::setWhere);

        return updateParser.parse(updateStructure, indentationLvl, paramId);
    }

    private String prependFrom(String column, Alias<String> from) {
        if (from.hasAlias()) {
            return from.getAlias() + "." + column;
        }
        return column;
    }
}
