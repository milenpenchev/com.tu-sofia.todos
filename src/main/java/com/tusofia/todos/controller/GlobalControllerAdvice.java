package com.tusofia.todos.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("loggedIn")
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("userId") != null;
    }
}