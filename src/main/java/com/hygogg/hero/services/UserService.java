package com.hygogg.hero.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.hygogg.hero.models.User;
import com.hygogg.hero.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void createUser(Map<String, String> body) {
		User user = new User(body);
		this.userRepository.save(user);
	}
	
}
