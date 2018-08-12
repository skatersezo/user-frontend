package com.iespinosa.userFront.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iespinosa.userFront.domain.PrimaryAccount;
import com.iespinosa.userFront.domain.SavingsAccount;
import com.iespinosa.userFront.domain.User;
import com.iespinosa.userFront.domain.security.UserRole;
import com.iespinosa.userFront.service.UserService;
import com.iespinosa.userFront.service.UserServiceImpl.RoleServiceImpl;

// Here we register the HomeController as a Bean
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	// We request the path mapping and we return the template name recognized by spring
	// Spring will know that the extension will be *.html
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		User user = new User();
		
		model.addAttribute("user", user);
		
		return "signup";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String signupPost(@ModelAttribute("user") User user, Model model) {
		
		if(userService.checkIfUserExists(user.getUsername(), user.getEmail())) {
			
			if(userService.checkIfEmailExists(user.getEmail())) {
				model.addAttribute("emailExists", true);
			}
			
			if(userService.checkIfUsernameExists(user.getUsername())) {
				model.addAttribute("usernameExists", true);
			}
			
			return "signup";
		} else {
			
			Set<UserRole> userRoles = new HashSet<>();
			userRoles.add(new UserRole(user, roleService.findByName("ROLE_USER")));
			
			userService.createUser(user, userRoles); 
			
			return "redirect:/";
		}
		
		
	}
	
	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
		
		User user = userService.findByUsername(principal.getName());
		PrimaryAccount primaryAccount = user.getPrimaryAccount();
		SavingsAccount savingsAccount = user.getSavingsAccount();
		
		model.addAttribute("primaryAccount", primaryAccount);
		model.addAttribute("savingsAccount", savingsAccount);
		
		return "userFront";
		
	}
	
	
	 

}
 