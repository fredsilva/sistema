package br.gov.to.sefaz.persistence.configuration;

import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * Interface para marcação de pacotes de entidades de persistência, para que um pacode de entidades seja visivel
 * para o {@link javax.persistence.EntityManager} e por consequencia possa ser utilizado pelos
 * {@link org.springframework.stereotype.Repository}, obrigatoriamente devem possuir uma classe implementando esta
 * inderface em sua raiz.
 *
 * @see PersistenceConfiguration#entityManagerFactory(DataSource, Properties, List)
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
public interface EntityPackageMarker {
}
