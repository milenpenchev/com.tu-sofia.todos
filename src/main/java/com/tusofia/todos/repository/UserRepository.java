package com.tusofia.todos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.tusofia.todos.model.User;

import jakarta.annotation.PostConstruct;

@Repository
public class UserRepository {
	private List<User> users = new ArrayList<>();
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("post constructing");
		
		users.add(new User(UUID.randomUUID().toString(),
				"todo", "todo"));
		users.add(new User(UUID.randomUUID().toString(),
				"user1", "user1"));
		users.add(new User(UUID.randomUUID().toString(),
				"user2", "user2"));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public Optional<User> findByUsernameAndPassword(String username, String password) {
		return users.stream()
				.filter(user -> user.getUsername().equals(username)
						&& user.getPassword().equals(password))
				.findFirst();
	}
}
