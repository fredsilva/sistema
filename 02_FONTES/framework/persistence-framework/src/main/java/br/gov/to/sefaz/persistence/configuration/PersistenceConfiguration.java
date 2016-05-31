package br.gov.to.sefaz.persistence.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Configurações do spring para componentes de persistencia.
 *
 * @author <a href="mailto:gabriel.dias@ntconsult.com.br">gabriel.dias</a>
 * @since 14/04/2016 18:30:00
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfiguration {

    /**
     * Retorna o {@link DataSource} com as informações necessárias para a conexão com o banco de dados.
     *
     * @return {@link DataSource} de acesso ao banco de dados
     */
    @Bean
    public DataSource dataSource() {
        JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        return dsLookup.getDataSource("java:/OracleDS");
    }

    /**
     * Método que retorna as propriedades ou parâmetros de configuração do hibernate.
     *
     * @return Propriedades de configuração do hibernate
     */
    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        return properties;
    }

    /**
     * Cria a factory do {@link javax.persistence.EntityManager} que será utilizado pela camada de persistência do
     * sistema.
     *
     * @param dataSource           configurações para conexão.
     * @param hibernateProperties  parâmetros de configuração do hibernate.
     * @param entityPackageMarkers lista contendo todas as implementações de entityPackageMarkers do projeto, definindo
     *                             assim os pacotes de entidades que serão escaneados pelo
     *                             {@link javax.persistence.EntityManager}.
     * @return Fábrica do {@link javax.persistence.EntityManager} ou {@link javax.persistence.EntityManagerFactory}
     */
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties,
            List<EntityPackageMarker> entityPackageMarkers) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        List<String> collect = entityPackageMarkers.stream()
                .map(epm -> epm.getClass().getPackage().getName()).collect(Collectors.toList());
        collect.add(Jsr310JpaConverters.class.getPackage().getName());
        em.setPackagesToScan(collect.stream().toArray(String[]::new));
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
     * @param entityManagerFactory factory do {@link javax.persistence.EntityManager} que utilizará o gerenciador de
     *                             transação criado.
     * @return Gerenciador de transações {@link javax.persistence.EntityManager}
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
