package com.serosoft.admin.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.serosoft.admin.model.User;
import com.serosoft.admin.service.UserService;
import com.serosoft.admin.util.EncryptUtil;

@Controller
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private EncryptUtil util;
	
	@RequestMapping(value="/user/home")
	public String home(Model model ,ModelMap modelMap,HttpServletRequest request)throws Exception{
		model.addAttribute("userlist",userService.userList() );
		model.addAttribute("page", "Manage User");
		
		return "index";
	}
	
	@RequestMapping(value="/user/addUser")
	public String addUser(Model model,ModelMap modelMap){
		if(!model.containsAttribute("user")) {
			model.addAttribute("user", new User());
			model.addAttribute("page", "Add User");
			return "adduser";
		}
		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "user",
				modelMap.get("errors"));
		model.addAttribute("page", "Add User");
		return "adduser";
	}
	
	
	@RequestMapping(value="/user/save", method=RequestMethod.POST)
	public String saveUser( @ModelAttribute("user")@Valid User user,BindingResult result,
			RedirectAttributes redirectAttrs , Model model, HttpServletRequest request) throws Exception {
		
		if(user.getId()!=null)
		 {
			User userDb =userService.getById(user.getId());
			if(userDb==null){
				 redirectAttrs.addFlashAttribute("msg", "User Not Available ");
				return "redirect:/user/home";
			}
			if(result.hasErrors()) {
				model.addAttribute("userlist",userService.userList() );
				model.addAttribute("user", user);
				redirectAttrs.addFlashAttribute("errors", result);
				 redirectAttrs.addFlashAttribute("user", user);
				 logger.debug("Error when adding user  "+result.toString());
				 return "redirect:/user/editUser/?id="+EncryptUtil.encrypt(Long.toString(user.getId()));
	          }
			
			logger.debug("user from db "+userDb.getEmail());
			Map<String,User> users=userService.getUserMap();
			users.remove(userDb.getEmail());
			
			if(users.containsKey(user.getEmail())){
				model.addAttribute("userlist",userService.userList() );
				model.addAttribute("user", user);
				redirectAttrs.addFlashAttribute("msgs", "Email already exist");
				redirectAttrs.addFlashAttribute("errors", result);
				redirectAttrs.addFlashAttribute("user", user);
				 logger.debug("Error when adding user  "+result.toString());
				 return "redirect:/user/editUser/?id="+EncryptUtil.encrypt(Long.toString(user.getId()));
			}
			 userService.save(user);
			 model.asMap().remove("user");
			 redirectAttrs.getFlashAttributes().remove("user");
			 redirectAttrs.addFlashAttribute("msg", "succesfully update user");
		 }
		
	     else{
			 if(result.hasErrors()) {
				 redirectAttrs.addFlashAttribute("errors", result);
				 redirectAttrs.addFlashAttribute("user", user);
				  logger.debug("Error when adding user  "+result.toString());
	             return "redirect:/user/addUser";
	          }
			 userService.save(user);
			 model.asMap().remove("user");
			 redirectAttrs.getFlashAttributes().remove("user");
			 redirectAttrs.addFlashAttribute("msg", "Succesfully add user");
		 }
		 
		 
		return "redirect:/user/home";
	}
	@RequestMapping(value="/user/editUser", method=RequestMethod.GET)
	public String editUser(RedirectAttributes redirectAttrs , Model model,ModelMap modelMap, HttpServletRequest request) throws Exception {
		if(request.getParameter("id")==null || request.getParameter("id")==""){
			return "redirect:/user/home";
		}
		
		String decrptedId=request.getParameter("id");
		String id=EncryptUtil.decrypt(decrptedId);
		Long userId=Long.parseLong(id);
		
		
		if(!model.containsAttribute("user")) {
			User user=userService.getById(userId);
			logger.debug("user from db "+user.getEmail());
			model.addAttribute("userlist",userService.userList() );
			model.addAttribute("user", user);
			model.addAttribute("page", "Update User");
			return "index";
		}
		model.addAttribute("userlist",userService.userList() );
		model.addAttribute("page", "Update User");
		modelMap.put(BindingResult.MODEL_KEY_PREFIX + "user",
				modelMap.get("errors"));
		return "index";
	}
	
	
	@RequestMapping(value="/user/deleteUser", method=RequestMethod.GET)
	public @ResponseBody String deleteUser(RedirectAttributes redirectAttrs , Model model, HttpServletRequest request) throws Exception {
		String id=request.getParameter("id");
		if(id==null || id==""){
			return "redirect:/user/home";
		}
		Long userId=Long.parseLong(id);
		System.out.println(request.getRemoteHost());
		userService.deleteUser(userId);
        logger.debug("delete user by  "+request.getUserPrincipal().getName()+" "+new Date());
		logger.debug("user deleted, whose id is "+id);
		String msg="Successfully deleted user";
		return msg;
	}
	@RequestMapping(value="/user/checkUser", method=RequestMethod.GET,consumes={"application/text"},produces={"application/text"})
	public @ResponseBody String checkAvailability(RedirectAttributes redirectAttrs , Model model, HttpServletRequest request) throws Exception {
		String email=request.getParameter("email");
		User user=userService.getByEmail(email);
		StringBuffer msg=new StringBuffer();
		if(user!=null){
			 msg.append("NOT");
		}
		else{
			msg.append("YES");
		}
		logger.debug("check email exist or not "+email);
		return msg.toString();
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleIOException(Exception ex) {
	    ModelAndView model = new ModelAndView("global_error");
	    logger.error(ex);
	    model.addObject("exception", ex);
	    return model;
	}
	
}
