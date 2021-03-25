package edu.config;

import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "edu")
public class ApplicationContextConfig {

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());

        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource());
        em.setPackagesToScan("edu.business.entities");
        em.setPersistenceUnitName("RestSushiShopPU");
        em.setJpaVendorAdapter(jpaVendorAdapter());
        em.setJpaProperties(getJpaVendorAdapterExtraProperties());


        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:rest-sushi-shop-testdb;DB_CLOSE_DELAY=-1"); // ;DB_CLOSE_DELAY=-1 é para manter a istância do banco viva enquanto a JVM rodar
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();

        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");

        return adapter;
    }

    private Properties getJpaVendorAdapterExtraProperties() {
        Properties jpaVendorAdapterExtraProperties = new Properties();

        // cria o schema ao inicializar a sessão do Hibernate e drop ao finalizá-la
        jpaVendorAdapterExtraProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        return jpaVendorAdapterExtraProperties;
    }

    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        //
        // Faz a injeção dos beans EntityManagerFactory e EntityManager nos campos anotados
        // com @PersistenceUnit e @PersistenceContext respectivamente.
        //
        return new PersistenceAnnotationBeanPostProcessor();
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        //
        // Traduz as exceções na camada de acesso a dados para uma exceção única do Spring referente a essa camada.
        //
        return new PersistenceExceptionTranslationPostProcessor();
    }


}
