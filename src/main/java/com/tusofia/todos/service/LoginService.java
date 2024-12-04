package com.tusofia.todos.service;

import org.springframework.stereotype.Service;

import com.tusofia.todos.exception.NotFoundException;
import com.tusofia.todos.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final UserService userService;
	
	public User loginUser(String username, String password) {
		User loggedUser = userService.findUser(username, password)
				.orElseThrow(() -> new NotFoundException("User cound not be found"));
		return loggedUser;
	}
}
