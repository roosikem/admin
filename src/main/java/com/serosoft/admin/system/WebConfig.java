package com.serosoft.admin.system;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.serosoft.admin.util.EncryptUtil;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages="com.serosoft.admin")
@Import({HibernateConfig.class,SecurityConfiguration.class})
public class WebConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = Logger.getLogger(WebConfig.class);
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver(){
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		
		resolver.setSuffix(".jsp");
		resolver.setCache(false);
		logger.info("Created InternalResourceViewResolver");
		return resolver;
	}
	
	@Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer()
    {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("hibernate.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        logger.info("PropertyPlaceholderConfigurer initialized ");
        return ppc;
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
	@Bean
	public MessageSource messageSource()
	{
	    ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
	    bean.setBasename("messages");
	    
	    return bean;
	}
	@Bean
	EncryptUtil getEncryptUtil() throws Exception{
		SecretKey key = KeyGenerator.getInstance("DES").generateKey();
		return new EncryptUtil(key);
	}

	
}
