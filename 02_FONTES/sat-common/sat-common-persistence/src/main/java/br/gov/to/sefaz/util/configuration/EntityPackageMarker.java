package br.gov.to.sefaz.util.configuration;

import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * Interface para marcação de pacotes de entidades de persistência, para que um pacode de entidades seja visivel
 * para o {@link javax.persistence.EntityManager} e por consequencia possa ser utilizado pelos
 * {@link org.springframework.stereotype.Repository}, obrigatoriamente devem possuir uma classe implementando esta
 * inderface em sua raiz.
 *
 * @see PersistenceSpringConfiguration#entityManagerFactory(DataSource, Properties, List)
 *
 * @author gabriel.dias
 */
public interface EntityPackageMarker {
}
