package br.gov.to.sefaz.persistence.query.parser;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Interface responsável por desenvolver o componente QualifyQueryParser.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/07/2016 18:10:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier("defaultQueryParser")
public @interface QualifyQueryParser {
}
