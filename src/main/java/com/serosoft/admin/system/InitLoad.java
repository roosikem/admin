package com.serosoft.admin.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.ibatis.common.jdbc.ScriptRunner;

@Repository("initLoad")
public class InitLoad  implements ApplicationListener<ContextRefreshedEvent>{
	private static final Logger logger = Logger.getLogger(InitLoad.class);
	@Value("${jdbc.initload}")     private String initload;
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
      boolean b =Boolean.parseBoolean(initload);
        if(b){
		 	String aSQLScriptFilePath="/manish.sql";
		    ApplicationContext ac=arg0.getApplicationContext();
 	
		 	 Reader reader;
				try {
					String a= ac.getClassLoader().getResource(aSQLScriptFilePath).toString();
				 	String n=a.substring(6,a.length());
					File f=new File(n);
					Connection connection = dataSource.getConnection();
					ScriptRunner sr = new ScriptRunner(connection, false, false);
					reader = new BufferedReader(
					                   new FileReader(f));
					sr.runScript(reader);
					reader.close();
					logger.info("script load in database succesfully");
				} catch (Exception e) {
					logger.error(e);
				}
	}else{
		logger.info("database script file not load set property of jdbc.initLoad in hibernate.properties to load default data");
	}
	}
}
