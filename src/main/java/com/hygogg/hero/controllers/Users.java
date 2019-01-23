package com.hygogg.hero.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hygogg.hero.services.UserService;
import com.hygogg.hero.validators.UserValidator;

@Controller
public class Users {
	
	private UserValidator userValidator;
	private UserService userService;
	
	public Users (UserValidator userValidator, UserService userService) {
		this.userValidator = userValidator;
		this.userService = userService;
	}

	@RequestMapping("/")
	public String logreg() {
		return "logreg";
	}
	
	@PostMapping("/register")
	public String register(@RequestParam Map<String, String> body, RedirectAttributes flash) {
		HashMap<String, Object> response = this.userValidator.validate(body);
		
		if ((boolean) response.get("valid")) {
			this.userService.createUser(body);
		} else {
			flash.addFlashAttribute("errors", response);
		}
		
		return "redirect:/";
	}
	
}
