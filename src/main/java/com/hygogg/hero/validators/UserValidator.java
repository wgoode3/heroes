package com.hygogg.hero.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.hygogg.hero.models.User;
import com.hygogg.hero.services.UserService;
import org.mindrot.jbcrypt.BCrypt;

@Component
public class UserValidator {
	
	public static Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+");
	private UserService userService;
	
	public UserValidator(UserService userService) {
		this.userService = userService;
	}

	public HashMap<String, Object> validate (Map<String, String> body) {
		
		HashMap<String, Object> response = new HashMap<String, Object>();
		
		if (body.get("username").length() < 3) {
			response.put("username", "Username must be 3 characters or longer");
		}
		
		if (body.get("email").length() < 1) {
			response.put("email", "Email is required");
		} else if (!EMAIL_REGEX.matcher(body.get("email")).matches()) {
			response.put("email", "Invalid email");
		} else {
			User u = this.userService.findByEmail(body.get("email"));
			if (u != null) {
				response.put("email", "Email is already in use");
			}
		}
		
		if (body.get("password").length() < 8) {
			response.put("password", "Password must be 8 characters or longer");
		}
		
		if (!body.get("password").equals(body.get("confirm"))) {
			response.put("confirm", "Confirm Password must match Password");
		}
		
		if (response.isEmpty()) {
			response.put("valid", true);
		} else {
			response.put("valid", false);
		}
		
		return response;
		
	}
	
	public HashMap<String, Object> authenticate(Map<String, String> body) {
		
		HashMap<String, Object> response = new HashMap<String, Object>();
		User u = null;
		
		if (body.get("email").length() < 1) {
			response.put("email", "Email is required");
		} else if (!EMAIL_REGEX.matcher(body.get("email")).matches()) {
			response.put("email", "Invalid email");
		} else {
			u = this.userService.findByEmail(body.get("email"));
			if (u == null) {
				response.put("email", "Unknown email");
			}
		}
		
		if (body.get("password").length() < 8) {
			response.put("password", "Password must be 8 characters or longer");
		} else if (u != null && !BCrypt.checkpw(body.get("password"), u.getPassword())) {
			response.put("password", "Incorrect password");
		}
		
		if (response.isEmpty()) {
			response.put("valid", true);
			response.put("user", u);
		} else {
			response.put("valid", false);
		}
		
		return response;
		
	}
}
