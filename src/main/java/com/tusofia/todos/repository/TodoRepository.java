package com.tusofia.todos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tusofia.todos.model.Todo;

@Repository
public class TodoRepository {
	private List<Todo> todos = new ArrayList<>();
	
	public List<Todo> findAll() {
		return todos;
	}
	
	public Optional<Todo> findById(String id) {
		return todos.stream()
				.filter(todo -> todo.getId().equals(id))
				.findFirst();
	}
	
	public List<Todo> findByUserId(String userId) {
		return todos.stream()
				.filter(todo -> todo.getUserId().equals(userId))
				.collect(Collectors.toList());
	}
	
	public void addTodo(Todo todo) {
        todos.add(todo);
    }
	
	public void deleteTodo(String id) {
		todos.removeIf(todo -> todo.getId().equals(id));
	}
}