package br.gov.to.sefaz.util.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Configurações do spring para componentes de persistencia.
 *
 * @author gabriel.dias
 */
@Configuration
public class PersistenceSpringConfiguration {

    /**
     * Retorna o dataSource com as informações necessárias para a conexão com o banco de dados.
     */
    @Bean
    public DataSource dataSource() {
        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        return dsLookup.getDataSource("java:/OracleDS");
    }

    /**
     * Parâmetros de configuração do hibernate.
     */
    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");

        return properties;
    }

    /**
     * Cria a factory do {@link javax.persistence.EntityManager} que será utilizado pela camada de persistência do
     * sistema.
     *
     * @param dataSource configurações para conexão.
     * @param hibernateProperties parâmetros de configuração do hibernate.
     * @param entityPackageMarkers lista contendo todas as implementações de entityPackageMarkers do projeto,
     *                             definindo assim os pacotes de entidades que serão
     *                             escaneados pelo {@link javax.persistence.EntityManager}.
     */
    @Bean
    public EntityManagerFactory entityManagerFactory(
            DataSource dataSource,
            Properties hibernateProperties,
            List<EntityPackageMarker> entityPackageMarkers) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(entityPackageMarkers.stream()
                .map(epm -> epm.getClass().getPackage().getName())
                .toArray(String[]::new));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(hibernateProperties);
        em.setPersistenceUnitName("sefazTOPU");
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();

        return em.getObject();
    }

    /**
     * Retorna o gerenciador de transação que será utilizado pelo {@link javax.persistence.EntityManager}.
     *
     * @param entityManagerFactory factory do {@link javax.persistence.EntityManager} que utilizará o gerenciador
     *         de transação criado.
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}