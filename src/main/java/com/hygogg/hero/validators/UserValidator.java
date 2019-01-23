package com.hygogg.hero.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {
	
	public static Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]+");

	public HashMap<String, Object> validate (Map<String, String> body) {
		
		HashMap<String, Object> response = new HashMap<String, Object>();
		
		if (body.get("username").length() < 3) {
			response.put("username", "Username must be 3 characters or longer");
		}
		
		if (body.get("email").length() < 1) {
			response.put("email", "Email is required");
		} else if (!EMAIL_REGEX.matcher(body.get("email")).matches()) {
			response.put("email", "Invalid email");
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
}
