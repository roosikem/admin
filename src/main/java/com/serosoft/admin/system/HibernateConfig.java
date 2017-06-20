package com.serosoft.admin.system;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class HibernateConfig {

	private static final Logger logger = Logger.getLogger(HibernateConfig.class);
	@Value("${jdbc.driverClassName}")     private String driverClassName;
    @Value("${jdbc.url}")                 private String url;
    @Value("${jdbc.username}")            private String username;
    @Value("${jdbc.password}")            private String password;
    @Value("${hibernate.dialect}")        private String hibernateDialect;
    @Value("${hibernate.show_sql}")       private String hibernateShowSql;
    @Value("${hibernate.hbm2ddl.auto}")   private String hibernateHbm2ddlAuto;
    @Value("${hibernate.current_session_context_class}") private String hibernate_current_session_context_class;
    
    @Bean()    
    public DataSource getDataSource()
    {
        DriverManagerDataSource ds = new DriverManagerDataSource();        
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password); 
        logger.info("Datasource set");
        return ds;
    }
    @Bean
    public Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
        properties.put("hibernate.current_session_context_class", hibernate_current_session_context_class);
        logger.info("Hibernate Properties set");
        return properties;
    }
    
    @Bean
    public LocalSessionFactoryBean getSessionFactory()
    {
    	LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
    	localSessionFactoryBean.setDataSource(getDataSource());
    	localSessionFactoryBean.setHibernateProperties(getHibernateProperties());  
    	localSessionFactoryBean.setPackagesToScan(new String[]{"com.serosoft.admin"});
    	logger.info("LocalSessionFactoryBean created");
        return localSessionFactoryBean;
    }
    
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager htm = new HibernateTransactionManager();
        htm.setSessionFactory(sessionFactory);
        logger.info("HibernateTransactionManager Initialized");
        return htm;
    }
}
