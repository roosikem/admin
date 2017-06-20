package com.serosoft.admin.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	    @Autowired
	    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
	       
	    }
	     
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	  
	      http.csrf().disable()
	      .authorizeRequests()
	        .antMatchers("/resources/**").permitAll()
	        .antMatchers( "/adminLogin","/login").permitAll() 
	        .antMatchers("/user/**").access("hasRole('ADMIN')")
	        .anyRequest().authenticated()
	        .and().formLogin()
	        .loginPage("/adminLogin").
                loginProcessingUrl("/login").
                usernameParameter("username").
                passwordParameter("password").
                defaultSuccessUrl("/user/home").	
		     and().logout().   
		           logoutUrl("/logout"). 
		           deleteCookies("remove")
		           .invalidateHttpSession(true) 
		           .logoutSuccessUrl("/")
	         .and()
	              .exceptionHandling()
	              .accessDeniedPage("/Access_Denied");
	         
	    }
}
