package com.hygogg.hero.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hygogg.hero.models.User;
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
	public String register(@RequestParam Map<String, String> body, RedirectAttributes flash, HttpSession session) {
		HashMap<String, Object> response = this.userValidator.validate(body);
		
		if ((boolean) response.get("valid")) {
			User user = this.userService.createUser(body);
			session.setAttribute("user", user);
			return "redirect:/main";
		} else {
			flash.addFlashAttribute("errors", response);
			return "redirect:/";
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> body, HttpSession session, RedirectAttributes flash) {
		HashMap<String, Object> response = this.userValidator.authenticate(body);
		if ((boolean) response.get("valid")) {
			User user = (User) response.get("user");
			session.setAttribute("user", user);
			return "redirect:/main";
		} else {
			flash.addFlashAttribute("errors", response);
			return "redirect:/";
		}
	}
	
}
