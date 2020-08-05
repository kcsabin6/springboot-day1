package com.rab3tech.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rab3tech.controller.dto.ProfileDTO;
import com.rab3tech.service.ProfileService;

@Controller
public class AuthController {

	@Autowired
	private ProfileService profileService;

	/* spring container says do not use -->> HttpServletRequest */
	
	@GetMapping({"/","/auth"})
	public String goLogin() {
		return "login";
	}
	
	
	

	@PostMapping("/auth") // data is sent to the database so use @PostMapping
	// annotation
	public String authUser(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, Model model) {
		ProfileDTO profileDTO = profileService.authUser(username, password);
		if (profileDTO != null) {
			session.setAttribute("userData", profileDTO);
			return "dashboard";

		} else {// user is not there
			model.addAttribute("hmmmm", "Sorry, username and password are not correct");
			return "login";
		}
	}

	@GetMapping("/logout") // data is retrieved from the database so use
	// @GetMapping annotation
	public String logout(HttpSession session, Model model) {
		if (session != null)
			session.invalidate();

		model.addAttribute("hmmmm", "You have logged out successfully!!");
		return "login";
	}

}
