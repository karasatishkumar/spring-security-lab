package com.techiekernel.web;

import org.springframework.stereotype.Service;

@Service
public class TechService {
	public User findUserByUsername(String userName) {
		User user = new User();
		user.setUserName("satish");
		user.setPassword("satish");
		user.setEnabled(true);
		return user;
	}
}