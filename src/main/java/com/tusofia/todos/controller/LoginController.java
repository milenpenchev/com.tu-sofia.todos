package com.tusofia.todos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tusofia.todos.exception.NotFoundException;
import com.tusofia.todos.model.User;
import com.tusofia.todos.service.LoginService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	private final LoginService loginService;
	
	@GetMapping("/login")
    public String loginPage() {
        return "login";
    }
	
	@PostMapping("/login")
	public String loginUser(@RequestParam String username,
			@RequestParam String password,
			HttpSession session) {
		try {
			User loggedUser = loginService.loginUser(username, password);
			session.setAttribute("userId", loggedUser.getId());
		} catch (NotFoundException nfe) {
			log.warn("incorrect login with username {}, password {}", username, password);
			return "redirect:/auth/login?error";
		}
		
		return "redirect:/todos";
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
}
