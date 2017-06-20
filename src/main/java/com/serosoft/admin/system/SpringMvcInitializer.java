package com.serosoft.admin.system;

import javax.servlet.ServletContext;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WebConfig.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	 @Override
	    protected void registerDispatcherServlet(ServletContext servletContext) {
	        super.registerDispatcherServlet(servletContext);
	         System.setProperty("rootPath", servletContext.getRealPath("/"));

	    }
	 
}
