package br.gov.to.sefaz.persistence.satquery.parser;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Interface responsável por desenvolver o componente QualifySatQueryParser.
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 18/07/2016 18:10:00
 */
@Retention(RetentionPolicy.RUNTIME)
@Qualifier("satQueryParser")
public @interface QualifySatQueryParser {
}
