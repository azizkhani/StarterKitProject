package org.baharan.common.controller.security;
 
import java.security.Principal;

import org.apache.log4j.Logger;
import org.baharan.service.security.BaharUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class LoginController {
	static final Logger logger = Logger.getLogger(LoginController.class); 
 
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal ) {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security Custom Form example");
		System.out.println("------------------------"+name);
		logger.info("Sample info message------------------------");
		return "hello";
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model,Principal principal) {
		return "login";
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
	}
}