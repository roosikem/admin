package com.serosoft.admin.controller;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	@RequestMapping(value={ "/" })
	public String adminLoginProccessing(){
			return "redirect:/adminLogin";
	}
	
	@RequestMapping(value={ "/adminLogin" })
	public String adminLogin(){
		if(getPrincipal()!=null && getPrincipal().equals("admin")){
			return "redirect:/user/home";
		}	
		return "login";
	}
	@RequestMapping(value={ "/Access_Denied" })
	public String accessDenied(){
		logger.info("access denied illegal access");
		return "access_denied";
	}
	
	private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
	
}
