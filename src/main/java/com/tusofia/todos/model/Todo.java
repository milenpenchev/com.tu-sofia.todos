package com.tusofia.todos.model;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	private String id;
    private String userId;
    
    @NotBlank(message = "Task description is required")
    private String text;
    
    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be in the future or today")
    private LocalDate dueDate;
    
    @NotBlank(message = "Priority is required")
    private String priority;
}