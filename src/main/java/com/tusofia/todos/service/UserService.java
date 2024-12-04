package com.tusofia.todos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tusofia.todos.model.User;
import com.tusofia.todos.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public Optional<User> findUser(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
}