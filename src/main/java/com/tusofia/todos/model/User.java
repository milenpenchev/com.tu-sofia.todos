package com.tusofia.todos.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Holds user details
 */
@Data
@AllArgsConstructor
public class User {
	private String id;
	private String username;
	private String password;
}