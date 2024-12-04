package com.tusofia.todos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tusofia.todos.exception.NotFoundException;
import com.tusofia.todos.model.Todo;
import com.tusofia.todos.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<Todo> getTodosByUser(String userId) {
        return todoRepository.findByUserId(userId);
    }

    public void addTodo(Todo todo) {
    	todoRepository.addTodo(todo);
    }

    public void deleteTodo(String id) {
    	todoRepository.deleteTodo(id);
    }

    public void updateTodo(Todo updatedTodo) {
    	Todo todo = todoRepository.findById(updatedTodo.getId())
    			.orElseThrow(() -> new NotFoundException("Todo with this id was not found"));
        todo.setText(updatedTodo.getText());
        todo.setPriority(updatedTodo.getPriority());
        todo.setDueDate(updatedTodo.getDueDate());
    }
    
    public Todo findById(String id) {
    	Todo todo = todoRepository.findById(id)
    			.orElseThrow(() -> new NotFoundException("Todo with this id was not found"));
    	return todo;
    }
}