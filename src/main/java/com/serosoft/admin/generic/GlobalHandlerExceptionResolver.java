package com.serosoft.admin.generic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver{
	private static final Logger logger = Logger.getLogger(GlobalHandlerExceptionResolver.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("exception", ex);
		mv.setViewName("global_error");
		logger.error(ex);
		return mv;
	}

}
