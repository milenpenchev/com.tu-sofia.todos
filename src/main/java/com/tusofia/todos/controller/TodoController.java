package com.tusofia.todos.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tusofia.todos.model.Todo;
import com.tusofia.todos.service.TodoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public String listTodos(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
//        if (userId == null) {
//            return "redirect:/auth/login";
//        }

        model.addAttribute("todos", todoService.getTodosByUser(userId));
        return "todo-list";
    }

    @GetMapping("/new")
    public String newTodoPage(Model model) {
    	model.addAttribute("todo", new Todo());
        return "todo-new";
    }

    @PostMapping("/new")
    public String addTodo(HttpSession session, @Valid @ModelAttribute("todo") Todo todo, 
    		BindingResult result) {
        // Check for validation errors
        if (result.hasErrors()) {
            return "todo-new"; // Return the form with errors
        }

        // Fetch userId from the session
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            todo.setId(UUID.randomUUID().toString()); // Generate a unique ID
            todo.setUserId(userId); // Associate TODO with the logged-in user
            todoService.addTodo(todo);
        }

        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(HttpSession session, @PathVariable String id) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            todoService.deleteTodo(id);
        }
        return "redirect:/todos";
    }
    
    @GetMapping("/edit/{id}")
    public String editTodoPage(@PathVariable String id, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
//        if (userId == null) {
//            return "redirect:/auth/login";
//        }

        Todo todo = todoService.findById(id);
        
        model.addAttribute("todo", todo);
        return "todo-edit";
    }
    
    @PostMapping("/edit")
    public String editTodo(HttpSession session, @Valid @ModelAttribute("todo") Todo todo, 
    		BindingResult result) {
        if (result.hasErrors()) {
            return "todo-edit"; // Return the form with validation errors
        }

        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            todo.setUserId(userId); 
            todoService.updateTodo(todo);
        }

        return "redirect:/todos";
    }


}
