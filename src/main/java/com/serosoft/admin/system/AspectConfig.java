package com.serosoft.admin.system;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class AspectConfig {

	private static final Logger logger = Logger.getLogger(AspectConfig.class);
	
	@Pointcut("execution(@org.springframework.transaction.annotation.Transactional * *(..))")
	public void transactionalMethod() {}
	
	@Pointcut("execution(* com.serosoft.admin.system.*.*(..))")
	public void systemService() { }
	 
	@Pointcut("execution(* com.serosoft.admin.dao.*.*(..))")
	public void daolayer() { }
	
	@Around("transactionalMethod()")
    public Object profile(ProceedingJoinPoint call) throws Throwable {
        Object returnValue=null;
        StopWatch clock = new StopWatch(getClass().getName());
        try {
            clock.start(call.toShortString());
            returnValue = call.proceed();
            
        }catch(Exception e){ 
        	logger.error(e);
        }
        
        finally {
            clock.stop();
            logger.info(clock.prettyPrint());
        }
        return returnValue;
    }
	@Before("systemService()")
	public void log(){
		logger.info("Applcation Service methode invoked");
	}
	
	@Around("systemService()")
    public Object systemProfile(ProceedingJoinPoint call) throws Throwable {
        Object returnValue=null;
        StopWatch clock = new StopWatch(getClass().getName());
        try {
            clock.start(call.toShortString());
            returnValue = call.proceed();
            
        }catch(Exception e){ 
        	logger.error(e);
        }
        
        finally {
            clock.stop();
            logger.info(clock.prettyPrint());
        }
        return returnValue;
    }
	
	
	@Before("daolayer()")
	public void daoLog(){
		logger.info("dao layer method invoked");
	}
	
	@Around("daolayer()")
    public Object daoProfile(ProceedingJoinPoint call) throws Throwable {
        Object returnValue=null;
        StopWatch clock = new StopWatch(getClass().getName());
        try {
            clock.start(call.toShortString());
            returnValue = call.proceed();
            
        }catch(Exception e){ 
        	logger.error(e);
        }
        
        finally {
            clock.stop();
            logger.info(clock.prettyPrint());
        }
        return returnValue;
    }
}
